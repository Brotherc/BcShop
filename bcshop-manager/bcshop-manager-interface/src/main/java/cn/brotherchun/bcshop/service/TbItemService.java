package cn.brotherchun.bcshop.service;

import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.pojo.TbItem;

public interface TbItemService {
	/**
	 *  测试通过商品id获取商品信息
	 * @param id 商品id
	 * @return  TbItem 商品信息
	 * @throws Exception
	 */
	public TbItem testGetTbItemById (Long id) throws Exception;
	
	/**
	 * 根据分页信息获取商品列表
	 * @param page 第几页
	 * @param rows 每页个数
	 * @return easyUI格式的结果
	 * @throws Exception
	 */
	public EasyUIDataGridResult getTbItemList(int page,int rows) throws Exception;
}
