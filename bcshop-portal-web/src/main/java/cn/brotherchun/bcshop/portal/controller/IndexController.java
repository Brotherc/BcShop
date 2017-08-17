package cn.brotherchun.bcshop.portal.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页展示Controller
 * <p>Title: IndexController</p>
 * <p>Description: </p>
 * <p>Company: www.brotherchun.cn</p> 
 * @version 1.0
 */
@Controller
public class IndexController {

	@RequestMapping("/index")
	public String showIndex(Model model) {
		return "index";
	}
}
