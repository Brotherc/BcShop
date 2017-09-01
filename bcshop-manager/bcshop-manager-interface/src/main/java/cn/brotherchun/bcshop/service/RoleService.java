package cn.brotherchun.bcshop.service;

import java.util.List;

import cn.brotherchun.bcshop.pojo.TbRole;

public interface RoleService {
	/**
	 * 获取角色列表
	 * @return
	 * @throws Exception
	 */
	public List<TbRole> roleList() throws Exception;
	
	public List<TbRole> roleListByQ(String q) throws Exception;
}
