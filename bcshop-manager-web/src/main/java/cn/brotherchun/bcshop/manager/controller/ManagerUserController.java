package cn.brotherchun.bcshop.manager.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.pojo.TbManageruser;
import cn.brotherchun.bcshop.service.ManagerService;

@Controller
public class ManagerUserController {
	
	@Autowired
	private ManagerService managerService;
	
	@RequestMapping("/manager/login")
	public @ResponseBody BcResult login(HttpSession session,String username,String password,String validatecode) throws Exception{
		String validatecodeSession=(String) session.getAttribute("validatecode");
		System.out.println(validatecodeSession);
		BcResult bcResult = managerService.login(validatecode, validatecodeSession, username, password);
		TbManageruser user=(TbManageruser) bcResult.getData();
		session.setAttribute("user", user);
		return bcResult;
	}
}
