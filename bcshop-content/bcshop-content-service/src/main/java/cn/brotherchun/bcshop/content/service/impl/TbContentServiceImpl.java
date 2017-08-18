package cn.brotherchun.bcshop.content.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.content.service.TbContentService;
import cn.brotherchun.bcshop.mapper.TbContentMapper;
import cn.brotherchun.bcshop.pojo.TbContent;
import cn.brotherchun.bcshop.pojo.TbContentExample;
import cn.brotherchun.bcshop.pojo.TbContentExample.Criteria;

@Service
public class TbContentServiceImpl implements TbContentService{

	@Autowired
	private TbContentMapper tbContentMapper;
	
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
		return BcResult.ok();
	}

	@Override
	public BcResult deleteTbContent(Long tbContentId) throws Exception {
		tbContentMapper.deleteByPrimaryKey(tbContentId);
		return BcResult.ok();
	}

	@Override
	public List<TbContent> findTbContentListByTbContentCategoryId(
			Long tbContentCategoryId) throws Exception {
		TbContentExample tbContentExample=new TbContentExample();
		Criteria criteria = tbContentExample.createCriteria();
		criteria.andCategoryIdEqualTo(tbContentCategoryId);
		//执行查询
		List<TbContent> tbContentList = tbContentMapper.selectByExample(tbContentExample);
		return tbContentList;
	}

}
