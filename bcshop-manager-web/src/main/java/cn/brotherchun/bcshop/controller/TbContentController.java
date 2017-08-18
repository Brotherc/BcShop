package cn.brotherchun.bcshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.content.service.TbContentService;
import cn.brotherchun.bcshop.pojo.TbContent;

@Controller
public class TbContentController {
	@Autowired
	private TbContentService tbContentService;
	
	//查询内容信息
	@RequestMapping("/content/query/list")
	public @ResponseBody EasyUIDataGridResult findTbContentList(Long categoryId,int page,int rows) throws Exception{
		return tbContentService.findTbContentListByTbContentCategoryId(categoryId, page, rows);
	}
	
	//添加内容信息
	@RequestMapping("/content/save")
	public @ResponseBody BcResult addTbContent(TbContent tbContent) throws Exception{
		return tbContentService.addTbContent(tbContent);
	}
	
	//修改内容信息
	@RequestMapping("/content/edit")
	public @ResponseBody BcResult updateTbContent(TbContent tbContent) throws Exception{
		return tbContentService.updateTbContent(tbContent);
	}
	
	//删除内容信息
	@RequestMapping("/content/delete")
	public @ResponseBody BcResult deleteTbContent(String ids) throws Exception{
		if(ids!=null){
			String[] split = ids.split(",");
			for(String id:split){
				tbContentService.deleteTbContent(Long.valueOf(id));
			}
		}
		return BcResult.ok();
	}
}
