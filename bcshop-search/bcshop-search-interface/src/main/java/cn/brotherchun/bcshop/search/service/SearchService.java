package cn.brotherchun.bcshop.search.service;

import cn.brotherchun.bcshop.common.pojo.SearchResult;


public interface SearchService {

	public SearchResult search(String keyword, int page, int rows)  throws Exception;
}
