package cn.brotherchun.bcshop.sso.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.brotherchun.bcshop.common.jedis.JedisClient;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.common.utils.JsonUtils;
import cn.brotherchun.bcshop.mapper.TbUserMapper;
import cn.brotherchun.bcshop.pojo.TbUser;
import cn.brotherchun.bcshop.pojo.TbUserExample;
import cn.brotherchun.bcshop.pojo.TbUserExample.Criteria;
import cn.brotherchun.bcshop.sso.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private TbUserMapper tbUserMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;
	
	@Override
	public BcResult login(TbUser tbUser) throws Exception {
		//用户名，手机或邮箱
		String username = tbUser.getUsername();
		String pwd=tbUser.getPassword();
		//登录数据校验
		if(!StringUtils.isNoneBlank(username))
			return BcResult.build(-1, "用户名，手机或邮箱为空");
		if(!StringUtils.isNoneBlank(tbUser.getPassword()))
			return BcResult.build(-1, "密码为空");
		//查询是否存在用户名，手机或密码
		TbUserExample tbUserExample=new TbUserExample();
		Criteria criteria = tbUserExample.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> userByNameList = tbUserMapper.selectByExample(tbUserExample);
		//查询是否存在用户名
		if(userByNameList!=null&&userByNameList.size()>0){
			//校验密码输入是否正确
			//先对密码进行MD5加密再与数据库中的密码进行比较
			pwd = DigestUtils.md5DigestAsHex(pwd.getBytes());
			if(pwd.equals(userByNameList.get(0).getPassword()))
				return BcResult.ok(getToken(userByNameList.get(0)));
			else return BcResult.build(-1, "密码输入错误");
		}
		//查询是否存在手机
		TbUserExample tbUserExample2=new TbUserExample();
		Criteria criteria2 = tbUserExample2.createCriteria();
		criteria2.andPhoneEqualTo(username);
		List<TbUser> userByPhoneList = tbUserMapper.selectByExample(tbUserExample2);
		if(userByPhoneList!=null&&userByPhoneList.size()>0){
			//校验密码输入是否正确
			//先对密码进行MD5加密再与数据库中的密码进行比较
			pwd = DigestUtils.md5DigestAsHex(pwd.getBytes());
			if(pwd.equals(userByPhoneList.get(0).getPassword()))
				return BcResult.ok(getToken(userByPhoneList.get(0)));
			else return BcResult.build(-1, "密码输入错误");
		}
		//查询是否存在邮箱
		TbUserExample tbUserExample3=new TbUserExample();
		Criteria criteria3 = tbUserExample3.createCriteria();
		criteria3.andEmailEqualTo(username);
		List<TbUser> userByEmailList = tbUserMapper.selectByExample(tbUserExample3);
		if(userByEmailList!=null&&userByEmailList.size()>0){
			//校验密码输入是否正确
			//先对密码进行MD5加密再与数据库中的密码进行比较
			pwd = DigestUtils.md5DigestAsHex(pwd.getBytes());
			if(pwd.equals(userByEmailList.get(0).getPassword()))
				return BcResult.ok(getToken(userByEmailList.get(0)));
			else return BcResult.build(-1, "密码输入错误");
		}
		return BcResult.build(-1, "用户名，手机或邮箱不存在");
	}
	//生成token
	public String getToken(TbUser tbUser) throws Exception{
		String token = UUID.randomUUID().toString();
		tbUser.setPassword(null);
		//把用户信息写入redis，key：token value： 用户信息
		jedisClient.set("SESSION:"+token, JsonUtils.objectToJson(tbUser));
		//设置session的过期时间
		jedisClient.expire("SESSION:"+token, SESSION_EXPIRE);
		return token;
	}
}
