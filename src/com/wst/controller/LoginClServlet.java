//这是一个控制器，主要完成对用户身份的验证
//控制器本身是不会去完成业务逻辑的，他主要是去调用模型model完成对数据的处理
package com.wst.controller;

import java.io.IOException;

import com.wst.model.*;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginClServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       //得到用户名和密码
		String u = request.getParameter("username");
		String p = request.getParameter("password");
		System.out.println("u="+u+" p="+p);
		//使用模型（UserBeanCl），完成对用户的验证
		//1.创建一个UserBeanCl对象
		UserBeanCl ubc = new UserBeanCl();
		if(ubc.checkUser(u, p)){
			System.out.println("这是使用的控制器完成验证");
			
			//在跳转welcome.jsp页面时，就要显示的数据，给welcome.jsp
			//准备好
			ArrayList al = ubc.getUsersByPage(1);
			int pageCount=ubc.getPageCount();
			//将al,pageCount放入reguset中
			request.setAttribute("result", al);
			request.setAttribute("pageCount", pageCount+"");
			
			//合法
			//转向，效率并不高
			//response.sendRedirect("welcome.jsp");
			//因为sendRedirect方法效率并不高，所以软件公司常常试用转发的方法
			//这种方法，他的效率高，同时request中的对象还可以在下一页面使用
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
		}else{
		//不合法
			//response.sendRedirect("login.jsp");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
      //合二为一
		this.doGet(request, response);
	}
}
