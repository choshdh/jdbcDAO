package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.AuthorVO;
import com.javaex.vo.BookVO;

public class AuthorDAO {

	Connection conn = null; // db 와 java 를 연결 해주는 객체이면서 쿼리를 입력받아 쿼리 실행 및 준비 담당을 하는 PreparedStatement 객체를 뱉어낸다
	PreparedStatement pstmt = null; //쿼리 입력 준비를 담당하는 객체로써 자신(객체)이(가) 가진 기능들로 입력 되어있는 쿼리를 좀더 편하게 수정 할 수 있게 해준다.
	ResultSet rs = null; //쿼리를 실행한 결과값(객체)으로써,, DB에서 테이블이라고 불리는 정보 덩어리를 꺼내와서 java에서 사용 할 수 있게끔 ResultSet 이라는 정보 덩어리(객체)로 만든것

	public void insertAuthor(AuthorVO vo) {
		// 0. import java.sql.*;
		connect();
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into author values(seq_author_id.nextval, ? , ? )"; // pstmt 객체를 사용하면 변수명 대신 ? 를 사용하여
																						// 삽입 할 수 있다.
			pstmt = conn.prepareStatement(query); // conn 객체가 쿼리를 받아서 pstmt 라는 객체로 만들어 뱉어냈다
			pstmt.setString(1, vo.getAuthorName()); // 쿼리의 ? 인덱스에 값을 채워 주고있다.
			pstmt.setString(2, vo.getAuthorDesc());
			int result = pstmt.executeUpdate(); // insert,update,delete 실행시 사용하는 함수 executeUpdate() 함수
			
			// 4.결과처리
			System.out.println("처리 결과 : " + result);

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			close();
		}
	}

	public List<AuthorVO> selectAllAuthor() {
		// 0. import java.sql.*;
		connect();
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select * from author";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery(); // select만 유일하게 executeQuery() 함수를 사용한다.
			
			List<AuthorVO> l = new ArrayList<AuthorVO>();
			// 4.결과처리
			while(rs.next()) {
				AuthorVO vo = new AuthorVO();
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				
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
	
	
	public void updateAuthor(AuthorVO avo) {
		// 0. import java.sql.*;
		connect();
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "update author set author_name = ? , author_desc = ? where author_id = ? "; //pstmt 객체를 사용하면 변수명 대신 ? 를 사용하여 삽입 할 수 있다.
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, avo.getAuthorName());
			pstmt.setString(2, avo.getAuthorDesc());
			pstmt.setInt(3, avo.getAuthorId());
			int result = pstmt.executeUpdate(); // insert,update,delete 실행시 사용하는 함수 executeUpdate() 함수
			
			// 4.결과처리
			System.out.println("처리 결과 : " + result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error:" + e);
			
		} finally {
			close();
		}
	}
	
	
	public void deleteAuthor(int authorId) {
		// 0. import java.sql.*;
				connect();
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "delete from author where author_id = ? ";
			pstmt = conn.prepareStatement(query); 
			pstmt.setInt(1, authorId);
			int result = pstmt.executeUpdate();
			
			// 4.결과처리	
			System.out.println("처리 결과 : " + result);
		} catch (SQLIntegrityConstraintViolationException e) {
			// TODO Auto-generated catch block
			System.out.println("삭제 하려는 저자가 출간한 도서 정보가 있어 삭제 할 수 없습니다.");
			System.err.println("경고 : 해당 저자 삭제를 꼭 원한다면 저자의 출간 도서를 먼저 전부 삭제 해주시기 바랍니다. ");
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
