package cn.brotherchun.bcshop.service.spider;

import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.brotherchun.bcshop.pojo.TbItem;
import cn.brotherchun.bcshop.service.utils.HttpUtils;
import cn.brotherchun.bcshop.service.utils.JsonOperatorUtil;

public class Spider {
	
	private CloseableHttpClient httpClient;
	
	public CloseableHttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(CloseableHttpClient httpClient) {
		this.httpClient = httpClient;
	}
	/**
	 * 抓取某件商品的基本信息(title和img)
	 * @param url 请求url
	 * @throws Exception
	 */
	public TbItem crawlItemBase(String url) throws Exception{
		System.out.println(url);
			String content = HttpUtils.getHtml(httpClient, url);
	       Document doc=Jsoup.parse(content);
	       Elements titles=doc.select(".sku-name");
	       Element title = titles.get(0);
	       System.out.println(title.text());
	       
	       Element element = doc.getElementById("spec-img");
	       String attr = element.attr("data-origin");
	       String img = attr.replaceAll("//", "http://");
	       
	       TbItem tbItem=new TbItem();
	       tbItem.setImage(img);
	       tbItem.setTitle(title.text());
	       return tbItem;
	};
	
	/**
	 * 抓取某件商品的价格
	 * @param url 请求url
	 * @throws Exception
	 */
	public  String crawlItemPrice(String url) throws Exception{
			String content = HttpUtils.getHtml(httpClient, url);
	       
	    // 解析该json
	       JSONArray jsonArray = JsonOperatorUtil.toJSONArray(content);
	       JSONObject jsonObj=(JSONObject) jsonArray.get(0);
			//京东商品json数据中价格的key为“p”
			String price = (String) jsonObj.get("p");
			return price;
	};
	
	/**
	 * 抓取某件商品的描述
	 * @param url 请求url
	 * @throws Exception
	 */
	public  String crawlItemDesc(String url) throws Exception{
			String json = HttpUtils.getHtml(httpClient, url);
	       
	    // 解析该json
	       JSONObject jsonObj = JsonOperatorUtil.toJSONObject(json);
			//京东商品json数据中描述的key为“content”
			String content = (String) jsonObj.get("content");

			String result = content.replaceAll("//", "http://");
			String desc = result.replaceAll("data-lazyload", "src");
			return desc;
	};
	
	/**
	 * 抓取某件商品的卖点
	 * @param url 请求url
	 * @throws Exception
	 */
	public  String crawlItemSellPoint(String url) throws Exception{
			String json = HttpUtils.getHtml(httpClient, url);
	       
	    // 解析该json
	       JSONObject jsonObj = JsonOperatorUtil.toJSONObject(json);
			//京东商品json数据中描述的key为“content”
	       List<JSONObject>jsonList = (List<JSONObject>) jsonObj.get("ads");
	       JSONObject jsonObj2= jsonList.get(0);
	      String sellPoint= (String) jsonObj2.get("ad");
	      return sellPoint;
	};
	/**
	 * 抓取某件商品的分类
	 * @param url 请求url
	 * @throws Exception
	 */
	public  String crawlItemCat(String url) throws Exception{
			String html = HttpUtils.getHtml(httpClient, url);
	       
			Document doc=Jsoup.parse(html);
			Elements cats = doc.select("#parameter-brand a");
			Element element = cats.get(0);
			String attr = element.attr("href");
			int indexCat = attr.indexOf("cat");
			String cat = attr.substring(indexCat, attr.indexOf("&", indexCat));
			cat=cat.replace(",", "%2C");
			return cat;
	};

}
