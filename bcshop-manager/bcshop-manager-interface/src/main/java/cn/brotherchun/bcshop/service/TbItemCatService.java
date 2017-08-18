package cn.brotherchun.bcshop.service;

import java.util.List;

import cn.brotherchun.bcshop.common.pojo.EasyUITreeNode;

public interface TbItemCatService {
	/**
	 * 根据父菜单id获取菜单分类子菜单
	 * @param parentId 父菜单id
	 * @return 树节点列表
	 * @throws Exception
	 */
	public List<EasyUITreeNode> getTbItemCatList(Long parentId) throws Exception;
	
}
