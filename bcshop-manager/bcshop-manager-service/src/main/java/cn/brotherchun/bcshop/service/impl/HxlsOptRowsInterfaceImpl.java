package cn.brotherchun.bcshop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.brotherchun.bcshop.common.utils.HxlsOptRowsInterface;
import cn.brotherchun.bcshop.common.utils.IDUtils;
import cn.brotherchun.bcshop.mapper.TbItemDescMapper;
import cn.brotherchun.bcshop.mapper.TbItemMapper;
import cn.brotherchun.bcshop.pojo.TbItem;
import cn.brotherchun.bcshop.pojo.TbItemDesc;

@Service
public class HxlsOptRowsInterfaceImpl implements HxlsOptRowsInterface {
	
	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired 
	private TbItemDescMapper tbItemDescMapper;

	@Override
	public String optRows(int sheetIndex, int curRow, List<String> rowlist)
			throws Exception {
		try {
			//得到导入的数据
			//rowlist数据 是一行数据，按照模版解析
			String title = rowlist.get(0);//标题
			String sellPoint = rowlist.get(1);//卖点
			String price = rowlist.get(2);//价格
			String num = rowlist.get(3);//库存
			String barcode = rowlist.get(4);//条形码
			String image = rowlist.get(5);//图片地址
			String cid = rowlist.get(6);//类目
			String status = rowlist.get(7);//状态
			String desc = rowlist.get(8);//描述
			
			//进行校验
			//校验价格合法性
			//校验状态的合法性
			
			//添加唯一校验
			//.....
			
			TbItem tbItem = new TbItem();
			long id = IDUtils.genItemId();
			tbItem.setId(id);
			tbItem.setTitle(title);
			tbItem.setSellPoint(sellPoint);
			tbItem.setPrice(new Long(price));
			tbItem.setNum(new Integer(num));
			tbItem.setBarcode(barcode);
			tbItem.setImage(image);
			tbItem.setCid(new Long(cid));
			tbItem.setStatus(new Byte(status));
			tbItem.setCreated(new Date());
			tbItem.setUpdated(new Date());
			
			//添加商品描述
			TbItemDesc tbItemDesc=new TbItemDesc();
			tbItemDesc.setItemId(id);
			tbItemDesc.setItemDesc(desc);
			tbItemDesc.setCreated(new Date());
			tbItemDesc.setUpdated(new Date());
			
			//校验调用mapper
			tbItemMapper.insert(tbItem);
			tbItemDescMapper.insert(tbItemDesc);
		} catch (Exception e) {
			e.printStackTrace();
			return "导入失败！";
		}
		
		
		return "success";
	}
}
