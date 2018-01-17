package com.javaex.vo;

import java.sql.Date;

public class BookVO {
	private int bookId;
	private String bookName;
	private String bookPub;
	private String bookPubDate;
	private int authorId;
	private String authorName;
	private String authorDesc;
	
	public BookVO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public BookVO(int bookId, String bookName, String bookPub, String bookPubDate, int authorId) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookPub = bookPub;
		this.bookPubDate = bookPubDate;
		this.authorId = authorId;
	}
	
	public BookVO(String bookName, String bookPub, String bookPubDate, int authorId) {
		super();
		this.bookName = bookName;
		this.bookPub = bookPub;
		this.bookPubDate = bookPubDate;
		this.authorId = authorId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookPub() {
		return bookPub;
	}

	public void setBookPub(String bookPub) {
		this.bookPub = bookPub;
	}

	public String getBookPubDate() {
		return bookPubDate;
	}

	public void setBookPubDate(String bookPubDate) {
		this.bookPubDate = bookPubDate;
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
		return "BookVO [bookId=" + bookId + ", bookName=" + bookName + ", bookPub=" + bookPub + ", bookPubDate="
				+ bookPubDate + ", authorId=" + authorId + ", authorName=" + authorName + ", authorDesc=" + authorDesc
				+ "]";
	}

	


	
	
}
