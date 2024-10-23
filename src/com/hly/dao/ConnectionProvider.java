package com.hly.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
	//Tạo một nhà cung cấp connection
	public static Connection getConnection() {
		
		try {
			//Đăng kí driver của mysql
			Class.forName("com.mysql.cj.jdbc.Driver");
			//tạo một anh chàng cho phép connection
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Inventory","root","12345");
			
			// trả về connection
			return connection;
		} catch (Exception e) {
			
			// nếu khong ket noi duco thi tra ve null
			return null;
		}
	}
	
	
	public static void main(String[] args) {
		
		if (getConnection() == null) {
			System.out.println("Thua á"); }
			else
				System.out.println("TK");
		
	}

}
