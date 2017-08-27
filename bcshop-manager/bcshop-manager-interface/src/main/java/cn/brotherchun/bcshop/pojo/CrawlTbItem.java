package cn.brotherchun.bcshop.pojo;

import java.io.Serializable;

public class CrawlTbItem extends TbItem implements Serializable{
	private Long crawlId;
	private String itemDesc;

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public Long getCrawlId() {
		return crawlId;
	}

	public void setCrawlId(Long crawlId) {
		this.crawlId = crawlId;
	}
	
}
