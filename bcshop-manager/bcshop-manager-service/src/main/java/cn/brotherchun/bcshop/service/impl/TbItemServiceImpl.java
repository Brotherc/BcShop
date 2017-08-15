package cn.brotherchun.bcshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.mapper.TbItemMapper;
import cn.brotherchun.bcshop.pojo.TbItem;
import cn.brotherchun.bcshop.pojo.TbItemExample;
import cn.brotherchun.bcshop.service.TbItemService;

@Service
public class TbItemServiceImpl implements TbItemService{

	@Autowired
	private TbItemMapper tbItemMapper;
	
	@Override
	public TbItem testGetTbItemById(Long id) throws Exception {
		return tbItemMapper.selectByPrimaryKey(id);
	}

	@Override
	public EasyUIDataGridResult getTbItemList(int page, int rows)
			throws Exception {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		List<TbItem> tbItemList = tbItemMapper.selectByExample(new TbItemExample());
		//取分页信息
		PageInfo<TbItem> pageInfo=new PageInfo<>(tbItemList);
		long total = pageInfo.getTotal();
		//创建返回结果对象
		EasyUIDataGridResult easyUIDataGridResult=new EasyUIDataGridResult(total, tbItemList);
		return easyUIDataGridResult;
	}

}
