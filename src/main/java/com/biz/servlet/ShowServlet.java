package com.biz.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.biz.entity.Student;
import com.biz.service.StudentRedisService;
import com.biz.util.PagesUtil;

/**
 * Servlet implementation class ShowServlet
 */
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数，得到当前页码
		String strNumber = request.getParameter("pageNumber");
		//判断当前页码
		int number = 1;
		if(strNumber!=null&&!strNumber.trim().equals(""))
		{
			number=Integer.parseInt(strNumber);
				
		}
		//调用服务类
		StudentRedisService studentRedisService  = new StudentRedisService();
		List<Student>  students = studentRedisService.QueryStudentByPages(number);
		//获得最大页码数
		Long maxPage = PagesUtil.pagesUtil();
		
		request.setAttribute("CURRENT", number);//当前页
		request.setAttribute("ADD", students);//集合
		request.setAttribute("MAX", maxPage);//最大页
		//实现跳转
		
		request.getRequestDispatcher("ShowStudent.jsp").forward(request, response);
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
