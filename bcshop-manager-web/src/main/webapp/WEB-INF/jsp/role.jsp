<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="roleList" title="角色列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/role/list',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">角色ID</th>
            <th data-options="field:'rolename',width:200">角色名</th>
            <th data-options="field:'roledesc',width:100">角色描述</th>
            <th data-options="field:'shortcode',width:100">简码</th>
            <th data-options="field:'rolecode',width:70">角色编码</th>
        </tr>
    </thead>
</table>
<div class="easyui-window" title="添加角色" id="addRoleWindow" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/role-add'" style="width:32%;height:55%;padding:10px;">
</div>
<div class="easyui-window" title="修改用户" id="roleEditWindow" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/role-edit'" style="width:32%;height:55%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var roleList = $("#roleList");
    	var sels = roleList.datagrid("getSelections");
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
        	$("#addRoleWindow").window({
        		onLoad :function(){
        			//清除数据
        			$('#roleAddForm').form('reset');
        		}
        	}).window("open");
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个角色才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个角色!');
        		return ;
        	}
        	
        	$("#roleEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#roleList").datagrid("getSelections")[0];
        			$("#roleEditForm").form("load",data);
        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中角色!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的角色吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/role/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除角色成功!',undefined,function(){
            					$("#roleList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    }];
</script>