package cn.brotherchun.bcshop.service;

import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.common.utils.BcResult;

public interface CrawlItemService {
	/**
	 * 抓取商品信息与描述
	 * @param url 请求url
	 * @throws Exception
	 */
	public BcResult crawlItems(String url) throws Exception;
	
	/**
	 * 从redis中取出爬取商品的信息
	 * @param page 第几页
	 * @param rows 每页个数
	 * @return
	 * @throws Exception
	 */
	public EasyUIDataGridResult getCrawlItem(int page,int rows) throws Exception;
}
