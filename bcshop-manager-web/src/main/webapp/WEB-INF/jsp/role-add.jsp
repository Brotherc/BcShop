<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="padding:10px 10px 10px 10px">
	<form id="roleAddForm" class="roleAddForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>角色名:</td>
	            <td><input class="easyui-textbox" type="text" name="rolename" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>角色描述:</td>
	            <td>
	            <input class="easyui-textbox" name="roledesc" data-options="required:true,multiline:true,validType:'length[0,150]'" style="height:60px;width: 280px;"></input>
	            </td>
	        </tr>
	    </table>
	</form>
	<div style="padding:5px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="roleAddPage.submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="roleAddPage.clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	var roleAddPage  = {
			submitForm : function (){

				if(!$('#roleAddForm').form('validate')){
					$.messager.alert('提示','表单还未填写完成!');
					return ;
				}
				
				$.post("/role/save",$("#roleAddForm").serialize(), function(data){
					if(data.status == 200){
						$.messager.alert('提示','新增角色成功!');
    					$("#roleList").datagrid("reload");
    					E3.closeCurrentWindow();
					}
					else{
						$.messager.alert('提示',data.msg);
					}
					
				});
			},
			clearForm : function(){
				$('#roleAddForm').form('reset');
			}
	};
</script>
