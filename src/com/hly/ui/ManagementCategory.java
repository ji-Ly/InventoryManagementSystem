package com.hly.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

public class ManagementCategory extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable tableCategory;
	private JTextField txtName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagementCategory frame = new ManagementCategory();
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
	
	private int categoryPK = 0;
	
	public ManagementCategory() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		
		//Khi mo len
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Manage Category");
		lblNewLabel.setFont(new Font("Impact", Font.BOLD, 38));
		lblNewLabel.setBounds(284, 10, 324, 76);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 106, 407, 426);
		contentPane.add(scrollPane);
		
		tableCategory = new JTable();
		
		//setViewportView để hiện title của table
		scrollPane.setViewportView(tableCategory);
		tableCategory.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"ID", "Name"
			}
		));
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(453, 234, 45, 26);
		contentPane.add(lblNewLabel_1);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtName.setBounds(453, 271, 371, 33);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		
		//event save
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Lấy tên từ ô nhập
				String name = txtName.getText();
				//Rỗng thì in ra message
				if(validateFields()) {
					JOptionPane.showMessageDialog(null, "All fields are required");
				}else {
					try {
						//Thực hiện add vào dtb
						Connection connection = ConnectionProvider.getConnection();
						PreparedStatement preparedStatement = connection.prepareStatement("insert into category (name) value (?)");
						preparedStatement.setString(1, name);
						
						preparedStatement.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "Category added successfully");
						setVisible(false);
						new ManagementCategory().setVisible(true);
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSave.setBounds(453, 328, 85, 30);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUpdate.setBounds(548, 328, 85, 30);
		contentPane.add(btnUpdate);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ManagementCategory().setVisible(true);
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnReset.setBounds(643, 328, 85, 30);
		contentPane.add(btnReset);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnClose.setBounds(738, 328, 85, 30);
		contentPane.add(btnClose);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(ManagementCategory.class.getResource("/com/hly/images/All_page_Background.png")));
		lblNewLabel_2.setBounds(0, 0, 850, 600);
		contentPane.add(lblNewLabel_2);
		
		
		// mặc định khi mở lên
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowOpened(java.awt.event.WindowEvent e) {
		    	//tạo một defaule table để lưu các doi tuong
		    	DefaultTableModel model = (DefaultTableModel) tableCategory.getModel();
		    	
		    	try {
					Connection connection = ConnectionProvider.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement("select * from category");
					
					ResultSet rs = preparedStatement.executeQuery();
					
					while(rs.next()) {
						model.addRow(new Object[] {rs.getString("category_PK"), rs.getString("name")});
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e);
				}
		    	
		    	btnUpdate.setEnabled(false);
		    }
		});
		
		
		//khi nhap chuot vao tung row
		tableCategory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//row nao?
				int index = tableCategory.getSelectedRow();
				//tao table model de lay du lieu
				TableModel model = tableCategory.getModel();
				
				String name = model.getValueAt(index, 1).toString();
				txtName.setText(name);
				//lay duoc id tu modeltable roi thi ném vào categoryPK khai báo đầu
				String id = model.getValueAt(index, 0).toString();
				categoryPK = Integer.parseInt(id);
				
				//save enable false
				btnSave.setEnabled(false);
				btnUpdate.setEnabled(true);
			
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				if(validateFields()) {
					JOptionPane.showMessageDialog(null, "All fields are required");
				}else {
					try {
						//lấy text ở ô nhâp rồi set lại 
						Connection connection = ConnectionProvider.getConnection();
						PreparedStatement preparedStatement = connection.prepareStatement("update category set name = ? where category_pk = ?");
						preparedStatement.setString(1, name);
						preparedStatement.setInt(2, categoryPK);
						
						preparedStatement.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "Category Updated successfully");
						setVisible(false);
						new ManagementCategory().setVisible(true);
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				}
				
				
			}
		});
		
	
	
	}
	//kiem tra xem cac truong co rong khong
	private boolean validateFields() {
		if(!txtName.getText().equals("")) return false;
		else return true;
	}
}
