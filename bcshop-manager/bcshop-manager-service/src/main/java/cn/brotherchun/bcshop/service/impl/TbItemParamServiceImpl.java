package cn.brotherchun.bcshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.mapper.TbItemParamMapper;
import cn.brotherchun.bcshop.pojo.TbItemParam;
import cn.brotherchun.bcshop.pojo.TbItemParamExample;
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
		for(TbItemParam tbItemParam:tbItemParamList){
			System.out.println(tbItemParam.getParamData());
		}
		//取分页信息
		PageInfo<TbItemParam> pageInfo=new PageInfo<>(tbItemParamList);
		long total = pageInfo.getTotal();
		//创建返回结果对象
		EasyUIDataGridResult easyUIDataGridResult=new EasyUIDataGridResult(total, tbItemParamList);
		return easyUIDataGridResult;
	}
	
	
}
