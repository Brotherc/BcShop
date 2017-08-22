package cn.brotherchun.bcshop.cart.service.impl;

import java.util.List;

import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.pojo.TbItem;


public interface CartService {
	/**
	 * 为购物车添加商品(redis)
	 * @param userId 用户id
	 * @param tbItemId 商品id
	 * @param num 商品数量
	 * @return
	 * @throws Exception
	 */
	public BcResult addTbItemCart(Long userId,Long tbItemId,Integer num) throws Exception;
	
	/**
	 * 合并cookie中的购物车商品
	 * @param userId 用户id
	 * @param tbItemList cookie中的购物车商品
	 * @return
	 * @throws Exception
	 */
	public BcResult mergeCartTbItemList(Long userId,List<TbItem>tbItemList) throws Exception;
	
	/**
	 * 获取购物车列表
	 * @param userId 用户id
	 * @return
	 * @throws Exception
	 */
	public List<TbItem> getCartTbItemList(Long userId) throws Exception;
	
	/**
	 * 更新购物车商品
	 * @param userId 用户id
	 * @param itemId 商品id
	 * @param num 商品数量
	 * @return
	 */
	public BcResult updateCartTbItem(long userId, long itemId, int num);
	
	/**
	 * 删除购物车商品
	 * @param userId 用户id
	 * @param itemId 商品id
	 * @return
	 */
	public BcResult deleteCartTbItem(long userId, long itemId);
}
