<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="http://<%=request.getServerName()+":" + request.getServerPort()+request.getContextPath()%>/"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
 <link rel="stylesheet" href="login/login.css" type="text/css" media="screen">
 <!-- 导入EasyUI库 -->  
 <script type="text/javascript" src="js/jquery.min.js"></script>
 <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
 <script type="text/javascript" src="easyui/easyui-lang-zh_CN.js"></script>
 <script type="text/javascript" src="easyui/easyui-ext.js"></script>
 <link type="text/css" rel="stylesheet" href="easyui/themes/gray/easyui.css">
 <link type="text/css" rel="stylesheet" href="easyui/themes/icon.css">
 
 
 <script type="text/javascript">
	 function center() {
	     var x = ($(document).width() - $("#login_panel").width()) / 2;
	     if (x < 0) x = 0;
	     var y = ($(document).height() - $("#login_panel").height()) / 2;
	     if(y < 0) y = 0;
	     $("#login_panel").css("left",x).css("top",y);
	     $("#login_shadow").css("left",x).css("top",y);
	  } 
	 
	 $(function(){
		 center();
	     $(window).resize(center);
	     
	     $("#loginBtn").click(function(){
	    	  var username = $("#username").val();
	    	  var password = $("#password").val();
	    	  if(username == "") {
	    		  $.messager.alert("提示","请输入用户名!");
	    		  $("#username").focus();
	    		  return;
	    	  }
	    	  
	    	  if(password == "") {
	    		  $.messager.alert("提示","请输入密码!");
	    		  $("#password").focus();
	    		  return;
	    	  }
	    	  
	    	  $.messager.progress({title : "请稍等",text : "正在登录..."});
	    	   $.ajax({
	    		  url  : "user/login",
	    		  data : {username : username ,password : password},
	    		  complete : function(){$.messager.progress("close");},
	    		  dataType : "json",
	    		  error : function(){$.messager.alert("提示","请求出错!");},
	    		  success : function(resp, textStatus, jqXHR) {
	    			  if(resp.success ) {
	    				  //跳转至后台首页
	    				  location.href="pages/main.jsp";
	    			  } else {   //登录失败
	    				  $.messager.alert("提示",resp.msg);
	    			  }
	    		  }
	    		   
	    	   });
	    	 
	     });
	     
	  });
	 
	 $().ready(function(){
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
	 function doAdd() {
	   	 
    	 $("#newForm").form("clear"); 
    	 $("#addWin").dialog("open");
    	 
     }
 </script>
</head>
<body>
	<div class="panel window" id="login_panel"
		style="display: block; width: 488px; left: 440px; top: 180px; z-index: 9001;">
		<div style="overflow: hidden; width: 486px; height: 286px;" title=""
			class="panel-body panel-body-noheader panel-body-noborder window-body window-body-noheader">
			<div class="panel" style="display: block; width: 486px;">
				<div title=""
					class="panel-body panel-body-noheader panel-body-noborder dialog-content"
					style="width: 486px; height: 249px;">
					<form id="form-body" style="">
						<ul>
							<li>
							    <input class="form-radio-other-input" type="radio" name="type" value="1" checked="checked">
								<label>普通用户</label> &nbsp;&nbsp;&nbsp;
								<input class="form-radio-other-input" type="radio" name="type" value="0"> 
								<label>管理员</label>
							</li>
							<li><label>账 号 </label> <input
								class="account form-textbox"
								type="text" name="username" id="username"></li>
							<li>
							<label>密 码 </label> 
							<input	class="password form-textbox"
								type="password" name="password" id="password">
							</li>
						</ul>
					</form>
					<div id="logo">
						<h1>SSHE基础平台系统</h1>
					</div>
				</div>
			</div>
			<div class="dialog-button">				
				<a	href="javascript:void(0)" class="l-btn" id="loginBtn">
					<span class="l-btn-left"><span	class="l-btn-text">登陆</span></span>
			  </a>
<!-- 			  <a	href="javascript:void(0)" class="r-btn" onclick="doAdd()">注册 -->
<!-- 			  </a> -->
			</div>
		</div>
	</div>
	<div class="window-shadow" id="login_shadow"
		style="display: block; left: 440px; top: 180px; z-index: 9000; width: 500px; height: 300px;"></div>

<!-- 	注册用户 -->
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