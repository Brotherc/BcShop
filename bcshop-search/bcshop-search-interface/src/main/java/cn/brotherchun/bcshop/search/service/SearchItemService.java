package cn.brotherchun.bcshop.search.service;

import cn.brotherchun.bcshop.common.utils.BcResult;

public interface SearchItemService {

	/**
	 * 将数据库的商品信息导入solr索引库
	 * @return
	 * @throws Exception
	 */
	public BcResult importAllItems() throws Exception;
}
