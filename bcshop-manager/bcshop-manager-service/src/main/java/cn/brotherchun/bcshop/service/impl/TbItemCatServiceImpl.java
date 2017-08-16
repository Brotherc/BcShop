package cn.brotherchun.bcshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.brotherchun.bcshop.common.pojo.EasyUITreeNode;
import cn.brotherchun.bcshop.mapper.TbItemCatMapper;
import cn.brotherchun.bcshop.pojo.TbItemCat;
import cn.brotherchun.bcshop.pojo.TbItemCatExample;
import cn.brotherchun.bcshop.pojo.TbItemCatExample.Criteria;
import cn.brotherchun.bcshop.service.TbItemCatService;

@Service
public class TbItemCatServiceImpl implements TbItemCatService{

	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
	@Override
	public List<EasyUITreeNode> getTbItemCatList(Long parentId)
			throws Exception {
		// 1、根据parentId查询节点列表
		TbItemCatExample tbItemCatExample=new TbItemCatExample();
		//设置查询条件
		Criteria criteria = tbItemCatExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> tbItemCatList = tbItemCatMapper.selectByExample(tbItemCatExample);
		// 2、转换成EasyUITreeNode列表。
		List<EasyUITreeNode> nodeList=new ArrayList<EasyUITreeNode>();
		for(TbItemCat tbItemCat:tbItemCatList){
			EasyUITreeNode easyUITreeNode=new EasyUITreeNode();
			easyUITreeNode.setId(tbItemCat.getId());
			easyUITreeNode.setText(tbItemCat.getName());
			easyUITreeNode.setState(tbItemCat.getIsParent()?"closed":"open");
			//添加到列表
			nodeList.add(easyUITreeNode);
		}
		// 3、返回。
		return nodeList;
	}

}
