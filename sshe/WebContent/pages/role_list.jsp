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
    	    url : 'role/list',    	  
    		fit : true,
    		border : false,
    		toolbar : "#tb",
    		fitColumns : true,
    		striped : true,
    		rownumbers : true,
    		singleSelect : true,    	
    		columns :[[    			
    			{title: "角色名称",field : "name",width: 150},
    			{title: "角色描述",field : "remark",width: 200},
    			{title: "是否启用",field : "enable",width: 100 }    		
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
    	 
    	  $("#grantForm").form({
    		 onSubmit: function() {   
    			   //获取角色的权限
    			   var nodes = $("#res_tree").tree("getChecked");
    			   var ids = [];
    			   for(var i = 0;i < nodes.length;i++) {
    				   ids.push(nodes[i].id);
    			   }
    			   
    			   $("#resource_ids").val(ids.join(","));
    			   $.messager.progress({msg : "正在授权..."}); //显示进度条，锁屏    			   
    		       return true;
    		    },   
    		 success:function(resp) {  
    			 $.messager.progress("close"); //关闭进度条    			
    			 var data = $.parseJSON(resp);  //字符串转JSON对象
        		 if(data.success) {
        			 //关闭对话框
        			 $("#grantWin").dialog("close");
        			 //提示
        			 parent.$.messager.show({title:"提示",msg:"授权成功",timeout:3000});	
        			 
        		 } else {
        			 $.messager.alert("提示",data.msg,"warning");
        		 } 
    		 }   

    	 })
    	 
    	 $("#addWin").dialog({
    		 width : 500,
    		 height : 300,
    		 title : "新增角色",
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
    	 
    	 $("#grantWin").dialog({
    		 width : 500,
    		 height : 500,
    		 title : "角色授权",
    		 iconCls : "icon-print",
    		 modal : true,
    		 closed : true,
    		 buttons : [
    			 {text : "确定",iconCls:'icon-ok',handler : function() {
    				 $("#grantForm").submit();
    			 }},
    			 {text : "取消",iconCls:'icon-cancel',handler : function() {$("#grantWin").dialog("close");}}    			 
    		 ]
    		 
    	 });
    	 
    	 $("#res_tree").tree({
     		url : "resource/tree",
     		lines : true,
     		method : "GET",
     		parentField : "pid",
     		checkbox : true,
     		cascadeCheck : false,
     		onCheck : function(node, checked) {
     			if(!checked) {
     				return;
     			}
     			$tree = $("#res_tree");
     			var pnode = $tree.tree("getParent",node.target); //获取当前节点的父节点
     			while(pnode != null) {
     				//选中父节点
     				$tree.tree("check",pnode.target);
     				pnode = $tree.tree("getParent",pnode.target);
     			}
     			
     		}
     	});
    	  
     }) 	
     
     //------------------------------------------------------------
     
     function doAdd() {
    	   	 
    	 $("#newForm").form("clear");   
    	 $("#input_enable").attr("checked","checked")
    	 $("#addWin").dialog("open");
    	 
     }
   
     function doGrant() {
    	 var row = $("#data_table").datagrid("getSelected");
    	 if(row == null) {
    		 $.messager.alert("提示","请选择要授权的角色!");
    		 return;
    	 }
    	 
    	 $("#grantForm").form("clear");
    	 $("#role_id").val(row.id);
    	 $("#role_name").text(row.name);
    	 $("#role_remark").text(row.remark);
    	 
    	 checkNo();  //清除选中
    	 
    	 //查询角色拥有的权限
    	 $.get("role/" + row.id + "/resources",{},function(ret) {
    		 if(ret.success) {
    			 //回填权限
    			 $tree = $("#res_tree");
    			 for(var i = 0;i < ret.data.length;i++) {
    				 if(ret.data[i] == null) {
    					 continue;
    				 }
    				 var resid = ret.data[i].id;
    				 var node = $tree.tree("find",resid);
    				 if(node != null)
    				    $tree.tree("check",node.target);
    			 } 
    			 
    			 //打开授权对话框
    	    	 $("#grantWin").dialog("open");  
    		 } else {
    			 $.messager.alert("提示",ret.msg);
    		 }
    		 
    	 },"json");
    	 
    	
     }
     
     function checkAll() {
    	var $tree = $("#res_tree");
     	var nodes = $tree.tree("getChecked","unchecked"); //获取所有未选中的
     	for(var i = 0;i < nodes.length;i++) {
     		$tree.tree("check",nodes[i].target);
     	}
     }
     
     function checkNo() {
    	var $tree = $("#res_tree");
    	var nodes = $tree.tree("getChecked"); 
    	for(var i = 0;i < nodes.length;i++) {
    		$tree.tree("uncheck",nodes[i].target);
    	}
     }
     
     function reverseCheck() {
    	 var $tree = $("#res_tree");
    	 
      	var nodes = $tree.tree("getChecked","unchecked"); //获取所有未选中的
      	var nodes2 = $tree.tree("getChecked"); 
      	
      	for(var i = 0;i < nodes.length;i++) {
      		$tree.tree("check",nodes[i].target);
      	}
      	    	
    	for(var i = 0;i < nodes2.length;i++) {
    		$tree.tree("uncheck",nodes2[i].target);
    	}
    	 
     }
     
  </script>
</head>
<body >
  
   <table id="data_table"></table>
      
    <div id="tb">
	   <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="doAdd()">新增</a>
	   <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="">修改</a>
	   <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="">删除</a>
	   <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-print',plain:true" onclick="doGrant()">授权</a>
	</div>
    
    
    
    <div id="addWin" style="padding:15px;">  
      <form id="newForm" action="role/add" method="post">          
	     <table border="0" width="95%" align="center">
	       <tr>
	          <td>角色名称:</td>
	          <td><input type="text" name="name" class="easyui-validatebox" data-options="required:true"/></td>
	       </tr>
	       <tr>
	          <td>角色描述:</td>
	          <td>
	             <textarea name="remark" rows="5" cols="30"></textarea>
	          </td>
	       </tr>
	       <tr>
	          <td>是否启用:</td>
	          <td>
	          <input type="radio" id="input_enable" name="enable" value="true" checked="checked"/>启用
	          <input type="radio"  name="enable" value="false" />禁用
	          </td>
	       </tr>	            	       
	      </table>
	    </form>
    </div>
    
     <div id="grantWin" >
        <div class="easyui-layout" data-options="fit:true">
     
         <div data-options="region:'center'">
            <ul id="res_tree"></ul>
         </div>
         
         <div data-options="region:'east'" style="width:180px;padding:10px">
             <div style="background-color:#eee;padding:5px;">
                 <h3><span id="role_name">角色名称</span></h3>
                 <p>
                    <span id="role_remark">角色描述</span>                 
                 </p>
             </div>
             
             <div style="background-color:#eee;margin-top:10px;text-align:center;padding:5px;">
                 <input type="button" value="全选" style="width:80px" onclick="checkAll()"/> <br/><br/>
                  <input type="button" value="全不选" style="width:80px" onclick="checkNo()"/><br/><br/>
                   <input type="button" value="反选" style="width:80px" onclick="reverseCheck()"/><br/><br/>
             </div>
         </div>
       </div>
       
       <form id="grantForm" action="role/grant" method="post">
          <input  type="hidden" name="roleid" id="role_id"/>
          <input type="hidden"  name="resourceIds" id="resource_ids"/>
       </form>
       
     </div>
</body>
</html>