 package com.ckcc.ass.OnlineBookStore1;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.*;
import java.awt.Desktop.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import entities.OrderDetail;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame implements ActionListener {
	private Color color = new Color(0, 50, 51);
	private JButton btnMenu;
	
	//button for pnl menu left (icon button)
	private JButton btn, btnAddNewL, btnListItemL, btnSearchItemL, btnGoShoppingCartL, btnMyShoppingCartL, btnCheckOutL, btnAddNewbooks;
	private JPanel pnlMenuR, pnlMenuLeft, pnlLeft, pnlRight;
	private final List<OrderDetail> orderList = new ArrayList<OrderDetail>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		setTitle("Online Bookstore V1.0");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		setBounds(100, 100, 1014, 610);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(10, 20));
		
		pnlLeft = new JPanel();
		pnlLeft.setBackground(Color.BLACK);
		panel.add(pnlLeft, BorderLayout.WEST);
		pnlLeft.setLayout(new BorderLayout(0, 0));
		
		pnlMenus = new JPanel();
		pnlMenus.setBackground(Color.BLACK);
		pnlLeft.add(pnlMenus, BorderLayout.WEST);
		pnlMenus.setLayout(new BorderLayout(0, 0));
		
		pnlMenuLeft = new JPanel();
		pnlMenus.add(pnlMenuLeft);
		pnlMenuLeft.setBorder(null);
		pnlMenuLeft.setBackground(new Color(0, 0, 0));
		pnlMenuLeft.setLayout(new GridLayout(10, 1, 0, 0));
		
		btnAddNewL = new JButton("");
		btnAddNewL.setFocusTraversalPolicyProvider(true);
		btnAddNewL.setBorderPainted(false);
		btnAddNewL.setFocusPainted(false);
		btnAddNewL.setToolTipText("Add New (Books, CDs, Magazines)");
		//btnAddNewL.setContentAreaFilled(false);
		btnAddNewL.setHorizontalAlignment(SwingConstants.LEFT);
		btnAddNewL.setIcon(new ImageIcon(MainFrame.class.getResource("/Resource/Books_32px.png")));
		btnAddNewL.setBackground(new Color(0, 0, 0));
		addMouseEffectToButton(btnAddNewL);
		pnlMenuLeft.add(btnAddNewL);
		
		
		btnListItemL = new JButton("");
		btnListItemL.setBorderPainted(false);
		btnListItemL.setFocusPainted(false);
		//btnListItemL.setContentAreaFilled(false);
		btnListItemL.setHorizontalAlignment(SwingConstants.LEFT);
		btnListItemL.setIcon(new ImageIcon(MainFrame.class.getResource("/Resource/Shop_24px.png")));
		btnListItemL.setBackground(Color.BLACK);
		addMouseEffectToButton(btnListItemL);
		pnlMenuLeft.add(btnListItemL);
		
		btnSearchItemL = new JButton("");
		btnSearchItemL.setBorderPainted(false);
		btnSearchItemL.setFocusPainted(false);
		btnSearchItemL.setHorizontalAlignment(SwingConstants.LEFT);
		btnSearchItemL.setBackground(Color.BLACK);
		btnSearchItemL.setIcon(new ImageIcon(MainFrame.class.getResource("/Resource/Search_24px.png")));
		addMouseEffectToButton(btnSearchItemL);
		pnlMenuLeft.add(btnSearchItemL);
		
		btnGoShoppingCartL = new JButton("");
		btnGoShoppingCartL.setBorderPainted(false);
		btnGoShoppingCartL.setFocusPainted(false);
		btnGoShoppingCartL.setHorizontalAlignment(SwingConstants.LEFT);
		btnGoShoppingCartL.setBackground(Color.BLACK);
		btnGoShoppingCartL.setIcon(new ImageIcon(MainFrame.class.getResource("/Resource/Add Shopping Cart_24px.png")));
		addMouseEffectToButton(btnGoShoppingCartL);
		pnlMenuLeft.add(btnGoShoppingCartL);
		
		btnMyShoppingCartL = new JButton("");
		btnMyShoppingCartL.setFocusPainted(false);
		btnMyShoppingCartL.setBorderPainted(false);
		btnMyShoppingCartL.setHorizontalAlignment(SwingConstants.LEFT);
		btnMyShoppingCartL.setBackground(Color.BLACK);
		btnMyShoppingCartL.setIcon(new ImageIcon(MainFrame.class.getResource("/Resource/Shopping Cart_32px.png")));
		addMouseEffectToButton(btnMyShoppingCartL);
		pnlMenuLeft.add(btnMyShoppingCartL);
		
		btnCheckOutL = new JButton("");
		btnCheckOutL.setFocusPainted(false);
		btnCheckOutL.setBorderPainted(false);
		btnCheckOutL.setHorizontalAlignment(SwingConstants.LEFT);
		btnCheckOutL.setBackground(Color.BLACK);
		btnCheckOutL.setIcon(new ImageIcon(MainFrame.class.getResource("/Resource/Checkout_24px.png")));
		addMouseEffectToButton(btnCheckOutL);
		pnlMenuLeft.add(btnCheckOutL);
		
		btnUser = new JButton("");
		btnUser.setBorderPainted(false);
		btnUser.setFocusPainted(false);
		btnUser.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnUser.setForeground(Color.WHITE);
		btnUser.setHorizontalAlignment(SwingConstants.LEFT);
		btnUser.setPreferredSize(new Dimension(33, 32));
		btnUser.setBackground(Color.BLACK);
		btnUser.setIcon(new ImageIcon(MainFrame.class.getResource("/Resource/Circled User Male_24px.png")));
		addMouseEffectToButton(btnUser);
		pnlLeft.add(btnUser, BorderLayout.SOUTH);
		
		btnMenu = new JButton("");
		pnlLeft.add(btnMenu, BorderLayout.NORTH);
		btnMenu.setBorderPainted(false);
		btnMenu.setFocusPainted(false);
		btnMenu.setBackground(new Color(0, 0, 0));
		btnMenu.setIcon(new ImageIcon(MainFrame.class.getResource("/Resource/menuIcon32.png")));
		addMouseEffectToButton(btnMenu);
		
		btnMenu.addActionListener(this);
		
		pnlMenuR = new JPanel();
		pnlMenuR.setBorder(null);
		pnlMenuR.setBackground(new Color(0, 0, 0));
		pnlMenuR.setLayout(new GridLayout(10, 1, 0, 0));
		pnlMenuR.setVisible(false);
		
		btnAddNewbooks = new JButton("Add New (Books, CDs or Magazines)");
		btnAddNewbooks.setFont(new Font("Verdana", Font.BOLD, 12));
		btnAddNewbooks.setForeground(new Color(255, 255, 255));
		btnAddNewbooks.setBackground(new Color(0, 0, 0));
		btnAddNewbooks.setHorizontalAlignment(SwingConstants.LEFT);
		btnAddNewbooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		pnlMenuR.add(btnAddNewbooks);
		
//		JButton btnListOfItems = new JButton("List Of Items");
//		btnListOfItems.setFont(new Font("Verdana", Font.BOLD, 12));
//		btnListOfItems.setForeground(new Color(255, 255, 255));
//		btnListOfItems.setBackground(new Color(0, 0, 0));
//		btnListOfItems.setHorizontalAlignment(SwingConstants.LEFT);
//		pnlMenuR.add(btnListOfItems);
//		
//		JButton btnListOfItem = new JButton("List Of Item by Categories");
//		btnListOfItem.setHorizontalAlignment(SwingConstants.LEFT);
//		btnListOfItem.setFont(new Font("Verdana", Font.BOLD, 12));
//		btnListOfItem.setForeground(Color.WHITE);
//		btnListOfItem.setBackground(Color.BLACK);
//		pnlMenuR.add(btnListOfItem);
//		
//		JButton btnSearchItem = new JButton("Search Item");
//		btnSearchItem.setOpaque(false);
//		btnSearchItem.setHorizontalAlignment(SwingConstants.LEFT);
//		btnSearchItem.setFont(new Font("Verdana", Font.BOLD, 12));
//		btnSearchItem.setForeground(Color.WHITE);
//		btnSearchItem.setBackground(Color.BLACK);
//		pnlMenuR.add(btnSearchItem);
//		
//		JButton btnGoShoppingCart = new JButton("Go Shopping Cart");
//		btnGoShoppingCart.setOpaque(false);
//		btnGoShoppingCart.setHorizontalAlignment(SwingConstants.LEFT);
//		btnGoShoppingCart.setFont(new Font("Verdana", Font.BOLD, 12));
//		btnGoShoppingCart.setForeground(Color.WHITE);
//		btnGoShoppingCart.setBackground(Color.BLACK);
//		pnlMenuR.add(btnGoShoppingCart);
//		
//		JButton btnMyShoppingCart = new JButton("My Shopping Cart");
//		btnMyShoppingCart.setHorizontalAlignment(SwingConstants.LEFT);
//		btnMyShoppingCart.setOpaque(false);
//		btnMyShoppingCart.setFont(new Font("Verdana", Font.BOLD, 12));
//		btnMyShoppingCart.setForeground(Color.WHITE);
//		btnMyShoppingCart.setBackground(Color.BLACK);
//		pnlMenuR.add(btnMyShoppingCart);
//		
//		JButton btnCheckOut = new JButton("Check Out");
//		btnCheckOut.setHorizontalAlignment(SwingConstants.LEFT);
//		btnCheckOut.setOpaque(false);
//		btnCheckOut.setFont(new Font("Verdana", Font.BOLD, 12));
//		btnCheckOut.setForeground(Color.WHITE);
//		btnCheckOut.setBackground(Color.BLACK);
//		pnlMenuR.add(btnCheckOut);
		
		pnlRight = new JPanel();
		pnlRight.setBackground(new Color(255,255,255));
		panel.add(pnlRight, BorderLayout.CENTER);
		pnlRight.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlRightTop = new JPanel();
		pnlRightTop.setBackground(new Color(240, 240, 240));
		pnlRight.add(pnlRightTop, BorderLayout.NORTH);
		pnlRightTop.setLayout(new BorderLayout(0, 0));
		
		lblOnlineBookStore = new JLabel("Online Book Store Management System");
		lblOnlineBookStore.setPreferredSize(new Dimension(250, 45));
		lblOnlineBookStore.setFont(new Font("Verdana", Font.BOLD, 14));
		lblOnlineBookStore.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnlineBookStore.setForeground(new Color(0, 0, 0));
		pnlRightTop.add(lblOnlineBookStore, BorderLayout.CENTER);
		
		pnlContainer = new JPanel();
		pnlContainer.setBackground(new Color(250, 250, 252));
		pnlRight.add(pnlContainer, BorderLayout.CENTER);
		pnlContainer.setLayout(new BorderLayout(0, 0));
		
		pnlEast = new JPanel();
		pnlRight.add(pnlEast, BorderLayout.EAST);
		pnlEast.setBackground(Color.WHITE);
		
		btnAddNewL.addActionListener(this);
		btnListItemL.addActionListener(this);
		btnGoShoppingCartL.addActionListener(this);
		btnMyShoppingCartL.addActionListener(this);
		btnCheckOutL.addActionListener(this);
		btnSearchItemL.addActionListener(this);
	}

	boolean isClicked = false;
	private JPanel pnlContainer;
	private JLabel lblOnlineBookStore;
	private JPanel pnlMenus;
	private JButton btnUser;
	private JPanel pnlEast;
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnMenu) {
			if(!isClicked) {

				isClicked = true;

				btnMenu.setText("  Online Book Store Mgt System");
				btnMenu.setForeground(Color.WHITE);
				btnMenu.setIcon(new ImageIcon(MainFrame.class.getResource("/Resource/back32.png")));
				btnAddNewL.setText("   Add New Book(s)                       ");
				btnAddNewL.setForeground(Color.WHITE);
				btnListItemL.setText("     List Of Items");
				btnListItemL.setForeground(Color.WHITE);
				btnSearchItemL.setText("     Search Items");
				btnSearchItemL.setForeground(Color.WHITE);
				btnGoShoppingCartL.setText("     Go Shopping Cart");
				btnGoShoppingCartL.setForeground(Color.WHITE);
				btnMyShoppingCartL.setText("   My Shopping Cart");
				btnMyShoppingCartL.setForeground(Color.WHITE);
				btnCheckOutL.setText("     History     ");
				btnCheckOutL.setForeground(Color.WHITE);
				btnUser.setText("    Chum Thea");
			
				
			}
			else {
				pnlMenuR.setVisible(false);
				isClicked = false;
				btnMenu.setText("");
				btnMenu.setIcon(new ImageIcon(MainFrame.class.getResource("/Resource/menuIcon32.png")));
				btnAddNewL.setText("");
				btnListItemL.setText("");
				btnSearchItemL.setText("");
				btnGoShoppingCartL.setText("");
				btnMyShoppingCartL.setText("");
				btnCheckOutL.setText("");
				btnUser.setText("");
				//pnlMenuLeft.setVisible(true);
			}
		}
		
		if(e.getSource() == btnAddNewL) {
			addComponentToContainerPanel(new AddNewPanel());
		}
		if(e.getSource() == btnListItemL) {
			addComponentToContainerPanel(new ListProductsPanel());
		}
		if(e.getSource() == btnGoShoppingCartL) {
			addComponentToContainerPanel(new GoShoppingCartPanel(orderList));
		}
		if(e.getSource() == btnMyShoppingCartL) {
			addComponentToContainerPanel(new MyShoppingCartPanel(orderList));
		}
		if(e.getSource() == btnCheckOutL) {
			addComponentToContainerPanel(new HistoryPanel());
		}
		if(e.getSource() == btnSearchItemL) {
			addComponentToContainerPanel(new HistoryPanel());
		}
		
	}
	
	public void addComponentToContainerPanel(JPanel pnl) {
		if(pnlContainer.getComponentCount() > 0) {
			pnlContainer.removeAll();
		}
		pnlContainer.add(pnl);
		pnlContainer.repaint();
		pnlContainer.revalidate();
	}
	
	public void addMouseEffectToButton(final JButton btn) {
		final Color color = new Color(192,57,43);
		final Color color1 = new Color( 0, 0, 0);
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn.setBackground(color);
				btn.setForeground(new Color(255, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btn.setBackground(color1);
				btn.setForeground(new Color(255, 255, 255));
			}		
		});
	}
}
