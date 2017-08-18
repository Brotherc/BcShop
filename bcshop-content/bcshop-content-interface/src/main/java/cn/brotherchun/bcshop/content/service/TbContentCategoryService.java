package cn.brotherchun.bcshop.content.service;

import java.util.List;

import cn.brotherchun.bcshop.common.pojo.EasyUITreeNode;
import cn.brotherchun.bcshop.common.utils.BcResult;

public interface TbContentCategoryService {
	/**
	 * 根据内容分类父id获取子内容分类列表
	 * @param parentId 内容分类父id
	 * @return
	 * @throws Exception
	 */
	public List<EasyUITreeNode> findTbContentCategoryList(Long parentId) throws Exception;
	
	/**
	 * 根据内容分类父id和添加的内容分类名字添加内容分类
	 * @param parentId 内容分类父id
	 * @param name 内容分类名字
	 * @return
	 * @throws Exception
	 */
	public BcResult addContentCategory(Long parentId,String name) throws Exception;
	
	/**
	 * 根据内容分类id修改内容分类信息
	 * @param contentCategoryId 内容分类id
	 * @param name 内容分类名字
	 * @return
	 * @throws Exception
	 */
	public BcResult updateContentCategory(Long contentCategoryId,String name) throws Exception;
	
	/**
	 * 根据内容分类id删除内容分类信息
	 * @param contentCategoryId 内容分类id
	 * @return
	 * @throws Exception
	 */
	public BcResult deleteContentCategory(Long contentCategoryId) throws Exception;
}
