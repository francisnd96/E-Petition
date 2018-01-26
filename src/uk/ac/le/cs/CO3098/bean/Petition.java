package uk.ac.le.cs.CO3098.bean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Petition {
	
	String title;
	String content;
	Date date;
	String creator;
	int sign;
	boolean isSigned;
	List<String> comments = new ArrayList<String>();
	int noComments;

	public int getNoComments() {
		return noComments;
	}

	public void setNoComments(int noComments) {
		this.noComments = noComments;
	}

	public Petition(int id, String title, String content, Date date, String creator, int sign) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.date = date;
		this.creator = creator;
		this.sign = sign;
	}
	
	public Petition(){
		
	}
	
	int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}
	
	public boolean getIsSigned() {
		return isSigned;
	}

	public void setIsSigned(boolean sign) {
		this.isSigned = sign;
	}
	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

}
