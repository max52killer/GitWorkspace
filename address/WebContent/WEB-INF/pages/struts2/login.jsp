<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>在线通讯录</title>
<style type="text/css">
	* {
	   padding : 0px;
	   margin : 0px;
	}
	
	body {
	  font-family: "宋体";
	  font-size: 12px;	 
	  color: #324451;
	}
	
	a:link,a:visited,a:hover,a:active {
	   text-decoration: none;
	   color: #324451;
  }
   
  #container {
    width : 1004px;
    position : absolute;
    left : 50%;
    margin-left : -502px;  
  }
  
  #banner {
    
     background-image : url(img/indexBG.jpg);
     height : 600px;
  
     
  }
   
  #banner #form {
     position :absolute;
     bottom : 10px;    
  } 
 
  
  .buttonClass {
	  font-family: "宋体";
	  font-size: 9pt;
	  border: 1px solid #88bfea;
	  background-color: #E5F1F8;
  } 
  
  form label,form input.buttonClass {
     position : relative;
     top : -13px;
  }
</style>
  <script type="text/javascript">
     function checkForm(form) {
    	 if (form.username.value == "") {
    		 alert("用户名不能为空");
    		 form.username.focus();
    		 return false;
    	 }
    	 
    	 if (form.password.value == "") {
    		 alert("密码不能为空");
    		 form.password.focus();
    		 return false;
    	 }
    	 
    	 if (form.checkCode.value == "") {
    		 alert("验证码不能为空");
    		 form.checkCode.focus();
    		 return false;
    	 }
    	 
    	 return true;
     }
  </script>
</head>
<body>
    <div id="container">
    	  <div id="banner">     	  	
    	  	 <div id="form">  
    	  	 <form action="login_execute.action" method="post" onsubmit="return checkForm(this)">
    	  	 	 <label for="username">用户名:</label> 
    	  	 	 <input type="text" id="username" name="username" value="${username }" size="16" class="buttonClass">
    	  	 	 <label for="password">密 码:</label> 
    	  	 	 <input type="password" id="password" name="password" size="18" class="buttonClass">
    	  	 	 <label for="checkCode">验证码:</label> 
    	  	 	 <img src="randomCode.action" onclick="this.src='randomCode.action?id=' + new Date().getTime()"/>
    	  	 	 <input type="text" id="checkCode" name="checkCode" size="10" class="buttonClass">
    	  	 	 <input type="image" src="img/loginButton.jpg"/>
    	  	 	 <font color="red"><b>${msg}</b></font>
    	  	 </form>  	  	
    	     </div>	   	  	 
    	  </div>    	  
    </div>
</body>
</html>
