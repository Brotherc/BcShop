package cn.brotherchun.bcshop.cart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.brotherchun.bcshop.cart.service.impl.CartService;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.common.utils.CookieUtils;
import cn.brotherchun.bcshop.common.utils.JsonUtils;
import cn.brotherchun.bcshop.pojo.TbItem;
import cn.brotherchun.bcshop.pojo.TbUser;
import cn.brotherchun.bcshop.service.TbItemService;

/**
 * 购物车处理Controller
 * <p>Title: CartController</p>
 * <p>Description: </p>
 * <p>Company: www.brotherchun.cn</p> 
 * @version 1.0
 */
@Controller
public class CartController {
	
	@Value("${Cart_KEY}")
	private String Cart_KEY;
	@Value("${CART_EXPIRE}")
	private Integer CART_EXPIRE;

	@Autowired
	private TbItemService tbItemService;
	@Autowired
	private CartService cartService;

	//添加商品到购物车
	@RequestMapping("/cart/add/{tbItemId}")
	public String addTbItemCart(HttpServletRequest request,HttpServletResponse response,@PathVariable Long tbItemId,@RequestParam(defaultValue="1")Integer num) throws Exception{
		TbUser user=(TbUser) request.getAttribute("user");
		if(user!=null){
			cartService.addTbItemCart(user.getId(), tbItemId, num);
			return "cartSuccess";
		}
		
		List<TbItem> cartList = getCartList(request);
		boolean flag=false;
		for(TbItem tbItem:cartList){
			if(tbItem.getId().equals(tbItemId)){
				tbItem.setNum(tbItem.getNum()+num);
				flag=true;
				break;
			}
		}
		if(!flag){
			TbItem tbItem = tbItemService.getItemById(tbItemId);
			String image = tbItem.getImage();
			if (StringUtils.isNoneBlank(image)) {
				String[] images = image.split(",");
				tbItem.setImage(images[0]);
			}

			tbItem.setNum(num);
			cartList.add(tbItem);
		}
		CookieUtils.setCookie(request, response, Cart_KEY, JsonUtils.objectToJson(cartList),CART_EXPIRE, true);
		return "cartSuccess";
	}
	//查看购物车商品列表
	@RequestMapping("/cart/cart")
	public String findCartTbItemList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		TbUser user=(TbUser) request.getAttribute("user");
		List<TbItem> cartList=getCartList(request);
		if(user!=null){
			cartService.mergeCartTbItemList(user.getId(), cartList);
			CookieUtils.deleteCookie(request, response, Cart_KEY);
			cartList = cartService.getCartTbItemList(user.getId());
		}

		request.setAttribute("cartList", cartList);
		return "cart";
	}
	
	//更新购物车商品数量
	@RequestMapping("/cart/update/num/{tbItemId}/{num}")
	public @ResponseBody BcResult updateCartTbItem(HttpServletRequest request,HttpServletResponse response,@PathVariable Long tbItemId,@PathVariable Integer num) throws Exception{
		//判断用户是否为登录状态
		TbUser user = (TbUser) request.getAttribute("user");
		if (user != null) {
			cartService.updateCartTbItem(user.getId(), tbItemId, num);
			return BcResult.ok();
		}
		
		List<TbItem> cartList=getCartList(request);
		for(TbItem tbItem:cartList){
			if(tbItem.getId().equals(tbItemId)){
				tbItem.setNum(num);
				break;
			}
		}
		CookieUtils.setCookie(request, response, Cart_KEY, JsonUtils.objectToJson(cartList), CART_EXPIRE, true);
		return BcResult.ok();
	}
	
	//清除购物车中某一商品
	@RequestMapping("/cart/delete/{tbItemId}")
	public String deleteCartTbItem(HttpServletRequest request,HttpServletResponse response,@PathVariable Long tbItemId) throws Exception{
		//判断用户是否为登录状态
		TbUser user = (TbUser) request.getAttribute("user");
		if (user != null) {
			cartService.deleteCartTbItem(user.getId(), tbItemId);
			return "redirect:/cart/cart.html";
		}
		
		List<TbItem> cartList=getCartList(request);
		for(TbItem tbItem:cartList){
			if(tbItem.getId().equals(tbItemId)){
				cartList.remove(tbItem);
				break;
			}
		}
		CookieUtils.setCookie(request, response, Cart_KEY, JsonUtils.objectToJson(cartList), CART_EXPIRE, true);
		return "redirect:/cart/cart.html";
	}
	
	// 从cookie中取购物车列表
	private List<TbItem> getCartList(HttpServletRequest request) {
		//取购物车列表
		String json = CookieUtils.getCookieValue(request, Cart_KEY, true);
		//判断json是否为null
		if (StringUtils.isNotBlank(json)) {
			//把json转换成商品列表返回
			List<TbItem> list = JsonUtils.jsonToList(json, TbItem.class);
			return list;
		}
		return new ArrayList<>();
	}

}
