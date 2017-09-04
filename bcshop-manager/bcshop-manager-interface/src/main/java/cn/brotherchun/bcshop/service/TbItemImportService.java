package cn.brotherchun.bcshop.service;

import cn.brotherchun.bcshop.common.utils.BcResult;

public interface TbItemImportService {

	public BcResult importTbItem(String filePath) throws Exception;
}
