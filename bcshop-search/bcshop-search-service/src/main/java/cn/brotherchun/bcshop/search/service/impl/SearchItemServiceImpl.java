package cn.brotherchun.bcshop.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.brotherchun.bcshop.common.pojo.SearchItem;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.search.mapper.ItemMapper;
import cn.brotherchun.bcshop.search.service.SearchItemService;


/**
 * 索引库维护Service
 * <p>Title: SearchItemServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.brotherchun.cn</p> 
 * @version 1.0
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public BcResult importAllItems() {
		try {
			//查询商品列表
			List<SearchItem> itemList = itemMapper.getItemList();
			//遍历商品列表
			for (SearchItem searchItem : itemList) {
				//创建文档对象
				SolrInputDocument document = new SolrInputDocument();
				//向文档对象中添加域
				document.addField("id", searchItem.getId());
				document.addField("item_title", searchItem.getTitle());
				document.addField("item_sell_point", searchItem.getSell_point());
				document.addField("item_price", searchItem.getPrice());
				document.addField("item_image", searchItem.getImage());
				document.addField("item_category_name", searchItem.getCategory_name());
				//把文档对象写入索引库
				solrServer.add(document);
			}
			//提交
			solrServer.commit();
			//返回导入成功
			return BcResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return BcResult.build(-1, "ERROR");
					
		}
	}

}
