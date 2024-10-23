package com.hly.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.hly.dao.ConnectionProvider;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableUser;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_1;
	private JTextField txtName;
	private JTextField txtMobileNumber;
	private JTextField txtEmail;
	private JTextField txtAddress;
	private JTextField txtPassword;
	private JButton btnSave;
	private JButton btnUpdate;
	private JButton btnReset;
	private JButton btnclose;
	private JLabel lblNewLabel_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageUser frame = new ManageUser();
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
	//int pk để khi click chuột vào row trong table thì lấy đucợ value
	private int appuserPk = 0;
	
	public ManageUser() {
		

		
	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tableUser = new JTable();
		
		JLabel lblNewLabel = new JLabel("Manage User");
		lblNewLabel.setBounds(304, 10, 266, 86);
		lblNewLabel.setFont(new Font("Impact", Font.BOLD, 37));
		contentPane.add(lblNewLabel);
		
		
		//Tạo scrollPane để chứa table
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 106, 444, 400);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(tableUser);
		tableUser.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Mobile Number", "Email", "Address", "Status"
			}
		));
		tableUser.getColumnModel().getColumn(0).setPreferredWidth(69);
		tableUser.getColumnModel().getColumn(1).setPreferredWidth(101);
		tableUser.getColumnModel().getColumn(2).setPreferredWidth(83);
		tableUser.getColumnModel().getColumn(3).setPreferredWidth(151);
		tableUser.getColumnModel().getColumn(4).setPreferredWidth(98);
		
		lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(483, 106, 103, 13);
		contentPane.add(lblNewLabel_1);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtName.setBounds(464, 129, 362, 25);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Mobile");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(483, 164, 103, 13);
		contentPane.add(lblNewLabel_2);
		
		txtMobileNumber = new JTextField();
		txtMobileNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtMobileNumber.setBounds(464, 187, 362, 25);
		contentPane.add(txtMobileNumber);
		txtMobileNumber.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(483, 222, 103, 13);
		contentPane.add(lblNewLabel_3);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtEmail.setBounds(464, 249, 362, 25);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(483, 284, 103, 13);
		contentPane.add(lblNewLabel_4);
		
		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtAddress.setBounds(464, 307, 362, 25);
		contentPane.add(txtAddress);
		txtAddress.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Status");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(483, 400, 103, 13);
		contentPane.add(lblNewLabel_5);
		
		JComboBox comboBoxStatus = new JComboBox();
		comboBoxStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBoxStatus.setModel(new DefaultComboBoxModel(new String[] {"Active", "Inactive"}));
		comboBoxStatus.setBounds(464, 423, 85, 21);
		contentPane.add(comboBoxStatus);
		
		JLabel lblNewLabel_6 = new JLabel("Password");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(483, 342, 103, 13);
		contentPane.add(lblNewLabel_6);
		
		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtPassword.setBounds(464, 365, 362, 25);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String mobileNumber = txtMobileNumber.getText();
				String email = txtEmail.getText();
				String address = txtAddress.getText();
				String password = txtPassword.getText();
				String status = (String) comboBoxStatus.getSelectedItem();
				
				
				//Kiểm tra xem các trường có rông không
				//Nếu rỗng thì in ra message
				if(validateFields("new")) {
					JOptionPane.showMessageDialog(null, "All fields are required");
					
					//con dap ung yeu cau thì them vao dtb
				}else {
					try {
						Connection connection = ConnectionProvider.getConnection();
						PreparedStatement preparedStatement = connection.prepareStatement("insert into appuser (userRole, name, mobileNumber, email, password, address, status) value ('Admin', ?, ?, ?, ?, ?, ?)");
						preparedStatement.setString(1, name);
						preparedStatement.setString(2, mobileNumber);
						preparedStatement.setString(3, email);
						preparedStatement.setString(4, password);
						preparedStatement.setString(5, address);
						preparedStatement.setString(6, status);
						
						preparedStatement.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "User added successfully");
						//Save xong thì tat jframe 
						setVisible(false);
						// rồi mở lại để windowlistioner reset lại bảng
						new ManageUser().setVisible(true);
					} catch (Exception e2) {
						e2.printStackTrace();
						
					}
				}
				
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSave.setBounds(464, 472, 75, 25);
		contentPane.add(btnSave);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String mobileNumber = txtMobileNumber.getText();
				String email = txtEmail.getText();
				String address = txtAddress.getText();
				String password = txtPassword.getText();
				String status = (String) comboBoxStatus.getSelectedItem();
				
				
				//Kiểm ra xem các trường có rỗng không, trừ pw, vì pw không cho edit
				if(validateFields("edit")) {
					JOptionPane.showMessageDialog(null, "All fields are required");
				}else {
					try {
						Connection connection = ConnectionProvider.getConnection();
						PreparedStatement preparedStatement = connection.prepareStatement("update appuser set name = ?, mobileNumber = ?, email = ?, address = ?, status = ? where appuser_pk = ?");
						preparedStatement.setString(1, name);
						preparedStatement.setString(2, mobileNumber);
						preparedStatement.setString(3, email);
						preparedStatement.setString(4, address);
						preparedStatement.setString(5, status);
						//appuserPK khi click chuotj vào row là đã có
						preparedStatement.setInt(6, appuserPk);
						
						preparedStatement.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "User updated successfully");
						setVisible(false);
						//xong thì new lại jframe
						new ManageUser().setVisible(true);
					} catch (Exception e2) {
						e2.printStackTrace();
						
					}
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUpdate.setBounds(559, 472, 80, 25);
		contentPane.add(btnUpdate);
		
		btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		//Nhấn nút reset thì tắt frame hiện tại rồi mở lại
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new ManageUser().setVisible(true);
			}
		});
		btnReset.setBounds(656, 472, 75, 25);
		contentPane.add(btnReset);
		
		//Nhấn nút này thì tắt jframe hiện tại
		btnclose = new JButton("Close");
		btnclose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnclose.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnclose.setBounds(751, 472, 75, 25);
		contentPane.add(btnclose);
		
		lblNewLabel_7 = new JLabel("New label");
		lblNewLabel_7.setIcon(new ImageIcon(ManageUser.class.getResource("/com/hly/images/All_page_Background.png")));
		lblNewLabel_7.setBounds(0, 0, 850, 600);
		contentPane.add(lblNewLabel_7);
		
		tableUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Tạo một cái index lấy vị trí của row
				int index = tableUser.getSelectedRow();
				//Tạo một model để lấy đối tượng trong bảng
				TableModel model = tableUser.getModel();
				
				//Lấy value của row index tạ trường 0 biến thành String 
				String id = model.getValueAt(index, 0).toString();
				appuserPk = Integer.parseInt(id);
				
				String name = model.getValueAt(index, 1).toString();
				//Ô nhập tên set thành name lấy trong bảng
				txtName.setText(name);
				
				String mobileNumber = model.getValueAt(index, 2).toString();
				txtMobileNumber.setText(mobileNumber);
				
				String email = model.getValueAt(index, 3).toString();
				txtEmail.setText(email);
				
				String address = model.getValueAt(index, 4).toString();
				txtAddress.setText(address);
				
				String status = model.getValueAt(index, 5).toString();
				//Xóa hết trạng thái
				comboBoxStatus.removeAllItems();
				//Nếu là active thì...
				if(status.equals("Active")) {
					comboBoxStatus.addItem("Active");
					comboBoxStatus.addItem("Inactive");
				}else {
					comboBoxStatus.addItem("Inactive");
					comboBoxStatus.addItem("Active");
				}
				
				//Chọn vô row xem thì password không cho sửa và đổi màu lại
				txtPassword.setEditable(false);
				txtPassword.setBackground(Color.DARK_GRAY);
				
				//Nút lưu bị vô hiệu hóa
				btnSave.setEnabled(false);
				//Cho phép update
				btnUpdate.setEnabled(true);
			}
		});
		
		
		
//		contentPane.addComponentListener(new ComponentAdapter() {
//			@Override
//			public void componentShown(ComponentEvent e) {
//				System.out.println("HIHI");
//				
//				DefaultTableModel model = (DefaultTableModel) tableUser.getModel();
//				try {
//					Connection connection = ConnectionProvider.getConnection();
//					PreparedStatement prepareStatement = connection.prepareStatement("select * from appuser where userRole = ?");
//					prepareStatement.setString(1,"Admin");
//					ResultSet rs = prepareStatement.executeQuery();
//					
//					while(rs.next()) {
//						model.addRow(new Object[] {rs.getString("appuser_PK"), rs.getString("name"), rs.getString("mobileNumber"), rs.getString("email"), rs.getString("address"), rs.getString("status")});
//					}
//				} catch (Exception e2) {
//					JOptionPane.showMessageDialog(null, e);
//				}
//				
//				btnUpdate.setEnabled(false);
//				
//			}
//		});
//		
		
		//Tạo một cái windows listiner mặc định mở jframe lên nó sẽ chạy
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowOpened(java.awt.event.WindowEvent e) {
		       
		    	//Tạo một bảng model rồi lấy dữ liệu từ tableUser
		        DefaultTableModel model = (DefaultTableModel) tableUser.getModel();
				try {
					Connection connection = ConnectionProvider.getConnection();
					PreparedStatement prepareStatement = connection.prepareStatement("select * from appuser where userRole = ?");
					prepareStatement.setString(1,"Admin");
					ResultSet rs = prepareStatement.executeQuery();
					//Mở frame lên dữ liệu mà có thì show ra
					while(rs.next()) {
						model.addRow(new Object[] {rs.getString("appuser_PK"), rs.getString("name"), rs.getString("mobileNumber"), rs.getString("email"), rs.getString("address"), rs.getString("status")});
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e);
				}
				//mạc định update false
				btnUpdate.setEnabled(false);
		    }
		});
		
	
	}
	
	//Kiểm tra xem trường dữ liệu gì
	private boolean validateFields(String formType) {
		//Nếu là edit và mọi cái không rỗng thì false;
		if(formType.equals("edit") && !txtName.getText().equals("") && !txtMobileNumber.getText().equals("") && !txtEmail.getText().equals("") && !txtAddress.getText().equals("")) {
			return false;
		//Nếu là new là mouj thứ khong rỗng thì false;
		}else if(formType.equals("new") && !txtName.getText().equals("") && !txtMobileNumber.getText().equals("") && !txtEmail.getText().equals("") && !txtAddress.getText().equals("") && !txtPassword.getText().equals("")) {
			return false;
		}
		return true;
	}
}
