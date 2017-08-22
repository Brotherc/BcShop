package cn.brotherchun.bcshop.cart.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.common.utils.CookieUtils;
import cn.brotherchun.bcshop.pojo.TbUser;
import cn.brotherchun.bcshop.sso.service.TokenService;

/**
 * 用户登录处理拦截器
 * <p>Title: LoginInterceptor</p>
 * <p>Description: </p>
 * <p>Company: www.brotherchun.cn</p> 
 * @version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor{
	
	@Autowired 
	private TokenService tokenService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object hander) throws Exception {
		// 前处理，执行handler之前执行此方法。
		//返回true，放行	false：拦截
		//1.从cookie中取token
		String token = CookieUtils.getCookieValue(request, "bcshop-token").toString();
		//2.如果没有token，未登录状态，直接放行
		if(!StringUtils.isNoneBlank(token))
			return true;
		//3.取到token，需要调用sso系统的服务，根据token取用户信息
		BcResult result = tokenService.getUserByToken(token);
		//4.没有取到用户信息。登录过期，直接放行。
		if(result.getStatus()!=200)
			return true;
		//5.取到用户信息。登录状态。
		TbUser tbUser=(TbUser)result.getData();
		//6.把用户信息放到request中。只需要在Controller中判断request中是否包含user信息。放行
		request.setAttribute("user", tbUser);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object hander, ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object hander, Exception exception)
			throws Exception {
		
	}

}
