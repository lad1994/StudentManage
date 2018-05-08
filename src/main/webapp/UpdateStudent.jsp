<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改学生信息</title>
</head>
<body>
<h1>学生信息修改页面</h1>
<form action="AddServlet" method="post">
<table border="1">


	

 <tr>
	<td>id：<input type="text" name="avgscore" ></td>
 </tr>
  <tr>
	<td>姓名：<input type="text" name="name" ></td>
 </tr>	
  <tr>
	<td>出生日期：<input type="text" name="birthday" placeholder="请输入年-月-日"></td>
 </tr>
  <tr>
	<td>备注：<input type="text" name="description" ></td>
 </tr>
  <tr>
	<td>平均分：<input type="text" name="id" ></td>
 </tr>	
 
 
 <tr>
	<td><button type="submit">确认修改信息</button></td>
 </tr>
</table>
</body>
</html>