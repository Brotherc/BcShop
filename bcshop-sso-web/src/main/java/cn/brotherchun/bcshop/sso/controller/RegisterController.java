package cn.brotherchun.bcshop.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.pojo.TbUser;
import cn.brotherchun.bcshop.sso.service.RegisterService;

@Controller
public class RegisterController {

	@Autowired
	private RegisterService registerService;
	
	//跳转注册页面
	@RequestMapping("/page/register")
	public String showRegister() throws Exception{
		return "register";
	}
	
	//用户注册数据校验
	@RequestMapping("/user/check/{param}/{type}")
	public @ResponseBody BcResult checkData(@PathVariable String param,@PathVariable int type) throws Exception{
		return registerService.checkData(param, type);
	}
	
	//用户注册
	@RequestMapping("/user/register")
	public @ResponseBody BcResult register(TbUser tbUser) throws Exception{
		return registerService.register(tbUser);
	}
	
}
