package cn.brotherchun.bcshop.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.brotherchun.bcshop.common.pojo.EasyUIDataGridResult;
import cn.brotherchun.bcshop.common.utils.BcResult;
import cn.brotherchun.bcshop.common.utils.IDUtils;
import cn.brotherchun.bcshop.mapper.TbItemDescMapper;
import cn.brotherchun.bcshop.mapper.TbItemMapper;
import cn.brotherchun.bcshop.mapper.TbItemParamItemMapper;
import cn.brotherchun.bcshop.pojo.TbItem;
import cn.brotherchun.bcshop.pojo.TbItemDesc;
import cn.brotherchun.bcshop.pojo.TbItemExample;
import cn.brotherchun.bcshop.pojo.TbItemParamItem;
import cn.brotherchun.bcshop.pojo.TbItemParamItemExample;
import cn.brotherchun.bcshop.pojo.TbItemParamItemExample.Criteria;
import cn.brotherchun.bcshop.service.TbItemService;

@Service
public class TbItemServiceImpl implements TbItemService{

	@Autowired
	private TbItemMapper tbItemMapper;
	
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Resource
	private Destination topicDestination;
	
	@Override
	public TbItem testGetTbItemById(Long id) throws Exception {
		return tbItemMapper.selectByPrimaryKey(id);
	}

	@Override
	public EasyUIDataGridResult getTbItemList(int page, int rows)
			throws Exception {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		List<TbItem> tbItemList = tbItemMapper.selectByExample(new TbItemExample());
		//取分页信息
		PageInfo<TbItem> pageInfo=new PageInfo<>(tbItemList);
		long total = pageInfo.getTotal();
		//创建返回结果对象
		EasyUIDataGridResult easyUIDataGridResult=new EasyUIDataGridResult(total, tbItemList);
		return easyUIDataGridResult;
	}

	@Override
	public BcResult addTbItem(TbItem tbItem, String desc,String paramData) throws Exception {
		// 1、生成商品id
		final long id = IDUtils.genItemId();
		// 2、补全TbItem对象的属性
		tbItem.setId(id);
		//商品状态，1-正常，2-下架，3-删除
		tbItem.setStatus((byte)1);
		tbItem.setCreated(new Date());
		tbItem.setUpdated(new Date());
		// 3、向商品表插入数据
		tbItemMapper.insert(tbItem);
		// 4、创建一个TbItemDesc对象
		TbItemDesc tbItemDesc=new TbItemDesc();
		// 5、补全TbItemDesc的属性
		tbItemDesc.setItemId(id);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(new Date());
		tbItemDesc.setUpdated(new Date());
		// 6、向商品描述表插入数据
		tbItemDescMapper.insert(tbItemDesc);
		//7、向商品规格参数表插入数据
		TbItemParamItem tbItemParamItem=new TbItemParamItem();
		tbItemParamItem.setItemId(id);
		tbItemParamItem.setParamData(paramData);
		tbItemParamItem.setCreated(new Date());
		tbItemParamItem.setUpdated(new Date());
		tbItemParamItemMapper.insert(tbItemParamItem);
		//发送商品添加消息
		//发送商品添加消息
		jmsTemplate.send(topicDestination, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(id + "");
				return textMessage;
			}
		});
		//返回成功
		return BcResult.ok();
	}

	@Override
	public BcResult getTbitemDescByTbItemId(Long tbItemId) throws Exception {
		TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(tbItemId);
		if(tbItemDesc!=null){
			return BcResult.build(200, "OK", tbItemDesc);
		}
		return BcResult.build(-1, "ERROR");
	}

	@Override
	public BcResult getTbItemById(Long tbItemId) throws Exception {
		TbItem tbItem = tbItemMapper.selectByPrimaryKey(tbItemId);
		if(tbItem!=null){
			TbItemParamItemExample tbItemParamItemExample=new TbItemParamItemExample();
			Criteria criteria = tbItemParamItemExample.createCriteria();
			criteria.andItemIdEqualTo(tbItemId);
			List<TbItemParamItem> tbItemParamItemList = tbItemParamItemMapper.selectByExampleWithBLOBs(tbItemParamItemExample);
			if(tbItemParamItemList!=null&&tbItemParamItemList.size()>0){
				TbItemParamItem tbItemParamItem = tbItemParamItemList.get(0);
				tbItem.setParamData(tbItemParamItem.getParamData());
			}
			return BcResult.build(200, "OK", tbItem);
		}
		return BcResult.build(-1, "ERROR");
	}

	@Override
	public BcResult updateTbItem(TbItem tbItem, String desc,String itemParams) throws Exception {
		Long id = tbItem.getId();
		//查询对应修改的商品信息
		TbItem tbItemDB = tbItemMapper.selectByPrimaryKey(id);
		//修改页面传过来的商品修改信息
		tbItemDB.setTitle(tbItem.getTitle());
		tbItemDB.setSellPoint(tbItem.getSellPoint());
		tbItemDB.setPrice(tbItem.getPrice());
		tbItemDB.setNum(tbItem.getNum());
		tbItemDB.setBarcode(tbItem.getBarcode());
		tbItemDB.setImage(tbItem.getImage());
		tbItemDB.setCid(tbItem.getCid());
		//添加修改商品的更新日期
		tbItemDB.setUpdated(new Date());
		//修改商品信息
		tbItemMapper.updateByPrimaryKey(tbItemDB);
		//查询对应商品的描述信息
		TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(id);
		//更新商品描述
		tbItemDesc.setItemDesc(desc);
		//修改商品描述的更新日期
		tbItemDesc.setUpdated(new Date());
		//修改商品描述
		tbItemDescMapper.updateByPrimaryKey(tbItemDesc);
		
		//修改商品规格参数
		TbItemParamItemExample itemParamItemExample=new TbItemParamItemExample();
		Criteria criteria = itemParamItemExample.createCriteria();
		criteria.andItemIdEqualTo(id);
		List<TbItemParamItem> tbItemParamItemList = tbItemParamItemMapper.selectByExampleWithBLOBs(itemParamItemExample);
		if(tbItemParamItemList!=null&&tbItemParamItemList.size()>0){
			TbItemParamItem tbItemParamItem = tbItemParamItemList.get(0);
			tbItemParamItem.setParamData(itemParams);
			tbItemParamItemMapper.updateByPrimaryKeyWithBLOBs(tbItemParamItem);
		}
		return BcResult.ok();
	}

	@Override
	public BcResult instockTbItem(Long tbItemId) throws Exception {
		//查询对应下架的商品信息
		TbItem tbItem = tbItemMapper.selectByPrimaryKey(tbItemId);
		//修改商品状态为下架
		tbItem.setStatus((byte)2);
		//更新商品信息
		tbItemMapper.updateByPrimaryKey(tbItem);
		return BcResult.ok();
	}

	@Override
	public BcResult reshelfTbItem(Long tbItemId) throws Exception {
		//查询对应上架的商品信息
		TbItem tbItem = tbItemMapper.selectByPrimaryKey(tbItemId);
		//修改商品状态为正常
		tbItem.setStatus((byte)1);
		//更新商品信息
		tbItemMapper.updateByPrimaryKey(tbItem);
		return BcResult.ok();
	}

	@Override
	public BcResult deleteTbItem(Long tbItemId) throws Exception {
		//查询对应删除的商品信息
		TbItem tbItem = tbItemMapper.selectByPrimaryKey(tbItemId);
		//修改商品状态为删除
		tbItem.setStatus((byte)3);
		//更新商品信息
		tbItemMapper.updateByPrimaryKey(tbItem);
		return BcResult.ok();
	}

}
