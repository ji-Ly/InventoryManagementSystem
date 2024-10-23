package com.hly.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hly.dao.ConnectionProvider;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setSize(1366, 768);
		setLocationRelativeTo(null);
		
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setBounds(1090, 184, 190, 58);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(1001, 284, 324, 13);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel_1);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(1001, 307, 324, 36);
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(1001, 359, 324, 13);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel_2);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(1001, 382, 324, 36);
		txtPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(txtPassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Lấy email và password từ ô nhập
				String email = txtEmail.getText();
				String password = txtPassword.getText();
				System.out.println(email);
				System.out.println(password);
				
				// Tạo biến tạm bằng 0
				int temp = 0;
				try {
					Connection connection = ConnectionProvider.getConnection();
					PreparedStatement prepsPreparedStatement = connection.prepareStatement("select * from appuser where email = ? and password = ? and status = 'Active'");
					
					prepsPreparedStatement.setString(1, email);
					prepsPreparedStatement.setString(2, password);
					
					ResultSet rs =  prepsPreparedStatement.executeQuery();
					
					while(rs.next()) {
						// Nếu có 1 kết quả trả về thì jframe hiện tại tắt visible
						temp = 1;
						setVisible(false);
						// Và tạo một cửa sổ mới Home 
						new Home(rs.getString("userRole")).setVisible(true);
					}
					
					// Nếu khoog có kết quả trả về nào cả thì show ra màn hình thông báo tức là sai email hoặc mat khau roi do
					if(temp == 0) {
						JOptionPane.showMessageDialog(null, "Incorrect email or password");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(1001, 439, 324, 29);
		btnNewButton.setIcon(new ImageIcon(Login.class.getResource("/com/hly/images/login.png")));
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Close");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int result = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Select:", JOptionPane.YES_NO_OPTION);
				
				if(result == 0) System.exit(0);
			}
		});
		btnNewButton_1.setBounds(1001, 478, 324, 29);
		btnNewButton_1.setIcon(new ImageIcon(Login.class.getResource("/com/hly/images/close.png")));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(Login.class.getResource("/com/hly/images/login-background.PNG")));
		lblNewLabel_3.setBounds(0, 0, 1366, 768);
		contentPane.add(lblNewLabel_3);
	}
}
