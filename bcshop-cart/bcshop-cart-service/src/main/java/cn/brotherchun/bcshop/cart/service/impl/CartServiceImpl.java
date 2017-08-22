package cn.brotherchun.bcshop.cart.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.brotherchun.bcshop.common.jedis.JedisClient;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.common.utils.JsonUtils;
import cn.brotherchun.bcshop.mapper.TbItemMapper;
import cn.brotherchun.bcshop.pojo.TbItem;


@Service
public class CartServiceImpl implements CartService{

	@Value("${CartUSER_KEY}")
	private String CartUSER_KEY;
	
	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Override
	public BcResult addTbItemCart(Long userId, Long tbItemId, Integer num)
			throws Exception {
		TbItem tbItem = getTbItem(userId, tbItemId);
		if(tbItem!=null){
			tbItem.setNum(tbItem.getNum()+num);
		}else {
			tbItem = tbItemMapper.selectByPrimaryKey(tbItemId);
			String image = tbItem.getImage();
			if (StringUtils.isNoneBlank(image)) {
				String[] images = image.split(",");
				tbItem.setImage(images[0]);
			}
			tbItem.setNum(num);
		}
		jedisClient.hset(CartUSER_KEY + ":" + userId, tbItemId + "", JsonUtils.objectToJson(tbItem));
		return BcResult.ok();
	}
	//从购物车中获取商品信息
	public TbItem getTbItem(Long userId,Long tbItemId){
		String json = jedisClient.hget(CartUSER_KEY + ":" + userId, tbItemId + "");
		if(json!=null){
			TbItem tbItem = JsonUtils.jsonToPojo(json, TbItem.class);
			return tbItem;
		}
		return null;
	}
	@Override
	public BcResult mergeCartTbItemList(Long userId,List<TbItem>tbItemList) throws Exception {
		for(TbItem tbItem:tbItemList){
			addTbItemCart(userId, tbItem.getId(), tbItem.getNum());
		}
		return BcResult.ok();
	}
	@Override
	public List<TbItem> getCartTbItemList(Long userId) throws Exception {
		String json = jedisClient.get(CartUSER_KEY + ":" + userId);
		Map<Long, TbItem> map = JsonUtils.jsonToMap(json, Long.class, TbItem.class);
		List<TbItem>cartList=new ArrayList<>();
		for(TbItem tbItem:map.values()){
			cartList.add(tbItem);
		}
		return cartList;
	}
	@Override
	public BcResult updateCartTbItem(long userId, long itemId, int num) {
		//从redis中取商品信息
		String json = jedisClient.hget(CartUSER_KEY + ":" + userId, itemId + "");
		//更新商品数量
		TbItem tbItem = JsonUtils.jsonToPojo(json, TbItem.class);
		tbItem.setNum(num);
		//写入redis
		jedisClient.hset(CartUSER_KEY + ":" + userId, itemId + "", JsonUtils.objectToJson(tbItem));
		return BcResult.ok();
	}
	@Override
	public BcResult deleteCartTbItem(long userId, long itemId) {
		// 删除购物车商品
		jedisClient.hdel(CartUSER_KEY + ":" + userId, itemId + "");
		return BcResult.ok();
	}
	@Override
	public BcResult clearCartTbItem(long userId) {
		//删除购物车信息
		jedisClient.set(CartUSER_KEY + ":" + userId, "");
		return BcResult.ok();
	}
}
