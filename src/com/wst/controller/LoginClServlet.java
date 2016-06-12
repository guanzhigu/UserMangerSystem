//����һ������������Ҫ��ɶ��û���ݵ���֤
//�����������ǲ���ȥ���ҵ���߼��ģ�����Ҫ��ȥ����ģ��model��ɶ����ݵĴ���
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
       //�õ��û���������
		String u = request.getParameter("username");
		String p = request.getParameter("password");
		System.out.println("u="+u+" p="+p);
		//ʹ��ģ�ͣ�UserBeanCl������ɶ��û�����֤
		//1.����һ��UserBeanCl����
		UserBeanCl ubc = new UserBeanCl();
		if(ubc.checkUser(u, p)){
			System.out.println("����ʹ�õĿ����������֤");
			
			//����תwelcome.jspҳ��ʱ����Ҫ��ʾ�����ݣ���welcome.jsp
			//׼����
			ArrayList al = ubc.getUsersByPage(1);
			int pageCount=ubc.getPageCount();
			//��al,pageCount����reguset��
			request.setAttribute("result", al);
			request.setAttribute("pageCount", pageCount+"");
			
			//�Ϸ�
			//ת��Ч�ʲ�����
			//response.sendRedirect("welcome.jsp");
			//��ΪsendRedirect����Ч�ʲ����ߣ����������˾��������ת���ķ���
			//���ַ���������Ч�ʸߣ�ͬʱrequest�еĶ��󻹿�������һҳ��ʹ��
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
		}else{
		//���Ϸ�
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
      //�϶�Ϊһ
		this.doGet(request, response);
	}
}
