package com.biz.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.biz.entity.Student;
import com.biz.service.StudentRedisService;

/**
 * Servlet implementation class AddServlet
 */
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//设置页面编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
        //获取客户端的数据
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String birthday =request.getParameter("birthday");					
		String description = request.getParameter("description");
		String avgscore1 = request.getParameter("avgscore");
		//redis中设置的String类型强转为int类型使用
		int avgscore = Integer.parseInt(avgscore1);
		
		
		//数据封装
		Student student = new Student();
		student.setId(id);
		student.setName(name);		
		student.setBirthday(birthday);
		student.setDescription(description);
		student.setAvgescore(avgscore);
		String member = id+","+name+","+birthday+","+description+","+avgscore1;
		
		
		
		//调用业务类，通过封装的数据，新增学生信息
		StudentRedisService studentRedisService = new StudentRedisService();
		studentRedisService.AddStudents(student, Double.parseDouble(id), member);
		request.getRequestDispatcher("ShowServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
