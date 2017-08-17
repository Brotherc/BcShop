package cn.brotherchun.bcshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.brotherchun.bcshop.common.pojo.EasyUITreeNode;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.service.TbItemCatService;

@Controller
public class TbItemCatController {
	@Autowired
	private TbItemCatService tbItemCatService;
	
	//根据父菜单id获取子菜单列表
	@RequestMapping("/item/cat/list")
	public @ResponseBody List<EasyUITreeNode> findTbItemCatList(@RequestParam(name="id",defaultValue="0")Long parentId) throws Exception{
		return tbItemCatService.getTbItemCatList(parentId);
	}
	
	//根据商品分类id获取商品分类信息
	@RequestMapping("/item/cat/query/{itemcatid}")
	public @ResponseBody BcResult getTbItemCat(@PathVariable Long itemcatid) throws Exception{
		return tbItemCatService.getTbItemCatByTbItemCatid(itemcatid);
	}
}
