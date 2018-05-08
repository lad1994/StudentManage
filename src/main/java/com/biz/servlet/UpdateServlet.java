package com.biz.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.entity.Student;
import com.biz.service.StudentRedisService;

/**
 * Servlet implementation class UpdateServlet
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ͨ��url�õ���Ӧ������ֵ
		String id=new String(request.getParameter("id").getBytes("ISO-8859-1"),"UTF-8");
		String name=new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
		String birthday=new String(request.getParameter("birthday").getBytes("ISO-8859-1"),"UTF-8");
		String description=new String(request.getParameter("description").getBytes("ISO-8859-1"),"UTF-8");
		String avgscore=new String(request.getParameter("avgscore").getBytes("ISO-8859-1"),"UTF-8");
		//ʹ�����ӳɸ�ʽ��Ҫ����ַ���
		String member =id+","+name+","+birthday+","+description+","+avgscore;
		System.out.println(member);
		Student student = new Student();
		//����ҵ����
		StudentRedisService studentRedisService = new StudentRedisService();
		studentRedisService.DeleteStudent(student, member);
		response.sendRedirect("UpdateStudent.jsp");
		
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
