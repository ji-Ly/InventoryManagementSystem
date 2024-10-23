package com.hly.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.attribute.AclEntry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.hly.dao.ConnectionProvider;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageCustomer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableCustomer;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField txtName;
	private JTextField txtMobileNumber;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageCustomer frame = new ManageCustomer();
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
	private int customerPK = 0;
	
	public ManageCustomer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Manage Customer");
		lblNewLabel.setFont(new Font("Impact", Font.BOLD, 39));
		lblNewLabel.setBounds(275, 21, 345, 67);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 98, 446, 455);
		contentPane.add(scrollPane);
		
		tableCustomer = new JTable();
		
		tableCustomer.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Mobile Number", "Email"
			}
		));
		scrollPane.setViewportView(tableCustomer);
		
		lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(481, 187, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Mobile Number");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(481, 246, 108, 13);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(481, 311, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtName.setBounds(481, 210, 345, 26);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtMobileNumber = new JTextField();
		txtMobileNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtMobileNumber.setBounds(481, 275, 345, 26);
		contentPane.add(txtMobileNumber);
		txtMobileNumber.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtEmail.setBounds(481, 334, 345, 26);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String mobileNumber = txtMobileNumber.getText();
				String email = txtEmail.getText();
				
				if(validateFields()) JOptionPane.showMessageDialog(null, "All fields are required"); 
				else {
					try {
						Connection connection = ConnectionProvider.getConnection();
						PreparedStatement preparedStatement = connection.prepareStatement("insert into customer(name, mobileNumber, email) value (?, ?, ?)");
						preparedStatement.setString(1, name);
						preparedStatement.setString(2, mobileNumber);
						preparedStatement.setString(3, email);
						
						preparedStatement.executeUpdate();
						JOptionPane.showMessageDialog(null, "Customer added successfully");
						
						setVisible(false);
						new ManageCustomer().setVisible(true);
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSave.setBounds(481, 381, 85, 40);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = txtName.getText();
				String mobileNumber = txtMobileNumber.getText();
				String email = txtEmail.getText();
				
				if(validateFields()) JOptionPane.showMessageDialog(null, "All fields are required"); 
				else {
					try {
						Connection connection = ConnectionProvider.getConnection();
						PreparedStatement preparedStatement = connection.prepareStatement("update customer set name =?, mobileNumber = ?, email = ? where customer_pk = ?");
						preparedStatement.setString(1, name);
						preparedStatement.setString(2, mobileNumber);
						preparedStatement.setString(3, email);
						preparedStatement.setInt(4, customerPK);
						preparedStatement.executeUpdate();
						JOptionPane.showMessageDialog(null, "Customer updated successfully");
						
						setVisible(false);
						new ManageCustomer().setVisible(true);
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUpdate.setBounds(563, 381, 85, 40);
		contentPane.add(btnUpdate);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ManageCustomer().setVisible(true);
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnReset.setBounds(658, 381, 85, 40);
		contentPane.add(btnReset);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnClose.setBounds(741, 381, 85, 40);
		contentPane.add(btnClose);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setIcon(new ImageIcon(ManageCustomer.class.getResource("/com/hly/images/All_page_Background.png")));
		lblNewLabel_4.setBounds(0, 0, 850, 600);
		contentPane.add(lblNewLabel_4);
		
		addWindowListener( new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) tableCustomer.getModel();
				
				try {
					Connection connection = ConnectionProvider.getConnection();
					Statement statement = connection.createStatement();
					
					ResultSet rs = statement.executeQuery("select * from customer");
					
					while(rs.next()) {
						model.addRow(new Object[] {
								rs.getString("customer_pk"), rs.getString("name"), rs.getString("mobileNumber"), rs.getString("email")
						});
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				btnUpdate.setEnabled(false);
			}
	
			
		});
		
		
		tableCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int index = tableCustomer.getSelectedRow();
				TableModel model = tableCustomer.getModel();
				
				String id = model.getValueAt(index, 0).toString();
				customerPK = Integer.parseInt(id);
				
				String name = model.getValueAt(index, 1).toString();
				txtName.setText(name);
				
				String mobileNumber = model.getValueAt(index, 2).toString();
				txtMobileNumber.setText(mobileNumber);
				
				String email = model.getValueAt(index, 3).toString();
				txtEmail.setText(email);
				
				btnSave.setEnabled(false);
				btnUpdate.setEnabled(true);
				
				
				
			}
			
			
		});
	}
	
	private boolean validateFields() {
		if(!txtName.getText().equals("") && !txtMobileNumber.getText().equals("") && !txtEmail.getText().equals("")) 
			return false;
		else return true;
	}
}
