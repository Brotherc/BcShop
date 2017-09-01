package cn.brotherchun.bcshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.brotherchun.bcshop.mapper.TbDictinfoMapper;
import cn.brotherchun.bcshop.pojo.TbDictinfo;
import cn.brotherchun.bcshop.pojo.TbDictinfoExample;
import cn.brotherchun.bcshop.pojo.TbDictinfoExample.Criteria;
import cn.brotherchun.bcshop.service.DictInfoService;

@Service
public class DictInfoServiceImpl implements DictInfoService{

	@Autowired
	private TbDictinfoMapper tbDictinfoMapper;
	
	@Override
	public List<TbDictinfo> tbDictinfoListByTypeCode(String typeCode)
			throws Exception {
		TbDictinfoExample tbDictinfoExample=new TbDictinfoExample();
		Criteria criteria = tbDictinfoExample.createCriteria();
		criteria.andTypecodeEqualTo(typeCode);
		List<TbDictinfo> tbDictinfoList = tbDictinfoMapper.selectByExample(tbDictinfoExample);
		return tbDictinfoList;
	}

}
