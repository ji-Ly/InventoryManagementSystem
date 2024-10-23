package com.hly.dao;

import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Tables {

	public static void main(String[] args) {

		//Lớp tabel dùng để tạo bảng
		Connection connection = null;
		Statement statement = null;
		try {
			connection = ConnectionProvider.getConnection();
			statement = connection.createStatement();

			// tạo bảng appuser

//			statement.executeUpdate(
//					"create table appuser(appuser_pk int AUTO_INCREMENT primary key, userRole varchar(50), name varchar(200), "
//					+ "mobileNumber varchar(50), email varchar(200), password varchar(50), address varchar(200), status varchar(50))");

			
			// Câu lệnh để thêm row
//			statement.execute(
//				
//					"insert into appuser(userRole, name, mobileNumber, email, password, address, status) value ('SuperAdmin','Super Admin','12345','superadmin@testemail.com','admin','india','true')");
		
//				
			//tao bang category 
	//		statement.executeUpdate(
	//				"create table category(category_pk int AUTO_INCREMENT primary key, name varchar(200))");
			
			//tao bang product
//			statement.executeUpdate(
//					"create table product(product_pk int AUTO_INCREMENT primary key, name varchar(200), quantity int, price int, description varchar(500), category_fk int)");
//					
			
			//tao bang customer
//			statement.executeUpdate("create table customer(customer_pk int AUTO_INCREMENT primary key, name varchar(200), mobileNumber varchar(50), email varchar(200))");

			
			statement.executeUpdate("create table orderDetails(order_pk int AUTO_INCREMENT primary key, orderID varchar(200), customer_fk int, orderDate varchar(200), totalPaid int)");
			
			JOptionPane.showMessageDialog(null, "Table created successfully");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
