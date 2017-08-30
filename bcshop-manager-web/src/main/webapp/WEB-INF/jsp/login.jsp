<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/gray/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="css/e3.css" />
<link rel="stylesheet" type="text/css" href="css/default.css" />
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</head>
<body style="background-color: #F3F3F3">
    <form class="easyui-dialog" id="loginform" title="用户登录" data-options="closable:false,draggable:false" style="width:400px;height:350px;padding:10px;" method="post">
       	<div style="margin-left: 50px;margin-top: 50px;">
       		<div style="margin-bottom:20px;">
	            <div>
	            	用户名: <input name="username" class="easyui-textbox" data-options="required:true" style="width:200px;height:32px" value="admin"/>
	            </div>
	        </div>
	        <div style="margin-bottom:20px">
	            <div>
	            	密&nbsp;&nbsp;&nbsp;码: <input name="password" class="easyui-textbox" type="password" style="width:200px;height:32px" data-options="required:true" value="admin"/>
	            </div>
	        </div>
	        <div style="margin-bottom:20px">
	            <div>
	            	验证码: <input name="validatecode" class="easyui-textbox" style="width:90px;height:32px" data-options="required:true" />
	            	<img id="loginform:vCode" src="/validatecode"
						onclick="javascript:document.getElementById('loginform:vCode').src='/validatecode?'+Math.random();" align="center" style="height:34px;margin-left:20px"/>
	            </div>
	        </div>
	        <div>
	            <a id="login" class="easyui-linkbutton" iconCls="icon-ok" style="width:100px;height:32px;margin-left: 50px">登录</a>
	        </div>
       	</div>
    </form>
    
    <script type="text/javascript">
    	$("#login").click(function(){
/*     		var username = $("[name=username]").val();
    		var password = $("[name=password]").val();
    		
    		if(username!="admin" || password!="admin"){
    			$.messager.alert('错误',"用户名密码不正确！");
    			return ; */
    		//有效性验证
    		if(!$('#loginform').form('validate')){
    			$.messager.alert('提示','表单还未填写完成!');
    			return ;
    		}
    		$.post("/manager/login",$("#loginform").serialize(), function(data){
    			$.messager.alert('提示',data.msg);
    			if(data.status == 200){
    	    		window.location.href="/index";
    			}
    		});

    	});
    </script>
</body>
</html>