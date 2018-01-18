package com.javaex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BookVO;

public class BookDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public void insertBook(BookVO vo) {
		// 0. import java.sql.*;
		connect();
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into book values(seq_book_id.nextval, ? , ? , ? , ?)"; // pstmt 객체를 사용하면 변수명 대신
																							// ? 를 사용하여 삽입 할 수 있다.
			pstmt = conn.prepareStatement(query); // conn 객체가 쿼리를 받아서 pstmt 라는 객체로 만들어 뱉는다
			pstmt.setString(1, vo.getBookName()); // 쿼리의 물음표 인덱스에 값을 채워주는 객체
			pstmt.setString(2, vo.getBookPub());
			pstmt.setString(3, vo.getBookPubDate());
			pstmt.setInt(4, vo.getAuthorId());
			int result = pstmt.executeUpdate(); // insert,update,delete 실행시 사용하는 함수 executeUpdate() 함수

			// 4.결과처리
			System.out.println("처리결과 : " + result);

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			close();
		}
	}

	public List<BookVO> selectAllBook() {
		// 0. import java.sql.*;
		connect();
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select * from book,author where book.author_id = author.author_id";
			pstmt = conn.prepareStatement(query); // conn 객체가 쿼리를 받아서 pstmt 라는 객체로 만들어 뱉는다
			rs = pstmt.executeQuery(); // DB에 있는 정보를 가져와 ResultSet 객체로 만든다
			
			List<BookVO> l = new ArrayList<BookVO>(); //DB 에서 받아오는 테이블의 레코드 값들을 JAVA 에서 사용 할 수 있게 List 객체에 BookVO 객체 별로 담는다
			// 4.결과처리
			while (rs.next()) {
				BookVO vo = new BookVO();
				int bookId = rs.getInt("book_id");
				String bookName = rs.getString("book_name");
				String bookPub = rs.getString("book_pub");
				String bookPubDate = String.valueOf(rs.getDate("book_pub_date"));
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				
				vo.setBookId(bookId);
				vo.setBookName(bookName);
				vo.setBookPub(bookPub);
				vo.setBookPubDate(bookPubDate);
				vo.setAuthorId(authorId);
				vo.setAuthorName(authorName);
				vo.setAuthorDesc(authorDesc);
				l.add(vo);
			}
			return l;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error:" + e);
			return null;
			
		} finally {
			close();
		}

	}
	
	public void updateBook(BookVO bvo) {
		// 0. import java.sql.*;
		connect();
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "update book set book_name = ? , book_pub = ? , book_pub_date = ? , author_id = ? where book_id = ?"; //pstmt 객체를 사용하면 변수명 대신 ? 를 사용하여 삽입 할 수 있다.
			pstmt = conn.prepareStatement(query); //conn 객체가 쿼리를 받아서 pstmt 라는 객체로 만들어 뱉는다
			pstmt.setString(1, bvo.getBookName());
			pstmt.setString(2, bvo.getBookPub());  //쿼리의 물음표 인덱스에 값을 채워주는 객체
			pstmt.setString(3, bvo.getBookPubDate());
			pstmt.setInt(4, bvo.getAuthorId());
			pstmt.setInt(5, bvo.getBookId());
			int result = pstmt.executeUpdate(); // insert,update,delete 실행시 사용하는 함수 executeUpdate() 함수
			
			// 4.결과처리
			System.out.println("처리 결과 : " + result);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			close();
		}
	}
	
	public void deleteBook(int bookId) {
		// 0. import java.sql.*;
		connect();
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "delete from book where book_id = ? "; //pstmt 객체를 사용하면 변수명 대신 ? 를 사용하여 삽입 할 수 있다.
			pstmt = conn.prepareStatement(query); //conn 객체가 쿼리를 받아서 pstmt 라는 객체로 만들어 뱉는다
			pstmt.setInt(1, bookId); //쿼리의 물음표 인덱스에 값을 채워주는 객체
			int result = pstmt.executeUpdate(); // insert,update,delete 실행시 사용하는 함수 executeUpdate() 함수
			
			// 4.결과처리	
			System.out.println("처리 결과 : " + result);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			close();
		}
		
	}

	private void connect() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; // server가 현재 pc 내에 존재하기때문에 localhost 라고 표현한 상태고
																// 서버가 다른 곳에 있을때는 ip주소를 기입해야 한다. 1521은 포트 번호를 뜻하며 ,
																// xe 는 오라클의 무료버전을 뜻함
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	private void close() {
		// 5. 자원정리

		try {
			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}

			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

}
