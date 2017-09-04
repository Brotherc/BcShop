package cn.brotherchun.bcshop.service;

import java.util.List;

import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.pojo.TbItem;
import cn.brotherchun.bcshop.pojo.TbItemDesc;

public interface TbItemService {
	/**
	 *  通过商品id获取展示商品详情的商品信息
	 * @param id 商品id
	 * @return  TbItem 商品信息
	 * @throws Exception
	 */
	public TbItem getItemById (Long id) throws Exception;
	
	/**
	 *  通过商品id获取展示商品详情的商品描述信息
	 * @param id 商品id
	 * @return  TbItem 商品信息
	 * @throws Exception
	 */
	public TbItemDesc getItemDescById (Long id) throws Exception;
	
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
	 * @param itemParams 添加的规格参数
	 * @return
	 * @throws Exception
	 */
	public BcResult addTbItem(TbItem tbItem,String desc,String itemParams) throws Exception;
	
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
	public BcResult updateTbItem(TbItem tbItem,String desc,String itemParams) throws Exception;
	
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
	
	/**
	 * 通过条件查询商品信息
	 * @param tbItem 封装条件的商品
	 * @return
	 * @throws Exception
	 */
	public List<TbItem> getTbItemList(TbItem tbItem) throws Exception;
}
