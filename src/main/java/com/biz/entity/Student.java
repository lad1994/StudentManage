package com.biz.entity;

import java.sql.Date;

public class Student {

	//student��ʵ����
	private String id;
	private String name;
	private String birthday;
	private String description;
	private int avgescore;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAvgescore() {
		return avgescore;
	}
	public void setAvgescore(int avgescore) {
		this.avgescore = avgescore;
	}
	public Student(String id, String name, String birthday, String description, int avgescore) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.description = description;
		this.avgescore = avgescore;
	}
	public Student() {
		super();
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", birthday=" + birthday + ", description=" + description
				+ ", avgescore=" + avgescore + "]";
	}
	
}
