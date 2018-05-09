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
		//��ȡ�������õ���ǰҳ��
		String strNumber = request.getParameter("pageNumber");
		//�жϵ�ǰҳ��
		int number = 1;
		if(strNumber!=null&&!strNumber.trim().equals(""))
		{
			number=Integer.parseInt(strNumber);
				
		}
		//���÷�����
		StudentRedisService studentRedisService  = new StudentRedisService();
		List<Student>  students = studentRedisService.QueryStudentByPages(number);
		//������ҳ����
		Long maxPage = PagesUtil.pagesUtil();
		
		request.setAttribute("CURRENT", number);//��ǰҳ
		request.setAttribute("ADD", students);//����
		request.setAttribute("MAX", maxPage);//���ҳ
		//ʵ����ת
		
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
