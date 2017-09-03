<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="padding:10px 10px 10px 10px">
	<form id="roleEditForm" class="roleEditForm" method="post">
		<input type="hidden" name="id"/>
	    <table cellpadding="5">
	        <tr>
	            <td>角色名:</td>
	            <td><input class="easyui-textbox" type="text" name="rolename" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>角色描述:</td>
	            <td><input class="easyui-textbox" type="text" name="roledesc" data-options="required:true,multiline:true" style="width: 280px;heigth:100px;"></input></td>
	        </tr>
	        <tr>
	            <td>简码:</td>
	           	<td>
	            	<input class="easyui-textbox" data-options="editable:false,required:true" name="shortcode" >
	            </td>
	        </tr>
	        <tr>
	            <td>角色编码:</td>
	           	<td>
	            	<input class="easyui-textbox" data-options="editable:false,required:true" name="rolecode" >
	            </td>
	        </tr>
	    </table>
	</form>
	<div style="padding:5px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="roleEditPage.submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="roleEditPage.clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">

var roleEditPage = {
		submitForm : function(){
			if(!$('#roleEditForm').form('validate')){
				$.messager.alert('提示','表单还未填写完成!');
				return ;
			}
			
			$.post("/role/edit",$("#roleEditForm").serialize(), function(data){
				if(data.status == 200){
					$.messager.alert('提示','修改角色成功!');
					$("#roleList").datagrid("reload");
					E3.closeCurrentWindow();
				}	
				else{
					$.messager.alert('提示',data.msg);
				}
			});
		},
		clearForm : function(){
			$('#roleEditForm').form('reset');
		}
};

</script>