package cn.brotherchun.bcshop.sso.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.mapper.TbUserMapper;
import cn.brotherchun.bcshop.pojo.TbUser;
import cn.brotherchun.bcshop.pojo.TbUserExample;
import cn.brotherchun.bcshop.pojo.TbUserExample.Criteria;
import cn.brotherchun.bcshop.sso.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService{

	@Autowired
	private TbUserMapper tbUserMapper;
	
	@Override
	public BcResult checkData(String param, int type) throws Exception {
		//根据不同的type生成不同的查询条件
		TbUserExample tbUserExample=new TbUserExample();
		Criteria criteria = tbUserExample.createCriteria();
		//1：用户名 2：手机号 3：邮箱
		if(type==1){
			criteria.andUsernameEqualTo(param);
		}else if(type==2){
			criteria.andPhoneEqualTo(param);
		}else if(type==3){
			criteria.andEmailEqualTo(param);
		}else {
			return BcResult.build(-1, "数据类型出错");			
		}
		//执行查询
		List<TbUser> tbUserList = tbUserMapper.selectByExample(tbUserExample);
		//判断结果中是否包含数据
		if(tbUserList!=null&&tbUserList.size()>0)
			return BcResult.ok(false);
		return BcResult.ok(true);
	}

	@Override
	public BcResult register(TbUser tbUser) throws Exception {
		//数据有效性校验
		if(!StringUtils.isNotBlank(tbUser.getUsername())||!StringUtils.isNoneBlank(tbUser.getPassword())||!StringUtils.isNoneBlank(tbUser.getPhone())){
			return BcResult.build(-1, "用户数据不完整，注册失败");
		}
		//1：用户名 2：手机号 3：邮箱
		BcResult result = checkData(tbUser.getUsername(), 1);
		if(!(boolean)result.getData())
			return BcResult.build(-1, "用户名已存在");
		BcResult result2 = checkData(tbUser.getPhone(), 2);
		if(!(boolean)result2.getData())
			return BcResult.build(-1, "用户手机已注册");
		//补全pojo属性
		tbUser.setCreated(new Date());
		tbUser.setUpdated(new Date());
		//对密码进行MD5加密
		String md5Pwd = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
		tbUser.setPassword(md5Pwd);
		//把用户数据插入到数据库
		tbUserMapper.insert(tbUser);
		//返回添加成功
		return BcResult.ok();
	}

}
