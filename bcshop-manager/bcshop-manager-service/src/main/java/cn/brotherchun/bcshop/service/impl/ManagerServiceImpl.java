package cn.brotherchun.bcshop.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
//		pwd = DigestUtils.md5DigestAsHex(pwd.trim().getBytes());
		//取出刚查询用户的密码
		TbManageruser tbManageruser = tbManageruserList.get(0);
		String md5Pwd=tbManageruser.getPassword();
		if(!pwd.equals(md5Pwd))
			return BcResult.build(-1, "用户名或密码错误");
		//校验成功，将用户密码设置为null，返回用户信息
		tbManageruser.setPassword(null);
		return BcResult.build(200, "登录成功", tbManageruser);
	}

}
