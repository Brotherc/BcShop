package cn.brotherchun.bcshop.manager.exception;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import cn.brotherchun.bcshop.common.utils.BcResult;

/**
 * 全局异常处理器
 * <p>Title: GlobalExceptionResolver</p>
 * <p>Description: </p>
 * <p>Company: www.brotherchun.cn</p> 
 * @version 1.0
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class); 
	
	// json转换器
	// 将异常信息转json
	private HttpMessageConverter<BcResult> jsonMessageConverter;

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		//打印控制台
		ex.printStackTrace();
		//写日志
		logger.debug("测试输出的日志。。。。。。。");
		logger.info("系统发生异常了。。。。。。。");
		logger.error("系统发生异常", ex);
		//发邮件、发短信
		//使用jmail工具包。发短信使用第三方的Webservice。
		

		// 转成springmvc底层对象（就是对action方法的封装对象，只有一个方法）
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		// 取出方法
		Method method = handlerMethod.getMethod();

		//获取用户请求的url
		String url = request.getRequestURI();
		// 判断方法是否返回json
		// 只要方法上有responsebody注解表示返回json
		// 查询method是否有responsebody注解
		ResponseBody responseBody = AnnotationUtils.findAnnotation(method,
				ResponseBody.class);
		if (responseBody != null) {
			// 将异常信息转json输出
			if(url.contains("/crawltbitem/crawl"))
				return this.resolveJsonException(request, response, handlerMethod,ex);
		}
		return new ModelAndView();
	}
	// 将异常信息转json输出
	private ModelAndView resolveJsonException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {

		// 构造爬虫异常结果
		BcResult result = BcResult.build(-1, "爬取商品失败!");
		
		HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
		
		try {
			//将exceptionResultInfo对象转成json输出
			jsonMessageConverter.write(result, MediaType.APPLICATION_JSON, outputMessage);
		} catch (HttpMessageNotWritableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ModelAndView();
	}
	public HttpMessageConverter<BcResult> getJsonMessageConverter() {
		return jsonMessageConverter;
	}

	public void setJsonMessageConverter(
			HttpMessageConverter<BcResult> jsonMessageConverter) {
		this.jsonMessageConverter = jsonMessageConverter;
	}
}
