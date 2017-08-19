package cn.brotherchun.bcshop.search.mapper;

import java.util.List;

import cn.brotherchun.bcshop.common.pojo.SearchItem;

public interface ItemMapper {
	public List<SearchItem> getItemList() throws Exception;
}
