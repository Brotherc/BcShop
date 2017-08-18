package cn.brotherchun.bcshop.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.brotherchun.bcshop.common.pojo.EasyUITreeNode;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.content.service.TbContentCategoryService;
import cn.brotherchun.bcshop.mapper.TbContentCategoryMapper;
import cn.brotherchun.bcshop.pojo.TbContentCategory;
import cn.brotherchun.bcshop.pojo.TbContentCategoryExample;
import cn.brotherchun.bcshop.pojo.TbContentCategoryExample.Criteria;

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService{

	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	
	@Override
	public List<EasyUITreeNode> findTbContentCategoryList(Long parentId)
			throws Exception {
		TbContentCategoryExample tbContentCategoryExample=new TbContentCategoryExample();
		Criteria criteria = tbContentCategoryExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> tbContentCategoryList = tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
		List<EasyUITreeNode> nodeList=new ArrayList<>();
		for(TbContentCategory contentCategory:tbContentCategoryList){
			EasyUITreeNode node=new EasyUITreeNode();
			node.setId(contentCategory.getId());
			node.setText(contentCategory.getName());
			node.setState(contentCategory.getIsParent()?"closed":"open");
			nodeList.add(node);
		}
		return nodeList;
	}

	@Override
	public BcResult addContentCategory(Long parentId, String name)
			throws Exception {
		TbContentCategory tbContentCategory=new TbContentCategory();
		tbContentCategory.setParentId(parentId);
		tbContentCategory.setName(name);
		tbContentCategory.setStatus(1);
		tbContentCategory.setSortOrder(1);
		tbContentCategory.setIsParent(false);
		tbContentCategory.setCreated(new Date());
		tbContentCategory.setUpdated(new Date());
		tbContentCategoryMapper.insert(tbContentCategory);
		TbContentCategory contentCategoryParent = tbContentCategoryMapper.selectByPrimaryKey(parentId);
		if(!contentCategoryParent.getIsParent()){
			contentCategoryParent.setIsParent(true);
			tbContentCategoryMapper.updateByPrimaryKey(contentCategoryParent);
		}
		return BcResult.ok(tbContentCategory);
	}

	@Override
	public BcResult updateContentCategory(Long contentCategoryId, String name)
			throws Exception {
		TbContentCategory contentCategory = tbContentCategoryMapper.selectByPrimaryKey(contentCategoryId);
		contentCategory.setName(name);
		contentCategory.setUpdated(new Date());
		tbContentCategoryMapper.updateByPrimaryKey(contentCategory);
		return BcResult.ok();
	}

	@Override
	public BcResult deleteContentCategory(Long contentCategoryId)
			throws Exception {
		//获取删除的内容分类信息
		TbContentCategory contentCategory = tbContentCategoryMapper.selectByPrimaryKey(contentCategoryId);
		//如果删除的是父节点，则报错
		if(contentCategory.getIsParent())
			return BcResult.build(-1, "ERROR");
		//获取删除的内容分类的父节点id
		Long parentId = contentCategory.getParentId();
		//删除内容分类
		tbContentCategoryMapper.deleteByPrimaryKey(contentCategoryId);
		//查询所有父节点id为已删除的内容分类父节点id的内容分类
		TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
		Criteria criteria = tbContentCategoryExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> contentCategoryList = tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
		//若果没有，则更新父节点为子节点
		if(contentCategoryList==null||contentCategoryList.size()<1){
			TbContentCategory contentCategoryParent = tbContentCategoryMapper.selectByPrimaryKey(parentId);
			contentCategoryParent.setIsParent(false);
			contentCategoryParent.setUpdated(new Date());
			tbContentCategoryMapper.updateByPrimaryKey(contentCategoryParent);
		}
		return BcResult.ok();
	}

}
