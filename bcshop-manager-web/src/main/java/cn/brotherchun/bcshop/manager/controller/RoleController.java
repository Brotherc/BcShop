package cn.brotherchun.bcshop.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.pojo.TbRole;
import cn.brotherchun.bcshop.service.RoleService;

@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/role/list")
	public @ResponseBody EasyUIDataGridResult roleList(int page,int rows) throws Exception{
		return roleService.roleList(page, rows);
	}
	
	@RequestMapping("/role/save")
	public @ResponseBody BcResult addRole(TbRole tbRole) throws Exception{
		return roleService.addRole(tbRole);
	}
	
	@RequestMapping("/role/edit")
	public @ResponseBody BcResult updateRole(TbRole tbRole) throws Exception{
		return roleService.updateRole(tbRole);
	}
	
	@RequestMapping("/role/delete")
	public @ResponseBody BcResult deleteRole(String ids) throws Exception{
		if(ids.trim()!=null){
			String[] split = ids.split(",");
			for(String id:split){
				roleService.deleteRole(new Long(id));
			}
		}
		return BcResult.ok();
	}
}
