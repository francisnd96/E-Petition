package uk.ac.le.cs.CO3098.bean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Comment {
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getMp() {
		return mp;
	}
	public void setMp(String mp) {
		this.mp = mp;
	}
	String comment;
	String mp;
}