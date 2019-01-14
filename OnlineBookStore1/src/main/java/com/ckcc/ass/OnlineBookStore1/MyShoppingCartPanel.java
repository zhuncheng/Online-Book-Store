package com.ckcc.ass.OnlineBookStore1;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.xml.validation.Validator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entities.Address;
import entities.Book;
import entities.Customer;
import entities.Order;
import entities.OrderDetail;
import entities.Stock;
import helper.ValidatorClass;

import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JRadioButton;

public class MyShoppingCartPanel extends JPanel implements ActionListener{
	private JTable table;
	private JPanel pnlButtons, panel, pnlCenter, pnlCenterButtom, pnlTable;
	private DefaultTableModel model;
	private Object[] columns = {"Book ID", "QTY", "Price", "Discount", "SubTotal"};
	private List<OrderDetail> orderDetails;
	private List<Order> orderList; 
	private JButton btnCheckOut, btnCheckout;
	private JPanel pnlButton1, pnlCustomerInfo, pnlCustomerGenInfo, pnlSp, pnlAddressInfo, pnl1, pnlAddresses, pnlBillingAddress, pnlDiscount;
	private JLabel lblName;
	private JTextField tfName, tfEmail, tfPhoneNumber, tfStreet, tfCity, tfState, tfCountry, tfCode;
	private JLabel lblEmail, lblPhoneNumber, lblStreet, lblCity, lblState, lblTotal, lblCountry, lblCode, label, label_1, label_4, lblBillingAddressAnd, lblDiscount, lblRemark, lblTotal_1, label_3, label_2;
	private JPanel pnlShippingAddress; 
	private JTextField tfStreet1, tfCity1, tfState1, tfCountry1, tfCode1, tfDiscount, tfRemark, tfTotal;
	private JRadioButton rdbtnYes, rdbtnNo;	
	private List<OrderDetail> orderList2 = new ArrayList<OrderDetail>();
	private JPanel pnlCheckoutButton;

	private Double total, subtotal = 0.0;

	/**
	 * Create the panel.
	 */
	public MyShoppingCartPanel(final List<OrderDetail> orderList) {
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel);
		panel.setLayout(new BorderLayout(10, 10));
		
		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(new Color(0, 128, 128));
		panel.add(pnlHeader, BorderLayout.NORTH);
		
		JLabel lblMyShoppingCart = new JLabel("My Shopping Cart");
		lblMyShoppingCart.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyShoppingCart.setForeground(Color.WHITE);
		lblMyShoppingCart.setFont(new Font("Verdana", Font.BOLD, 14));
		pnlHeader.add(lblMyShoppingCart);
		
		pnlCenter = new JPanel();
		pnlCenter.setBackground(new Color(250, 255, 240));
		panel.add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new BorderLayout(10, 10));
		
		pnlTable = new JPanel();
		pnlCenter.add(pnlTable, BorderLayout.CENTER);
		pnlTable.setLayout(new BorderLayout(10, 10));
		
		
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		model.setColumnIdentifiers(columns);
		
		lblTotal = new JLabel("Total: ");
		pnlTable.add(lblTotal, BorderLayout.SOUTH);
		lblTotal.setBackground(Color.WHITE);
		lblTotal.setFont(new Font("Verdana", Font.PLAIN, 16));
		
		pnlButtons = new JPanel();
		pnlTable.add(pnlButtons, BorderLayout.EAST);
		pnlButtons.setBackground(Color.WHITE);
		pnlButtons.setLayout(new BorderLayout(0, 0));
		
		pnlButton1 = new JPanel();
		pnlButtons.add(pnlButton1, BorderLayout.NORTH);
		pnlButton1.setLayout(new GridLayout(0, 1, 5, 2));
		
		btnCheckOut = new JButton("Go Check Out  ");
		pnlButton1.add(btnCheckOut);
		btnCheckOut.setForeground(Color.WHITE);
		btnCheckOut.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnCheckOut.setFocusPainted(false);
		btnCheckOut.setBackground(new Color(60, 179, 113));
		
		pnlSp = new JPanel();
		pnlTable.add(pnlSp, BorderLayout.CENTER);
		pnlSp.setLayout(new BorderLayout(0, 0));
			
			table = new JTable(model);
					
			JScrollPane sp = new JScrollPane(table);
			sp.setMaximumSize(new Dimension(327, 327));
			pnlSp.add(sp, BorderLayout.CENTER);
			sp.setAlignmentX(Component.LEFT_ALIGNMENT);
			
			table.setForeground(Color.WHITE);
			table.setSelectionForeground(Color.WHITE);
			table.setBackground(new Color(0, 128, 128));
			table.setSelectionBackground(new Color(0, 51, 51));
		
		pnlCenterButtom = new JPanel();
		pnlCenterButtom.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Check Out Information", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlCenterButtom.setBackground(Color.WHITE);
		pnlCenter.add(pnlCenterButtom, BorderLayout.NORTH);
		pnlCenterButtom.setLayout(new BorderLayout(0, 0));
		pnlCenterButtom.setVisible(false);
		
		pnlCustomerGenInfo = new JPanel();
		pnlCenterButtom.add(pnlCustomerGenInfo, BorderLayout.CENTER);
		pnlCustomerGenInfo.setLayout(new BorderLayout(0, 0));
		
		pnlCustomerInfo = new JPanel();
		pnlCustomerGenInfo.add(pnlCustomerInfo, BorderLayout.NORTH);
		pnlCustomerInfo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Customer Information", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		pnlCustomerInfo.setBackground(Color.WHITE);
		pnlCustomerInfo.setLayout(new GridLayout(0, 6, 0, 0));
		
		lblName = new JLabel("Name:");
		lblName.setForeground(Color.BLACK);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		pnlCustomerInfo.add(lblName);
		
		tfName = new JTextField();
		pnlCustomerInfo.add(tfName);
		tfName.setColumns(10);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		pnlCustomerInfo.add(lblEmail);
		
		tfEmail = new JTextField();
		pnlCustomerInfo.add(tfEmail);
		tfEmail.setColumns(10);
		
		lblPhoneNumber = new JLabel("Phone Number: ");
		lblPhoneNumber.setForeground(Color.BLACK);
		lblPhoneNumber.setHorizontalAlignment(SwingConstants.CENTER);
		pnlCustomerInfo.add(lblPhoneNumber);
		
		tfPhoneNumber = new JTextField();
		pnlCustomerInfo.add(tfPhoneNumber);
		tfPhoneNumber.setColumns(10);
		
		pnlAddressInfo = new JPanel();
		pnlCustomerGenInfo.add(pnlAddressInfo, BorderLayout.CENTER);
		pnlAddressInfo.setLayout(new BorderLayout(0, 0));
		
		pnl1 = new JPanel();
		pnl1.setBackground(Color.WHITE);
		pnlAddressInfo.add(pnl1, BorderLayout.NORTH);
		
		lblBillingAddressAnd = new JLabel("Billing Address and Shipping the same?");
		pnl1.add(lblBillingAddressAnd);
		
		/////////////////////////
		ButtonGroup btg = new ButtonGroup();
		rdbtnYes = new JRadioButton("Yes", true);
		rdbtnYes.setBackground(Color.WHITE);
		pnl1.add(rdbtnYes);
		
		rdbtnNo = new JRadioButton("No", false);
		rdbtnNo.setBackground(Color.WHITE);
		pnl1.add(rdbtnNo);
		
		btg.add(rdbtnYes); btg.add(rdbtnNo);
		
		rdbtnNo.addActionListener(this);
		rdbtnYes.addActionListener(this);
		///////////////////////
		pnlAddresses = new JPanel();
		pnlAddressInfo.add(pnlAddresses);
		pnlAddresses.setLayout(new BorderLayout(0, 0));
		
		pnlBillingAddress = new JPanel();
		pnlBillingAddress.setBackground(Color.WHITE);
		pnlBillingAddress.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Billing Address", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlAddresses.add(pnlBillingAddress, BorderLayout.NORTH);
		pnlBillingAddress.setLayout(new GridLayout(0, 6, 0, 0));
		
		lblStreet = new JLabel("Street: ");
		lblStreet.setHorizontalAlignment(SwingConstants.CENTER);
		pnlBillingAddress.add(lblStreet);
		
		tfStreet = new JTextField();
		pnlBillingAddress.add(tfStreet);
		tfStreet.setColumns(10);
		
		lblCity = new JLabel("City");
		lblCity.setHorizontalAlignment(SwingConstants.CENTER);
		pnlBillingAddress.add(lblCity);
		
		tfCity = new JTextField();
		pnlBillingAddress.add(tfCity);
		tfCity.setColumns(10);
		
		lblState = new JLabel("State: ");
		lblState.setHorizontalAlignment(SwingConstants.CENTER);
		pnlBillingAddress.add(lblState);
		
		tfState = new JTextField();
		pnlBillingAddress.add(tfState);
		tfState.setColumns(10);
		
		lblCountry = new JLabel("Country: ");
		lblCountry.setHorizontalAlignment(SwingConstants.CENTER);
		pnlBillingAddress.add(lblCountry);
		
		tfCountry = new JTextField();
		pnlBillingAddress.add(tfCountry);
		tfCountry.setColumns(10);
		
		lblCode = new JLabel("Code: ");
		lblCode.setHorizontalAlignment(SwingConstants.CENTER);
		pnlBillingAddress.add(lblCode);
		
		tfCode = new JTextField();
		pnlBillingAddress.add(tfCode);
		tfCode.setColumns(10);
		
		pnlShippingAddress = new JPanel();
		pnlShippingAddress.setBackground(Color.WHITE);
		pnlShippingAddress.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Shipping Address", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlAddresses.add(pnlShippingAddress, BorderLayout.SOUTH);
		pnlShippingAddress.setLayout(new GridLayout(0, 6, 0, 0));
		pnlShippingAddress.setVisible(false);
		
		label = new JLabel("Street: ");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		pnlShippingAddress.add(label);
		
		tfStreet1 = new JTextField();
		tfStreet1.setColumns(10);
		pnlShippingAddress.add(tfStreet1);
		
		label_1 = new JLabel("City");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		pnlShippingAddress.add(label_1);
		
		tfCity1 = new JTextField();
		tfCity1.setColumns(10);
		pnlShippingAddress.add(tfCity1);
		
		label_2 = new JLabel("State: ");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		pnlShippingAddress.add(label_2);
		
		tfState1 = new JTextField();
		tfState1.setColumns(10);
		pnlShippingAddress.add(tfState1);
		
		label_3 = new JLabel("Country: ");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		pnlShippingAddress.add(label_3);
		
		tfCountry1 = new JTextField();
		tfCountry1.setColumns(10);
		pnlShippingAddress.add(tfCountry1);
		
		label_4 = new JLabel("Code: ");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		pnlShippingAddress.add(label_4);
		
		tfCode1 = new JTextField();
		tfCode1.setColumns(10);
		pnlShippingAddress.add(tfCode1);
		
		pnlDiscount = new JPanel();
		pnlDiscount.setBackground(Color.WHITE);
		pnlDiscount.setBorder(new TitledBorder(null, "Check Out", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCustomerGenInfo.add(pnlDiscount, BorderLayout.SOUTH);
		pnlDiscount.setLayout(new GridLayout(0, 6, 0, 0));
		
		lblDiscount = new JLabel("Discount: ");
		lblDiscount.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDiscount.add(lblDiscount);
		
		tfDiscount = new JTextField();
		pnlDiscount.add(tfDiscount);
		tfDiscount.setColumns(10);
		
		lblRemark = new JLabel("Remark:");
		lblRemark.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDiscount.add(lblRemark);
		
		tfRemark = new JTextField();
		pnlDiscount.add(tfRemark);
		tfRemark.setColumns(10);
		
		lblTotal_1 = new JLabel("Total:");
		lblTotal_1.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDiscount.add(lblTotal_1);
		
		tfTotal = new JTextField();
		tfTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		tfTotal.setEnabled(false);
		pnlDiscount.add(tfTotal);
		tfTotal.setColumns(10);
		
		pnlCheckoutButton = new JPanel();
		pnlCheckoutButton.setBackground(Color.WHITE);
		pnlCenterButtom.add(pnlCheckoutButton, BorderLayout.SOUTH);
		
		btnCheckout = new JButton("Check Out");
		btnCheckout.setBackground(new Color(60, 179, 113));
		btnCheckout.setForeground(Color.WHITE);
		pnlCheckoutButton.add(btnCheckout);
		
		orderList2 = orderList;
	
		fillOrderTable(orderList);
		
		btnCheckOut.addActionListener(this);
		btnCheckout.addActionListener(this);
	}

	public List<OrderDetail> getOrderDetailFromDB(){
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(OrderDetail.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			System.out.println("Quering from db");
			session.beginTransaction();
			return session.createQuery("from OrderDetail").getResultList();
		}
		finally {
			factory.close();
		}
	}
	
	public void fillOrderTable(List<OrderDetail> orderList3) {
		System.out.println(orderList3.size());
		if(orderList3.size() > 0) {
			if(model.getRowCount() > 0) {
				model.getDataVector().removeAllElements();
			}
			
			for (OrderDetail orderDetail : orderList3) {
				model.addRow(new Object[] {orderDetail.getProduct_id(), orderDetail.getQty(),
						orderDetail.getPrice(), orderDetail.getDiscount(), orderDetail.getSubtotal() });
				subtotal += orderDetail.getSubtotal();
				lblTotal.setText("Total: $ " + subtotal);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Please go shopping Cart First", "Alert", JOptionPane.WARNING_MESSAGE);
			btnCheckOut.setEnabled(false);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCheckOut) {
			
			pnlCenterButtom.setVisible(true);
		}
		if(e.getSource() == rdbtnNo) {
			pnlShippingAddress.setVisible(true);
		}
		if(e.getSource() == rdbtnYes) {
			pnlShippingAddress.setVisible(false);
		}
		if(e.getSource() == btnCheckout) {
			if(isValidData()) {
				Double discount = Double.parseDouble(tfDiscount.getText());
				String remark = tfRemark.getText();
				double dis = subtotal * (0.01 * discount);
				total = subtotal - dis;
				
				String name = tfName.getText();
				String email = tfEmail.getText();
				String phone = tfPhoneNumber.getText();
				
				Order order = new Order();
				order.setDiscount(discount);
				order.setRemark(remark);
				order.setTotal(total);
				
				Address billingAddress = new Address(tfStreet.getText(), tfCity.getText(), tfState.getText(), tfCountry.getText(), tfCode.getText());
				Address shippingAddress = new Address(tfStreet1.getText(), tfCity1.getText(), tfState1.getText(), tfCountry1.getText(), tfCode1.getText());
				List<Address> addressList = new ArrayList<Address>();
				addressList.add(billingAddress);
				addressList.add(shippingAddress);
				
				Customer customer = new Customer(name, email, phone);
				customer.setAddresslist(addressList);
				
				order.setCustomer(customer);
				order.setOrderList(orderList2);
				
				
				for(OrderDetail orderDetail : orderList2) {
					double qty = orderDetail.getQty();
					double stockQty = getQty(orderDetail.getProduct_id());
					System.out.println(stockQty - qty);
					updateStockQTYDB(stockQty - qty, orderDetail.getProduct_id());
				}
				tfTotal.setText(total + "");
				saveOrderDB(order);
			}
		}
	}
	
	public void saveOrderDB(Order order) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Order.class)
				.addAnnotatedClass(OrderDetail.class)
				.addAnnotatedClass(Address.class)
				.addAnnotatedClass(Customer.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(order);
			session.getTransaction().commit();
			session.close();
			System.out.println("Done");
		}
		finally {
			factory.close();
		}
				
	}
	
	public void updateStockQTYDB(double qty, int stock_id) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Stock.class)
				.addAnnotatedClass(Book.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			session.createQuery("update Stock set qty = '" + qty + "' where id = '" + stock_id + "' ").executeUpdate();
			session.close();
			System.out.println("Updated!!!");
		}
		finally {
			factory.close();
		}
		
	}
	
	private List<Stock> stocks = getStockFromDB();
	
	public List<Stock> getStockFromDB(){
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Stock.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			System.out.println("Quering from db");
			session.beginTransaction();
			return session.createQuery("from Stock").getResultList();
		}
		finally {
			factory.close();
		}
	}
	
	public double getQty(int id) {
		for(Stock stock : stocks) {
			if(stock.getId() == id) {
				return stock.getQty();
			}
		}
		return 0;
	}
	
	public boolean isValidData() {
		return ValidatorClass.isPresent(tfName, "Customer Name") && ValidatorClass.isPresent(tfEmail, "Email") && ValidatorClass.isPresent(tfPhoneNumber, "Phone Number")
				&&  ValidatorClass.isPresent(tfDiscount, "Discount") && ValidatorClass.isDouble(tfDiscount, "Discount") && ValidatorClass.isPresent(tfRemark, "Remark");
	}
}
