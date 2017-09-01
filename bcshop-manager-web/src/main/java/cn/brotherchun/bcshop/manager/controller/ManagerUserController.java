package cn.brotherchun.bcshop.manager.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.pojo.TbDictinfo;
import cn.brotherchun.bcshop.pojo.TbManageruser;
import cn.brotherchun.bcshop.pojo.TbRole;
import cn.brotherchun.bcshop.service.DictInfoService;
import cn.brotherchun.bcshop.service.ManagerService;
import cn.brotherchun.bcshop.service.RoleService;

@Controller
public class ManagerUserController {
	
	@Autowired
	private ManagerService managerService;
	@Autowired
	private RoleService roleService;
	@Autowired 
	private DictInfoService dictInfoService;
	
	@RequestMapping("/manager/login")
	public @ResponseBody BcResult login(HttpSession session,String username,String password,String validatecode) throws Exception{
		String validatecodeSession=(String) session.getAttribute("validatecode");
		System.out.println(validatecodeSession);
		BcResult bcResult = managerService.login(validatecode, validatecodeSession, username, password);
		TbManageruser user=(TbManageruser) bcResult.getData();
		session.setAttribute("user", user);
		return bcResult;
	}
	@RequestMapping("/manager/logout")
	public String logout(HttpSession session) throws Exception{
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/manager/changePwd")
	public @ResponseBody BcResult changePwd(HttpSession session,String oldPwd,String newPwd,String newPwdTwo) throws Exception{
		TbManageruser user=(TbManageruser) session.getAttribute("user");
		return managerService.changePwd(user.getId(), newPwd, oldPwd, newPwdTwo);
	}
	
	@RequestMapping("/manageruser/list")
	public @ResponseBody EasyUIDataGridResult manageUserList(int page,int rows) throws Exception{
		return managerService.managerUserList(page, rows);
	}
	
	@RequestMapping("/manageruser/save")
	public @ResponseBody BcResult manageUserAdd(TbManageruser tbManageruser) throws Exception{
		return managerService.addManagerUser(tbManageruser);
	}
	
	@RequestMapping("/manageruser/edit")
	public @ResponseBody BcResult manageUserEdit(TbManageruser tbManageruser) throws Exception{
		return managerService.updateManagerUser(tbManageruser);
	}
	
	@RequestMapping("/manageruser/delete")
	public @ResponseBody BcResult manageUserDelete(String ids) throws Exception{
		if(ids.trim()!=null){
			String[] split = ids.split(",");
			for(String id:split){
				managerService.deleteManagerUser(new Long(id));
			}
		}
		return BcResult.ok();
	}
	
	@RequestMapping("/manageruser/rolelist")
	public @ResponseBody List<TbRole> manageUserRoleList(String q) throws Exception{
		if(StringUtils.isNoneBlank(q)){
			//根据q进行模糊查询
			return roleService.roleListByQ(q);
		}
		return roleService.roleList(); 
	}
	
	@RequestMapping("/manageruser/statuslist")
	public @ResponseBody List<TbDictinfo> manageUserStatusList() throws Exception{
		return dictInfoService.tbDictinfoListByTypeCode("001");
	}
}
