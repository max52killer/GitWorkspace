<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
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
    height : 440px;
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
    height : 440px;
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
     margin-left : 10px;
     margin-right : 10px;
     width : 810px;
  }
  
  #right table, #right table td ,#right table th {
    border : 1px solid #e6e6e6;
    border-collapse : collapse;    
    text-align : center;
    padding : 5px;  
  }
  
  #right .pager {
     text-align : right;
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
    	  	      	 <dd><a href="javascript:void(0);">功能三</a></dd>    	  	      	
    	  	      	 <dt>分组管理</dt>
    	  	      	 <dd><a href="group/list/page-1">分组列表</a></dd>
    	  	      	 <dd><a href="group/add">添加分组</a></dd>
    	  	      	 <dd><a href="javascript:void(0);">功能三</a></dd>    	  	      	
    	  	      	 <dt>系统维护</dt>
    	  	      	 <dd><a href="user/edit/pwd">修改登录密码</a></dd>
    	  	      	 <dd><a href="javascript:void(0);">功能二</a></dd>
    	  	      	 <dd><a href="javascript:void(0);">功能三</a></dd>    	  	      
    	  	      </dl>
    	     </div>
    	     <div id="right">
    	  	    <div class="rightMenuTitle">
    	  	      	  <h3>通讯录列表</h3>
    	  	     </div>
    	  	     <table>
    	  	     	  
    	  	     	  <tr>
    	  	     	  	 <th>姓名</th>
    	  	     	  	 <th>移动电话</th>
    	  	     	  	 <th>固定电话</th>
    	  	     	  	 <th>QQ</th>
    	  	     	  	 <th>Email</th>
    	  	     	  	 <th>分组</th>
    	  	     	  	 <th>编辑</th>
    	  	     	  	 <th>删除</th>
    	  	     	  </tr>
    	  	     	  <c:forEach  var="addr" items="${pb.rows }">
    	  	     	    <tr>
    	  	     	  	 <td>${addr.name }</td>
    	  	     	  	 <td>${addr.mobile }</td>
    	  	     	  	 <td>${addr.phone }</td>
    	  	     	  	 <td>${addr.qq }</td>
    	  	     	  	 <td>${addr.email }</td>
    	  	     	  	 <td>${addr.group.name }</td>
    	  	     	  	 <td> <a href="address/${addr.id }/edit"><img src="img/icon-edit.gif"/></a> </td>
    	  	     	  	 <td> <a href="address/${addr.id }/delete" onclick="return confirm('确定要删除吗?')"><img src="img/radio.GIF"/></a> </td>
    	  	     	   </tr>
    	  	     	  </c:forEach>
    	  	     	
    	  	     	  <tr>
    	  	     	  	<td colspan="8" align="right">
    	  	     	  		 <div class="pager">
    	  	     	  		  总${pb.total }条记录 每页${pb.pageSize }条 当前第${pb.page }/${pb.pageCount }页 
    	  	     	  		  <c:choose>
    	  	     	  		      <c:when test="${pb.first }"> 首页 上一页  </c:when>
    	  	     	  		      <c:otherwise>
    	  	     	  		          <a href="address/list/page-1">首页 </a> <a  href="address/list/page-${pb.page-1 }">上一页  </a>
    	  	     	  		      </c:otherwise>
    	  	     	  		  </c:choose>
    	  	     	  		<c:choose>
    	  	     	  		   <c:when test="${pb.last }"> 下一页 尾页</c:when>
    	  	     	  		   <c:otherwise>
    	  	     	  		      <a href="address/list/page-${pb.page+1 }">下一页</a> <a href="address/list/page-${pb.pageCount }">尾页</a>
    	  	     	  		   </c:otherwise>
    	  	     	  		</c:choose>
    	  	     	  		  <select onchange="location.href='address/list/page-' + this.value">
    	  	     	  		     <c:forEach var="i" begin="1" end="${pb.pageCount }">
    	  	     	  		          <option value="${i }"  ${ pb.page == i ? "selected='selected'" : "" }  >${i }/${pb.pageCount }页</option>
    	  	     	  		     </c:forEach>
    	  	     	  		  	
    	  	     	  		  </select>
    	  	     	  		 <div>  
    	  	     	  	</td>
    	  	     	  </tr>
    	  	    </table>
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
