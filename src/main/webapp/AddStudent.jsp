<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增学生信息</title>

 <script type="text/javascript">
 function myCheck()
 {
   for(var i=0;i<document.form1.elements.length-1;i++)
   {
    if(document.form1.elements[i].value=="")
    {
      alert("未填写完整无法提交");
      document.form1.elements[i].focus();
      return false;
    }
   }
   return true;
   
 }

</script>

</head>
<body>
<h1>新增学生信息页面</h1>
<form action="AddServlet" method="post" name="form1" onSubmit="return myCheck()">
<table border="1">
 	
	


 <tr>
	<td>id：<input type="text" name="avgscore" id="t5" ></td>
 </tr>

  <tr>
	<td>姓名：<input type="text" name="name" id="t2" ></td>
 </tr>
  <tr>
	<td>出生日期：<input type="text" name="birthday" id="t3" placeholder="请输入年-月-日"></td>
 </tr>	
  <tr>
	<td>备注：<input type="text" name="description" id="t4" ></td>
 </tr>
  <tr>
	<td>平均分：<input type="text" name="id" id="t1" ></td>
 </tr>
 
 
 
 
 <tr>
	<td><button type="submit" >新增学生信息</button></td>
 </tr>
</table>
</form>
</body>
</html>