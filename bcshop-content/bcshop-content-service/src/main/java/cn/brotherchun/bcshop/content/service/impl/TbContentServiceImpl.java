package cn.brotherchun.bcshop.content.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.brotherchun.bcshop.common.jedis.JedisClient;
import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.common.utils.JsonUtils;
import cn.brotherchun.bcshop.content.service.TbContentService;
import cn.brotherchun.bcshop.mapper.TbContentMapper;
import cn.brotherchun.bcshop.pojo.TbContent;
import cn.brotherchun.bcshop.pojo.TbContentExample;
import cn.brotherchun.bcshop.pojo.TbContentExample.Criteria;

@Service
public class TbContentServiceImpl implements TbContentService{

	@Autowired
	private TbContentMapper tbContentMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${CONTENT_LIST")
	private String CONTENT_LIST;
	
	@Override
	public EasyUIDataGridResult findTbContentListByTbContentCategoryId(
			Long tbContentCategoryId, int page, int rows) throws Exception {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//设置查询信息
		TbContentExample tbContentExample=new TbContentExample();
		Criteria criteria = tbContentExample.createCriteria();
		criteria.andCategoryIdEqualTo(tbContentCategoryId);
		//执行查询
		List<TbContent> tbContentList = tbContentMapper.selectByExample(tbContentExample);
		//取分页信息
		PageInfo<TbContent> pageInfo=new PageInfo<>(tbContentList);
		long total = pageInfo.getTotal();
		//创建返回结果对象
		EasyUIDataGridResult easyUIDataGridResult=new EasyUIDataGridResult(total, tbContentList);
		return easyUIDataGridResult;
	}

	@Override
	public BcResult addTbContent(TbContent tbContent) throws Exception {
		tbContent.setCreated(new Date());
		tbContent.setUpdated(new Date());
		tbContentMapper.insert(tbContent);
		//缓存同步
		jedisClient.hdel(CONTENT_LIST, tbContent.getCategoryId().toString());
		return BcResult.ok(tbContent);
	}

	@Override
	public BcResult updateTbContent(TbContent tbContent) throws Exception {
		Long id = tbContent.getId();
		TbContent tbContentDB = tbContentMapper.selectByPrimaryKey(id);
		tbContentDB.setTitle(tbContent.getTitle());
		tbContentDB.setSubTitle(tbContent.getSubTitle());
		tbContentDB.setTitleDesc(tbContent.getTitleDesc());
		tbContentDB.setUrl(tbContent.getUrl());
		tbContentDB.setPic(tbContent.getPic());
		tbContentDB.setPic2(tbContent.getPic2());
		tbContentDB.setContent(tbContent.getContent());
		tbContentDB.setUpdated(new Date());
		tbContentMapper.updateByPrimaryKeyWithBLOBs(tbContentDB);
		//缓存同步
		jedisClient.hdel(CONTENT_LIST, tbContent.getCategoryId().toString());
		return BcResult.ok();
	}

	@Override
	public BcResult deleteTbContent(Long tbContentId) throws Exception {
		TbContent tbContent = tbContentMapper.selectByPrimaryKey(tbContentId);
		//缓存同步
		jedisClient.hdel(CONTENT_LIST, tbContent.getCategoryId().toString());
		tbContentMapper.deleteByPrimaryKey(tbContentId);
		return BcResult.ok();
	}

	@Override
	public List<TbContent> findTbContentListByTbContentCategoryId(
			Long tbContentCategoryId) throws Exception {
		//查询缓存
		try {
			//如果缓存中有直接相应结果
			String json = jedisClient.hget(CONTENT_LIST, tbContentCategoryId+"");
			System.out.println(json);
			if(StringUtils.isNotBlank(json)){
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//如果没有查询数据库
		TbContentExample tbContentExample=new TbContentExample();
		Criteria criteria = tbContentExample.createCriteria();
		criteria.andCategoryIdEqualTo(tbContentCategoryId);
		//执行查询
		List<TbContent> tbContentList = tbContentMapper.selectByExample(tbContentExample);
		//把结果添加到缓存
		try {
			jedisClient.hset(CONTENT_LIST, tbContentCategoryId+"", JsonUtils.objectToJson(tbContentList));
			System.out.println("添加缓存");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tbContentList;
	}

}
