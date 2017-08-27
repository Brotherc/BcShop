package cn.brotherchun.bcshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.brotherchun.bcshop.common.jedis.JedisClient;
import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.common.utils.JsonUtils;
import cn.brotherchun.bcshop.pojo.CrawlTbItem;
import cn.brotherchun.bcshop.pojo.TbItem;
import cn.brotherchun.bcshop.service.CrawlItemService;
import cn.brotherchun.bcshop.service.spider.Spider;

@Service
public class CrawlItemServiceImpl implements CrawlItemService{

	@Autowired
	private Spider spider;
	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_CRAWLITEM_LIST_KEY}")
	private String REDIS_CRAWLITEM_LIST_KEY;
	@Value("${CRAWLITEM_CACHE_EXPIRE}")
	private int CRAWLITEM_CACHE_EXPIRE;
	
	@Override
	public EasyUIDataGridResult getCrawlItem(int page, int rows) throws Exception {
		int start=(page-1)*rows;
		int end=page*rows-1;
		List<String> list = jedisClient.lget(REDIS_CRAWLITEM_LIST_KEY, start, end);
		List<CrawlTbItem>crawlTbItemList=new ArrayList<>();
		for(String json:list){
			CrawlTbItem crawlTbItem = JsonUtils.jsonToPojo(json, CrawlTbItem.class);
			crawlTbItemList.add(crawlTbItem);
		}
		Long total=jedisClient.llen(REDIS_CRAWLITEM_LIST_KEY);
		EasyUIDataGridResult easyUIDataGridResult=new EasyUIDataGridResult(total, crawlTbItemList);
		return easyUIDataGridResult;
	}
	
	@Override
	public void crawlItems(String url) throws Exception {
		// TODO Auto-generated method stub
		//根据不同的url选择不同的爬取方式
		CrawlTbItem crawlTbItem = crawlItem(url);
		//将商品保存到redis中
		if(crawlTbItem!=null){
			String json = JsonUtils.objectToJson(crawlTbItem);		
			if(jedisClient.exists(REDIS_CRAWLITEM_LIST_KEY)){
				jedisClient.ldel(REDIS_CRAWLITEM_LIST_KEY, json);
				jedisClient.llset(REDIS_CRAWLITEM_LIST_KEY, json);
			}
			else{
				jedisClient.llset(REDIS_CRAWLITEM_LIST_KEY, json);
				//设置过期时间
				jedisClient.expire(REDIS_CRAWLITEM_LIST_KEY, CRAWLITEM_CACHE_EXPIRE);
			}
		}
	}
	
	//爬取的url例如https://item.jd.com/389582.html
	public CrawlTbItem crawlItem(String url) throws Exception {
		//爬title和img
		TbItem tbItem = spider.crawlItemBase(url);
		CrawlTbItem crawlTbItem=new CrawlTbItem();
		crawlTbItem.setTitle(tbItem.getTitle());
		crawlTbItem.setImage(tbItem.getImage());

		//爬sellPoint
		String id = getTbItemIdByUrl(url);
		String cat=spider.crawlItemCat(url);
		String spUrl="https://cd.jd.com/promotion/v2?skuId="+id+"&area=1_1_1_1&"+cat;
		String sellPoint = spider.crawlItemSellPoint(spUrl);
		
		//爬价格
		String pUrl="https://p.3.cn/prices/mgets?&skuIds=J_"+id;
		String price = spider.crawlItemPrice(pUrl);
		Double dPrice= new Double(price)*1000;
		
		//爬描述
		
		String descUrl="https://cd.jd.com/description/channel?skuId="+id+"&mainSkuId="+id;
		String desc = spider.crawlItemDesc(descUrl);
		
		//把描述设置到商品中
		
		//为TbItem设置值
		crawlTbItem.setSellPoint(sellPoint);
		crawlTbItem.setPrice(dPrice.longValue());
		crawlTbItem.setItemDesc(desc);
		crawlTbItem.setCrawlId(new Long(id));
		return crawlTbItem;
	}
	
	//根据url取出商品id
	public String getTbItemIdByUrl(String url) throws Exception{
		String id=url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("."));
		return id;
	}
	
}
