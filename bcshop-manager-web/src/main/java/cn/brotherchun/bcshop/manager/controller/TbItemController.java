package cn.brotherchun.bcshop.manager.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.common.utils.UUIDBuild;
import cn.brotherchun.bcshop.pojo.TbItem;
import cn.brotherchun.bcshop.service.TbItemImportService;
import cn.brotherchun.bcshop.service.TbItemService;

@Controller
public class TbItemController {

	@Autowired
	private TbItemService tbItemService;
	@Autowired
	private TbItemImportService tbItemImportService;
	
	@Value("${IMPORT_ITEM_EXCEL_PRE}")
	private String IMPORT_ITEM_EXCEL_PRE;
	
	//测试通过id获取商品
	@RequestMapping("/tbitem/{id}")
	private @ResponseBody TbItem testFindTbItemById(@PathVariable Long id) throws Exception{
		return tbItemService.getItemById(id);
	}
	
	//根据分页信息获取商品信息
	@RequestMapping("/tbitem/list")
	public @ResponseBody EasyUIDataGridResult findTbItemList(int page,int rows) throws Exception{
		return tbItemService.getTbItemList(page, rows);
	}
	
	//添加商品
	@RequestMapping("/tbitem/save")
	public @ResponseBody BcResult addTbItem(TbItem tbItem,String desc,String itemParams) throws Exception{
		return tbItemService.addTbItem(tbItem, desc,itemParams);
	}
	
	// 加载商品描述
	@RequestMapping("/tbitem/querydesc/{id}")
	public @ResponseBody BcResult getTbItemDesc(@PathVariable Long id) throws Exception{
		return tbItemService.getTbitemDescByTbItemId(id);
	}
	
	//加载商品信息
	@RequestMapping("/tbitem/paramquery/{id}")
	public @ResponseBody BcResult getTbItem(@PathVariable Long id) throws Exception{
		return tbItemService.getTbItemById(id);
	}
	
	//修改商品信息
	@RequestMapping("/tbitem/update")
	public @ResponseBody BcResult updateTbItem(TbItem tbItem,@RequestParam String desc,String itemParams) throws Exception{
		return tbItemService.updateTbItem(tbItem, desc,itemParams);
	}
	
	//下架商品
	@RequestMapping("/tbitem/instock")
	public @ResponseBody BcResult instockTbItem(String ids) throws Exception{
		if(ids!=null){
			String[] split = ids.split(",");
			for(String id:split){
				tbItemService.instockTbItem(Long.valueOf(id));
			}
		}
		return new BcResult().ok();
	}
	
	//上架商品
	@RequestMapping("/tbitem/reshelf")
	public @ResponseBody BcResult reshelfTbItem(String ids) throws Exception{
		if(ids!=null){
			String[] split = ids.split(",");
			for(String id:split){
				tbItemService.reshelfTbItem(Long.valueOf(id));
			}
		}
		return new BcResult().ok();
	}
	
	//删除商品
	@RequestMapping("/tbitem/delete")
	public @ResponseBody BcResult deleteTbItem(String ids) throws Exception{
		if(ids!=null){
			String[] split = ids.split(",");
			for(String id:split){
				tbItemService.deleteTbItem(Long.valueOf(id));
			}
		}
		return new BcResult().ok();
	}
	
	//药品导入提交
	@RequestMapping("/tbitem/import")
	public @ResponseBody BcResult importTbItem(
			//写上传的文件
			MultipartFile tbItemImportFile
			)throws Exception{
		
		//将上传的文件写到磁盘
		String originalFilename  = tbItemImportFile.getOriginalFilename();
		//写入磁盘的文件
		File file = new File(IMPORT_ITEM_EXCEL_PRE+UUIDBuild.getUUID()+originalFilename.substring(originalFilename.lastIndexOf(".")));
		if(!file.exists()){
			//如果文件目录 不存在则创建
			file.mkdirs();
		}
		
		//将内存中的文件写磁盘
		tbItemImportFile.transferTo(file);
		//上传文件磁盘上路径 
		String absolutePath = file.getAbsolutePath();
		
		return tbItemImportService.importTbItem(absolutePath);
	
	}

}
