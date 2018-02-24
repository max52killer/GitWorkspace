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
 <style type="text/css">
      .menu-item2 {
        cursor: pointer;
        width: 140px;
        padding: 5px 2px 5px 26px;
        background: url("img/menu.png") no-repeat scroll 2px 6px;
        margin-left: 10px;
      }
      
      .menu-item2:HOVER {
	      background-color: #ddd;
      }
   
   </style>
 
  <script type="text/javascript">
     var tabCount = 0;
  
     $().ready(function(){
    	
    	 
    	 $("#work_tab").tabs({
    		 fit : true,
    		 border : false,
    		 onClose : function() {
    			 tabCount--;
    		 }
    		 
    	 });
    	 
    	 $("#menu_tree").tree({
    		lines : true,
    		url : "user/menu",
    		parentField : "pid",
    		onClick : function(node) {
    			if(node.attributes.url == null) {
    				return;
    			}
    			
    			addTab(node.text,node.attributes.url);
    		}
    	 }) 
    	 
     })
  
     
     function addTab(pTitle,pUrl) {
    	 var $tabs =  $("#work_tab");
    	 
    	 
    	 if($tabs.tabs("exists",pTitle) ) {  //如果存还，则选中它
    		 $tabs.tabs("select",pTitle);
    		 return;
    	 }
    	 
    	 if(tabCount >= 10) {
    		 $.messager.alert("提示","你已经打开了太多的Tab页，为性能考虑，请关闭不用的tab页！");
    		 return;
    	 }
    	 
    	 //不存在，则创建    	 
    	 $tabs.tabs("add",{
    		 title : pTitle,
    		 //href : pUrl,
    		 content : "<iframe src='" + pUrl + "' frameborder='false' style='border:false;width:100%;height:99%'></iframe>",
    		 closable : true,
    	 });
    	 tabCount++;
     }
     
     function logout() {
    	 $.messager.confirm("提示","确定要退出吗?",function(r) {
    		 if(!r) {
    			 return;
    		 }
    		 
    		 $.post("user/logout",{},function(ret) {
    			 if(ret.success) {
    				location.href="login.jsp";
    				 
    			 } else {
    				 $.messager.alert("提示",ret.msg);
    			 }
    			 
    		 },"json");
    		 
    	 });
    	 
     }
  </script>
</head>
<body class="easyui-layout">
 
    <!-- 北部  -->
    <div data-options="region:'north',border:false" style="height:80px;background: url(img/header_bg.png) no-repeat scroll right bottom;position: relative;">
        <img src="img/logo.gif">
        
        <div style="position: absolute;right:2px;bottom: 5px;">
           <a href="javascript:void(0)" class="easyui-menubutton" data-options="menu:'#mm1',iconCls:'icon-cut'" >用户</a>  
           <a href="javascript:void(0)" class="easyui-menubutton" data-options="menu:'#mm2',iconCls:'icon-help'" >帮助</a>  
			<div id="mm1" style="width:100px;">  
			    <div >修改密码</div>  
			    <div >我的权限</div>  
			    <div class="menu-sep"></div>
			    <div onclick="logout()">退出</div>  
			</div>  
			
			<div id="mm2" style="width:100px;">  
			    <div >联系管理员</div> 			    
			    <div class="menu-sep"></div>
			    <div >关于</div>  
			</div>  
           
        </div>
    </div>  
    
    <!-- 南部 -->
    <div data-options="region:'south'" data-options="border:false" style="height:40px;background:#eee;text-align: center;line-height: 38px">
              copyright:www.51cc.com
    </div>  
   
    <!-- 导航菜单 -->
    <div data-options="region:'west',title:'导航菜单',split:true,iconCls:'icon-search'" style="width:200px;">
      
       <ul id="menu_tree"></ul>
      
       <%--
       <div  class="easyui-accordion" data-options="fit:true,border :false"> 
        
		    <div title="Easyui示例"  style="overflow:auto;padding:10px;">  
		          
		          <div class="menu-item2" onclick="addTab('基础示例','pages/demo_base.jsp')">基础示例</div>
		          <div class="menu-item2" onclick="addTab('消息框示例','pages/demo_messager.jsp')">消息框示例</div>
		          <div class="menu-item2" onclick="addTab('Tree示例','pages/demo_tree.jsp')">Tree示例</div>
		          <div class="menu-item2" onclick="addTab('权限树','pages/res_tree.jsp')">权限树</div>
		          
		    </div>  
		    
		    <div title="系统管理"  style="padding:10px;">  
		        <div class="menu-item2" onclick="addTab('菜单管理','pages/resource_list.jsp')">菜单管理</div>
		        <div class="menu-item2" onclick="addTab('角色管理','pages/role_list.jsp')">角色管理</div> 
		        <div class="menu-item2" onclick="addTab('用户管理','pages/user_list.jsp')">用户管理</div>
		        <div class="menu-item2" onclick="addTab('数据字典管理','pages/data_dict.jsp')">数据字典</div>
		    </div>  
		    
		    <div title="组织机构管理">  
		        <div class="menu-item2">部门管理</div>
		        <div class="menu-item2" onclick="addTab('员工管理','pages/emp_list.jsp')">员工管理</div>
		    </div>  
      </div> 
       --%> 
    </div> 
     
    <!-- 工作区 -->
    <div data-options="region:'center'" >
        <div id="work_tab" >  
		    <div title="首页">  
		       <img src="img/bg.jpg"/>
		    </div>  
		    
		</div>  
    
    </div>  
    
    
   
    
</body>
</html>