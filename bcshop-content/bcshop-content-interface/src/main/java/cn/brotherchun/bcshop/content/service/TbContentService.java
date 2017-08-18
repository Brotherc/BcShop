package cn.brotherchun.bcshop.content.service;

import java.util.List;

import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.pojo.TbContent;

public interface TbContentService {
	/**
	 * 根据内容分类id及分页信息获取内容信息
	 * @param tbContentCategoryId 内容分类id
	 * @param page 第几页
	 * @param rows 每页个数
	 * @return
	 * @throws Exception
	 */
	public EasyUIDataGridResult findTbContentListByTbContentCategoryId(Long tbContentCategoryId,int page,int rows) throws Exception;

	/**
	 * 根据内容分类id获取内容信息
	 * @param tbContentCategoryId 内容分类id
	 * @return
	 * @throws Exception
	 */
	public List<TbContent> findTbContentListByTbContentCategoryId(Long tbContentCategoryId) throws Exception;
	
	/**
	 * 添加内容信息
	 * @param tbContent 内容信息
	 * @return
	 * @throws Exception
	 */
	public BcResult addTbContent(TbContent tbContent) throws Exception;
	
	/**
	 * 修改内容信息
	 * @param tbContent 内容信息
	 * @return
	 * @throws Exception
	 */
	public BcResult updateTbContent(TbContent tbContent) throws Exception;
	
	/**
	 * 根据内容信息id删除内容信息
	 * @param tbContentId 内容信息id
	 * @return
	 * @throws Exception
	 */
	public BcResult deleteTbContent(Long tbContentId) throws Exception;
}
