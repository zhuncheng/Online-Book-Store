package com.ckcc.ass.OnlineBookStore1;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import entities.Book;
import entities.Stock;
import helper.DBHelper;

import java.awt.event.*;
import java.util.List;
import javax.swing.border.*;

public class ListProductsPanel extends JPanel implements ActionListener{
	private JTable table;
	private JPanel panel, pnlHeader, pnlCenter, pnlCTop, pnlButtons, pnlBtn, pnlButton;
	private Object[] columns = {"ID#", "Title", "Publisher", "Published Year", "ISBN", "Price", "Author", "Edition", "Valume", "Quantity"};
	private DefaultTableModel model;
	private JButton btnRefresh, btnSearch, btnEdit, btnSave;
	private JComboBox<String> cboFilter;
	private JTextField tfSearch, tfTitle, tfPublisher, tfPrice, tfQty;
	private Color color = new Color(250, 250, 252);
	private List<Book> books;
	private JPanel pnlBookInfo, pnlEdit, pnlCCenter, pnl1;
	private JLabel lblPublisher, lblTitle, lblPrice1, lblValume, lblBookInformation;
	private JSeparator separator;
	
	/**
	 * Create the panel.
	 */
	public ListProductsPanel() {
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(10, 10));
		//
		pnlHeader = new JPanel();
		pnlHeader.setBackground(new Color(0, 128, 128));
		panel.add(pnlHeader, BorderLayout.NORTH);
		
		JLabel lblListOfItems = new JLabel("List Of Items");
		lblListOfItems.setFont(new Font("Verdana", Font.BOLD, 14));
		lblListOfItems.setForeground(Color.WHITE);
		pnlHeader.add(lblListOfItems);
		
		//
		pnlCenter = new JPanel();
		pnlCenter.setBackground(Color.WHITE);
		panel.add(pnlCenter, BorderLayout.CENTER);
	
		pnlCenter.setLayout(new BorderLayout(10, 10));
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		model.setColumnIdentifiers(columns);
		
		pnlCCenter = new JPanel();
		pnlCCenter.setBackground(new Color(255, 255, 255));
		pnlCenter.add(pnlCCenter, BorderLayout.CENTER);
		pnlCCenter.setLayout(new BorderLayout(10, 10));
		
		
		
			fillStockToTable();
			//DBHelper.fillBookToTable(table, model);
			
			table = new JTable(model);
			table.setRowMargin(4);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setSelectionBackground(new Color(0, 51, 51));
			table.setSelectionForeground(Color.WHITE);
			table.setForeground(Color.WHITE);
			table.setGridColor(new Color(255, 255, 255));
			table.setBackground(new Color(0, 148, 158));
			
			
			JScrollPane sp = new JScrollPane(table);
			pnlCCenter.add(sp, BorderLayout.CENTER);
			
			pnlCTop = new JPanel();
			pnlCCenter.add(pnlCTop, BorderLayout.NORTH);
			pnlCTop.setBackground(new Color(230, 230, 250));
			pnlCTop.setLayout(new BorderLayout(40, 40));
			
			tfSearch = new JTextField();
			tfSearch.setOpaque(false);
			tfSearch.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
			pnlCTop.add(tfSearch, BorderLayout.CENTER);
			tfSearch.setColumns(10);
			
			btnSearch = new JButton("Search");
			btnSearch.setFocusPainted(false);
			btnSearch.setIcon(new ImageIcon(ListProductsPanel.class.getResource("/Resource/Search_12px_1.png")));
			btnSearch.setForeground(new Color(255, 255, 255));
			btnSearch.setBackground(new Color(60, 179, 113));
			pnlCTop.add(btnSearch, BorderLayout.EAST);
			
			btnRefresh = new JButton("Refresh");
			pnlCTop.add(btnRefresh, BorderLayout.WEST);
			btnRefresh.setIcon(new ImageIcon(ListProductsPanel.class.getResource("/Resource/Refresh_24px.png")));
			btnRefresh.setFocusPainted(false);
			btnRefresh.setBackground(new Color(60, 179, 113));
			btnRefresh.setForeground(new Color(255, 255, 255));
			
			btnRefresh.addActionListener(this);
			
			pnlButton = new JPanel();
			pnlCCenter.add(pnlButton, BorderLayout.SOUTH);
			pnlButton.setLayout(new GridLayout(0, 2, 20, 10));
			
			btnEdit = new JButton("      EDIT      ");
			btnEdit.setForeground(Color.WHITE);
			btnEdit.setFocusPainted(false);
			btnEdit.setBackground(new Color(60, 179, 113));
			pnlButton.add(btnEdit);
			
			btnDelete = new JButton("      DELETE      ");
			btnDelete.setForeground(Color.WHITE);
			btnDelete.setFocusPainted(false);
			btnDelete.setBackground(new Color(60, 179, 113));
			pnlButton.add(btnDelete);
			
			pnlEdit = new JPanel();
			pnlEdit.setBackground(Color.WHITE);
			pnlEdit.setBorder(null);
//			pnlEdit.setVisible(false);
			pnlCenter.add(pnlEdit, BorderLayout.EAST);
			pnlEdit.setLayout(new BorderLayout(0, 10));
			
			pnl1 = new JPanel();
			pnl1.setBackground(color);
			pnlEdit.add(pnl1, BorderLayout.NORTH);
			pnl1.setLayout(new BorderLayout(0, 0));
			
			separator = new JSeparator();
			pnl1.add(separator, BorderLayout.CENTER);
			
			pnlBookInfo = new JPanel();
			pnlBookInfo.setBackground(Color.WHITE);
			pnl1.add(pnlBookInfo, BorderLayout.SOUTH);
			pnlBookInfo.setLayout(new GridLayout(4, 2, 10, 10));
			
			lblTitle = new JLabel("     Title:");
			lblTitle.setFont(new Font("Verdana", Font.PLAIN, 12));
			pnlBookInfo.add(lblTitle);
			
			tfTitle = new JTextField();
			tfTitle.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
			tfTitle.setOpaque(false);
			pnlBookInfo.add(tfTitle);
			tfTitle.setColumns(10);
			
			lblPublisher = new JLabel("     Publisher:");
			lblPublisher.setFont(new Font("Verdana", Font.PLAIN, 12));
			pnlBookInfo.add(lblPublisher);
			
			tfPublisher = new JTextField();
			tfPublisher.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
			tfPublisher.setOpaque(false);
			pnlBookInfo.add(tfPublisher);
			tfPublisher.setColumns(10);
			
			lblPrice1 = new JLabel("     Price:");
			lblPrice1.setFont(new Font("Verdana", Font.PLAIN, 12));
			pnlBookInfo.add(lblPrice1);
			
			tfPrice = new JTextField();
			tfPrice.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
			tfPrice.setOpaque(false);
			pnlBookInfo.add(tfPrice);
			tfPrice.setColumns(10);
			
			lblValume = new JLabel("     Quantity:");
			lblValume.setFont(new Font("Verdana", Font.PLAIN, 12));
			pnlBookInfo.add(lblValume);
			
			tfQty = new JTextField();
			tfQty.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
			tfQty.setOpaque(false);
			pnlBookInfo.add(tfQty);
			tfQty.setColumns(10);
			
			pnlTitle = new JPanel();
			pnlTitle.setBackground(new Color(0, 128, 128));
			pnl1.add(pnlTitle, BorderLayout.NORTH);
			pnlTitle.setLayout(new BorderLayout(0, 0));
			
			lblBookInformation = new JLabel("Book Information");
			pnlTitle.add(lblBookInformation, BorderLayout.CENTER);
			lblBookInformation.setBackground(new Color(0, 128, 128));
			lblBookInformation.setHorizontalAlignment(SwingConstants.CENTER);
			lblBookInformation.setForeground(Color.WHITE);
			lblBookInformation.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			pnlBtn = new JPanel();
			pnlBtn.setBackground(Color.WHITE);
			pnlEdit.add(pnlBtn, BorderLayout.CENTER);
			pnlBtn.setLayout(new BorderLayout(0, 0));
			
			btnSave = new JButton("Save");
			btnSave.setForeground(new Color(255, 255, 255));
			btnSave.setBackground(new Color(60, 179, 113));
			pnlBtn.add(btnSave, BorderLayout.NORTH);
			btnSearch.addActionListener(this);
		
		pnlButtons = new JPanel();
		pnlButtons.setVisible(false);
		pnlButtons.setBackground(color);
		panel.add(pnlButtons, BorderLayout.WEST);
		pnlButtons.setLayout(new BorderLayout(0, 0));
		
		cboFilter = new JComboBox<String>();
		pnlButtons.add(cboFilter, BorderLayout.NORTH);
		cboFilter.setPreferredSize(new Dimension(80, 20));
		cboFilter.setBackground(new Color(230, 230, 250));
		cboFilter.setAlignmentX(Component.RIGHT_ALIGNMENT);
		cboFilter.addItem("ID");
		cboFilter.addItem("Title");
		cboFilter.addItem("Author");
		
		btnEdit.addActionListener(this);
		btnSave.addActionListener(this);
		btnDelete.addActionListener(this);
	}
	
	boolean isClicked = false;
	private JButton btnDelete;
	private JPanel pnlTitle;
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {	
		//Button Refresh
		if(e.getSource() == btnRefresh && isClicked) {
			fillStockToTable();
		}
		
		//Button Search
		if(e.getSource() == btnSearch) {
			String search = "";
			if(tfSearch.getText().equals("")) {
				JOptionPane.showMessageDialog(pnlCenter, "Please enter text to search", "Warning", JOptionPane.WARNING_MESSAGE);
				tfSearch.requestFocus();
			}
			else
			{
				 search = tfSearch.getText().toLowerCase();
			
				List<Book> books = searchBookDB(search);
				
				if(books.isEmpty()) {
					JOptionPane.showMessageDialog(table, "Search not found!", "Alert", JOptionPane.WARNING_MESSAGE);
				}
				else {
					//remove all rows from table
					model.getDataVector().removeAllElements();
					revalidate();
					//add to table
					for (Book book : books) {
						model.addRow(book.getBookModel());
					}
					isClicked = true;
				}
			}
		}
		
		//Button Edit
		if(e.getSource() == btnEdit) {
			if(table.getSelectedRow() > -1) {
				int index = table.getSelectedRow();
				tfTitle.setText(model.getValueAt(index, 1).toString());
				tfPublisher.setText(model.getValueAt(index, 2).toString());
				tfPrice.setText(model.getValueAt(index, 5).toString());
				tfQty.setText(model.getValueAt(index, 9).toString());
			}
		}
		if(e.getSource() == btnSave) {
			if(table.getSelectedRow() > -1) {
				int index = table.getSelectedRow();
				int id = (Integer) model.getValueAt(index, 0);
				double price = Double.parseDouble(tfPrice.getText());
				double qty = Double.parseDouble(tfQty.getText());
				updateStockQtyDB(id, qty);
				model.setValueAt(tfQty.getText(), index, 9);
				
			}
		}
		
		//btn Delete
		if(e.getSource() == btnDelete) {
			if(table.getSelectedRow() > -1) {
				int index = table.getSelectedRow();
				int id = (Integer) model.getValueAt(index, 0);
				//remove from table
				model.removeRow(index);
				//delete from db
				deleteStockDB(id);
			}
			else {
				JOptionPane.showMessageDialog(null, "Please select a row to delete", "Cannot Delete", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	public List<Book> getBookFromDB(){
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Book.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			System.out.println("Quering from db");
			session.beginTransaction();
			return session.createQuery("from Book").getResultList();
		}
		finally {
			factory.close();
		}
	}
	
	public List<Book> searchBookDB(String search){
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Book.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {	
			session.beginTransaction();
			return session.createQuery("from Book b where b.title = '" + search + "' OR b.author = '" + search + "' " ).getResultList();
		}finally {
			factory.close();
		}
	}
	
	public void updateStockQtyDB(int stock_id, double qty) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Book.class)
				.addAnnotatedClass(Stock.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {	
			session.beginTransaction();
			session.createQuery("update Stock set qty = '" + qty + "' where id = '" + stock_id + "' " ).executeUpdate();
		}finally {
			factory.close();
		}
	}
	
	
	public void deleteStockDB(int stock_id) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Stock.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {	
			session.beginTransaction();
			session.createQuery("delete Stock where id = '" + stock_id + "' ").executeUpdate();
		}finally {
			factory.close();
		}
	}
	
	public void fillStockToTable() {
		List<Stock> stocks = getStockFromDB();
		if(stocks.size() > 0) {
			if(model.getRowCount() > 0) {
				model.getDataVector().removeAllElements();
			}
			for (Stock stock : stocks) {
				model.addRow(stock.getStockModel());
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
}
