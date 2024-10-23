package com.hly.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.hly.dao.ConnectionProvider;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageProduct extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableProduct;
	private JLabel lblNewLabel_1;
	private JLabel lblQuantity;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField txtName;
	private JTextField txtQuantity;
	private JTextField txtPrice;
	private JTextField txtDescription;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageProduct frame = new ManageProduct();
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
	
	private int productPK = 0;
	private int totalQuantity = 0;
	
	JComboBox comboBoxCategory;
	
	
	public ManageProduct() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Manage Product");
		lblNewLabel.setFont(new Font("Impact", Font.BOLD, 40));
		lblNewLabel.setBounds(280, 10, 300, 74);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 115, 516, 414);
		contentPane.add(scrollPane);
		
		tableProduct = new JTable();
		
		tableProduct.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Quantity", "Price", "Description", "Category ID", "Category Name"
			}
		));
		tableProduct.getColumnModel().getColumn(6).setPreferredWidth(95);
		scrollPane.setViewportView(tableProduct);
		
		lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(572, 132, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuantity.setBounds(572, 186, 109, 25);
		contentPane.add(lblQuantity);
		
		lblNewLabel_3 = new JLabel("Price");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(572, 250, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Description");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(572, 301, 78, 13);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Category");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(572, 353, 78, 13);
		contentPane.add(lblNewLabel_5);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtName.setBounds(572, 157, 230, 19);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtQuantity = new JTextField();
		txtQuantity.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtQuantity.setBounds(572, 221, 230, 19);
		contentPane.add(txtQuantity);
		txtQuantity.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtPrice.setBounds(572, 272, 230, 19);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		txtDescription = new JTextField();
		txtDescription.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtDescription.setBounds(572, 324, 230, 19);
		contentPane.add(txtDescription);
		txtDescription.setColumns(10);
		
		comboBoxCategory = new JComboBox();
		comboBoxCategory.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBoxCategory.setBounds(572, 376, 230, 21);
		contentPane.add(comboBoxCategory);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String quantity = txtQuantity.getText();
				String price = txtPrice.getText();
				String description = txtDescription.getText();
				//Lấy cái item ra rồi tách ra Iv va name của category
				String category = (String) comboBoxCategory.getSelectedItem();
				String[] categoryID = category.split(" - ", 0);
				
				if(validateFields("new")) {
					JOptionPane.showMessageDialog(null, "All fields are required");
				}else {
					try {
						Connection connection = ConnectionProvider.getConnection();
						PreparedStatement preparedStatement = connection.prepareStatement("insert into product (name, quantity, price, description, category_fk)  value (?, ?, ?, ?, ?)");
					preparedStatement.setString(1, name);
					preparedStatement.setString(2, quantity);
					preparedStatement.setString(3, price);
					preparedStatement.setString(4, description);
					preparedStatement.setString(5, categoryID[0]);//ID
					
					preparedStatement.executeUpdate();
					JOptionPane.showMessageDialog(null, "Product added successfully");
					
					setVisible(false);
					new ManageProduct().setVisible(true);
					
					
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				}
				
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSave.setBounds(572, 489, 85, 30);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String quantity = txtQuantity.getText();
				String price = txtPrice.getText();
				String description = txtDescription.getText();
				String category = (String) comboBoxCategory.getSelectedItem();
				String[] categoryID = category.split(" - ", 0);
				
				if(validateFields("edit")) {
					JOptionPane.showMessageDialog(null, "All fields are required");
				}else {
					try {
						if(!quantity.equals("")) {
							totalQuantity = totalQuantity + Integer.parseInt(quantity);
						}
						
						Connection connection = ConnectionProvider.getConnection();
						PreparedStatement preparedStatement = connection.prepareStatement("update product set name = ?, quantity =?, price = ?, desciption = ?, category_fk= ? where product_pk = ?");
					preparedStatement.setString(1, name);
					preparedStatement.setInt(2, totalQuantity);
					preparedStatement.setString(3, price);
					preparedStatement.setString(4, description);
					preparedStatement.setString(5, categoryID[0]);
					preparedStatement.setInt(6, productPK);
					
					preparedStatement.executeUpdate();
					JOptionPane.showMessageDialog(null, "Product updated successfully");
					
					setVisible(false);
					new ManageProduct().setVisible(true);
					
					
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				}
				
			}
		});
	
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUpdate.setBounds(572, 436, 85, 30);
		contentPane.add(btnUpdate);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ManageProduct().setVisible(true);
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnReset.setBounds(717, 436, 85, 30);
		contentPane.add(btnReset);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
			
		});
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnClose.setBounds(717, 489, 85, 30);
		contentPane.add(btnClose);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(ManageProduct.class.getResource("/com/hly/images/All_page_Background.png")));
		lblNewLabel_2.setBounds(0, 0, 850, 600);
		contentPane.add(lblNewLabel_2);
		
		
		
		//windowlisner
		addWindowListener(new java.awt.event.WindowAdapter() {
			 public void windowOpened(WindowEvent e) {
				 
				 getAllCategory();
				 
				 DefaultTableModel model = (DefaultTableModel) tableProduct.getModel();
				 try {
					Connection connection = ConnectionProvider.getConnection();
					Statement statement = connection.createStatement();
					
					ResultSet rs = statement.executeQuery("select * from product inner join category on product.category_fk = category.category_pk");
					while(rs.next()) {
						model.addRow(new Object[] {
								  rs.getString("product_pk"),
					                rs.getString("name"),
					                rs.getString("quantity"),
					                rs.getString("price"),
					                rs.getString("description"),
					                rs.getString("category_fk"),
					                rs.getString(8)
						});
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				 btnUpdate.setEnabled(false);
				 
			 }
			 
			
		});
		
		tableProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableProduct.getSelectedRow();
				TableModel model = tableProduct.getModel();
				
				String id = model.getValueAt(index, 0).toString();
				productPK = Integer.parseInt(id);
				
				String name = model.getValueAt(index, 1).toString();
				txtName.setText(name);
				
				String quantity = model.getValueAt(index, 2).toString();
				totalQuantity = 0;
				lblQuantity.setText("Add Quantity");
				totalQuantity = Integer.parseInt(quantity);
				
				String price = model.getValueAt(index, 3).toString();
				txtPrice.setText(price);
				
				String description = model.getValueAt(index, 4).toString();
				txtDescription.setText(description);
				
				comboBoxCategory.removeAllItems();
				String categoryID = model.getValueAt(index, 5).toString();
				String categoryName = model.getValueAt(index, 6).toString();
				comboBoxCategory.addItem(categoryID +" - " + categoryName);
				
				try {
					Connection connection = ConnectionProvider.getConnection();
					Statement statement = connection.createStatement();
					//Lấy hết
					ResultSet rs = statement.executeQuery("select * from category");
				
					
					while(rs.next()) {
						if(Integer.parseInt(categoryID) != rs.getInt(1))
						
						comboBoxCategory.addItem(rs.getString("category_pk") + " - " + rs.getString("name"));
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
				
				btnSave.setEnabled(false);
				btnUpdate.setEnabled(true);
				
			}
		});
		
	}
	
	//Lấy tất cả category
	private void getAllCategory() {
		try {
			Connection connection = ConnectionProvider.getConnection();
			Statement statement = connection.createStatement();
			//Lấy hết
			ResultSet rs = statement.executeQuery("select * from category");
			//xóa item
			comboBoxCategory.removeAllItems();
			
			while(rs.next()) {
				//sua lai comboBoxCategory
				//ghep pk va name cua category
				comboBoxCategory.addItem(rs.getString("category_pk") + " - " + rs.getString("name"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	//Kiem tra cac truong du lieu
	private boolean validateFields(String formType) {
		if(formType.equals("edit") && !txtName.getText().equals("") && !txtPrice.getText().equals("") && !txtDescription.getText().equals("")) {
			return false;
		}else if(formType.equals("new") && !txtName.getText().equals("") && !txtPrice.getText().equals("") && !txtDescription.getText().equals("") && !txtQuantity.getText().equals("") ) {
			return false;
		}
		return true;
	}
	
	
	
	
}
