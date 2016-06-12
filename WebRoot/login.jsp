<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body bgcolor="lavender">
   <center>
     <h1>用户登录</h1>
     <hr>
     <!--<form action="loginCl.jsp" method="post">-->
     <form action="LoginClServlet" method="post">
      <table>
       <tr>
        <td>用户名：</td>
        <td><input type="text" name="username"></td>
       </tr>
       <tr>
        <td>密&nbsp;&nbsp;码：</td>
        <td><input type="password" name="password"></td>
       </tr>
       <tr>
        <td></td>
        <td><input type="submit" value="登录">&nbsp;&nbsp;
        <input type="reset" value="重置"></td>
       </tr>
      </table>
      </form>
   </center> 
  </body>
</html>
