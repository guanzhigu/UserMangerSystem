<%@ page language="java" import="java.util.*,java.sql.*,com.wst.model.*" pageEncoding="UTF-8" import="java.sql.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>欢迎页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
   <body>
   登录成功！恭喜恭喜！<%=request.getParameter("user") %><br/>
   <a href="login.jsp">返回重新登录</a>
   <hr>
   <h1>用户信息列表</h1>
   <%
      //定义四个分页会用到的变量
      int pageSize=3;
      int pageNow=1;  //默认显示第一页
     // int rowCount=0;  //该值从数据库查询
     //int pageCount=0;  //该值是通过
      
      //对pageNow进行修改 ，接收用户显示的页数（pageNow）
      String s_pageNow=request.getParameter("pageNow");
      
      if(s_pageNow!=null){
       //接收到pageNow
       pageNow=Integer.parseInt(s_pageNow);
      }
      //调用UserBeanCl的方法(创建一个UserBean的实例，然后调用他的某个方法 )，完成分页的显示
      //UserBeanCl ubc= new UserBeanCl();
      // ArrayList al = ubc.getUsersByPage(pageNow);
      //要显示的用户信息从request中
       ArrayList al =(ArrayList)request.getAttribute("resulst");
      //显示;
       %>
       <table border="1">
       <tr>
        <td>用户ID</td><td>用户名字</td><td>密码</td><td>E-Mail</td><td>用户级别</td>
       </tr>
       <%
        for(int i=0;i<al.size();i++){
        //从al中取出UserBean
        UserBean ub = (UserBean)al.get(i);
         %>
          <tr>
        <td><%=ub.getId() %></td><td><%=ub.getUsername() %></td>
        <td><%=ub.getPassword() %></td>
        <td><%=ub.getEmail() %></td>
        <td><%=ub.getGrade() %></td>
       </tr>
         <% 
        }
        %>
       </table>
      <% 
      //上一页
      if(pageNow!=1){
      out.println("<a href=UserClServlet?pageNow="+(pageNow-1)+">上一页</a>");
      }
      //得到pageCount
     
      String s_pageCount = (String)request.getAttribute("pageCount");
      int pageCount=Integer.parseInt(s_pageCount);
      //显示超链接
      for(int i=0;i<pageCount;i++){
       out.println("<a href=UserClServlet?pageNow>"+i+"</a>");
      }
      //下一页
      if(pageNow!=pageCount){
       out.println("<a href=UserClServlet?pageNow="+(pageNow+1)+">下一页</a>");
     }
    %>
  </body>
</html>
