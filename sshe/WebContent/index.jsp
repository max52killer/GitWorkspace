<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC >
<html>
	<head>
		<base href="http://<%=request.getServerName()+":" + request.getServerPort()+request.getContextPath()%>/"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>51</title>
		
		<!-- 导入EasyUI库 -->  
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="easyui/easyui-lang-zh_CN.js"></script>
		<link type="text/css" rel="stylesheet" href="easyui/themes/default/easyui.css">
		<link type="text/css" rel="stylesheet" href="easyui/themes/icon.css">
		
		<script type="text/javascript">
			$().ready(function(){
				
				$("#input_birth").datebox({
					editable:false
				});
			})
		</script>
	</head>
	<body>
		<h2>EasyUI示例</h2>
	    <hr/>
	    
	    <p>
	       出生日期：<input type="text" class="easyui-datebox" data-options="editable:false">
	    </p>
	    
	      <p>
	           出生日期：<input type="text" id="input_birth">
	    </p>
	    
	    <p>
	       <a href="pages/main.jsp">主页面</a>
	    </p>
	   <!--  <div class="easyui-window" data-options="width:400,height:300,title:'Test'"></div> -->
	</body>
</html>