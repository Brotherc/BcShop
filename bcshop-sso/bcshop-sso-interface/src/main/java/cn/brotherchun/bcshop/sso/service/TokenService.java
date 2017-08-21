package cn.brotherchun.bcshop.sso.service;

import cn.brotherchun.bcshop.common.utils.BcResult;


/**
 * 根据token查询用户信息
 * <p>Title: TokenService</p>
 * <p>Description: </p>
 * <p>Company: www.brotherchun.cn</p> 
 * @version 1.0
 */
public interface TokenService {

	public BcResult getUserByToken(String token);
}
