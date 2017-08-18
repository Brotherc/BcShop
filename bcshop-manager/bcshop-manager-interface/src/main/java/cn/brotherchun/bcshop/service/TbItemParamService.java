package cn.brotherchun.bcshop.service;


import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.common.utils.BcResult;

public interface TbItemParamService {
	/**'
	 * 获取商品规格参数
	 * @return
	 * @throws Exception
	 */
	public EasyUIDataGridResult findTbItemParamList(int page,int rows) throws Exception;
	
	/**
	 * 根据商品分类id获取商品规格参数
	 * @param itemCatId 商品分类id
	 * @return
	 * @throws Exception
	 */
	public BcResult getTbItemParamByItemCatId(Long itemCatId) throws Exception;
	
	/**
	 * 根据商品分类id与规格参数添加商品规格参数
	 * @param itemCatId 商品分类id
	 * @param paramData 商品规格参数
	 * @return
	 * @throws Exception
	 */
	public BcResult addTbItemParam(Long itemCatId,String paramData) throws Exception;
	
	/**
	 * 根据商品规格参数id删除商品规格参数
	 * @param tbItemParamId 商品规格参数id
	 * @return
	 * @throws Exception
	 */
	public BcResult deleteTbItemParam(Long tbItemParamId) throws Exception;
}
