package cn.brotherchun.bcshop.service;

import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.pojo.TbManageruser;

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
	
	/**
	 * 查询后台用户列表
	 * @param page 第几页
	 * @param rows 每页个数
	 * @return easyUI格式的结果
	 * @throws Exception
	 */
	public EasyUIDataGridResult managerUserList(int page,int rows) throws Exception;
	
	/**
	 * 添加用户
	 * @param tbManageruser 添加的用户信息
	 * @return
	 * @throws Exception
	 */
	public BcResult addManagerUser(TbManageruser tbManageruser) throws Exception;
	
	/**
	 * 修改用户
	 * @param tbManageruser 修改的用户信息
	 * @return
	 * @throws Exception
	 */
	public BcResult updateManagerUser(TbManageruser tbManageruser) throws Exception;
	
	/**
	 * 删除用户
	 * @param id 删除的用户的id
	 * @return
	 * @throws Exception
	 */
	public BcResult deleteManagerUser(Long id) throws Exception;
}
