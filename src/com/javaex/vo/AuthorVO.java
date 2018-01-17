package com.javaex.vo;

public class AuthorVO {
	private int authorId;
	private String authorName;
	private String authorDesc;
	
	public AuthorVO() {
		// TODO Auto-generated constructor stub
	}
	
	public AuthorVO(String authorName, String authorDesc) {
		super();
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}
	
	public AuthorVO(int authorId, String authorName, String authorDesc) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAuthorDesc() {
		return authorDesc;
	}
	public void setAuthorDesc(String authorDesc) {
		this.authorDesc = authorDesc;
	}

	@Override
	public String toString() {
		return "AuthorVO [authorId=" + authorId + ", authorName=" + authorName + ", authorDesc=" + authorDesc + "]";
	}

	
}
