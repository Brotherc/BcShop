package cn.brotherchun.bcshop.service;

import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.common.utils.BcResult;
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
	
	/**
	 * 添加商品与对应商品的描述
	 * @param tbItem 添加的商品信息
	 * @param desc 添加的商品描述
	 * @return 
	 * @throws Exception
	 */
	public BcResult addTbItem(TbItem tbItem,String desc) throws Exception;
	
	/**
	 * 根据商品id获取商品描述
	 * @param tbItemId 商品id
	 * @return
	 * @throws Exception
	 */
	public BcResult getTbitemDescByTbItemId(Long tbItemId) throws Exception;
	
	/**
	 * 根据商品id获取商品信息
	 * @param tbItemId 商品id
	 * @return
	 * @throws Exception
	 */
	public BcResult getTbItemById(Long tbItemId) throws Exception;
	
	/**
	 * 修改商品信息
	 * @param tbItem 修改的商品信息
	 * @param desc 修改的商品描述
	 * @return
	 * @throws Exception
	 */
	public BcResult updateTbItem(TbItem tbItem,String desc) throws Exception;
	
	/**
	 * 根据商品id下架商品
	 * @param tbItemId 商品id
	 * @return
	 * @throws Exception
	 */
	public BcResult instockTbItem(Long tbItemId) throws Exception;
	
	/**
	 * 根据商品id上架商品
	 * @param tbItemId 商品id
	 * @return
	 * @throws Exception
	 */
	public BcResult reshelfTbItem(Long tbItemId) throws Exception;
	
	/**
	 * 根据商品id删除商品
	 * @param tbItemId 商品id
	 * @return
	 * @throws Exception
	 */
	public BcResult deleteTbItem(Long tbItemId) throws Exception;
}
