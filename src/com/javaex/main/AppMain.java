package com.javaex.main;

import java.util.List;

import com.javaex.dao.AuthorDAO;
import com.javaex.dao.BookDAO;
import com.javaex.vo.AuthorVO;
import com.javaex.vo.BookVO;

public class AppMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AuthorVO avo = new AuthorVO("조승현", "31살");
		BookVO bvo = new BookVO("책책", "승현출판사", "2018-01-03", 1);
		
		AuthorDAO aDao = new AuthorDAO();
		aDao.insertAuthor(avo);
		
		BookDAO bDao = new BookDAO();
		bDao.insertBook(bvo);
		
		List<AuthorVO> l1 = aDao.selectAuthor();
		System.out.println(l1.toString());
		
		List<BookVO> l2 = bDao.selectBook();
		System.out.println(l2.toString());
		
	}

}
