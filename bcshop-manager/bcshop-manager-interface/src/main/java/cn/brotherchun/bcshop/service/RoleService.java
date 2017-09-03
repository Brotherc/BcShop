package cn.brotherchun.bcshop.service;

import java.util.List;

import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.pojo.TbRole;

public interface RoleService {
	/**
	 * 获取角色列表
	 * @return
	 * @throws Exception
	 */
	public List<TbRole> roleList() throws Exception;
	
	public List<TbRole> roleListByQ(String q) throws Exception;
	
	public EasyUIDataGridResult roleList(int page,int rows) throws Exception;
	
	public BcResult addRole(TbRole tbRole) throws Exception;
	
	public BcResult updateRole(TbRole tbRole) throws Exception;
	
	public BcResult deleteRole(Long id) throws Exception;
}
