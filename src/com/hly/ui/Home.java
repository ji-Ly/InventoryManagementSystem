package com.hly.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home("a");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	
	}

	public Home(String role) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 768);
		contentPane = new JPanel();
		System.out.println(role);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		// Nhấn nút User thì mở ra Jframe của manageUser
		JButton btnUser = new JButton("User");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManageUser().setVisible(true);
			}
		});
		btnUser.setIcon(new ImageIcon(Home.class.getResource("/com/hly/images/Users.png")));
		btnUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUser.setBounds(20, 10, 145, 51);
		contentPane.add(btnUser);
		
		JButton btnNewButton_1 = new JButton("Category");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Mo ra manage câtegory
				
				new ManagementCategory().setVisible(true);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Home.class.getResource("/com/hly/images/category.png")));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(206, 10, 152, 50);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Product");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManageProduct().setVisible(true);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(Home.class.getResource("/com/hly/images/product.png")));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_2.setBounds(399, 10, 145, 50);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Customer");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManageCustomer().setVisible(true);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(Home.class.getResource("/com/hly/images/customers.png")));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_3.setBounds(592, 10, 165, 50);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Order");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManageOrder().setVisible(true);
			}
		});
		btnNewButton_4.setIcon(new ImageIcon(Home.class.getResource("/com/hly/images/Orders.png")));
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_4.setBounds(796, 10, 145, 50);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("View Order");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewOrder().setVisible(true);
			}
		});
		btnNewButton_5.setIcon(new ImageIcon(Home.class.getResource("/com/hly/images/View-orders.png")));
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_5.setBounds(990, 10, 172, 50);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Logout");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// tạo một cái JOptionPane để thông báo cho người dùng chọn yes hay no
				int response = JOptionPane.showConfirmDialog(null, "Do you want to logout application", "Select", JOptionPane.YES_NO_OPTION);
				//Nếu chọn yes thì trả vè 0
				//SetVisible false rồi trả lại trang Login set visible = true;
				if(response == 0) {
					setVisible(false);
					new Login().setVisible(true);
				}
			
						
			}
		});
		btnNewButton_6.setIcon(new ImageIcon(Home.class.getResource("/com/hly/images/Exit.png")));
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_6.setBounds(1197, 10, 145, 50);
		contentPane.add(btnNewButton_6);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Home.class.getResource("/com/hly/images/home_background.png")));
		lblNewLabel.setBounds(10, -16, 1366, 768);
		contentPane.add(lblNewLabel);
		
		
		
		if(role.equals("admin")) {
			btnUser.setVisible(false);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
