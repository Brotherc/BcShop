package cn.brotherchun.bcshop.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.service.CrawlItemService;

@Controller
public class CrawlController {
	@Autowired
	private CrawlItemService crawlItemService;

	
	//加载爬取页面时，从redis中取出爬取的商品
	@RequestMapping("/crawltbitem/list")
	public @ResponseBody EasyUIDataGridResult getCrawlTbItemList(int page,int rows) throws Exception{
		EasyUIDataGridResult easyUIDataGridResult = crawlItemService.getCrawlItem(page, rows);
		return easyUIDataGridResult;
	}
	
	@RequestMapping("/crawltbitem/crawl")
	public @ResponseBody BcResult crawlTbItems(String url) throws Exception{
		crawlItemService.crawlItems(url);
		return BcResult.ok();
	}
}
