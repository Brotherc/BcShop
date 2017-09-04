<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	//***********按钮**************
	$('#submitbtn').linkbutton({   
		iconCls: 'icon-ok'  
	});  
	$('#closebtn').linkbutton({   
		iconCls: 'icon-cancel'  
	});
});
//文件导入提交
function itemimport(){
	
	$.ajax({
		url:'/tbitem/import',
		type:'POST',
		data:new FormData($('#itemimportForm')[0]),
		async:false,
		cache:false,
		contentType:false,
		processData:false,
		success:function(data){
			$.messager.alert('提示',data.msg);
			$("#itemImportWindow").window('close');
			$("#itemList").datagrid("reload");
		},
		error:function(data){
			$.messager.alert('提示',data.msg);
		}
	});
}
</script>
<style type="text/css">
	.grid {
		BORDER-BOTTOM: #D3D3D3 1px solid; BORDER-LEFT: #D3D3D3 1px solid; BORDER-COLLAPSE: collapse; BORDER-TOP: #D3D3D3 1px solid; BORDER-RIGHT: #D3D3D3 1px solid
	}
	.toptable {
		WIDTH: 100%; BACKGROUND: #eee
	}
	.category {
		 COLOR: #003373; FONT-WEIGHT: bold
	}

</style>

<form id="itemimportForm" name="itemimportForm" action="/tbitem/import" method="post" enctype="multipart/form-data">
	<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" bgColor=#D3D3D3>
		<TBODY>
			<TR>
				<TD background=images/r_0.gif width="100%">
					<TABLE cellSpacing=0 cellPadding=0 width="100%">
						<TBODY>
							<TR>
								<TD>&nbsp;商品信息导入</TD>
								<TD align=right>&nbsp;</TD>
							</TR>
						</TBODY>
					</TABLE>
				</TD>
			</TR>
			<TR>
				<TD>
					<TABLE class="toptable grid" border=1 cellSpacing=1 cellPadding=4
						align=center>
						<TBODY>
							
							<TR>
								<TD height=30 align=right>导入说明：</TD>
								<TD >
								1、导入文件为Excel 97-2003版本，文件扩展名为.xls，如果使用高版本的Excel请另存为Excel 97-2003版本。
								<br>2、点击 <a class="blue" href="${baseurl}template/ypxx_template.xls"><u>药品信息模板</u></a> 下载，并按照说明录入药品信息。
								<br>3、导入文件内容填写完毕请在下方选择导入文件，点击 导入按钮。
								</TD>
							</TR>
							<TR>
								<TD height=30 align=right>选择导入文件</TD>
								<TD style="background:#fff;">
								<input type="file" name="tbItemImportFile" />					
								</TD>
							</TR>
							<TR>
								
								<TD colspan=2  align=center style="background:#fff;">
									<a href="javascript:void(0)" id="submitbtn" class="easyui-linkbutton" onclick="itemimport()">提交</a>
								</TD>
							</TR>
						</TBODY>
					</TABLE>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
</form>


