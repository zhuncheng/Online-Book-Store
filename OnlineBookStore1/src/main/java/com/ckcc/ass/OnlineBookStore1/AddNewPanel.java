package com.ckcc.ass.OnlineBookStore1;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entities.Book;
import entities.Stock;
import helper.DBHelper;

import java.awt.*;

public class AddNewPanel extends JPanel implements ActionListener{

	private JPanel panel, pnlRight, pnlLeft, pnlHeader, pnlRightTop, pnlBookInfo, pnlItemInfo, pnlButtons;
	private JLabel lblAddNew, lblNewLabel, lblPublisher, lblPublishedYear, lblIsbn, lblPrice, lblA, lblAuthor, lblvalume;
	private JTextField tfTitle, tfPublisher, tfYearPublished, tfISBN, tfPrice, tfAuthor, tfEdition, tfValume; 
	private JButton btnNew, btnClear;
	private Color color = new Color(0, 139, 139);
	private JTable table;
	private Object[] columns = {"ID#", "Title", "Publisher", "Published Year", "ISBN", "Price", "Author", "Editor", "Valumne", "QTY"};
	private DefaultTableModel model;
	private JLabel lblQuantity;
	private JTextField tfQty;

	/**
	 * Create the panel.
	 */
	public AddNewPanel() {
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel);
		panel.setLayout(new BorderLayout(20, 10));
		
		pnlHeader = new JPanel();
		pnlHeader.setBackground(Color.BLACK);
		panel.add(pnlHeader, BorderLayout.NORTH);
		
		lblAddNew = new JLabel("Add New Book(s)");
		lblAddNew.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNew.setFont(new Font("Verdana", Font.BOLD, 14));
		lblAddNew.setForeground(Color.WHITE);
		pnlHeader.add(lblAddNew);
		
		pnlLeft = new JPanel();
		pnlLeft.setBackground(new Color(0, 204, 153));
		panel.add(pnlLeft, BorderLayout.CENTER);
		
		//make table become uneditable 
		model = new DefaultTableModel();
		model = new DefaultTableModel(5, 9) {
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		
		model.setColumnIdentifiers(columns);
		pnlLeft.setLayout(new BorderLayout(0, 0));
		
//		DBHelper.fillBookToTable(table, model);
		fillStockTable();
		table = new JTable(model);
		table.setForeground(Color.WHITE);
		table.setSelectionForeground(Color.WHITE);
		table.setBackground(color);
		table.setSelectionBackground(new Color(0, 51, 51));
		JScrollPane sp = new JScrollPane(table);
		pnlLeft.add(sp);
		
		pnlRight = new JPanel();
		pnlRight.setPreferredSize(new Dimension(300, 10));
		pnlRight.setBackground(Color.WHITE);
		panel.add(pnlRight, BorderLayout.WEST);
		pnlRight.setLayout(new BorderLayout(0, 0));
		
		pnlRightTop = new JPanel();
		pnlRightTop.setBackground(new Color(240, 248, 240));
		pnlRight.add(pnlRightTop, BorderLayout.NORTH);
		pnlRightTop.setLayout(new BorderLayout(0, 10));
		
		pnlItemInfo = new JPanel();
		pnlRightTop.add(pnlItemInfo, BorderLayout.NORTH);
		pnlItemInfo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Item Information", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnlItemInfo.setLayout(new GridLayout(0, 2, 20, 20));
		pnlItemInfo.setBackground(color);
		
		lblNewLabel = new JLabel("      Title:");
		lblNewLabel.setFont(new Font("Source Sans Pro Light", Font.PLAIN, 12));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		pnlItemInfo.add(lblNewLabel);
		
		tfTitle = new JTextField();
		tfTitle.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		tfTitle.setOpaque(false);
		pnlItemInfo.add(tfTitle);
		tfTitle.setColumns(10);
		
		lblPublisher = new JLabel("       Publisher:");
		lblPublisher.setFont(new Font("Source Sans Pro Light", Font.PLAIN, 12));
		lblPublisher.setForeground(Color.WHITE);
		lblPublisher.setHorizontalAlignment(SwingConstants.LEFT);
		pnlItemInfo.add(lblPublisher);
		
		tfPublisher = new JTextField();
		tfPublisher.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		tfPublisher.setOpaque(false);
		pnlItemInfo.add(tfPublisher);
		tfPublisher.setColumns(10);
		
		lblPublishedYear = new JLabel("       Published Year: ");
		lblPublishedYear.setFont(new Font("Source Sans Pro Light", Font.PLAIN, 12));
		lblPublishedYear.setForeground(Color.WHITE);
		lblPublishedYear.setHorizontalAlignment(SwingConstants.LEFT);
		pnlItemInfo.add(lblPublishedYear);
		
		tfYearPublished = new JTextField();
		tfYearPublished.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		tfYearPublished.setOpaque(false);
		pnlItemInfo.add(tfYearPublished);
		tfYearPublished.setColumns(10);
		
		lblIsbn = new JLabel("       ISBN:");
		lblIsbn.setFont(new Font("Source Sans Pro Light", Font.PLAIN, 12));
		lblIsbn.setForeground(Color.WHITE);
		lblIsbn.setHorizontalAlignment(SwingConstants.LEFT);
		pnlItemInfo.add(lblIsbn);
		
		tfISBN = new JTextField();
		tfISBN.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		tfISBN.setOpaque(false);
		pnlItemInfo.add(tfISBN);
		tfISBN.setColumns(10);
		
		lblPrice = new JLabel("       Price:");
		lblPrice.setFont(new Font("Source Sans Pro Light", Font.PLAIN, 12));
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		pnlItemInfo.add(lblPrice);
		
		tfPrice = new JTextField();
		tfPrice.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		tfPrice.setOpaque(false);
		pnlItemInfo.add(tfPrice);
		tfPrice.setColumns(10);
		
		pnlBookInfo = new JPanel();
		pnlRightTop.add(pnlBookInfo, BorderLayout.CENTER);
		pnlBookInfo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Book Information", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnlBookInfo.setBackground(color);
		pnlBookInfo.setLayout(new GridLayout(0, 2, 20, 20));
		
		lblAuthor = new JLabel("      Author:");
		lblAuthor.setFont(new Font("Source Sans Pro Light", Font.PLAIN, 12));
		lblAuthor.setForeground(Color.WHITE);
		lblAuthor.setHorizontalAlignment(SwingConstants.LEFT);
		pnlBookInfo.add(lblAuthor);
		
		tfAuthor = new JTextField();
		tfAuthor.setOpaque(false);
		tfAuthor.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		pnlBookInfo.add(tfAuthor);
		tfAuthor.setColumns(10);
		
		lblA = new JLabel("       Edition:");
		lblA.setFont(new Font("Source Sans Pro Light", Font.PLAIN, 12));
		lblA.setForeground(Color.WHITE);
		lblA.setHorizontalAlignment(SwingConstants.LEFT);
		pnlBookInfo.add(lblA);
		
		tfEdition = new JTextField();
		tfEdition.setOpaque(false);
		tfEdition.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		pnlBookInfo.add(tfEdition);
		tfEdition.setColumns(10);
		
		lblvalume = new JLabel("       Valume");
		lblvalume.setFont(new Font("Source Sans Pro Light", Font.PLAIN, 12));
		lblvalume.setForeground(Color.WHITE);
		lblvalume.setHorizontalAlignment(SwingConstants.LEFT);
		pnlBookInfo.add(lblvalume);
		
		tfValume = new JTextField();
		tfValume.setOpaque(false);
		tfValume.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		pnlBookInfo.add(tfValume);
		tfValume.setColumns(10);
		
		lblQuantity = new JLabel("     Quantity:");
		lblQuantity.setForeground(Color.WHITE);
		lblQuantity.setHorizontalAlignment(SwingConstants.LEFT);
		pnlBookInfo.add(lblQuantity);
		
		tfQty = new JTextField();
		pnlBookInfo.add(tfQty);
		tfQty.setColumns(10);
		
		pnlButtons = new JPanel();
		pnlRightTop.add(pnlButtons, BorderLayout.SOUTH);
		pnlButtons.setBackground(new Color(240, 248, 240));
		pnlButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnNew = new JButton("Add to Stock");
		btnNew.setForeground(Color.WHITE);
		btnNew.setBackground(new Color(60, 179, 113));
		pnlButtons.add(btnNew);
		
		btnClear = new JButton("     Clear     ");
		btnClear.setForeground(Color.WHITE);
		btnClear.setBackground(new Color(255, 69, 0));
		pnlButtons.add(btnClear);
		
		btnNew.addActionListener(this);
		btnClear.addActionListener(this);

	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnNew) {
			if(isValidData()) {
				String title = tfTitle.getText();
				String publisher = tfPublisher.getText();
				String publishedDate = tfYearPublished.getText();
				String ISBN = tfISBN.getText();
				double price = Double.parseDouble(tfPrice.getText());
				String author = tfAuthor.getText();
				int edition = Integer.parseInt(tfEdition.getText());
				int valume = Integer.parseInt(tfValume.getText());
				int qty = Integer.parseInt(tfQty.getText());
				
				Book book = new Book(title, publisher, publishedDate, ISBN, price, author, edition, valume);
				
				Stock stock = new Stock();
				stock.setQty(qty);
				stock.setBook(book);
				//book.setStock(stock);
				addStockToDB(stock);
//				model.addRow(new Object[] {});
			}
		}
		
		if(e.getSource() == btnClear) {
			clearControls();
		}
	}
	
	public void clearControls() {
		tfTitle.setText("");
		tfPublisher.setText("");
		tfYearPublished.setText("");
		tfISBN.setText("");
		tfPrice.setText("");
		tfAuthor.setText("");
		tfEdition.setText("");
		tfValume.setText("");
	}
	
	public void addStockToDB(Stock stock) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Stock.class)
				.addAnnotatedClass(Book.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(stock);
			session.getTransaction().commit();
			model.addRow(stock.getStockModel());
			System.out.println(stock.toString());
			System.out.println("saving to table stock");
			
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
		return isPresent(tfTitle, "Textfield") && isPresent(tfPublisher, "Textfield Publisher") && isPresent(tfYearPublished, "Textfield Published Year")
				&& isPresent(tfISBN, "Textfield ISBN") && isPresent(tfAuthor, "TextField Author") && isPresent(tfPrice, "Textfield Price")
				&& isDouble(tfPrice, "Textfield Price") && isPresent(tfEdition, "TextField Edition") && isInteger(tfEdition, "TextField Edition")
				&& isPresent(tfValume, "Textfield Valume") && isInteger(tfValume, "TextField Valume");
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
	
	public void fillStockTable() {
		List<Stock> stocks = getStockFromDB();
		if(stocks.size() > 0) {
			if(model.getRowCount() > 0) {
				model.getDataVector().removeAllElements();
			}
			for (Stock stock : stocks) {
				model.addRow(new Object[] {stock.getBook().getId(), stock.getBook().getTitle(), stock.getBook().getPublisher(),
						stock.getBook().getPublishedYear(), stock.getBook().getISBN(), stock.getBook().getPrice(), stock.getBook().getAuthor(), 
						stock.getBook().getEdition(), stock.getBook().getValume(), stock.getQty()});
			}
		}
		else {
			//JOptionPane.showMessageDialog(null, "No Stock!!!");
		}
	}
	
	public Book getBookFromDB(int book_id){
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Book.class)
				.addAnnotatedClass(Stock.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();		
				//return session.createQuery("from Book where id = '" + book_id + "' " ).getResultList();
			return session.get(Book.class, book_id);
		}
		finally {
			factory.close();
		}
	}
}
