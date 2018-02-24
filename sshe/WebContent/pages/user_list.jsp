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
    	 $("#data_table").datagrid({
    		idField : 'id',
    	    url : 'user/list',    	  
    		fit : true,
    		border : false,
    		toolbar : "#tb",
    		fitColumns : true,
    		striped : true,
    		rownumbers : true,
    		singleSelect : true,    	
    		columns :[[    			
    			{title: "用户名",field : "username",width: 150},
    			{title: "呢称",field : "nickname",width: 200},
    			{title: "创建时间",field : "createTime",width: 200,formatter : function(value,row,index) {
    				return new Date(value).toLocaleString();
    			}},
    			{title: "是否启用",field : "enable",width: 100 } , 
    			{title: "拥有角色",field : "roles",width: 250,formatter : function(value,row,index) {
    				var rnames = [];
    				$.each(value,function(i,role) {    					
    					rnames.push(role.name);
    				});
    				return rnames.join(",");
    			} }
    		]],    	
    		pagination : true,
    		pageSize : 15,
    		pageList : [15,20,30,50],
    		onLoadSuccess : function() {
    			$("#data_table").datagrid("clearSelections");
    		} 		
    		 
    	 });
    	 
    	 //----------------
    	 
    	 $("#newForm").form({
    		 onSubmit: function() {   
    			   var ret =  $("#newForm").form("validate");  
    			   if(!ret) {  //校验失败
    				   return false;
    			   }
    			   
    			   if($("#user_pwd").val() != $("#user_repwd").val()) {
    				   $.messager.alert("提示","两次输入的密码不一致");
    				   return false;
    			   }
    			   
    			   $.messager.progress({msg : "正在添加..."}); //显示进度条，锁屏    			   
    		       return true;
    		    },   
    		 success:function(resp) {  
    			 $.messager.progress("close"); //关闭进度条
    			 var data = $.parseJSON(resp);  //字符串转JSON对象
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
    		 title : "新增用户",
    		 iconCls : "icon-add",
    		 modal : true,
    		 closed : true,
    		 buttons : [
    			 {text : "确定",iconCls:'icon-ok',handler : function() {    				
    			
    				$("#newForm").submit(); 
    			 }},
    			 {text : "取消",iconCls:'icon-cancel',handler : function() {$("#addWin").dialog("close");}}    			 
    		 ]
    		 
    	 });   
    	 
    	 //加载所有角色
    	 $.get("role/all",{},function(ret){
    		 if(ret.success) {
    			 var html = "";
    			for(var i= 0;i < ret.data.length;i++) {
    				if(i > 0 && i % 3 == 0) {
    					html += "<br/>";
    				}
    				
    				html += " <input type='checkbox' name='roleIds' value='" + ret.data[i].id + "'/> " + ret.data[i].name;
    			   
    				
    			}
    			 
    		   $("#role_ids").html(html);
    		 } else {
    			 $.messager.alert("提示",ret.msg);
    		 }
    		 
    	 },"json");
    	 
    	  
     }) 	
     
     //------------------------------------------------------------
     
     function doAdd() {
    	   	 
    	 $("#newForm").form("clear"); 
    	 $("#addWin").dialog("open");
    	 
     }
       
  </script>
</head>
<body >
  
   <table id="data_table"></table>
      
    <div id="tb">
	   <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="doAdd()">新增</a>
	   <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="">修改</a>
	   <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="">删除</a>
	</div>
    
    
    
    <div id="addWin" style="padding:15px;">  
      <form id="newForm" action="user/add" method="post">          
	     <table border="0" width="95%" align="center">
	       <tr>
	          <td>用户名:</td>
	          <td><input type="text" name="username" class="easyui-validatebox" data-options="required:true"/></td>
	       </tr>
	       <tr>
	          <td>呢称:</td>
	          <td>
	             <input type="text" name="nickname"/>
	          </td>
	       </tr>
	        <tr>
	          <td>密码:</td>
	          <td>
	             <input type="password" name="password" id="user_pwd" class="easyui-validatebox" data-options="required:true"/>
	          </td>
	       </tr>
	       <tr>
	          <td>确认密码:</td>
	          <td>
	             <input type="password" name="repwd" id="user_repwd"/>
	          </td>
	       </tr>
	       <tr>
	          <td>是否启用:</td>
	          <td>
	          <input type="radio" id="input_enable" name="enable" value="true" checked="checked"/>启用
	          <input type="radio"  name="enable" value="false" />禁用
	          </td>
	       </tr>	
	       
	       <tr>
	          <td>拥有角色:</td>
	          <td id="role_ids">
	          </td>
	       </tr>
	                   	       
	      </table>
	    </form>
    </div>
     
</body>
</html>