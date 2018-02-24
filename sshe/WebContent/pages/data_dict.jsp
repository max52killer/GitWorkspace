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
    	 $("#data_table").datagrid({
    		idField : 'id',
    	    url : 'dataDict/values',
    		fit : true,
    		border : false,
    		toolbar : "#tb",
    		fitColumns : true,
    		striped : true,
    		rownumbers : true,
    		singleSelect : true,     		
    		columns :[[    			
    			{title: "字典编码",field : "code",width: 100},
    			{title: "字典值",field : "name",width: 150},
    			{title: "值描述",field : "remark",width:250},    			
    		]],    	
    		pagination : false,    			
    		onLoadSuccess : function() {
    			$("#data_table").datagrid("clearSelections");
    		}    		
    	 });
    	 
    	 //-------------------------
    	 
    	 $("#type_tree").tree({
    		 url : "dataDict/type/tree",
    		 lines : true,
    		 onClick : function(node) {
    			// $.messager.alert("提示","code is " + node.attributes.code);
    			//修改标题
    			$("#layout_body").layout("panel","center").panel({title : node.text + "的可用值"});
    			
    			//刷新数据表格
    			$("#data_table").datagrid("load",{pcode : node.attributes.code});
    			
    		 }
    		 
    	 })
    	 
    	 
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
        			 $("#data_table").datagrid("load");
        			 
        		 } else {
        			 $.messager.alert("提示",data.msg,"warning");
        		 } 
    		 }   

    	 })
    	 
    	 $("#addWin").dialog({
    		 width : 500,
    		 height : 300,
    		 title : "新增员工",
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
    	 
    	 var typeNode = $("#type_tree").tree("getSelected");
    	 if(typeNode == null) {
    		 $.messager.alert("提示","请选择字典类型");
    		 return;
    	 }
    	 
    	 $("#newForm").form("clear");
    	 $("#hidden_pcode").val(typeNode.attributes.code);
    	 $("#addWin").dialog({
    		 title : "新增" + typeNode.text 
    	 });
    	 $("#addWin").dialog("open");
    	 
     }
     
    
    
     
  </script>
</head>
<body class="easyui-layout" id="layout_body">
   <div data-options="region:'west',border:true" title="数据字典类型" style="width:200px;padding:10px">
       <ul id="type_tree"></ul>
   </div>
   <div data-options="region:'center',border:false" title="数据字典取值">
       <table id="data_table"></table>
   </div>
   
    
    <div id="tb">
	   <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="doAdd()">新增</a>
	   <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="">修改</a>
	   <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="">删除</a>
	</div>
    
    
    <div id="addWin" style="padding:15px;">  
      
      <form id="newForm" action="dataDict/add" method="post"> 
         <input type="hidden" name="pcode" id="hidden_pcode">       
	     <table border="0" width="95%" align="center">
	       <tr>
	          <td>字典编码:</td>
	          <td><input type="text" name="code" class="easyui-validatebox" data-options="required:true"/></td>
	       </tr>
	       <tr>
	          <td>字典值:</td>
	          <td><input type="text" name="name" class="easyui-validatebox" data-options="required:true"/></td>
	       </tr>
	             
	        <tr>
	          <td>描述:</td>
	          <td><input type="text" name="remark"/></td>
	       </tr>
	       	      
	      </table>
	    </form>
    </div>
    
   
    
</body>
</html>