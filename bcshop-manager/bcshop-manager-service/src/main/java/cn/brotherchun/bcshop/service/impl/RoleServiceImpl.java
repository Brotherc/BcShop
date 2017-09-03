package cn.brotherchun.bcshop.service.impl;

import java.util.List;





import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.common.utils.PinYin4jUtils;
import cn.brotherchun.bcshop.mapper.TbRoleMapper;
import cn.brotherchun.bcshop.pojo.TbRole;
import cn.brotherchun.bcshop.pojo.TbRoleExample;
import cn.brotherchun.bcshop.pojo.TbRoleExample.Criteria;
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

	@Override
	public EasyUIDataGridResult roleList(int page, int rows) throws Exception {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		List<TbRole> tbRoleList = tbRoleMapper.selectByExample(new TbRoleExample());
		//取分页信息
		PageInfo<TbRole> pageInfo=new PageInfo<>(tbRoleList);
		long total = pageInfo.getTotal();
		//创建返回结果对象
		EasyUIDataGridResult easyUIDataGridResult=new EasyUIDataGridResult(total, tbRoleList);
		return easyUIDataGridResult;
	}

	@Override
	public BcResult addRole(TbRole tbRole) throws Exception {
		//非空判断
		String rolename = tbRole.getRolename();
		if(!StringUtils.isNoneBlank(rolename))
			return BcResult.build(-1, "请输入角色名");
		String roledesc = tbRole.getRoledesc();
		if(!StringUtils.isNoneBlank(roledesc))
			return BcResult.build(-1, "请输入角色描述");
		//不允许添加用户名相同的角色
		TbRoleExample tbRoleExample=new TbRoleExample();
		Criteria criteria = tbRoleExample.createCriteria();
		criteria.andRolenameEqualTo(rolename);
		List<TbRole> tbRoleList = tbRoleMapper.selectByExample(tbRoleExample);
		if(tbRoleList!=null&&tbRoleList.size()>0)
			return BcResult.build(-1, "该角色已存在");
		//补全简码
		String[] headString = PinYin4jUtils.getHeadByString(rolename);
		String shortcode = StringUtils.join(headString,"");
		tbRole.setShortcode(shortcode);
		//补全角色编码
		String rolecode = PinYin4jUtils.hanziToPinyin(rolename, "");
		tbRole.setRolecode(rolecode);
		tbRoleMapper.insert(tbRole);
		return BcResult.ok();
	}

	@Override
	public BcResult updateRole(TbRole tbRole) throws Exception {
		//非空判断
		String rolename = tbRole.getRolename();
		if(!StringUtils.isNoneBlank(rolename))
			return BcResult.build(-1, "请输入角色名");
		String roledesc = tbRole.getRoledesc();
		if(!StringUtils.isNoneBlank(roledesc))
			return BcResult.build(-1, "请输入角色描述");
		//修改的角色必须存在
		TbRole role = tbRoleMapper.selectByPrimaryKey(tbRole.getId());
		if(role==null)
			return BcResult.build(-1, "角色不存在");
		//如果修改了角色名，不允许重复
		if(!rolename.trim().equals(role.getRolename())){
			TbRoleExample tbRoleExample=new TbRoleExample();
			Criteria criteria = tbRoleExample.createCriteria();
			criteria.andRolenameEqualTo(rolename.trim());
			List<TbRole> tbRoleList = tbRoleMapper.selectByExample(tbRoleExample);
			if(tbRoleList!=null&&tbRoleList.size()>0)
				return BcResult.build(-1, "角色名已存在");
			role.setRolename(rolename.trim());
		}
		role.setRoledesc(roledesc);
		tbRoleMapper.updateByPrimaryKey(role);
		return BcResult.ok();
	}

	@Override
	public BcResult deleteRole(Long id) throws Exception {
		//删除的角色必须存在
		TbRole role = tbRoleMapper.selectByPrimaryKey(id);
		if(role==null)
			return BcResult.build(-1, "角色不存在");
		tbRoleMapper.deleteByPrimaryKey(id);
		return BcResult.ok();
	}
}
