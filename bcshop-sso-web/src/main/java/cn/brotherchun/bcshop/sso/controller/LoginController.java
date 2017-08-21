package cn.brotherchun.bcshop.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.common.utils.CookieUtils;
import cn.brotherchun.bcshop.pojo.TbUser;
import cn.brotherchun.bcshop.sso.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Value("${TOKEN_KEY}")
	private String TOKEN_KEY;
	
	//跳转登录页面
	@RequestMapping("/page/login")
	public String showLogin() throws Exception{
		return "login";
	}
	
	//用户登录
	@RequestMapping("/user/login")
	public @ResponseBody BcResult login(TbUser tbUser,HttpServletRequest request,HttpServletResponse response) throws Exception{
		BcResult result = loginService.login(tbUser);
		//判断是否登录成功
		if(result.getStatus()==200){
			String token=result.getData().toString();
			//如果登录成功需要把token写入cookie
			CookieUtils.setCookie(request, response, TOKEN_KEY, token);
		}
		return result;
	}
}
