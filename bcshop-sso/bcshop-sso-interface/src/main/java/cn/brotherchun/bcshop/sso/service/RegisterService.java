package cn.brotherchun.bcshop.sso.service;

import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.pojo.TbUser;

public interface RegisterService {
	/**
	 * 注册校验数据
	 * @param param 注册数据
	 * @param type 数据类型 1：用户名 2：手机号 3：邮箱
	 * @return
	 * @throws Exception
	 */
	public BcResult checkData(String param ,int type) throws Exception;

	/**
	 * 用户注册
	 * @param tbUser 用户注册信息
	 * @return
	 * @throws Exception
	 */
	public BcResult register(TbUser tbUser) throws Exception;
}
