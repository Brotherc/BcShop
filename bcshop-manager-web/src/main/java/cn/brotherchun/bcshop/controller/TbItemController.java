package cn.brotherchun.bcshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.pojo.TbItem;
import cn.brotherchun.bcshop.service.TbItemService;

@Controller
public class TbItemController {

	@Autowired
	private TbItemService tbItemService;
	
	//测试通过id获取商品
	@RequestMapping("/tbitem/{id}")
	private @ResponseBody TbItem testFindTbItemById(@PathVariable Long id) throws Exception{
		return tbItemService.testGetTbItemById(id);
	}
	
	//根据分页信息获取商品信息
	@RequestMapping("/tbitem/list")
	public @ResponseBody EasyUIDataGridResult findTbItemList(int page,int rows) throws Exception{
		return tbItemService.getTbItemList(page, rows);
	}
	
	//添加商品
	@RequestMapping("/tbitem/save")
	public @ResponseBody BcResult addTbItem(TbItem tbItem,String desc) throws Exception{
		return tbItemService.addTbItem(tbItem, desc);
	}
	
	// 加载商品描述
	@RequestMapping("/tbitem/querydesc/{id}")
	public @ResponseBody BcResult getTbItemDesc(@PathVariable Long id) throws Exception{
		return tbItemService.getTbitemDescByTbItemId(id);
	}
	
	//加载商品规格
	@RequestMapping("/tbitem/paramquery/{id}")
	public @ResponseBody BcResult getTbItem(@PathVariable Long id) throws Exception{
		return tbItemService.getTbItemById(id);
	}
	
	//修改商品信息
	@RequestMapping("/tbitem/update")
	public @ResponseBody BcResult updateTbItem(TbItem tbItem,@RequestParam String desc) throws Exception{
		System.out.println(desc);
		return tbItemService.updateTbItem(tbItem, desc);
	}
	
	//下架商品
	@RequestMapping("/tbitem/instock")
	public @ResponseBody BcResult instockTbItem(String ids) throws Exception{
		if(ids!=null){
			String[] split = ids.split(",");
			for(String id:split){
				tbItemService.instockTbItem(Long.valueOf(id));
			}
		}
		return new BcResult().ok();
	}
	
	//上架商品
	@RequestMapping("/tbitem/reshelf")
	public @ResponseBody BcResult reshelfTbItem(String ids) throws Exception{
		if(ids!=null){
			String[] split = ids.split(",");
			for(String id:split){
				tbItemService.reshelfTbItem(Long.valueOf(id));
			}
		}
		return new BcResult().ok();
	}
	
	//删除商品
	@RequestMapping("/tbitem/delete")
	public @ResponseBody BcResult deleteTbItem(String ids) throws Exception{
		if(ids!=null){
			String[] split = ids.split(",");
			for(String id:split){
				tbItemService.deleteTbItem(Long.valueOf(id));
			}
		}
		return new BcResult().ok();
	}
}
