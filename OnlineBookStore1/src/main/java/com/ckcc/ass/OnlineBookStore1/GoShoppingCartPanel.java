package com.ckcc.ass.OnlineBookStore1;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.*;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import entities.Book;
import entities.Order;
import entities.OrderDetail;
import entities.Stock;
import helper.DBHelper;


public class GoShoppingCartPanel extends JPanel {
	private JTextField tfBookID;
	private JTextField tfBookQty;
	private JTextField tfDiscount;
	private List<Book> books;
	private JButton btnAddToCart;
	private JTable table;
	private DefaultTableModel model;
	private Object[] columns = {"ID#", "Title", "ISBN", "Price", "Author", "Quantity"};
	private JTextField tfPrice;
	private JTextField tfSubTotal;
	
	private Order order = new Order();
//	private OrderDetailListHelper helper;

	/**
	 * Create the panel.
	 */
	public GoShoppingCartPanel(final List<OrderDetail> orderList) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(10, 10));
		
		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(new Color(0, 128, 128));
		panel.add(pnlHeader, BorderLayout.NORTH);
		
		JLabel lblGoShoppingCart = new JLabel("Go Shopping Cart");
		lblGoShoppingCart.setFont(new Font("Verdana", Font.BOLD, 14));
		lblGoShoppingCart.setForeground(Color.WHITE);
		pnlHeader.add(lblGoShoppingCart);
		
		JPanel pnlCenter = new JPanel();
		pnlCenter.setBackground(new Color(244, 244, 255));
		panel.add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new BorderLayout(0, 10));
		
		JPanel pnlCenterTop = new JPanel();
		pnlCenter.add(pnlCenterTop, BorderLayout.NORTH);
		pnlCenterTop.setLayout(new BorderLayout(10, 0));
		pnlCenterTop.setBackground(Color.white);
		
		JPanel pnlInfo = new JPanel();
		pnlCenterTop.add(pnlInfo, BorderLayout.CENTER);
		pnlInfo.setBackground(new Color(255, 255, 255));
		pnlInfo.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblInputBookIsbn = new JLabel("       Input Book ISBN:");
		lblInputBookIsbn.setForeground(new Color(0, 0, 0));
		pnlInfo.add(lblInputBookIsbn);
		
		tfBookID = new JTextField();
		tfBookID.setEditable(false);
		tfBookID.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		pnlInfo.add(tfBookID);
		tfBookID.setColumns(10);
		
		JLabel lblInputBookQty = new JLabel("       Input Book Qty:");
		lblInputBookQty.setForeground(new Color(0, 0, 0));
		pnlInfo.add(lblInputBookQty);
		
		tfBookQty = new JTextField();
		tfBookQty.setOpaque(false);
		tfBookQty.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		pnlInfo.add(tfBookQty);
		tfBookQty.setColumns(10);
		
		JLabel lblDiscount = new JLabel("       Discount:");
		lblDiscount.setForeground(new Color(0, 0, 0));
		pnlInfo.add(lblDiscount);
		
		tfDiscount = new JTextField();
		tfDiscount.setOpaque(false);
		tfDiscount.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		pnlInfo.add(tfDiscount);
		tfDiscount.setColumns(10);
		
		JLabel lblPrice = new JLabel("       Price: ");
		lblPrice.setForeground(new Color(0, 0, 0));
		pnlInfo.add(lblPrice);
		
		tfPrice = new JTextField();
		tfPrice.setEditable(false);
		tfPrice.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		pnlInfo.add(tfPrice);
		tfPrice.setColumns(10);
		
		JLabel lblSubTotal = new JLabel("       Sub Total:");
		pnlInfo.add(lblSubTotal);
		
		tfSubTotal = new JTextField();
		tfSubTotal.setEditable(false);
		tfSubTotal.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		pnlInfo.add(tfSubTotal);
		tfSubTotal.setColumns(10);
		
		JPanel pnlButtons = new JPanel();
		pnlCenterTop.add(pnlButtons, BorderLayout.EAST);
		pnlButtons.setBackground(new Color(255, 255, 255));
		pnlButtons.setLayout(new GridLayout(3, 1, 0, 0));
		
		btnAddToCart = new JButton("Add To Cart");
		btnAddToCart.setFocusPainted(false);
		btnAddToCart.setForeground(new Color(255, 255, 255));
		btnAddToCart.setIcon(new ImageIcon(GoShoppingCartPanel.class.getResource("/Resource/Shopping Cart_24px.png")));
		btnAddToCart.setBackground(new Color(60, 179, 113));
		pnlButtons.add(btnAddToCart);
		
		JPanel pnlCenterButtom = new JPanel();
		pnlCenter.add(pnlCenterButtom, BorderLayout.CENTER);
		pnlCenterButtom.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTable = new JPanel();
		pnlCenterButtom.add(pnlTable, BorderLayout.CENTER);
		pnlTable.setLayout(new BorderLayout(0, 0));
		
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		model.setColumnIdentifiers(columns);
		table = new JTable(model);
		table.setSelectionForeground(Color.WHITE);
		table.setBackground(new Color(0, 139, 139));
		table.setForeground(Color.WHITE);
		table.setSelectionBackground(new Color(0, 51, 51));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRow() > -1) {
					int index = table.getSelectedRow();
					tfBookID.setText(model.getValueAt(index, 0).toString());
					tfPrice.setText(model.getValueAt(index, 3).toString());
				}
			}
		});
		JScrollPane sp = new JScrollPane(table);
		pnlTable.add(sp, BorderLayout.CENTER);
		
		books = DBHelper.getBookFromDB();
		//fillBooksToTable();
		//DBHelper.fillBookToTable(table, model);
		//fillBooksToTable();
		fillStockTable();
		
		btnAddToCart.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnAddToCart) {
					//orderDetails = new ArrayList<OrderDetail>();
					if(tfBookID.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please select a book in table to do the shopping cart.");
					}
					else if(isValidData()) {
						int product_id = Integer.parseInt(tfBookID.getText());
						double qty = Double.parseDouble(tfBookQty.getText());
						double discount = Double.parseDouble(tfDiscount.getText());
						double price = Double.parseDouble(tfPrice.getText());
						double total = qty * price;
						double subtotal = total - (discount * total)/ 100; 
					
						double qtyDB = Double.parseDouble(model.getValueAt(table.getSelectedRow(), 5).toString());
						
						if(qty <= qtyDB) 
						{
							tfSubTotal.setText(subtotal + " $");
							OrderDetail od = new OrderDetail(product_id, qty, discount, subtotal, price);
							
							orderList.add(od);
							//saveOrderDetailToDB(od);
						}
						else 
						{
							String msg = "The Qty of book you want to buy is out of stock\n\nPlese enter new qty";
							JOptionPane.showMessageDialog(null, msg);
							tfBookQty.setText("");
							tfBookQty.requestFocus();
						}
					}
				
				
					}
				}
		});
		
	}
	
	public void fillStockTable() {
		List<Stock> stocks = getStockFromDB();
		if(stocks.size() > 0) {
			if(model.getRowCount() > 0) {
				model.getDataVector().removeAllElements();
			}
			for (Stock stock : stocks) {
				model.addRow(new Object[] {stock.getBook().getId(), stock.getBook().getTitle(),  stock.getBook().getISBN(), stock.getBook().getPrice(), stock.getBook().getAuthor(), 
						 stock.getQty()});
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "No Stock!!!");
		}
	}
	
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
	
	public void saveOrderDetailToDB(OrderDetail od) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(OrderDetail.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(od);
			session.getTransaction().commit();
			
		}finally {
			factory.close();
		}
	}
	
	public void saveOrderToDB(Order order) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(OrderDetail.class)
				.addAnnotatedClass(Order.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(order);
			session.getTransaction().commit();
			
		}finally {
			factory.close();
		}
	}
	
	public boolean isInteger(JTextField textField, String name) {
		try {
			Integer.parseInt(textField.getText());
			return true;
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, name + " must be an Integer.", "Error Entry", JOptionPane.ERROR_MESSAGE);
			textField.requestFocus();
			return false;
		}
	}
	
	public boolean isDouble(JTextField textField, String name) {
		try {
			Double.parseDouble(textField.getText());
			return true;
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, name + " must be a Double.", "Error Entry", JOptionPane.ERROR_MESSAGE);
			textField.requestFocus();
			return false;
		}
	}
	
	public boolean isPresent(JTextField textField, String name) {
		if(textField.getText().equals("")) {
			JOptionPane.showMessageDialog(null, name + " is required a field.", "Error Entry", JOptionPane.ERROR_MESSAGE);
			textField.requestFocus();
			return false;
		}
		return true;
	}
	
	public boolean isValidData() {
		return isPresent(tfBookQty, "Book Qty") && isInteger(tfBookQty, "Textfield Book Qty") && isInteger(tfBookID, "Textfield Book ID") && isPresent(tfDiscount, "TextField Discount") && isDouble(tfDiscount, "Textfield Discount");
	}
}
