package cn.brotherchun.bcshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	//跳转首页
	@RequestMapping("/")
	public String showIndex() throws Exception{
		return "index";
	}
	
	//跳转页面
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page) throws Exception{
		return page;
	}
}
