package cn.brotherchun.bcshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.search.service.SearchItemService;

@Controller
public class SearchItemController {
	@Autowired
	private SearchItemService searchItemService;
	
	@RequestMapping("/index/item/import")
	public @ResponseBody BcResult importAllSearchItem() throws Exception{
		return searchItemService.importAllItems();
	}
}
