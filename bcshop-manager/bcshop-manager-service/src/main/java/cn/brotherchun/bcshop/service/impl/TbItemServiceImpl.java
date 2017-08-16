package cn.brotherchun.bcshop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.common.utils.IDUtils;
import cn.brotherchun.bcshop.mapper.TbItemDescMapper;
import cn.brotherchun.bcshop.mapper.TbItemMapper;
import cn.brotherchun.bcshop.pojo.TbItem;
import cn.brotherchun.bcshop.pojo.TbItemDesc;
import cn.brotherchun.bcshop.pojo.TbItemExample;
import cn.brotherchun.bcshop.service.TbItemService;

@Service
public class TbItemServiceImpl implements TbItemService{

	@Autowired
	private TbItemMapper tbItemMapper;
	
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	
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

	@Override
	public BcResult addTbItem(TbItem tbItem, String desc) throws Exception {
		// 1、生成商品id
		long id = IDUtils.genItemId();
		// 2、补全TbItem对象的属性
		tbItem.setId(id);
		//商品状态，1-正常，2-下架，3-删除
		tbItem.setStatus((byte)1);
		tbItem.setCreated(new Date());
		tbItem.setUpdated(new Date());
		// 3、向商品表插入数据
		tbItemMapper.insert(tbItem);
		// 4、创建一个TbItemDesc对象
		TbItemDesc tbItemDesc=new TbItemDesc();
		// 5、补全TbItemDesc的属性
		tbItemDesc.setItemId(id);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(new Date());
		tbItemDesc.setUpdated(new Date());
		// 6、向商品描述表插入数据
		tbItemDescMapper.insert(tbItemDesc);
		return BcResult.ok();
	}

}
