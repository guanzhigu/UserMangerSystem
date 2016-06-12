//这是一个处理类，有些人把他叫做bo，主要是封装对user表的各种操作
//主要就是增加增删修改查
package com.wst.model;

import com.wst.model.ConnDB;

import java.sql.*;
import java.util.*;

public class UserBeanCl {
  //空间
	private Statement st =null;
	private ResultSet rs = null;
	private Connection ct = null;
	
	private int pageSize=3;
	private int rowCount=0;  //该值从数据库查询
	private int pageCount=0; //该值是通过pageSize和rowCount
	//返回分页的总数
	public int getPageCount(){
		try{
			//得到链接
			ct=new ConnDB().getConn();
			st=ct.createStatement();
		    //4.查询
		     rs = st.executeQuery("select count(*) from user");
		       
		     if(rs.next()){
		    	 rowCount=rs.getInt(1);
		     }
		     //计算pageCount
			   if(rowCount%pageSize==0){
			     pageCount=rowCount/pageSize;
			    } else{
			     pageCount=rowCount/pageSize+1;
			    }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close();
		}
		return pageCount;
		
	}
	//得到用户需要显示的用户信息（分页）
	public ArrayList getUsersByPage(int pageNow){
		ArrayList al =  new ArrayList();
		try{
			ct=new ConnDB().getConn();
			 //3.创建Statement
		    st=ct.createStatement();
		    //4.查 询
		    rs=st.executeQuery("select * from user where id limit "+(pageNow-1)*pageSize+","+pageSize);
		     
		    //请注意，一定要rs.next(),不然你就惨了
		   while(rs.next()){
		   UserBean ub =new UserBean();
		   ub.setId(rs.getInt(1));
		   ub.setUsername(rs.getString(2));
		   ub.setPassword(rs.getString(3));
		   ub.setEmail(rs.getString(4));
		   ub.setGrade(rs.getInt(5));
		   //将ub放入到arraylist里去
		   al.add(ub);
		    } 
		    //查询出需要显示的记录
		      
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			this.close();
		}
		return al;
	}
	//关闭
	public void close(){
		try{
			if(rs!=null){
				rs.close();
				rs=null;
			}
			if(st!=null){
				st=null;
			}
			if(ct!=null){
				ct=null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//验证用户是否存在
	public boolean checkUser(String u, String p){
		boolean b=false;
		try{
			 //到数据库中去查询用户的验证
		    ct = new ConnDB().getConn(); 
		    //3.创建Statement
		     st=ct.createStatement();
		    //4.查询
		     rs = st.executeQuery("select password from user where username='"+u+"'");
		    //根据结果判断
		    if(rs.next()){
		     //说明用户名存在
		     if(rs.getString(1).equals(p)){
		      //如果相等，一定是合法的
		   b=true;
		     }
		    }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//关闭打开的各种资源
			this.close();
		}
		return b;
	}
}
