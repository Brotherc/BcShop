package cn.brotherchun.bcshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.service.TbItemParamService;

@Controller
public class TbItemParamController {

	@Autowired 
	private TbItemParamService tbItemParamService;
	
	@RequestMapping("/item/param/list")
	public @ResponseBody EasyUIDataGridResult findTbItemParamList(int page,int rows) throws Exception{
		return tbItemParamService.findTbItemParamList(page, rows);
	}
}
