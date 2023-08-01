// Jai Swaminarayan KESHAV , Swami-Shreeji
package com.book.manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookDao {
	public static boolean insert(Book book) {
		boolean f = false;
		Connection con = ConnectionProvider.createConnection();
		
		String query = "insert into books values(?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getPublisher());
			pstmt.setString(4, book.getPrice());
			
			int rowsAffected = pstmt.executeUpdate();
			f = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f;
	}

	public static boolean delete(String title) {
		// TODO Auto-generated method stub
		boolean f = false;
		Connection con = ConnectionProvider.createConnection();
		
		String query = "delete from books where title = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, title);
			
			int rowsAffected = pstmt.executeUpdate();
			f = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f;
	}

	public static String update(String title, String price) {
		// TODO Auto-generated method stub
		String answer = null;
		Connection con = ConnectionProvider.createConnection();
		
		String query = "select * from books where title = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, title);
			ResultSet set = pstmt.executeQuery();
			if(set == null) {
				answer =  "Invalid book title";
			}else {
				String query2 = "update books set price = ? where title = ?";
				PreparedStatement pstmt2 = con.prepareStatement(query2);
				pstmt2.setString(1, price);
				pstmt2.setString(2, title);
				pstmt2.executeUpdate();
				answer =  "Successfully updated !";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answer;
	}

	public static List<Book> getList() {
		// TODO Auto-generated method stub
		Connection con = ConnectionProvider.createConnection();
		List<Book> books = new ArrayList<>();
		String query = "select * from books";
		try {
			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery(query);
			while(set.next()) {
				String title = set.getString(1);
				String author = set.getString(2);
				String publisher = set.getString(3);
				String price = set.getString(4);
				Book book = new Book();
				book.setTitle(title);
				book.setAuthor(author);
				book.setPublisher(publisher);
				book.setPrice(price);
				books.add(book);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}
	public static ArrayList<Book> getSortedPriceList() {
		// TODO Auto-generated method stub
		ArrayList<Book> books = (ArrayList<Book>) getList();
		Collections.sort(books, new PriceComparator());
		return books;
	}
	public static ArrayList<Book> getSortedTitleList() {
		// TODO Auto-generated method stub
		ArrayList<Book> books = (ArrayList<Book>) getList();
		Collections.sort(books, new TitleComparator());
		return books;
	}
	public static ArrayList<Book> searchParticular(String title){
		ArrayList<Book> books = new ArrayList<>();
		Connection con = ConnectionProvider.createConnection();
		
		String query = "select * from books where title = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, title);
			
			ResultSet set = pstmt.executeQuery();
			while(set.next()) {
				String btitle = set.getString(1);
				String author = set.getString(2);
				String publisher = set.getString(3);
				String price = set.getString(4);
				Book book = new Book();
				book.setTitle(title);
				book.setAuthor(author);
				book.setPublisher(publisher);
				book.setPrice(price);
				books.add(book);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}
}

class PriceComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		double d1 = Double.parseDouble(((Book)o1).getPrice());
		Double val1 = Double.valueOf(d1);
		double d2 = Double.parseDouble(((Book)o2).getPrice());
		Double val2 = Double.valueOf(d2);
		return val1.compareTo(val2);
	}
	
}

class TitleComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return (((Book)o1).getTitle()).compareTo(((Book)o2).getTitle());
	}
	
}
