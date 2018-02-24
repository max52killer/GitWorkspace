<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <base href="http://<%=request.getServerName()+":" + request.getServerPort()+request.getContextPath()%>/"/>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>51cc</title>
 <!-- 导入EasyUI库 -->  
 <script type="text/javascript" src="js/jquery.min.js"></script>
 <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
 <script type="text/javascript" src="easyui/easyui-lang-zh_CN.js"></script>
 <script type="text/javascript" src="easyui/easyui-ext.js"></script>
 <link type="text/css" rel="stylesheet" href="easyui/themes/gray/easyui.css">
 <link type="text/css" rel="stylesheet" href="easyui/themes/icon.css">
  <script type="text/javascript">
   
     $().ready(function(){
    	
    	 
    	 //
    	 $("#data_table").treegrid({
    		idField : 'id',
    	    url : 'resource/list',
    	    treeField : "name",
    	    parentField : "pid",
    		fit : true,
    		border : false,
    		toolbar : "#tb",
    		fitColumns : true,
    		striped : true,
    		rownumbers : true,
    		singleSelect : true,    	
    		columns :[[    			
    			{title: "权限名称",field : "name",width: 150},
    			{title: "权限编码",field : "code",width: 100},
    			{title: "类型",field : "type",width: 100 },
    			{title: "URL",field : "url",width: 200},
    			{title: "描述",field : "remark",width: 200},
    			{title: "排序值",field : "sortno",width: 100},
    		]],    	
    		pagination : false,    		
    		onLoadSuccess : function() {
    			$("#data_table").treegrid("clearSelections");
    		} 		
    		 
    	 });
    	 
    	 //----------------
    	 
    	 $("#newForm").form({
    		 onSubmit: function() {   
    			   var ret =  $("#newForm").form("validate");  
    			   if(!ret) {  //校验失败
    				   return false;
    			   }
    			   $.messager.progress({msg : "正在添加..."}); //显示进度条，锁屏    			   
    		       return true;
    		    },   
    		 success:function(resp) {  
    			 var data = $.parseJSON(resp);  //字符串转JSON对象
    			 $.messager.progress("close"); //关闭进度条
        		 if(data.success) {
        			 //关闭对话框
        			 $("#addWin").dialog("close");
        			 //提示
        			 parent.$.messager.show({title:"提示",msg:"添加成功",timeout:3000}); 
        			 //重新加载当前页数据
        			 $("#data_table").treegrid("load");
        			 
        		 } else {
        			 $.messager.alert("提示",data.msg,"warning");
        		 } 
    		 }   

    	 })
    	 
    	 $("#addWin").dialog({
    		 width : 500,
    		 height : 300,
    		 title : "新增子菜单",
    		 iconCls : "icon-add",
    		 modal : true,
    		 closed : true,
    		 buttons : [
    			 {text : "确定",iconCls:'icon-ok',handler : function() {
    				
    				 //$.messager.alert('提示','正在添加...')
    				 //ajax提交表单
    				$("#newForm").submit(); 
    			 }},
    			 {text : "取消",iconCls:'icon-cancel',handler : function() {$("#addWin").dialog("close");}}    			 
    		 ]
    		 
    	 });
    	  
     }) 	
     
     //------------------------------------------------------------
     
     function doAdd() {
    	 var sel = $("#data_table").treegrid("getSelected");
    	 if(sel == null) {
    		 $.messager.alert("提示","请选择父菜单！");
    		 return;
    	 }
    	 
    	 if(sel.type == "action") {
    		 $.messager.alert("提示","不能在操作按钮下添加子菜单!");
    		 return;
    	 }
    	 
    	 $("#newForm").form("clear");
    	 
    	 $("#hidden_pid").val(sel.id);
    	 
    	 $("#addWin").dialog("open");
    	 
     }
   
     
  </script>
</head>
<body >
  
   <table id="data_table"></table>
      
    <div id="tb">
	   <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="doAdd()">新增子菜单</a>
	   <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="">修改</a>
	   <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="">删除</a>
	</div>
    
    
    
    <div id="addWin" style="padding:15px;">  
      <form id="newForm" action="resource/add" method="post">  
         <input type="hidden" name="parent.id" id="hidden_pid"/>      
	     <table border="0" width="95%" align="center">
	       <tr>
	          <td>编码:</td>
	          <td><input type="text" name="code" class="easyui-validatebox" data-options="required:true,validType:'length[5,10]'"/></td>
	       </tr>
	       <tr>
	          <td>名称:</td>
	          <td><input type="text" name="name" class="easyui-validatebox" data-options="required:true"/></td>
	       </tr>
	       <tr>
	          <td>类型:</td>
	          <td>
	          <input type="radio"  name="type" value="menu" checked="checked"/>菜单
	          <input type="radio"  name="type" value="action" />按钮
	          </td>
	       </tr>
	       
	       <tr>
	          <td>URL</td>
	          <td>
	            <input type="text" name="url" />          
	          
	          </td>
	       </tr>
	       
	       <tr>
	          <td>描述</td>
	          <td>
	            <input type="text" name="remark" />          
	          
	          </td>
	       </tr>
	        <tr>
	          <td>排序值:</td>
	          <td><input type="text" name="sortno" class="easyui-numberbox" data-options="min:100"/></td>
	       </tr>
	       	       
	      	       
	      </table>
	    </form>
    </div>
    
   
    
</body>
</html>