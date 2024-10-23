package com.hly.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.TableView.TableRow;

import com.hly.dao.ConnectionProvider;
import com.hly.dao.InventoryUtils;
import com.hly.open.OpenPDF;
import com.mysql.cj.xdevapi.Table;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class ViewOrder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableCustomer;
	private JTable tableOrders;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewOrder frame = new ViewOrder();
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
	public ViewOrder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("View Orders");
		lblNewLabel.setFont(new Font("Impact", Font.BOLD, 38));
		lblNewLabel.setBounds(313, 10, 240, 53);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 106, 323, 348);
		contentPane.add(scrollPane);
		
		tableCustomer = new JTable();
		tableCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int index = tableCustomer.getSelectedRow();
				TableModel model = tableCustomer.getModel();
				
				String id = model.getValueAt(index, 0).toString();
				
				DefaultTableModel modelTableOrder = (DefaultTableModel) tableOrders.getModel();
				modelTableOrder.setRowCount(0);
			
				try {
					Connection connection = ConnectionProvider.getConnection();
					Statement statement = connection.createStatement();
					
					ResultSet rs = statement.executeQuery("select * from orderDetails where customer_fk="+id+"");
					
					while(rs.next()) {
						modelTableOrder.addRow(new Object[] {
							rs.getString("customer_fk"), rs.getString("orderID"), rs.getString("orderDate"), rs.getString("totalPaid")	
						});
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				
				
			}
			
			
			
		});
		tableCustomer.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Mobile Number", "Email"
			}
		));
		tableCustomer.getColumnModel().getColumn(2).setPreferredWidth(87);
		scrollPane.setViewportView(tableCustomer);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(404, 117, 308, 0);
		contentPane.add(scrollPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(461, 106, 338, 348);
		contentPane.add(scrollPane_2);
		
		tableOrders = new JTable();
	
		tableOrders.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Order ID", "Date", "Total Paid"
			}
		));
		scrollPane_2.setViewportView(tableOrders);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setBounds(690, 493, 85, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Customer List");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(152, 83, 200, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Order List");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(605, 83, 65, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(ViewOrder.class.getResource("/com/hly/images/All_page_Background.png")));
		lblNewLabel_3.setBounds(0, 0, 850, 600);
		contentPane.add(lblNewLabel_3);
		
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
				
				
				
			}
			
		});
		tableOrders.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int index = tableOrders.getSelectedRow();
				TableModel model = tableOrders.getModel();
				
				String orderID = model.getValueAt(index, 0).toString();
				
				try {
					System.out.println(orderID);
					System.out.println(InventoryUtils.billPath+""+orderID+".pdf");
					OpenPDF.openByID(orderID);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				
				
				
			}
		});
	}

}
