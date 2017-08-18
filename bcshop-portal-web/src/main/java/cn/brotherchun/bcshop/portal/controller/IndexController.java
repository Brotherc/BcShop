package cn.brotherchun.bcshop.portal.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.brotherchun.bcshop.content.service.TbContentService;
import cn.brotherchun.bcshop.pojo.TbContent;

/**
 * 首页展示Controller
 * <p>Title: IndexController</p>
 * <p>Description: </p>
 * <p>Company: www.brotherchun.cn</p> 
 * @version 1.0
 */
@Controller
public class IndexController {

	@Value("${CONTENT_LUNBO_ID}")
	private Long CONTENT_LUNBO_ID;
	
	@Autowired
	private TbContentService tbContentService;
	
	@RequestMapping("/index")
	public String showIndex(Model model) throws Exception{
		//查询内容列表
		List<TbContent> tbContentList = tbContentService.findTbContentListByTbContentCategoryId(CONTENT_LUNBO_ID);
		// 把结果传递给页面
		model.addAttribute("ad1List", tbContentList);
		return "index";
	}
}
