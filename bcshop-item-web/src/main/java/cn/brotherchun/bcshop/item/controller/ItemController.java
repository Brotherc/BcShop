package cn.brotherchun.bcshop.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.brotherchun.bcshop.item.pojo.Item;
import cn.brotherchun.bcshop.pojo.TbItem;
import cn.brotherchun.bcshop.pojo.TbItemDesc;
import cn.brotherchun.bcshop.service.TbItemService;

/**
 * 商品详情页面展示Controller
 * <p>Title: ItemController</p>
 * <p>Description: </p>
 * <p>Company: www.brotherchun.cn</p> 
 * @version 1.0
 */
@Controller
public class ItemController {

	@Autowired 
	private TbItemService tbItemService;
	
	//获取商品详情信息
	@RequestMapping("/item/{itemId}")
	public String getItem(@PathVariable Long itemId,Model model) throws Exception{
		TbItem tbItem = tbItemService.getItemById(itemId);
		Item item=new Item(tbItem);
		model.addAttribute("item", item);
		TbItemDesc tbItemDesc = tbItemService.getItemDescById(itemId);
		model.addAttribute("itemDesc", tbItemDesc);
		return "item";
	}
}
