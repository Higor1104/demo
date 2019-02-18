package com.example.demo;

public class LogFuture  {

	private String type;
	
	private Integer age;

	public LogFuture(String type, Integer age) {
		this.type = type;
		this.age = age;
	}
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "type = " + this.type + " age = " + this.age; 
	}
	
}