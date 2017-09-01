<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>bc商城后台管理系统</title>
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/gray/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="css/e3.css" />
<link rel="stylesheet" type="text/css" href="css/default.css" />
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<style type="text/css">
	.content {
		padding: 10px 10px 10px 10px;
	}
</style>
</head>
<body class="easyui-layout">
    <!-- 头部标题 -->
	<div data-options="region:'north',border:false" style="height:60px; padding:5px; background:#F3F3F3"> 
		<span class="northTitle">bc商城后台管理系统</span>
	    <span class="loginInfo">
	    	登录用户：${user.username}&nbsp;&nbsp;
	    	角色：
	    	<c:if test="${user.type==0 }">系统管理员</c:if>
	    <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_kzmbMenu',iconCls:'icon-help'">控制面板</a>
	    </span>
	</div>
	<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
		<div onclick="changePwd();">修改密码</div>
		<div class="menu-sep"></div>
		<div onclick="showAbout();">联系管理员</div>
		<div class="menu-sep"></div>
		<div onclick="logout();">退出系统</div>
	</div>
    <div data-options="region:'west',title:'菜单',split:true" style="width:180px;">
    	<ul id="menu" class="easyui-tree" style="margin-top: 10px;margin-left: 5px;">
         	<li>
         		<span>用户管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'user'}">用户信息管理</li>
	         		<li data-options="attributes:{'url':'role'}">用户角色管理</li>
	         	</ul>
         	</li>
         	<li>
         		<span>商品管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'item-add'}">新增商品</li>
	         		<li data-options="attributes:{'url':'item-list'}">查询商品</li>
	         		<li data-options="attributes:{'url':'item-param-list'}">规格参数</li>
	         	</ul>
         	</li>
         	<li>
         		<span>网站内容管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'content-category'}">内容分类管理</li>
	         		<li data-options="attributes:{'url':'content'}">内容管理</li>
	         	</ul>
         	</li>
         	<li>
         		<span>索引库管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'index-item'}">solr索引库维护</li>
	         	</ul>
         	</li>
         	<li>
         		<span>爬虫管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'crawlitem-list'}">爬取商品</li>
	         	</ul>
         	</li>
         </ul>
    </div>
    <div data-options="region:'center',title:''">
    	<div id="tabs" class="easyui-tabs">
		    <div title="首页" style="padding:20px;">
		        	
		    </div>
		</div>
    </div>
    <!-- 页脚信息 -->
	<div data-options="region:'south',border:false" style="height:20px; background:#F3F3F3; padding:2px; vertical-align:middle;">
		<span id="sysVersion">系统版本：V1.0</span>
	    <span id="nowTime"></span>
	</div>
	
	<!--修改密码窗口-->
    <div id="changePwdWindow" class="easyui-window" title="修改密码" collapsible="false" minimizable="false" modal="true" closed="true" resizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 200px; padding: 5px;
        background: #fafafa">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
               <form id="changePwdForm">
	                <table cellpadding=3>
	                	<tr>
	                        <td>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
	                        <td><input  required="true" data-options="validType:'length[4,6]'" id="txtOldPass" type="Password" class="easyui-textbox" /></td>
	                    </tr>
	                    <tr>
	                        <td>新&nbsp;&nbsp;密&nbsp;&nbsp;码：</td>
	                        <td><input  required="true" data-options="validType:'length[4,6]'" id="txtNewPass" type="Password" class="easyui-textbox" /></td>
	                    </tr>
	                    <tr>
	                        <td>确认密码：</td>
	                        <td><input required="true" data-options="validType:'length[4,6]'" id="txtRePass" type="Password" class="easyui-textbox" /></td>
	                    </tr>
	                </table>
               </form>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >确定</a> 
                <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>
<script type="text/javascript">

$("#btnCancel").click(function(){
	$('#changePwdWindow').window('close');
});

//为确定按钮绑定事件
$("#btnEp").click(function(){
	//进行表单校验
	var v = $("#changePwdForm").form("validate");
	if(v){
		var oldPwd = $("#txtOldPass").val();
		//表单校验通过，手动校验两次输入是否一致
		var newPwd = $("#txtNewPass").val();
		var newPwdTwo = $("#txtRePass").val();
		if(newPwd == newPwdTwo){
			//两次输入一致，发送ajax请求
			$.post("/manager/changePwd",{"oldPwd":oldPwd,"newPwd":newPwd,"newPwdTwo":newPwdTwo},function(data){
				if(data.status == 200){
					//修改成功，关闭修改密码窗口
					$.messager.alert("提示信息",data.msg,"success");
					$("#changePwdWindow").window("close");
					//重新登录
					location.href = '/manager/logout';
				}else{
					//修改密码失败，弹出提示
					$.messager.alert("提示信息",data.msg,"error");
				}
			});
		}else{
			//两次输入不一致，弹出错误提示
			$.messager.alert("提示信息","两次新密码输入不一致！","warning");
		}
	}
});

// 退出登录
function logout() {
	$.messager
	.confirm('系统提示','您确定要退出本次登录吗?',function(isConfirm) {
		if (isConfirm) {
			location.href = '/manager/logout';
		}
	});
}

// 修改密码
function changePwd() {
	//打开修改密码窗口
	$('#changePwdWindow').window('open');
}
// 版权信息
function showAbout(){
	$.messager.alert("BCv1.0","管理员邮箱: lcs@BrotherChun.cn");
}

$(function(){
	$('#menu').tree({
		onClick: function(node){
			if($('#menu').tree("isLeaf",node.target)){
				var tabs = $("#tabs");
				var tab = tabs.tabs("getTab",node.text);
				if(tab){
					tabs.tabs("select",node.text);
				}else{
					tabs.tabs('add',{
					    title:node.text,
					    href: node.attributes.url,
					    closable:true,
					    bodyCls:"content"
					});
				}
			}
		}
	});
});
setInterval("document.getElementById('nowTime').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);
</script>
</body>
</html>