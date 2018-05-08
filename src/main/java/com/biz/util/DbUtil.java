package com.biz.util;

import com.biz.entity.Student;

public class DbUtil {

	public static Student backResults(String body)
	{
		   Student student = new Student();
		   String[] arr = body.split(",");
		   student.setId(arr[0]);
		   student.setName(arr[1]);
		   student.setBirthday(arr[2]);
		   student.setDescription(arr[3]);
		   student.setAvgescore(Integer.parseInt(arr[4]));		   
		   return student;
	}
}
