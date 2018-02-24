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
     //检查数组array中是否包含元素ele
     function contains(array,ele) {
    	for(var i = 0;i < array.length;i++) {
    		if(ele == array[i]) {
    			return true;
    		}
    	}
    	
    	return false;
     }
  
     var jobs = null;
  
     //完成数据字典编码到显示值的转换
     function formatter_job(value,row,index) {
    	 if(jobs == null) 
    		  return value;
    	 
    	 for(var i = 0;i < jobs.length;i++) {
    		 if(jobs[i].code == value) {
    			 return jobs[i].name;
    		 }
    	 }
    	 
    	 return value;
    	 
     }
  
     $().ready(function(){
    	 
    	 //加载数据字典job的值
    	 $.get("dataDict/itemValues",{pcode : "job"},function(ret) {
    		 
    		 if(ret.success) {
    			 $("#id_job").combobox("loadData",ret.data);
    			 jobs = ret.data;
    		 }
    		 
    	 },"json");
    	 
    	 //
    	 $("#data_table").datagrid({
    		idField : 'id',
    	    url : 'employee/query',
    		fit : true,
    		border : false,
    		toolbar : "#tb",
    		fitColumns : true,
    		striped : true,
    		rownumbers : true,
    		singleSelect : false,
    		checkOnSelect : true,
    		selectOnCheck : true,
    		pagePosition : "bottom",
    		sortName : "jobno",
    		sortOrder : "asc",
    		frozenColumns :[[  
    			{field : "id",checkbox: true},
    			{title: "工号",field : "jobno",width: 130,sortable:true},
    			{title: "姓名",field : "name",width: 130,sortable:true},
    		]],
    		columns :[[    			
    			{title: "性别",field : "sex",width: 100,sortable:true,align:"center"},
    			{title: "入职日期",field : "hiredate",width: 150,sortable:true},
    			{title: "工作岗位",field : "job",width: 150,sortable:true,formatter : formatter_job },
    			{title: "联系电话",field : "phone",width: 160},
    			{title: "工资",field : "salary",width: 120,sortable:true,align:"right",formatter:function(value,row,index){ 
    				return value >= 15000 ? "****" : value;
    				
    			}},
    		]],
    		pageNumber : 1,
    		pagination : true,
    		pageSize : 15,
    		pageList : [15,20,30,50],
    		rowStyler : function(index,row) {
    			 if(row.salary > 20000) {
    				 return "color:red;font-weight:bold;";
    			 }
    		},
    		/*loadFilter : function(ret) {
    			if(ret.success) {
    				return ret.data;
    			}
    			
    			$.messager.alert("提示",ret.msg,"error");
    		},*/
    		onLoadSuccess : function() {
    			$("#data_table").datagrid("clearSelections");
    		},
    		onRowContextMenu : function(e, rowIndex, rowData) {
    			e.preventDefault(); //阻止默认行为
    			//选中点击行
    			$("#data_table").datagrid("clearSelections");
    			$("#data_table").datagrid("selectRow",rowIndex);
    			//显示菜单
    			$("#mm").menu("show",{
    				left : e.pageX,
    				top :  e.pageY
    			});
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
    	  
     
    	 $("#editForm").form({
    		 onSubmit: function() {   
    			   var ret =  $("#editForm").form("validate");  
    			   if(!ret) {  //校验失败
    				   return false;
    			   }
    			   $.messager.progress({msg : "正在修改..."}); //显示进度条，锁屏    			   
    		       return true;
    		    },   
    		 success:function(resp) {  
    			 var data = $.parseJSON(resp);  //字符串转JSON对象
    			 $.messager.progress("close"); //关闭进度条
        		 if(data.success) {
        			 //关闭对话框
        			 $("#editWin").dialog("close");
        			 //提示
        			 parent.$.messager.show({title:"提示",msg:"修改成功",timeout:3000}); 
        			 //重新加载当前页数据
        			 $("#data_table").datagrid("reload");
        			 
        		 } else {
        			 $.messager.alert("提示",data.msg,"warning");
        		 } 
    		 }   

    	 })
     $("#editWin").dialog({
    		 width : 500,
    		 height : 300,
    		 title : "修改员工",
    		 iconCls : "icon-edit",
    		 modal : true,
    		 closed : true,
    		 buttons : [
    			 {text : "确定",iconCls:'icon-ok',handler : function() {    				
    				 //ajax提交表单
    				$("#editForm").submit(); 
    			 }},
    			 {text : "取消",iconCls:'icon-cancel',handler : function() {$("#editWin").dialog("close");}}    			 
    		 ]
    		 
    	 });
    	 
    	 //加载用户的操作权限
    	 $.get("user/actions",{},function(ret){
    		 if(ret.success){
    			 var actions = ret.data; //用户拥有的权限
    			 var ctls = ["employee_add","employee_edit","employee_delete","employee_query"];
    			 $.each(ctls,function(i,v){
    				 if(!contains(actions,v)) {  //检查是否有指定的权限
    					 //隐藏指定的按钮或菜单
    					 $("." + v).hide();
    				 }
    			 })
    			 
    		 }else {
    			 $.messager.alert("提示",ret.msg);
    		 }
    	 },"json");
    	 
    	 
    	
     })
     
     //------------------------------------------------------------
     
     function doDelete() {
    	 //获取选中记录
    	 var sels = $("#data_table").datagrid("getSelections");
    	 if(sels == null || sels.length == 0) {
    		 $.messager.alert("提示","请选择要删除的记录！");
    		 return;
    	 }
    	 
    	 //收集选中记录的id
    	 var aid = [];
    	 for(var i = 0;i < sels.length;i++) {
    		 aid.push(sels[i].id);
    	 }
    	 
    	 $.messager.confirm("提示","确定要删除选定数据吗?",function(r){
    		 if(!r) {  //取消
    			 return;
    		 }    		 
    		 
    		 $.messager.progress({msg : "正在删除..."}); //显示进度条，锁屏
    		 //向后台发ajax请求
    		 $.post("employee/delete",{ids:aid.join(",")},function(data) {
        		 $.messager.progress("close"); //关闭进度条
        		 if(data.success) {
        			 //提示
        			parent.$.messager.show({title:"提示",msg:"删除成功",timeout:3000}); 
        			 //重新加载当前页数据
        			 $("#data_table").datagrid("reload");
        			 
        		 } else {
        			 $.messager.alert("提示",data.msg,"warning");
        		 }
        		 
        	 },"json");
    		 
    	 });
    	 
     }
     //------------------------------------------------------------
     
     function doAdd() {
    	 $("#newForm").form("clear");
    	 $("#addWin").dialog("open");
    	 
     }
     
     function doEdit() {
    	//获取选中记录
    	 var sels = $("#data_table").datagrid("getSelections");
    	 if(sels == null || sels.length == 0) {
    		 $.messager.alert("提示","请选择要修改的记录！");
    		 return;
    	 }
    	 
    	 if(sels.length > 1) {
    		 $.messager.alert("提示","每次只能修改一条记录！");
    		 return;
    	 }
    	 
    	 $("#editForm").form("load",sels[0]);
    	 
    	 $("#editWin").dialog("open");
    	 
     }
     //------------------------------------------------------------
     
     function doQuery() {
    	var params = {
    		jobno : $("#search_jobno").val(),
    		name : $("#search_name").val(),
    		job : $("#search_job").combobox("getValue"),
    		hiredate : $("#search_hiredate").datebox("getValue"),
    		sex : $("#search_sex").combobox("getValue"),
    		phone : $("#search_phone").val(),
    	} ;
    	
    	//console.info(params);
    	 
    	$("#data_table").datagrid("load",params); 
    	 
     }
     
  </script>
</head>
<body class="easyui-layout">
   <div data-options="region:'north',border:false" style="height:110px">
       <fieldset style="margin:0px;padding:10px;border:1px solid #ddd;background-color:#fafafa;">
        <legend>查询条件</legend>
       <form id="search_form">
        <table width="100%">
          <tr height="35">
             <td width="60" align="left">&nbsp;&nbsp;工号:</td>
             <td width="120"><input type="text" id="search_jobno" name="jobno"/></td>
             <td width="60" align="left">&nbsp;&nbsp;姓名:</td>
             <td width="120"><input type="text" id="search_name" name="name"/></td>            
             <td width="60" align="left">&nbsp;&nbsp;工作岗位:</td>
             <td width="120">
              <select name="job" class="easyui-combobox" id="search_job">
                   <option value="">不限</option>
	               <option>保安</option>
	               <option>销售</option>
	               <option>客服</option>
	               <option>出纳</option>
	                <option>会计</option>
	                 <option>经理</option>
	            </select></td>
             <td>&nbsp;</td>             
          </tr>
          <tr>
             <td width="60" align="left">&nbsp;&nbsp;联系电话:</td>
             <td width="120"><input type="text" id="search_phone" name="phone"/></td>
            
             <td width="60" align="left">&nbsp;&nbsp;入职时间:</td>
             <td  >
                <input type="text" name="hiredate" id="search_hiredate" data-options="editable:false" class="easyui-datebox"/> 
                           
             </td>
              <td width="60" align="left">&nbsp;&nbsp;性别:</td>
             <td width="120"> 
                 <select name="sex" class="easyui-combobox" id="search_sex">
                  <option value="">不限</option>
	               <option>男</option>
	               <option>女</option>
	            </select>
             </td>
             <td>
               <a id="btn" href="javascript:void(0)" class="easyui-linkbutton employee_query" data-options="iconCls:'icon-search',plain:true" onclick="doQuery()">查询</a> 
               <a id="btn" href="javascript:void(0)" class="easyui-linkbutton employee_query" data-options="iconCls:'icon-cancel',plain:true" onclick="$('#search_form').get(0).reset()">清空</a> 
             </td>             
          </tr>
      </table>
      </form>
     </fieldset>
   </div>
   <div data-options="region:'center',border:false">
       <table id="data_table"></table>
   </div>
   
   
    
    <div id="tb">    
	   <a href="javascript:void(0)" class="easyui-linkbutton employee_add" data-options="iconCls:'icon-add',plain:true" onclick="doAdd()">新增</a>	
	   <a href="javascript:void(0)" class="easyui-linkbutton employee_edit" data-options="iconCls:'icon-edit',plain:true" onclick="doEdit()">修改</a>
	   <a href="javascript:void(0)" class="easyui-linkbutton employee_delete" data-options="iconCls:'icon-remove',plain:true" onclick="doDelete()">删除</a>
	</div>
    
    
    
    <div id="addWin" style="padding:15px;">  
      <form id="newForm" action="employee/add" method="post">        
	     <table border="0" width="95%" align="center">
	       <tr>
	          <td>工号:</td>
	          <td><input type="text" name="jobno" class="easyui-validatebox" data-options="required:true,validType:'length[5,10]'"/></td>
	       </tr>
	       <tr>
	          <td>姓名:</td>
	          <td><input type="text" name="name" class="easyui-validatebox" data-options="required:true"/></td>
	       </tr>
	       <tr>
	          <td>性别:</td>
	          <td>
	          <input type="radio"  name="sex" value="男" checked="checked"/>男
	          <input type="radio"  name="sex" value="女" />女
	          </td>
	       </tr>
	       
	       <tr>
	          <td>工作岗位:</td>
	          <td>
	            <select name="job" class="easyui-combobox" id="id_job" style="width:170px;" data-options="textField:'name',valueField:'code'">	            
	            </select>
	          </td>
	       </tr>
	       
	        <tr>
	          <td>入职日期:</td>
	          <td><input type="text" name="hiredate" class="easyui-datebox" data-options="editable:false"/></td>
	       </tr>
	       
	        <tr>
	          <td>联系电话:</td>
	          <td><input type="text" name="phone"/></td>
	       </tr>
	       
	       <tr>
	          <td>工资:</td>
	          <td>
	          <input type="text"  name="salary" class="easyui-numberbox" data-options="min:1000,max:50000,value:2000"/>
	          </td>
	       </tr>
	      	       
	      </table>
	    </form>
    </div>
    
    
    <div id="editWin" style="padding:15px;">  
      <form id="editForm" action="employee/edit" method="post">   
         <input type="hidden" name="id">     
	     <table border="0" width="95%" align="center">
	       <tr>
	          <td>工号:</td>
	          <td><input type="text" name="jobno" class="easyui-validatebox" data-options="required:true,validType:'length[5,10]'"/></td>
	       </tr>
	       <tr>
	          <td>姓名:</td>
	          <td><input type="text" name="name" class="easyui-validatebox" data-options="required:true"/></td>
	       </tr>
	       <tr>
	          <td>性别:</td>
	          <td>
	          <input type="radio"  name="sex" value="男" checked="checked"/>男
	          <input type="radio"  name="sex" value="女" />女
	          </td>
	       </tr>
	       
	       <tr>
	          <td>工作岗位:</td>
	          <td>
	            <select name="job" class="easyui-combobox">
	               <option>保安</option>
	               <option>销售</option>
	               <option>客服</option>
	               <option>出纳</option>
	                <option>会计</option>
	                 <option>经理</option>
	            </select>
	          </td>
	       </tr>
	       
	        <tr>
	          <td>入职日期:</td>
	          <td><input type="text" name="hiredate" class="easyui-datebox" data-options="editable:false"/></td>
	       </tr>
	       
	        <tr>
	          <td>联系电话:</td>
	          <td><input type="text" name="phone"/></td>
	       </tr>
	       
	       <tr>
	          <td>工资:</td>
	          <td>
	          <input type="text"  name="salary" class="easyui-numberbox" data-options="min:1000,max:50000,value:2000"/>
	          </td>
	       </tr>
	      	       
	      </table>
	    </form>
    </div>
    
    <!-- 右键菜单 -->
    <div id="mm" class="easyui-menu" style="width:120px;">  
	    <div data-options="iconCls:'icon-add'" onclick="doAdd()" class="employee_add">新增</div> 
	    <div class="menu-sep"></div> 
	    <div data-options="iconCls:'icon-edit'" onclick="doEdit()" class="employee_edit">修改</div>  
	    <div data-options="iconCls:'icon-remove'" onclick="doDelete()" class="employee_delete">删除</div>    
   </div>  
    
</body>
</html>