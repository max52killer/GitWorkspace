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
    	$("#tree").tree({
    		url : "resource/tree",
    		lines : true,
    		method : "GET",
    		parentField : "pid"
    	});
    }) 
 </script>
</head>
<body>
    <h1><font color="green">权限树</font></h1>
    
   <ul id="tree"></ul>
    
    
</body>
</html>