package cn.brotherchun.bcshop.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.mapper.TbManageruserMapper;
import cn.brotherchun.bcshop.pojo.TbManageruser;
import cn.brotherchun.bcshop.pojo.TbManageruserExample;
import cn.brotherchun.bcshop.pojo.TbManageruserExample.Criteria;
import cn.brotherchun.bcshop.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService{

	@Autowired
	private TbManageruserMapper tbManageruserMapper;
	
	@Override
	public BcResult login(String validatecodePage, String validatecodeSession,
			String username, String pwd) throws Exception {
		//非空校验
		if(!StringUtils.isNotBlank(username))
			return BcResult.build(-1, "请输入用户名");
		if(!StringUtils.isNotBlank(pwd))
			return BcResult.build(-1, "请输入密码");
		if(!StringUtils.isNotBlank(validatecodePage))
			return BcResult.build(-1, "请输入验证码");
		if(!validatecodePage.trim().equals(validatecodeSession))
			return BcResult.build(-1, "验证码输入错误");
		//登录用户名必须存在
		TbManageruserExample tbManageruserExample=new TbManageruserExample();
		Criteria criteria = tbManageruserExample.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbManageruser> tbManageruserList = tbManageruserMapper.selectByExample(tbManageruserExample);
		if(tbManageruserList==null||tbManageruserList.size()<1)
			return BcResult.build(-1, "该用户不存在");
		//校验密码是否正确
		//先对密码加密，再校验
		pwd = DigestUtils.md5DigestAsHex(pwd.trim().getBytes());
		//取出刚查询用户的密码
		TbManageruser tbManageruser = tbManageruserList.get(0);
		String md5Pwd=tbManageruser.getPassword();
		if(!pwd.equals(md5Pwd))
			return BcResult.build(-1, "用户名或密码错误");
		//校验成功，将用户密码设置为null，返回用户信息
		tbManageruser.setPassword(null);
		return BcResult.build(200, "登录成功", tbManageruser);
	}

	@Override
	public BcResult changePwd(Long id, String newPwd, String oldPwd,String newPwdTwo)
			throws Exception {
		//非空校验
		if(!StringUtils.isNotBlank(oldPwd))
			return BcResult.build(-1, "请输入密码");
		if(!StringUtils.isNotBlank(newPwd))
			return BcResult.build(-1, "请输入新密码");
		if(!StringUtils.isNotBlank(newPwdTwo))
			return BcResult.build(-1, "请再次输入新密码");
		//二次新密码是否相同
		if(!newPwdTwo.trim().equals(newPwd.trim()))
			return BcResult.build(-1, "新密码输入不一致");
		//判断修改密码用户是否存在
		TbManageruser tbManageruser = tbManageruserMapper.selectByPrimaryKey(id);
		if(tbManageruser==null)
			return BcResult.build(-1, "用户不存在");
		//判断旧密码是否正确
		//先对旧密码加密
		oldPwd = DigestUtils.md5DigestAsHex(oldPwd.trim().getBytes());
		if(!oldPwd.equals(tbManageruser.getPassword()))
			return BcResult.build(-1, "密码错误");
		tbManageruser.setPassword(DigestUtils.md5DigestAsHex(newPwd.trim().getBytes()));
		tbManageruser.setUpdated(new Date());
		tbManageruserMapper.updateByPrimaryKey(tbManageruser);
		return BcResult.build(200, "密码修改成功，请重新登录");
	}

	@Override
	public EasyUIDataGridResult managerUserList(int page, int rows)
			throws Exception {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		List<TbManageruser> tbManageruserList = tbManageruserMapper.selectByExample(new TbManageruserExample());
		//取分页信息
		PageInfo<TbManageruser> pageInfo=new PageInfo<>(tbManageruserList);
		long total = pageInfo.getTotal();
		//创建返回结果对象
		EasyUIDataGridResult easyUIDataGridResult=new EasyUIDataGridResult(total, tbManageruserList);
		return easyUIDataGridResult;
	}

	@Override
	public BcResult addManagerUser(TbManageruser tbManageruser)
			throws Exception {
		//非空判断
		String username = tbManageruser.getUsername();
		if(!StringUtils.isNoneBlank(username))
			return BcResult.build(-1, "请输入用户名");
		String pwd = tbManageruser.getPassword();
		if(!StringUtils.isNoneBlank(pwd))
			return BcResult.build(-1, "请输入密码");
		Integer status = tbManageruser.getStatus();
		if(status==null)
			return BcResult.build(-1, "请选择正确的用户状态");
		Integer type = tbManageruser.getType();
		if(type==null)
			return BcResult.build(-1, "请选择正确的用户类型");
		//添加的用户名不允许重复
		TbManageruserExample tbManageruserExample=new TbManageruserExample();
		Criteria criteria = tbManageruserExample.createCriteria();
		criteria.andUsernameEqualTo(username.trim());
		List<TbManageruser> tbManageruserList = tbManageruserMapper.selectByExample(tbManageruserExample);
		if(tbManageruserList!=null&&tbManageruserList.size()>0)
			return BcResult.build(-1, "用户名已存在");
		//对用户密码加密
		String password = tbManageruser.getPassword();
		String md5=DigestUtils.md5DigestAsHex(password.trim().getBytes());
		tbManageruser.setPassword(md5);
		tbManageruser.setCreated(new Date());
		tbManageruser.setUpdated(new Date());
		tbManageruserMapper.insert(tbManageruser);
		return BcResult.ok();
	}

	@Override
	public BcResult updateManagerUser(TbManageruser tbManageruser)
			throws Exception {
		//非空判断
		String username = tbManageruser.getUsername();
		if(!StringUtils.isNoneBlank(username))
			return BcResult.build(-1, "请输入用户名");
		String pwd = tbManageruser.getPassword();
		if(!StringUtils.isNoneBlank(pwd))
			return BcResult.build(-1, "请输入密码");
		Integer status = tbManageruser.getStatus();
		if(status==null)
			return BcResult.build(-1, "请选择正确的用户状态");
		Integer type = tbManageruser.getType();
		if(type==null)
			return BcResult.build(-1, "请选择正确的用户类型");
		
		//查询用户信息
		TbManageruser manageruser = tbManageruserMapper.selectByPrimaryKey(tbManageruser.getId());
		//如果修改了用户名，修改的用户名不允许重复
		if(!username.trim().equals(manageruser.getUsername())){
			TbManageruserExample tbManageruserExample=new TbManageruserExample();
			Criteria criteria = tbManageruserExample.createCriteria();
			criteria.andUsernameEqualTo(username.trim());
			List<TbManageruser> tbManageruserList = tbManageruserMapper.selectByExample(tbManageruserExample);
			if(tbManageruserList!=null&&tbManageruserList.size()>0)
				return BcResult.build(-1, "用户名已存在");
			manageruser.setUsername(username.trim());
		}

		//如果修改了密码，则修改为加密后的密码
		if(!pwd.equals(manageruser.getPassword())){
			String md5Pwd=DigestUtils.md5DigestAsHex(pwd.trim().getBytes());
			manageruser.setPassword(md5Pwd);
		}
		manageruser.setNickname(tbManageruser.getNickname());
		manageruser.setPhone(tbManageruser.getPhone());
		manageruser.setEmail(tbManageruser.getEmail());
		manageruser.setStatus(status);
		manageruser.setType(type);
		manageruser.setUpdated(new Date());
		tbManageruserMapper.updateByPrimaryKey(manageruser);
		return BcResult.ok();
	}

	@Override
	public BcResult deleteManagerUser(Long id) throws Exception {
		//删除的用户必须存在
		TbManageruser tbManageruser = tbManageruserMapper.selectByPrimaryKey(id);
		if(tbManageruser==null)
			return BcResult.build(-1, "删除的用户不存在");
		tbManageruserMapper.deleteByPrimaryKey(id);
		return BcResult.ok();
	}

}
