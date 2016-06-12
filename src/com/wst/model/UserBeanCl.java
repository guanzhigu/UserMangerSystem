//����һ�������࣬��Щ�˰�������bo����Ҫ�Ƿ�װ��user��ĸ��ֲ���
//��Ҫ����������ɾ�޸Ĳ�
package com.wst.model;

import com.wst.model.ConnDB;

import java.sql.*;
import java.util.*;

public class UserBeanCl {
  //�ռ�
	private Statement st =null;
	private ResultSet rs = null;
	private Connection ct = null;
	
	private int pageSize=3;
	private int rowCount=0;  //��ֵ�����ݿ��ѯ
	private int pageCount=0; //��ֵ��ͨ��pageSize��rowCount
	//���ط�ҳ������
	public int getPageCount(){
		try{
			//�õ�����
			ct=new ConnDB().getConn();
			st=ct.createStatement();
		    //4.��ѯ
		     rs = st.executeQuery("select count(*) from user");
		       
		     if(rs.next()){
		    	 rowCount=rs.getInt(1);
		     }
		     //����pageCount
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
	//�õ��û���Ҫ��ʾ���û���Ϣ����ҳ��
	public ArrayList getUsersByPage(int pageNow){
		ArrayList al =  new ArrayList();
		try{
			ct=new ConnDB().getConn();
			 //3.����Statement
		    st=ct.createStatement();
		    //4.�� ѯ
		    rs=st.executeQuery("select * from user where id limit "+(pageNow-1)*pageSize+","+pageSize);
		     
		    //��ע�⣬һ��Ҫrs.next(),��Ȼ��Ͳ���
		   while(rs.next()){
		   UserBean ub =new UserBean();
		   ub.setId(rs.getInt(1));
		   ub.setUsername(rs.getString(2));
		   ub.setPassword(rs.getString(3));
		   ub.setEmail(rs.getString(4));
		   ub.setGrade(rs.getInt(5));
		   //��ub���뵽arraylist��ȥ
		   al.add(ub);
		    } 
		    //��ѯ����Ҫ��ʾ�ļ�¼
		      
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			this.close();
		}
		return al;
	}
	//�ر�
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
	//��֤�û��Ƿ����
	public boolean checkUser(String u, String p){
		boolean b=false;
		try{
			 //�����ݿ���ȥ��ѯ�û�����֤
		    ct = new ConnDB().getConn(); 
		    //3.����Statement
		     st=ct.createStatement();
		    //4.��ѯ
		     rs = st.executeQuery("select password from user where username='"+u+"'");
		    //���ݽ���ж�
		    if(rs.next()){
		     //˵���û�������
		     if(rs.getString(1).equals(p)){
		      //�����ȣ�һ���ǺϷ���
		   b=true;
		     }
		    }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//�رմ򿪵ĸ�����Դ
			this.close();
		}
		return b;
	}
}
