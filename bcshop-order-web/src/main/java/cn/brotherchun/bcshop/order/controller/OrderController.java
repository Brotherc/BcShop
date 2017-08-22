package cn.brotherchun.bcshop.order.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.brotherchun.bcshop.cart.service.impl.CartService;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.order.pojo.OrderInfo;
import cn.brotherchun.bcshop.order.service.OrderService;
import cn.brotherchun.bcshop.pojo.TbItem;
import cn.brotherchun.bcshop.pojo.TbUser;

@Controller
public class OrderController {

	@Autowired 
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("order/order-cart")
	public String showOrder(HttpServletRequest request) throws Exception{
		TbUser tbUser = (TbUser) request.getAttribute("user");
		List<TbItem> cartTbItemList = cartService.getCartTbItemList(tbUser.getId());
		request.setAttribute("cartList", cartTbItemList);
		return "order-cart";
	}
	
	@RequestMapping(value="/order/create", method=RequestMethod.POST)
	public String createOrder(OrderInfo orderInfo, HttpServletRequest request) throws Exception{
		//取用户信息
		TbUser user = (TbUser) request.getAttribute("user");
		//把用户信息添加到orderInfo中。
		orderInfo.setUserId(user.getId());
		orderInfo.setBuyerNick(user.getUsername());
		//调用服务生成订单
		BcResult bcResult = orderService.createOrder(orderInfo);
		//如果订单生成成功，需要删除购物车
		if (bcResult.getStatus() == 200) {
			//清空购物车
			cartService.clearCartTbItem(user.getId());
		}
		//把订单号传递给页面
		request.setAttribute("orderId", bcResult.getData());
		request.setAttribute("payment", orderInfo.getPayment());
		//返回逻辑视图
		return "success";
	}
}
