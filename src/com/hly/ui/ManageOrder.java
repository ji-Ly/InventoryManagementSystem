package com.hly.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.hly.dao.ConnectionProvider;
import com.hly.dao.InventoryUtils;
import com.hly.open.OpenPDF;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageOrder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableCustomer;
	private JTable tableProduct;
	private JTable tableCart;
	private JTextField txtCustomerName;
	private JTextField txtCustomerMobileNumber;
	private JTextField txtCustomerEmail;
	private JTextField txtProductName;
	private JTextField txtProductPrice;
	private JTextField txtProductDescription;
	private JTextField txtOrderQuantity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageOrder frame = new ManageOrder();
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
	private int productPK = 0;
	private int finalTotalPrice = 0;
	private String orderID = "";
	
	public ManageOrder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		tableCart = new JTable();
		JLabel lblNewLabel = new JLabel("Manage Order");
		lblNewLabel.setFont(new Font("Impact", Font.BOLD, 38));
		lblNewLabel.setBounds(591, 10, 266, 64);
		contentPane.add(lblNewLabel);
		
		JLabel lblFinalTotalPrice = new JLabel("00000");
		lblFinalTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFinalTotalPrice.setBounds(1200, 432, 72, 36);
		contentPane.add(lblFinalTotalPrice);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 153, 334, 202);
		contentPane.add(scrollPane);
		
		tableCustomer = new JTable();
		tableCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableCustomer.getSelectedRow();
				TableModel model = tableCustomer.getModel();
				
				String id = model.getValueAt(index, 0).toString();
				customerPK = Integer.parseInt(id);
				
				String name = model.getValueAt(index, 1).toString();
				txtCustomerName.setText(name);
				
				String mobileNumber = model.getValueAt(index, 2).toString();
				txtCustomerMobileNumber.setText(mobileNumber);
				
				String email = model.getValueAt(index, 3).toString();
				txtCustomerEmail.setText(email);
			}
		});
		tableCustomer.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Mobile Number", "Email"
			}
		));
		tableCustomer.getColumnModel().getColumn(0).setPreferredWidth(46);
		tableCustomer.getColumnModel().getColumn(2).setPreferredWidth(85);
		tableCustomer.getColumnModel().getColumn(3).setPreferredWidth(78);
		scrollPane.setViewportView(tableCustomer);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(385, 153, 519, 218);
		contentPane.add(scrollPane_1);
		
		tableProduct = new JTable();
		tableProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int index = tableProduct.getSelectedRow();
				TableModel model = tableProduct.getModel();
				
				String id = model.getValueAt(index, 0).toString();
				productPK = Integer.parseInt(id);
				
				String productName = model.getValueAt(index, 1).toString();
				txtProductName.setText(productName);
				
				String productPrice = model.getValueAt(index, 2).toString();
				txtProductPrice.setText(productPrice);
				
				String productDescription = model.getValueAt(index, 4).toString();
				txtProductDescription.setText(productDescription);
				
			}
		});
		tableProduct.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Price", "Quantity", "Description", "CategoryID", "CategoryName"
			}
		));
		tableProduct.getColumnModel().getColumn(0).setPreferredWidth(49);
		tableProduct.getColumnModel().getColumn(6).setPreferredWidth(87);
		scrollPane_1.setViewportView(tableProduct);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(952, 153, 390, 236);
		contentPane.add(scrollPane_2);
		
		
		scrollPane_2.setViewportView(tableCart);
		
		
		
		tableCart.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ProductID", "Name", "Quantity", "Price", "Desciption", "Sub total"
			}
		));
		
		JLabel lblNewLabel_1 = new JLabel("Customer List");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(135, 119, 141, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Product List");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(618, 119, 132, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Cart");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(1135, 119, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Customer selected:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(10, 432, 141, 21);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Name");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(10, 463, 45, 13);
		contentPane.add(lblNewLabel_5);
		
		txtCustomerName = new JTextField();
		txtCustomerName.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCustomerName.setBounds(72, 486, 217, 25);
		contentPane.add(txtCustomerName);
		txtCustomerName.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Mobile Number");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6.setBounds(10, 521, 119, 13);
		contentPane.add(lblNewLabel_6);
		
		txtCustomerMobileNumber = new JTextField();
		txtCustomerMobileNumber.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCustomerMobileNumber.setBounds(72, 552, 213, 29);
		contentPane.add(txtCustomerMobileNumber);
		txtCustomerMobileNumber.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Email");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_7.setBounds(10, 587, 45, 13);
		contentPane.add(lblNewLabel_7);
		
		txtCustomerEmail = new JTextField();
		txtCustomerEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCustomerEmail.setBounds(72, 610, 217, 25);
		contentPane.add(txtCustomerEmail);
		txtCustomerEmail.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Product selected:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_8.setBounds(385, 432, 141, 21);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Product Name");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_9.setBounds(385, 463, 96, 13);
		contentPane.add(lblNewLabel_9);
		
		txtProductName = new JTextField();
		txtProductName.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtProductName.setBounds(453, 488, 366, 23);
		contentPane.add(txtProductName);
		txtProductName.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Product Price");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_10.setBounds(385, 521, 119, 13);
		contentPane.add(lblNewLabel_10);
		
		txtProductPrice = new JTextField();
		txtProductPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtProductPrice.setBounds(453, 552, 366, 23);
		contentPane.add(txtProductPrice);
		txtProductPrice.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Product desciption");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_11.setBounds(385, 587, 141, 13);
		contentPane.add(lblNewLabel_11);
		
		txtProductDescription = new JTextField();
		txtProductDescription.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtProductDescription.setBounds(453, 618, 366, 25);
		contentPane.add(txtProductDescription);
		txtProductDescription.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Order quantity");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_12.setBounds(385, 653, 119, 13);
		contentPane.add(lblNewLabel_12);
		
		txtOrderQuantity = new JTextField();
		txtOrderQuantity.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtOrderQuantity.setBounds(493, 670, 119, 38);
		contentPane.add(txtOrderQuantity);
		txtOrderQuantity.setColumns(10);
		
		
		JButton btnNewButton = new JButton("Add To Cart");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nuOfUnits = txtOrderQuantity.getText();
				
				if(!nuOfUnits.equals("")) {
					String productName = txtProductName.getText();
					String productPrice = txtProductPrice.getText();
					String productDesciption = txtProductDescription.getText();
					
					int totolPrice = Integer.parseInt(txtOrderQuantity.getText()) * Integer.parseInt(productPrice);
					
					int checkStock = 0;
					int checkProductAlreadyExistInCard = 0;
					
					try {
						Connection connecton = ConnectionProvider.getConnection();
						Statement statement = connecton.createStatement();
						
						ResultSet rs = statement.executeQuery("select * from product where product_pk = " + productPK + "");
						while(rs.next()) {
							if(rs.getInt("quantity") >= Integer.parseInt(nuOfUnits)) {
								checkStock = 1;
								
							}else {
								JOptionPane.showMessageDialog(null, "Product is out of stock. Only " + rs.getInt("quantity")+" left");
							}
						}
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
					if(checkStock == 1) {
						DefaultTableModel model = (DefaultTableModel) tableCart.getModel();
						if(tableCart.getRowCount() != 0) {
							for(int i=0; i < model.getRowCount(); i++) {
								if(Integer.parseInt(model.getValueAt(i, 0).toString()) == productPK) {
									checkProductAlreadyExistInCard = 1;
									JOptionPane.showMessageDialog(null, "Product already exist in card");
								}
							}
						}
						if(checkProductAlreadyExistInCard == 0) {
							model.addRow(new Object[] {
									productPK, productName, nuOfUnits, productPrice, productDesciption, totolPrice
							});
							finalTotalPrice = finalTotalPrice + totolPrice;
							lblFinalTotalPrice.setText(String.valueOf(finalTotalPrice));
							JOptionPane.showMessageDialog(null, "Added successfully");
						}
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "No of quantity and product filed is required");
				}
				
				clearProductFields();
			}
		});
		
		
		
		
		
		
		btnNewButton.setBounds(628, 667, 150, 44);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_13 = new JLabel("Total Amount RS:");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_13.setBounds(1050, 432, 141, 36);
		contentPane.add(lblNewLabel_13);
		
	
		
		JButton btnSaveOrderDetails = new JButton("Save Order Details");
		btnSaveOrderDetails.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSaveOrderDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(finalTotalPrice != 0 && !txtCustomerName.getText().equals("")) {
					orderID = getUniqueID("Bill-");
					
					DefaultTableModel model = (DefaultTableModel) tableCart.getModel();
					if(tableCart.getRowCount() != 0) {
						for(int i = 0; i < tableCart.getRowCount(); i++) {

							try {
								Connection connection = ConnectionProvider.getConnection();
								Statement statement = connection.createStatement();
								statement.executeUpdate("update product set quantity = quantity-" + Integer.parseInt(model.getValueAt(i, 2).toString()) + " where product_pk =" + Integer.parseInt(model.getValueAt(i, 2).toString()));
								
							} catch (Exception e2) {
								e2.printStackTrace();
							}
						}
					}
					
					try {
						SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
						Calendar cal = Calendar.getInstance();
						Connection connection = ConnectionProvider.getConnection();
						PreparedStatement preparedStatement = connection.prepareStatement("insert into orderDetails (orderID, customer_fk, orderDate, totalPaid) values (?, ?, ?, ?)");
						preparedStatement.setString(1, orderID);
						preparedStatement.setInt(2, customerPK);
						preparedStatement.setString(3, myFormat.format(cal.getTime()));
						preparedStatement.setInt(4, finalTotalPrice);
						
						preparedStatement.executeUpdate();
				
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					
					
					com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
					try {
						SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
						Calendar cal = Calendar.getInstance();
						
						PdfWriter.getInstance(doc, new FileOutputStream( InventoryUtils.billPath +""+orderID+".pdf"));
						doc.open();
						
						Paragraph projectName = new Paragraph("                                                      Inventory Management System\n");
						doc.add(projectName);
						
						Paragraph starLine = new Paragraph("***********************************************************************************************************************************************\n");
						doc.add(starLine);
						
						Paragraph details = new Paragraph("\tOrder ID: " + orderID +"\nDate: " + myFormat.format(cal.getTime()) + "\nTotal Paid: " + finalTotalPrice);
						doc.add(details);
						
						doc.add(starLine);
						PdfPTable table1 = new PdfPTable(5);
						PdfPCell nameCell = new PdfPCell(new Phrase("Name"));
						PdfPCell discriptionCell = new PdfPCell(new Phrase("Description"));
						PdfPCell priceCell = new PdfPCell(new Phrase("Price Per Unit"));
						PdfPCell quantityCell = new PdfPCell(new Phrase("Quantity"));
						PdfPCell subToTolPriceCell = new PdfPCell(new Phrase("Sub Total Price"));
						
						BaseColor backgroundColor = new BaseColor(255, 204, 51);
						nameCell.setBackgroundColor(backgroundColor);
						discriptionCell.setBackgroundColor(backgroundColor);
						priceCell.setBackgroundColor(backgroundColor);
						quantityCell.setBackgroundColor(backgroundColor);
						subToTolPriceCell.setBackgroundColor(backgroundColor);
						
						table1.addCell(nameCell);
						table1.addCell(discriptionCell);
						table1.addCell(priceCell);
						table1.addCell(quantityCell);
						table1.addCell(subToTolPriceCell);
						
						for(int i = 0; i < tableCart.getRowCount(); i++) {
							table1.addCell(tableCart.getValueAt(i, 1).toString());
							table1.addCell(tableCart.getValueAt(i, 4).toString());
							table1.addCell(tableCart.getValueAt(i, 3).toString());
							table1.addCell(tableCart.getValueAt(i, 2).toString());
							table1.addCell(tableCart.getValueAt(i, 5).toString());
						}
						
						doc.add(table1);
						doc.add(starLine);
						
						Paragraph thanksMsg = new Paragraph("Thank you. Please visit again.");
						doc.add(thanksMsg);
						//open
						OpenPDF.openByID(orderID);
						
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					doc.close();
					setVisible(false);
					new ManageOrder().setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "Please add some product to cart or select customer");
				}
				
				
				
				
				
			}
		});
		btnSaveOrderDetails.setBounds(1000, 505, 320, 64);
		contentPane.add(btnSaveOrderDetails);
		
		JButton btnNewButton_2 = new JButton("Reset");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ManageOrder().setVisible(true);
			}
		});
		btnNewButton_2.setBounds(1000, 587, 320, 36);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Close");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_3.setBounds(1099, 671, 132, 36);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_15 = new JLabel("New label");
		lblNewLabel_15.setIcon(new ImageIcon(ManageOrder.class.getResource("/com/hly/images/Orders_background.png")));
		lblNewLabel_15.setBounds(0, 0, 1366, 768);
		contentPane.add(lblNewLabel_15);
		
		
		addWindowListener(new WindowAdapter() {
			 public void windowOpened(WindowEvent e) {
				 txtCustomerName.setEditable(false);
				 txtCustomerMobileNumber.setEditable(false);
				 txtCustomerEmail.setEditable(false);
				 
				 txtProductName.setEditable(false);
				 txtProductPrice.setEditable(false);
				 txtProductDescription.setEditable(false);
				 
				 DefaultTableModel customerModel = (DefaultTableModel) tableCustomer.getModel();
				 DefaultTableModel productModel = (DefaultTableModel) tableProduct.getModel();
				 
				 try {
					Connection connection = ConnectionProvider.getConnection();
					Statement statement = connection.createStatement();
					
					ResultSet rs = statement.executeQuery("select * from customer");
					
					while(rs.next()) {
						customerModel.addRow(new Object[] {
							rs.getString("customer_pk"), rs.getString("name"), rs.getString("mobileNumber"), rs.getString("email")	
						});
					}
					
					rs = statement.executeQuery("select * from product inner join category on product.category_fk = category.category_pk");
					
					while(rs.next()) {
						productModel.addRow(new Object[] {
							rs.getString("product_pk"), rs.getString("name"), rs.getString("price"), rs.getString("quantity"), rs.getString("description"),
							rs.getString("category_fk"), rs.getString(8)
						});
					}
					
					
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
				 
			 }
			
		});
		
		tableCart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int index = tableCart.getSelectedRow();
				int response = JOptionPane.showConfirmDialog(null, "Do you want to remove this product", "Select", JOptionPane.YES_NO_OPTION);
				
				if(response == 0) {
					TableModel model = tableCart.getModel();
					String subTotal = model.getValueAt(index, 5).toString();
					finalTotalPrice = finalTotalPrice - Integer.parseInt(subTotal); 
					lblFinalTotalPrice.setText(String.valueOf(finalTotalPrice));
					((DefaultTableModel) tableCart.getModel()).removeRow(index);
					
				}
						
			}
		});
	}

	private void clearProductFields() {
		productPK = 0;
		txtProductName.setText("");
		txtProductPrice.setText("");
		txtProductDescription.setText("");
		txtOrderQuantity.setText("");
	}
	
	public String getUniqueID(String prefix) {
		return prefix + System.nanoTime();
	}
}
