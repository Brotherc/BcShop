package cn.brotherchun.bcshop.sso.service;

import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.pojo.TbUser;

public interface LoginService {
	public BcResult login(TbUser tbUser) throws Exception;
}
