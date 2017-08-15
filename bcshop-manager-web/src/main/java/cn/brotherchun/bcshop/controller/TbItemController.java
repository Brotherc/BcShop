package cn.brotherchun.bcshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.brotherchun.bcshop.pojo.TbItem;
import cn.brotherchun.bcshop.service.TbItemService;

@Controller
public class TbItemController {

	@Autowired
	private TbItemService tbItemService;
	
	//测试通过id获取商品
	@RequestMapping("/tbitem/{id}")
	private @ResponseBody TbItem testFindTbItemById(@PathVariable Long id) throws Exception{
		return tbItemService.testGetTbItemById(id);
	}
}
