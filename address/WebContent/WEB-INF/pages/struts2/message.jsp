<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>在线通讯录</title>
<style type="text/css">
		
	body {
	  font-family: "宋体";
	  font-size: 12px;	 
	  color: #324451;
	}
	
   
  #container {
    width : 350px;
    height : 162px;
    position : absolute;
    top : 120px;
    left : 50%;
    margin-left : -175px;   
    border : 1px solid #88bfea;
    
  }
 
  #title {
     height : 32px;
     background-color : #e5f1f8;
     border-bottom : 1px solid #88bfea; 
     font-size :13px;
     font-weight : bold;
     line-height : 32px;
     padding-left : 6px;
  }
  
  #content {
     padding-top : 40px;
     text-align : center;
  }
</style>
</head>
<body>
    <div id="container">
    	  <div id="title">
    	  	  提示
    	  </div>
    	  <div id="content">
    	  	 <p> ${msg }
    	  	</p>
    	  	 <input type="button" value="确定" onclick="location.href='address_list.action'"/>
    	  </div>
    </div>
</body>
</html>
