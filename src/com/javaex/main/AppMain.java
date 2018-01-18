package com.javaex.main;

import java.util.List;
import java.util.Scanner;

import com.javaex.dao.AuthorDAO;
import com.javaex.dao.BookDAO;
import com.javaex.vo.AuthorVO;
import com.javaex.vo.BookVO;

public class AppMain {
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AuthorDAO aDao = new AuthorDAO();
		BookDAO bDao = new BookDAO();
		List<AuthorVO> al = null;
		List<BookVO> bl = null;
		
		Scanner sc = new Scanner(System.in);
				
		System.out.println("도서 장부 프로그램입니다.");
		
		menu : while(true) {
			System.out.println("메인메뉴를 선택해주세요.");
			System.out.println("1.저자 관리 2.도서 관리 3.프로그램 종료");
			String menuSelectNum = sc.nextLine();
			switch (menuSelectNum) {
			case "1" : 
				suvMenu : while(true) {
					System.out.println("서브메뉴를 선택해주세요.");
					System.out.println("1.저자 추가  2.저자 열람  3.저자 수정  4.저자 삭제  5.메인메뉴로 나가기");
					String suvMenuSelectNum = sc.nextLine();
					String authorId = "";
					String authorName = "";
					String authorDesc = "";
					AuthorVO avo = null;
					switch (suvMenuSelectNum) {
					case "1" :
						System.out.println("추가할 저자 정보를 기입해주세요.");
						System.out.println("저자명 >>");
						authorName = sc.nextLine();
						System.out.println("저자서술 >>");
						authorDesc = sc.nextLine();
						avo = new AuthorVO();
						avo.setAuthorName(authorName);
						avo.setAuthorDesc(authorDesc);
						aDao.insertAuthor(avo);
						break;
					case "2" :
						al = aDao.selectAllAuthor();
						for(AuthorVO a : al) {
							System.out.println(a);
						}
						break;
					case "3" :
						System.out.println("수정할 저자 번호를 선택해주세요.");
						System.out.println("저자번호 >>");
						authorId = sc.nextLine();
						System.out.println("수정할 저자 정보를 기입해주세요.");
						System.out.println("저자명 >>");
						authorName = sc.nextLine();
						System.out.println("저자서술 >>");
						authorDesc = sc.nextLine();
						avo = new AuthorVO();
						avo.setAuthorId(Integer.parseInt(authorId));
						avo.setAuthorName(authorName);
						avo.setAuthorDesc(authorDesc);
						aDao.updateAuthor(avo);
						break;
					case "4" :
						System.out.println("삭제할 저자 번호를 선택해주세요.");
						System.out.println("저자번호 >>");
						authorId = sc.nextLine();
						aDao.deleteAuthor(Integer.parseInt(authorId));
						break;
					case "5" :
						break suvMenu;
					default :
						System.out.println("잘못된 입력입니다.");
					}
				}
				break;
			case "2" :
				suvMenu : while(true) {
					System.out.println("서브메뉴를 선택해주세요.");
					System.out.println("1.도서 추가  2.도서 열람  3.도서 수정  4.도서 삭제  5.메인메뉴로 나가기");
					String suvMenuSelectNum = sc.nextLine();
					String bookId = "";
					String bookName = "";
					String bookPub = "";
					String bookPubDate = "";
					String authorId = "";
					switch(suvMenuSelectNum) {
					case "1" :
						System.out.println("추가할 도서 정보를 기입해주세요.");
						System.out.println("도서명 >>");
						bookName = sc.nextLine();
						System.out.println("출판사 >>");
						bookPub = sc.nextLine();
						System.out.println("출판일 >>");
						bookPubDate = sc.nextLine();
						System.out.println("저자번호 >>");
						authorId = sc.nextLine();
						BookVO bvo = new BookVO();
						bvo.setBookName(bookName);
						bvo.setBookPub(bookPub);
						bvo.setBookPubDate(bookPubDate);
						bvo.setAuthorId(Integer.parseInt(authorId));
						bDao.insertBook(bvo);
						break;
					case "2" :
						bl = bDao.selectAllBook();
						for(BookVO b : bl) {
							System.out.println(b);
						}
						break;
					case "3" :
						System.out.println("수정할 도서 번호를 선택해주세요.");
						System.out.println("도서번호 >>");
						bookId = sc.nextLine();
						System.out.println("도서명 >>");
						bookName = sc.nextLine();
						System.out.println("출판사 >>");
						bookPub = sc.nextLine();
						System.out.println("출판일 >>");
						bookPubDate = sc.nextLine();
						System.out.println("저자번호 >>");
						authorId = sc.nextLine();
						bvo = new BookVO();
						bvo.setBookId(Integer.parseInt(bookId));
						bvo.setBookName(bookName);
						bvo.setBookPub(bookPub);
						bvo.setBookPubDate(bookPubDate);
						bvo.setAuthorId(Integer.parseInt(authorId));
						bDao.updateBook(bvo);
						break;
					case "4" :
						System.out.println("삭제할 도서 번호를 선택해주세요.");
						System.out.println("도서번호 >>");
						bookId = sc.nextLine();
						bDao.deleteBook(Integer.parseInt(bookId));
						break;
					case "5" :
						break suvMenu;
					default :
						System.out.println("잘못된 입력입니다.");
					}
				}
				break;
			case "3" :
				System.out.println("프로그램을 종료합니다");
				break menu;
			default : 
				System.out.println("잘못된 입력입니다.");
			}
		}

		System.out.println("도서 장부 프로그램이 종료되었습니다.");
		
	}


}
