<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="crawlItemList" title="爬取商品列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/crawltbitem/list',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">商品ID</th>
        	<th data-options="field:'crawlId',width:60,hidden:true">爬取商品ID</th>
            <th data-options="field:'title',width:200">商品标题</th>
            <th data-options="field:'cid',width:100">叶子类目</th>
            <th data-options="field:'sellPoint',width:100">卖点</th>
            <th data-options="field:'price',width:70,align:'right',formatter:E3.formatPrice">价格</th>
            <th data-options="field:'num',width:70,align:'right'">库存数量</th>
            <th data-options="field:'barcode',width:70,align:'right'">二维码</th>
            <th data-options="field:'itemDesc',width:100,hidden:true">描述</th>
            <th data-options="field:'image',width:100,hidden:true">图片</th>
            <th data-options="field:'status',width:60,align:'center',formatter:E3.formatItemStatus">状态</th>
            <th data-options="field:'created',width:130,align:'center',formatter:E3.formatDateTime">创建日期</th>
            <th data-options="field:'updated',width:130,align:'center',formatter:E3.formatDateTime">更新日期</th>
            
        </tr>
    </thead>
</table>
<div id="itemSaveWindow" class="easyui-window" title="保存商品" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/item-add'" style="width:80%;height:80%;padding:10px;">
</div>
<div id="itemCrawlWindow" class="easyui-window" title="爬取商品" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/crawlitem-crawl'" style="width:30%;height:35%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var itemList = $("#crawlItemList");
    	var sels = itemList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].crawlId);
    	}
    	ids = ids.join(",");
    	return ids;
    };
    
    var toolbar = [{
        text:'爬取商品',
        iconCls:'icon-remove',
        handler:function(){
        	//TODO 显示输入框供输入url，发送请求，抓取，重新加载列表页
        	$("#itemCrawlWindow").window({
        		onLoad :function(){
        	
        		}
        		
        	}).window("open");
        }
    },{
        text:'保存商品',
        iconCls:'icon-add',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个商品才能保存!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个商品!');
        		return ;
        	}
        	
        	$("#itemSaveWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#crawlItemList").datagrid("getSelections")[0];
         			data.priceView = E3.formatPrice(data.price);
        			$("#itemAddForm").form("load",data);
        			
        			
        			E3.init({
        				"pics" : data.image,
        				"cid" : data.cid,
        				fun:function(node){
        					E3.changeItemParam(node, "itemAddForm");
        				}
        			});
        			
        			// 加载商品描述
        			itemAddEditor.html(data.itemDesc);
        		}
        	}).window("open");
        }
    }];
</script>