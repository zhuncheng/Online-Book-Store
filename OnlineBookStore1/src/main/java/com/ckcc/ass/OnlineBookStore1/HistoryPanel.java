package com.ckcc.ass.OnlineBookStore1;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.util.List;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entities.Address;
import entities.Order;
import entities.OrderDetail;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.UIManager;

public class HistoryPanel extends JPanel implements ActionListener{
	private JTable table;
	private Object[] columns = {"Customer ID#", "Order ID#", "Total", "Remark"};
	private DefaultTableModel model;
	private JTextField tfCusID;
	private JTextField tfCusName;
	private JTextField tfCustomerEmail;
	private JTextField tfPhoneNumber;
	private JButton btnShow, btnBack;
	private Object[] column = {"ID", "Book ID#", "QTY", "Price", "Discount", "Subtotal", "Order ID#"};
	private DefaultTableModel orderDetailModel;
	private JPanel pnlInvoice, pnlCustomers;
	private JLabel lblTotal, lblDiscount;
	 

	/**
	 * Create the panel.
	 */
	public HistoryPanel() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(10, 10));
		
		JPanel pnlTop = new JPanel();
		pnlTop.setBackground(new Color(0, 128, 128));
		panel.add(pnlTop, BorderLayout.NORTH);
		
		JLabel lblHistory = new JLabel("History");
		lblHistory.setForeground(Color.WHITE);
		pnlTop.add(lblHistory);
		
		JPanel pnlCenter = new JPanel();
		panel.add(pnlCenter, BorderLayout.CENTER);
		
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		model.setColumnIdentifiers(columns);
		pnlCenter.setLayout(new BorderLayout(10, 10));
		
		pnlCustomers = new JPanel();
		pnlCenter.add(pnlCustomers, BorderLayout.CENTER);
		pnlCustomers.setLayout(new BorderLayout(0, 0));
		table = new JTable(model);
		table.setSelectionForeground(Color.WHITE);
		table.setSelectionBackground(new Color(0, 0, 0));
		table.setForeground(Color.WHITE);
		table.setBackground(new Color(0, 128, 128));
		JScrollPane sp = new JScrollPane(table);
		pnlCustomers.add(sp);
		
		JPanel pnlButton = new JPanel();
		pnlButton.setBackground(Color.WHITE);
		pnlCustomers.add(pnlButton, BorderLayout.EAST);
		pnlButton.setLayout(new BorderLayout(0, 0));
		
		btnShow = new JButton("      Show      ");
		btnShow.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnShow.setBackground(new Color(46, 139, 87));
		btnShow.setForeground(Color.WHITE);
		pnlButton.add(btnShow, BorderLayout.NORTH);
		
		pnlInvoice = new JPanel();
		pnlInvoice.setBackground(Color.WHITE);
		pnlInvoice.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Invoice Information", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlCenter.add(pnlInvoice, BorderLayout.NORTH);
		pnlInvoice.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlCustomerInfo = new JPanel();
		pnlCustomerInfo.setBackground(Color.WHITE);
		pnlCustomerInfo.setBorder(new TitledBorder(null, "Customer Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlInvoice.add(pnlCustomerInfo, BorderLayout.NORTH);
		pnlCustomerInfo.setLayout(new GridLayout(0, 4, 10, 10));
		
		JLabel lblCustomerId = new JLabel("Customer ID: ");
		pnlCustomerInfo.add(lblCustomerId);
		
		tfCusID = new JTextField();
		pnlCustomerInfo.add(tfCusID);
		tfCusID.setColumns(10);
		
		JLabel lblCustomerName = new JLabel("Customer Name:");
		pnlCustomerInfo.add(lblCustomerName);
		
		tfCusName = new JTextField();
		pnlCustomerInfo.add(tfCusName);
		tfCusName.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		pnlCustomerInfo.add(lblEmail);
		
		tfCustomerEmail = new JTextField();
		pnlCustomerInfo.add(tfCustomerEmail);
		tfCustomerEmail.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		pnlCustomerInfo.add(lblPhoneNumber);
		
		tfPhoneNumber = new JTextField();
		pnlCustomerInfo.add(tfPhoneNumber);
		tfPhoneNumber.setColumns(10);
		
		JPanel pnlTable = new JPanel();
		pnlTable.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "List Of Purchased Book(s)", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlTable.setBackground(Color.WHITE);
		pnlInvoice.add(pnlTable, BorderLayout.CENTER);
		pnlInvoice.setVisible(false);
		orderDetailModel = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		orderDetailModel.setColumnIdentifiers(column);
		pnlTable.setLayout(new BorderLayout(0, 0));
		
		tableOrder = new JTable(orderDetailModel);
		tableOrder.setForeground(Color.WHITE);
		tableOrder.setBackground(new Color(0, 128, 128));
		tableOrder.setSelectionBackground(Color.BLACK);
		JScrollPane sp1 = new JScrollPane(tableOrder);
		pnlTable.add(sp1, BorderLayout.CENTER);
		
		JPanel pnlTotal = new JPanel();
		pnlTotal.setBackground(Color.WHITE);
		pnlTable.add(pnlTotal, BorderLayout.SOUTH);
		pnlTotal.setLayout(new BorderLayout(0, 0));
		
		lblTotal = new JLabel("Total: $");
		pnlTotal.add(lblTotal, BorderLayout.NORTH);
		
		lblDiscount = new JLabel("New label");
		pnlTotal.add(lblDiscount, BorderLayout.SOUTH);
		
		JPanel pnlBtn = new JPanel();
		pnlBtn.setBackground(Color.WHITE);
		pnlInvoice.add(pnlBtn, BorderLayout.EAST);
		
		btnBack = new JButton("      Back      ");
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color(46, 139, 87));
		pnlBtn.add(btnBack);
		
		fillCustomerTable();
		
		btnShow.addActionListener(this);
		btnBack.addActionListener(this);

	}

	List<Order> orderList = getOrderFromDB();
	private JTable tableOrder;
	
	public void fillCustomerTable() {
	
		//System.out.println(orderList.size());
		for (Order order : orderList) {
//			System.out.println(order.getCustomer().getId());
			model.addRow(new Object[] {order.getCustomer().getId(), order.getId(), order.getTotal(), order.getRemark()});
		}
		System.out.println();
		//model.addRow(new Object[] {});
	}
	
	public List<Order> getOrderFromDB(){
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Order.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			System.out.println("Quering order from db");
			session.beginTransaction();
			return session.createQuery("from Order").getResultList();
		}
		finally {
			factory.close();
		}
	}


	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnShow) {
			if(model.getRowCount() > 0) {
				pnlInvoice.setVisible(true);
				pnlCustomers.setVisible(false);
				if(table.getSelectedRow() > -1) {
					int index = table.getSelectedRow();
					System.out.println(model.getValueAt(index, 1).toString());
					int orderID = Integer.parseInt(model.getValueAt(index, 1).toString());

					for(Order order : orderList) {

						if(order.getId() == orderID) {
							
							tfCusID.setText(order.getCustomer().getId() + "");
							tfCusName.setText(order.getCustomer().getName());
							tfCustomerEmail.setText(order.getCustomer().getEmail());
							tfPhoneNumber.setText(order.getCustomer().getPhone());
							
							if(orderDetailModel.getRowCount() > 0) {
								orderDetailModel.getDataVector().removeAllElements();
							}
							
							for(OrderDetail orderDetail : order.getOrderList()) {
								System.out.println(orderDetail.getSubtotal() + "$");
								double subtotal1 = orderDetail.getSubTotal();

							      orderDetailModel.addRow(new Object[] {orderDetail.getId(), orderDetail.getProduct_id(), orderDetail.getQty(), orderDetail.getPrice(), orderDetail.getDiscount(),
							    		  (subtotal1 * -0.1), order.getId()});
							}
							lblTotal.setText("Total : $" + order.getTotal());
							lblDiscount.setText("Discount: % " + order.getDiscount());
							
						}
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Please select a row to show", "Error", JOptionPane.WARNING_MESSAGE);

			}
		}
		if(e.getSource() == btnBack) {
			pnlCustomers.setVisible(true);
			pnlInvoice.setVisible(false);
		}
	}
 
	
}
