package cn.brotherchun.bcshop.order.service;

import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.order.pojo.OrderInfo;

public interface OrderService {
		/**
		 * 创建订单
		 * @param orderInfo 订单信息
		 * @return
		 * @throws Exception
		 */
		public BcResult createOrder(OrderInfo orderInfo) throws Exception;
}

