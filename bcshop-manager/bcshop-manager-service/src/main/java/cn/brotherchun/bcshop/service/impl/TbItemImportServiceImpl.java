package cn.brotherchun.bcshop.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.common.utils.HxlsOptRowsInterface;
import cn.brotherchun.bcshop.common.utils.HxlsRead;
import cn.brotherchun.bcshop.service.TbItemImportService;

@Service
public class TbItemImportServiceImpl implements TbItemImportService{
	
	@Autowired
	private HxlsOptRowsInterface hxlsOptRowsInterface;

	@Override
	public BcResult importTbItem(String filePath) throws Exception {
		//调用工具类进行药品目录 导入
		HxlsRead xls2csv = null;
		try {
			//第一个参数就是导入的文件
			//第二个参数就是导入文件中哪个sheet
			//第三个参数导入接口的实现类对象
			xls2csv = new HxlsRead(filePath,0,hxlsOptRowsInterface);
			xls2csv.process();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//提示导入成功条数
		long success_num = xls2csv.getOptRows_success();
		//导入失败数量
		long failure_num = xls2csv.getOptRows_failure();
		
		//对导入失败记录处理
		//获取导入失败记录
		//xls2csv.getFailrows()
		//获取导入记录的title
		//xls2csv.getRowtitle();
		//获取导入失败原因
		//xls2csv.getFailmsgs()
		
		//将上边获取到的失败记录、title、失败原因，导出成一个 excel
		//使用工具类进行导出，得到导出文件下载路径
		//......
		
		String msg=success_num+"件商品倒入成功，"+failure_num+"件商品导入失败";
		return BcResult.build(200, msg);
	}

}
