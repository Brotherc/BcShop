package cn.brotherchun.bcshop.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.service.TbItemParamService;

@Controller
public class TbItemParamController {

	@Autowired 
	private TbItemParamService tbItemParamService;
	
	//查询商品规格参数列表
	@RequestMapping("/item/param/list")
	public @ResponseBody EasyUIDataGridResult findTbItemParamList(int page,int rows) throws Exception{
		return tbItemParamService.findTbItemParamList(page, rows);
	}
	
	//查询商品规格参数
	@RequestMapping("/item/param/query/itemcatid/{itemCatId}")
	public @ResponseBody BcResult getTbItemParam(@PathVariable Long itemCatId) throws Exception{
		return tbItemParamService.getTbItemParamByItemCatId(itemCatId);
	}
	
	//添加商品规格参数
	@RequestMapping("/item/param/save/{itemCatId}")
	public @ResponseBody BcResult getTbItemParam(@PathVariable Long itemCatId,String paramData) throws Exception{
		return tbItemParamService.addTbItemParam(itemCatId, paramData);
	}
	
	//删除商品规格参数
	@RequestMapping("/item/param/delete")
	public @ResponseBody BcResult getTbItemParam(String ids) throws Exception{
		if(ids!=null){
			String[] split = ids.split(",");
			for(String id:split){
				tbItemParamService.deleteTbItemParam(Long.valueOf(id));
			}
		}
		return BcResult.ok();
	}
}
