<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="padding:10px 10px 10px 10px">
	<form id="manageruserAddForm" class="manageruserForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>用户名:</td>
	            <td><input class="easyui-textbox" type="text" name="username" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>密码:</td>
	            <td><input class="easyui-textbox" type="password" name="password" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>真实姓名:</td>
	            <td><input class="easyui-textbox" type="text" name="nickname" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>手机:</td>
	            <td><input class="easyui-textbox" type="text" name="phone" data-options="validType:'phone'" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>邮箱:</td>
	            <td><input class="easyui-textbox" type="text" name="email" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>状态:</td>
	           	<td>
	            	<input class="easyui-combobox" data-options="editable:false,required:true,url:'/manageruser/statuslist',valueField:'dictcode',textField:'info'" name="status" >
	            </td>
	        </tr>
	        <tr>
	            <td>类型:</td>
	            <td>
	            	<input class="easyui-combobox" data-options="mode:'remote',required:true,url:'/manageruser/rolelist',valueField:'id',textField:'rolename'" name="type">
	            </td>
	        </tr>
	    </table>
	</form>
	<div style="padding:5px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="manageruserAddPage.submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="manageruserAddPage.clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	var manageruserAddPage  = {
			submitForm : function (){

				if(!$('#manageruserAddForm').form('validate')){
					$.messager.alert('提示','表单还未填写完成!');
					return ;
				}
				
				var reg = /^1[3|4|5|7|8][0-9]{9}$/;
				//扩展手机号校验规则
				$.extend($.fn.validatebox.defaults.rules, { 
					phone: { 
						validator: function(value,param){ 
							return reg.test(value);
						}, 
						message: '手机号输入有误！' 
					}
				});
				
				$.post("/manageruser/save",$("#manageruserAddForm").serialize(), function(data){
					if(data.status == 200){
						$.messager.alert('提示','新增用户成功!');
    					$("#manageruserList").datagrid("reload");
    					E3.closeCurrentWindow();
					}
					else{
						$.messager.alert('提示',data.msg);
					}
					
				});
			},
			clearForm : function(){
				$('#manageruserAddForm').form('reset');
			}
	};
</script>
