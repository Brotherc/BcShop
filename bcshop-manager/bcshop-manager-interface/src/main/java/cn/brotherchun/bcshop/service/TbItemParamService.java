package cn.brotherchun.bcshop.service;


import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;

public interface TbItemParamService {
	/**'
	 * 获取商品规格参数
	 * @return
	 * @throws Exception
	 */
	public EasyUIDataGridResult findTbItemParamList(int page,int rows) throws Exception;
}
