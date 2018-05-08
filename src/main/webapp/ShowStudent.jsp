<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.biz.entity.Student"%>
<%@page import="java.util.List"%>
<%@page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看学生信息</title>
<script type="text/javascript" src="jquery-3.3.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	$("#home").click(function(){
	
		 var currPage=$("#currPage").val();
		
		if(currPage==1){
			alert("当前是第一页");
		} else{
			
			$("#home").attr("href","ShowServlet?pageNumber=1");
			
		}
			
	}); 
	
	
	$("#last").click(function(){
		
		var currPage=$("#currPage").val();
		
		if(currPage<=1||currPage==null)
		{
			alert("当前是第一页");
			}else{
				currPage--;
				$("#last").attr("href","ShowServlet?pageNumber="+currPage);
			}
	});
	
	
	$("#next").click(function(){
		var currPage=$("#currPage").val();
		var maxPage = $("#sumPage").val();
		if(currPage>=maxPage||currPage==null){
			alert("当前是最后一页");
		}else{
			currPage++;
			$("#next").attr("href","ShowServlet?pageNumber="+currPage);
		}
	})
});


</script>
</head>
<body>
<table border="1">

	<tr>
	<td>平均分：按分数降序排序</td>
	
	<td>姓名</td>
	<td>出生日期</td>
	<td>备注</td>
	<td>id</td>
	</tr>
	
	
	
	
	
	
	
	
	
	
<% 
	List<Student> students =(List<Student>) request.getAttribute("ADD");
	Integer currentPage =(Integer)request.getAttribute("CURRENT");
	Long maxPage =(Long) request.getAttribute("MAX");
	
	if(students!=null&&students.size()>0)
	{
		for(int i=0;i<students.size();i++){
		Student student = students.get(i);
%>






	<tr>
	<td><%=student.getId() %></td>
	<td><%=student.getName() %></td>
	<td><%=student.getBirthday() %></td>
	<td><%=student.getDescription() %></td>
	<td><%=student.getAvgescore() %></td>
	<td><input type="button" name="update" onclick="window.location.href='UpdateServlet?id=<%=student.getId() %>&name=<%=student.getName() %>&birthday=<%=student.getBirthday() %>&description=<%=student.getDescription() %>&avgscore=<%=student.getAvgescore() %>'" value="修改学生信息"></td>
	
	<td><input type="button" name="del" onclick="window.location.href='DeleteServlet?id=<%=student.getId() %>&name=<%=student.getName() %>&birthday=<%=student.getBirthday() %>&description=<%=student.getDescription() %>&avgscore=<%=student.getAvgescore() %>'" value="删除该学生"></td>
	
	</tr>
	<%
	  }
  	}
  %>  
  
  
  
  	
  	<tr>
  	<td><a href="" id="home">第一页</a></td>
  	<td><a href="" id="last">上一页</a></td>
  	<td><a href="" id="next">下一页</a></td>
  	<td><a href="" id="end">最后一页</a></td>
  	
  	
  	<td>
  	
  	
  	当前页数：<input type="text" id="currPage"  readonly="value" value="<%=currentPage %>"/>
  	</td>
  	<td>
  	总页数：<input type="text" id="sumPage" readonly="value" value="<%=maxPage %>"/>
  	
  	</td>
  	<td></td>
  	
  		<tr><td>
	<input type="button" onclick="window.location.href='index.jsp'" value="回到主页">  
	<input type="button" onclick="window.location.href='AddStudent.jsp'" value="新增学生信息">  
  	</td></tr>
  	</tr>
  	
  	
  	
  	
  	
  	
  	
</table>
</body>
</html>