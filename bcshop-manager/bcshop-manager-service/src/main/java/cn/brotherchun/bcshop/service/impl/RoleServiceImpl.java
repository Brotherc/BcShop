package cn.brotherchun.bcshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.brotherchun.bcshop.mapper.TbRoleMapper;
import cn.brotherchun.bcshop.pojo.TbRole;
import cn.brotherchun.bcshop.pojo.TbRoleExample;
import cn.brotherchun.bcshop.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private TbRoleMapper tbRoleMapper;
	
	@Override
	public List<TbRole> roleList() throws Exception {
		List<TbRole> tbRoleList = tbRoleMapper.selectByExample(new TbRoleExample());
		return tbRoleList;
	}

	@Override
	public List<TbRole> roleListByQ(String q) throws Exception {
		TbRoleExample tbRoleExample=new TbRoleExample();
		tbRoleExample.or().andShortcodeLike("%"+q+"%");
		tbRoleExample.or().andRolecodeLike("%"+q+"%");
		List<TbRole> tbRoleList = tbRoleMapper.selectByExample(tbRoleExample);
		return tbRoleList;
	}

}
