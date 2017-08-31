package cn.brotherchun.bcshop.service;

import cn.brotherchun.bcshop.common.utils.BcResult;

public interface ManagerService {
	/**
	 * 用户登录校验
	 * @param validatecodePage 输入验证码 
	 * @param validatecodeSession 正确验证码
	 * @param username 用户名
	 * @param pwd 密码
	 * @return
	 * @throws Exception
	 */
	public BcResult login(String validatecodePage,String validatecodeSession,String username,String pwd) throws Exception;
	
	/**
	 * 用户修改密码
	 * @param id 用户id
	 * @param newPwd 新密码
	 * @param oldPwd 旧密码
	 * @param newPwdTwo 第二次输入新密码
	 * @return
	 * @throws Exception
	 */
	public BcResult changePwd(Long id,String newPwd,String oldPwd,String newPwdTwo) throws Exception;
}
