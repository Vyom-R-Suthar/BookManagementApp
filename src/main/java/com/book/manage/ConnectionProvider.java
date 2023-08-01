// Jai Swaminarayan KESHAV , Swami-Shreeji
package com.book.manage;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
	static Connection con;
	public static Connection createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/book_manage";
			String uname = "root";
			String password = "Kv139@mysql";
			
			con = DriverManager.getConnection(url,uname,password);
		} catch (Exception e) {
			// TODO: handle exception
		}
		 return con;
	}
}
