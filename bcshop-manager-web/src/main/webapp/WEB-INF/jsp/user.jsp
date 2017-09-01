<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="manageruserList" title="用户列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/manageruser/list',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60,hidden:true">用户ID</th>
        	<th data-options="field:'password',width:60,hidden:true">密码</th>
            <th data-options="field:'username',width:200">用户名</th>
            <th data-options="field:'nickname',width:100">真实姓名</th>
            <th data-options="field:'phone',width:100">手机号</th>
            <th data-options="field:'email',width:70">邮箱</th>
            <th data-options="field:'status',width:70,formatter:E3.formatManagerUserStatus">状态</th>
            <th data-options="field:'type',width:60,align:'center',formatter:E3.formatManagerUserType">类型</th>
            <th data-options="field:'created',width:130,align:'center',formatter:E3.formatDateTime">创建日期</th>
            <th data-options="field:'updated',width:130,align:'center',formatter:E3.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>
<div class="easyui-window" title="添加用户" id="addManageruserWindow" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/user-add'" style="width:32%;height:55%;padding:10px;">
</div>
<div class="easyui-window" title="修改用户" id="manageruserEditWindow" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/user-edit'" style="width:32%;height:55%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var manageruserList = $("#manageruserList");
    	var sels = manageruserList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbar = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	$("#addManageruserWindow").window({
        		onLoad :function(){
        			//清除数据
        			$('#manageruserAddForm').form('reset');
        		}
        	}).window("open");
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个用户才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个用户!');
        		return ;
        	}
        	
        	$("#manageruserEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#manageruserList").datagrid("getSelections")[0];
        			$("#manageruserEditForm").form("load",data);
        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中用户!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的用户吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/manageruser/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除用户成功!',undefined,function(){
            					$("#manageruserList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    }];
</script>