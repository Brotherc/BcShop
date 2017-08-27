package cn.brotherchun.bcshop.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.brotherchun.bcshop.common.pojo.EasyUITreeNode;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.content.service.TbContentCategoryService;

@Controller
public class TbContentCategoryController {
	@Autowired 
	private TbContentCategoryService tbContentCategoryService;
	
	//查询内容分类列表
	@RequestMapping("/content/category/list")
	public @ResponseBody List<EasyUITreeNode> findTbContentCategoryList(@RequestParam(name="id",defaultValue="0")Long parentId) throws Exception{
		return tbContentCategoryService.findTbContentCategoryList(parentId);
	}
	
	//添加内容分类
	@RequestMapping("/content/category/create")
	public @ResponseBody BcResult addContentCategory(Long parentId,String name) throws Exception{
		return tbContentCategoryService.addContentCategory(parentId, name);
	}
	
	//修改内容分类
	@RequestMapping("/content/category/update")
	public @ResponseBody BcResult updateContentCategory(Long id,String name) throws Exception{
		return tbContentCategoryService.updateContentCategory(id, name);
	}
	
	//删除内容分类
	@RequestMapping("/content/category/delete/")
	public @ResponseBody BcResult deleteContentCategory(Long id) throws Exception{
		return tbContentCategoryService.deleteContentCategory(id);
	}
}
