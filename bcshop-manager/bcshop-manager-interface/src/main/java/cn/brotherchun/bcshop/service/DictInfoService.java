package cn.brotherchun.bcshop.service;

import java.util.List;

import cn.brotherchun.bcshop.pojo.TbDictinfo;

public interface DictInfoService {
	public List<TbDictinfo> tbDictinfoListByTypeCode(String typeCode) throws Exception;
}
