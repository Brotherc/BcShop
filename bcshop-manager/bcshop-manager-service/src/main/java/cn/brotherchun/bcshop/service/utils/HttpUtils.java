package cn.brotherchun.bcshop.service.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.htmlparser.Parser;

public class HttpUtils {
	public static String getHtml(CloseableHttpClient httpclient,String url){
		CloseableHttpResponse response =null;
		try {

			// 利用HTTP GET向服务器发起请求
			HttpGet get = new HttpGet(url);
			
			// 获得服务器响应的的所有信息
			response = httpclient.execute(get);
			// 获得服务器响应回来的消息体（不包括HTTP HEAD）
			HttpEntity entity = response.getEntity();
			if (entity != null) {		
				String html = null;
				// 获得响应的字符集编码信息
				// 即获取HTTP HEAD的：Content-Type:text/html;charset=UTF-8中的字符集信息
				String charset =null; 
				if(entity.getContentEncoding()!=null)
					charset=entity.getContentEncoding().getValue();
				InputStream is = entity.getContent();
				byte[] content = IOUtils.toByteArray(is);
				

				//假如HTTP HEAD中不包含charset的信息，则从网页内容的<meta>标签中提取charset信息
				if(charset == null){
					
					//如果返回的数据是js
					String contentType = entity.getContentType().getValue();
					if(contentType.contains("javascript")){
						return new String(content,"gbk");
					}
					
					//尝试用ISO-8859-1这个编码来解释HTML
					html = new String(content,"ISO-8859-1");
					Parser parser = new Parser();
					parser.setInputHTML(html);
					//尝试解释本网页，HTMLParser在解释网页的过程中，会自动提取
					//<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>中
					//所包含的编码信息
					parser.parse(null);
					
					//如果网页中不包含编码信息，则这个值返回空
					charset = parser.getEncoding();
				}
				
				if(charset != null&&!charset.equals("ISO-8859-1") ){ //可以采用猜测算法（现在忽略）
					html = new String(content,charset);
				}

				return html;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
