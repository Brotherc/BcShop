package cn.brotherchun.bcshop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.mapper.TbItemParamMapper;
import cn.brotherchun.bcshop.pojo.TbItemParam;
import cn.brotherchun.bcshop.pojo.TbItemParamExample;
import cn.brotherchun.bcshop.pojo.TbItemParamExample.Criteria;
import cn.brotherchun.bcshop.service.TbItemParamService;

@Service
public class TbItemParamServiceImpl implements TbItemParamService{

	@Autowired
	private TbItemParamMapper tbItemParamMapper;

	@Override
	public EasyUIDataGridResult findTbItemParamList(int page,int rows) throws Exception {
		TbItemParamExample tbItemParamExample=new TbItemParamExample();
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		List<TbItemParam> tbItemParamList = tbItemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);
		//取分页信息
		PageInfo<TbItemParam> pageInfo=new PageInfo<>(tbItemParamList);
		long total = pageInfo.getTotal();
		//创建返回结果对象
		EasyUIDataGridResult easyUIDataGridResult=new EasyUIDataGridResult(total, tbItemParamList);
		return easyUIDataGridResult;
	}

	@Override
	public BcResult getTbItemParamByItemCatId(Long itemCatId) throws Exception {
		TbItemParamExample tbItemParamExample=new TbItemParamExample();
		Criteria criteria = tbItemParamExample.createCriteria();
		criteria.andItemCatIdEqualTo(itemCatId);
		List<TbItemParam> tbItemParamList = tbItemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);
		if(tbItemParamList!=null&&tbItemParamList.size()>0)
			return BcResult.ok(tbItemParamList.get(0));
		return BcResult.ok();
	}

	@Override
	public BcResult addTbItemParam(Long itemCatId, String paramData)
			throws Exception {
		TbItemParam tbItemParam=new TbItemParam();
		tbItemParam.setItemCatId(itemCatId);
		tbItemParam.setParamData(paramData);
		tbItemParam.setCreated(new Date());
		tbItemParam.setUpdated(new Date());
		tbItemParamMapper.insert(tbItemParam);
		return BcResult.ok();
	}

	@Override
	public BcResult deleteTbItemParam(Long tbItemParamId) throws Exception {
		tbItemParamMapper.deleteByPrimaryKey(tbItemParamId);
		return BcResult.ok();
	}
	
	
}
