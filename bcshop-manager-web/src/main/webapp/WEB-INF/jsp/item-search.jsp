<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="itemSearchForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>商品类目:</td>
	            <td>
	            	<a href="javascript:void(0)" class="easyui-linkbutton selectItemCat">选择类目</a>
	            	<input type="hidden" name="cid" style="width: 280px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td>商品标题:</td>
	            <td><input class="easyui-textbox" type="text" name="title" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>商品卖点:</td>
	            <td><input class="easyui-textbox" name="sellPoint" data-options="multiline:true,validType:'length[0,150]'" style="height:60px;width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>商品价格:</td>
	            <td><input class="easyui-numberbox" type="text" name="priceView" data-options="min:1,max:99999999,precision:2" />
	            	<input type="hidden" name="price"/>
	            </td>
	        </tr>
	        <tr>
	            <td>库存数量:</td>
	            <td><input class="easyui-numberbox" type="text" name="num" data-options="min:1,max:99999999,precision:0" /></td>
	        </tr>
	        <tr>
	            <td>条形码:</td>
	            <td>
	                <input class="easyui-textbox" type="text" name="barcode" data-options="validType:'length[1,30]'" />
	            </td>
	        </tr>
	    </table>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchForm()">搜索</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="outputForm()">导出</a>
	</div>
</div>
<script type="text/javascript">

	$(function(){
		$('#itemSearchForm').form('reset');
		//初始化类目选择和图片上传器
		E3.init({fun:function(node){
			//根据商品的分类id取商品 的规格模板，生成规格信息。第四天内容。
			E3.changeItemParam(node, "itemSearchForm");
		}});
	});
	//提交表单
	function searchForm(){
		//有效性验证
		if(!$('#itemSearchForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		//取商品价格，单位为“分”
		$("#itemSearchForm [name=price]").val(eval($("#itemSearchForm [name=priceView]").val()) * 100);
		
		//datagrid的方法load方法要求传入json数据，最终将 json转成key/value数据传入action
		//将form表单数据提取出来，组成一个json
		var formdata = $("#itemSearchForm").serializeJson();
		$('#itemList').datagrid('load',formdata);
	}
	
	function outputForm(){
		$.post("/tbitem/output",$("#itemSearchForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','导出商品成功!',undefined,function(){
					$("#itemList").datagrid("reload");
				});
			}
		});
	}
</script>
