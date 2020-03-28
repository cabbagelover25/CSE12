package com.cse.ds;
import java.util.List;

public class Response {
	
	private Integer responseCode;
	private String responseString;
	private Student content;
	private List<Student> listContent;
	
	public Integer getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseString() {
		return responseString;
	}
	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}
	public Student getContent() {
		return content;
	}
	public void setContent(Student content) {
		this.content = content;
	}
	public List<Student> getListContent() {
		return listContent;
	}
	public void setListContent(List<Student> listContent) {
		this.listContent = listContent;
	}
}
