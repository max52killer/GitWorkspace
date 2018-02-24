<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="http://<%=request.getServerName()+":" + request.getServerPort()+request.getContextPath()%>/"/>
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
     height : 78px;
     background-image : url(img/top_06.gif);  
     background-repeat : repeat-x;
  }
   
  #banner span {
    font-size : 36px;
    line-height : 78px;
    padding-left : 14px;
    font-family : 幼圆,宋体;
  }

  #topMenu {
     height : 31px;
     background-image : url(img/top_07.gif);  
     background-repeat : repeat-x;  
  }
  
  #topMenu span {
     line-height : 31px;
     padding-left : 20px;
     padding-right : 20px;
  }
  
  #topMenu #exit {
    float : right;
  }
  
 
  #center br {
    clear : both;
  }
  
  #left {
    margin-top : 20px;
    float : left;
    border : 1px solid #88bfea;
    height : 420px;
    width : 170px;
    background-image: url(img/top_08.gif);
    background-position: center bottom;
    background-repeat: repeat-x;
  }
  
  #left .leftMenuTitle {
     height : 50px;
     background-color : #e5f1f8;
     color: #30424F;
     font-size: 14px;
     font-weight: bold;
     text-align : center;
  }
  
  #left .leftMenuTitle h3 {     
     font-size : 14px;
     background-image : url(img/menu_1.gif);
     background-repeat :no-repeat;
     padding-left : 5px;
     height : 36px;
     line-height : 36px;
     margin-left : 20px;
     position : relative;
     top : 7px;
  }
  
  #left .menuList {
     text-align : center;
  }
  
  #left .menuList dt {
    height : 25px;
    border-bottom : 1px solid #e6e6e6;
    border-top : 1px solid #e6e6e6;
    background-color : #d2e1ed;
    color : #090;
    line-height : 25px;
    font-size : 13px;
    font-weight : bold;
  }
  
  #left .menuList dd  {
     line-height : 25px;
     font-size : 13px;
     font-weight : bold;
  }
  
  #right {
    margin-top : 20px;
    float : left ;
    width : 830px;
    height : 420px;
    border : 1px solid #88bfea;
  }
 
  #right .rightMenuTitle {
      height : 50px;
      background-color : #e5f1f8;
  }
  
  #right  .rightMenuTitle h3 {    
     background-image : url(img/214.gif);
     background-repeat : no-repeat;
     height : 36px;
     line-height : 36px;
     padding-left : 40px;
     margin-left : 10px;
     position : relative;
     top : 7px;
  }
  
  #right table {
     margin-top : 30px;
     margin-left : 50px;    
     width : 700px;
  }
  
  #right table, #right table td ,#right table th {
    border : 1px solid #e6e6e6;
    border-collapse : collapse;    
    text-align : left;
    padding : 8px 20px;  
  }
  
  #right .pager {
     text-align : right;
  }
  
  .btn {
     padding : 2px 11px;
  }
  
  #bottom {     
     margin-top : 20px;
     height : 31px;
     background-image : url(img/top_07.gif);  
     background-repeat : repeat-x;
     line-height : 31px;
     text-align : right;
     font-weight : bold;    
  }

  
</style>
</head>
<body>
    <div id="container">
    	  <div id="banner">
    	  	  <span>在线通讯录</span>
    	  </div>
    	  <div id="topMenu">
    	  	  <span id="time">北京时间</span>
    	  	  <span id="exit"><a href="logout">退出系统</a></span>
    	  </div>
    	  
    	  <div id="center">
    	  	 <div id="left">
    	  	      <div class="leftMenuTitle">
    	  	      	  <h3>系统工具栏</h3>
    	  	      </div>
    	  	      <dl class="menuList">
    	  	      	 <dt>通讯录管理</dt>
    	  	      	 <dd><a href="address/list/page-1">通讯录列表</a></dd>
    	  	      	 <dd><a href="address/add">添加通讯录</a></dd>
    	  	      	 <dd><a href="">功能三</a></dd>    	  	      	
    	  	      	 <dt>分组管理</dt>
    	  	      	 <dd><a href="group/list/page-1">分组列表</a></dd>
    	  	      	 <dd><a href="group/add">添加分组</a></dd>
    	  	      	 <dd><a href="">功能三</a></dd>    	  	      	
    	  	      	 <dt>系统维护</dt>
    	  	      	 <dd><a href="user/edit/pwd">修改登录密码</a></dd>
    	  	      	 <dd><a href="">功能二</a></dd>
    	  	      	 <dd><a href="">功能三</a></dd>    	  	      
    	  	      </dl>
    	     </div>
    	     <div id="right">
    	  	    <div class="rightMenuTitle">
    	  	      	  <h3>添加通讯录</h3>
    	  	     </div>
    	  	     
    	  	     <form action=""  method="post">
	    	  	     <table>	    	  	     	  
	    	  	     	  <tr>
	    	  	     	  	 <td width="15%">姓 &nbsp;名:</td>
	    	  	     	  	 <td><input type="text" name="name" value="${addr.name }" size="10"/> * </td>    	  	     	  	 
	    	  	     	  </tr> 
	    	  	     	  <tr>
	    	  	     	  	 <td width="15%">手机号码:</td>
	    	  	     	  	 <td><input type="text" name="mobile" />  </td>    	  	     	  	 
	    	  	     	  </tr> 
	    	  	     	  <tr>
	    	  	     	  	 <td width="15%">固定电话:</td>
	    	  	     	  	 <td><input type="text" name="phone" />  </td>    	  	     	  	 
	    	  	     	  </tr>  
	    	  	     	  <tr>
	    	  	     	  	 <td width="15%"> QQ:</td>
	    	  	     	  	 <td><input type="text" name="qq" size="16" />  </td>    	  	     	  	 
	    	  	     	  </tr> 
	    	  	     	  <tr>
	    	  	     	  	 <td width="15%"> Email:</td>
	    	  	     	  	 <td><input type="text" name="email" size="35" />  </td>    	  	     	  	 
	    	  	     	  </tr> 
	    	  	     	   <tr>
	    	  	     	  	 <td width="15%"> 所属分组:</td>
	    	  	     	  	 <td>
	    	  	     	  	 	  <select name="group.id">
	    	  	     	  	 	     <c:forEach var="g" items="${groups }">
	    	  	     	  	 	           <option value="${g.id }">${g.name }</option>
	    	  	     	  	 	     </c:forEach>	    	  	     	  	 	  	
	    	  	     	  	 	  </select>
	    	  	     	  	 </td>    	  	     	  	 
	    	  	     	  </tr>   
	    	  	     	  <tr>	    	  	     	  	
	    	  	     	  	 <td colspan="2" style="text-align:center;">
	    	  	     	  	 	   <input class="btn" type="submit" value="提交"/>
	    	  	     	  	 	   <input class="btn" type="reset" value="重填"/>
	    	  	     	  	 </td>    	  	     	  	 
	    	  	     	  </tr>           	  	     	 
	    	  	    </table>
    	  	    </form>
    	     </div>
    	     <br/>
    	  </div>
    	  <div id="bottom">
    	  	  <span>
    	  	  	Copyright &copy; 1998 - 2013
              xxxx
              Inc. All Rights Reserved
            </span>
    	  </div>
    </div>
</body>
</html>
