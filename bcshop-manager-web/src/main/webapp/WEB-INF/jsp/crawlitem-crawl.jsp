<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
		<div style="margin-bottom:20px">
			url:
			<input class="easyui-textbox" style="width:100%;height:32px" id="url">
		</div>
		
		<div>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" style="width:20%;height:32px" onclick="crawlItem()">爬取</a>
			目前仅限爬取京东商品
		</div>
</div>
<script type="text/javascript">
		function crawlItem(){
			var url = "/crawltbitem/crawl";
			var crawlurl=$("#url").val();
			$.post(url,{"url":crawlurl},function(data){
				if(data.status == 200){
					$.messager.alert('提示','爬取商品成功!',undefined,function(){
						$(".panel-tool-close").click();
						$("#crawlItemList").datagrid("reload");
					});
				}else{
					var message=data.msg;
					$.messager.alert('提示',message,undefined,function(){
						$(".panel-tool-close").click();
						$("#crawlItemList").datagrid("reload");
					});
				}
			});
		};
</script>
