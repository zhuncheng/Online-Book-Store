package com.ckcc.ass.OnlineBookStore1;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.ImageIcon;

public class AddNewPanel extends JPanel implements ActionListener{

	private JPanel panel, pnlRight, pnlLeft, pnlHeader;
	private JButton btnBooks;
	private JButton btnCds;
	private JButton btnMagazines;
	private JLabel lblAddNew;
	/**
	 * Create the panel.
	 */
	public AddNewPanel() {
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		pnlHeader = new JPanel();
		pnlHeader.setBackground(Color.BLACK);
		panel.add(pnlHeader, BorderLayout.NORTH);
		
		lblAddNew = new JLabel("Add New(Books, CDs, Magazines)");
		lblAddNew.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNew.setFont(new Font("Verdana", Font.BOLD, 14));
		lblAddNew.setForeground(Color.WHITE);
		pnlHeader.add(lblAddNew);
		
		pnlLeft = new JPanel();
		pnlLeft.setBackground(new Color(255, 255, 255));
		panel.add(pnlLeft, BorderLayout.WEST);
		pnlLeft.setLayout(new GridLayout(3, 1, 10, 0));
		
		btnBooks = new JButton("Books");
		btnBooks.setIcon(new ImageIcon(AddNewPanel.class.getResource("/Resource/Books_32px.png")));
		btnBooks.setBackground(Color.BLACK);
		btnBooks.setFont(new Font("Verdana", Font.BOLD, 14));
		btnBooks.setForeground(Color.WHITE);
		btnBooks.setHorizontalAlignment(SwingConstants.LEFT);
		pnlLeft.add(btnBooks);
		
		btnCds = new JButton("CDs");
		btnCds.setIcon(new ImageIcon(AddNewPanel.class.getResource("/Resource/CD_32px.png")));
		btnCds.setBackground(Color.BLACK);
		btnCds.setFont(new Font("Verdana", Font.BOLD, 14));
		btnCds.setForeground(Color.WHITE);
		btnCds.setHorizontalAlignment(SwingConstants.LEFT);
		pnlLeft.add(btnCds);
		
		btnMagazines = new JButton("Magazines");
		btnMagazines.setIcon(new ImageIcon(AddNewPanel.class.getResource("/Resource/Book Shelf_24px.png")));
		btnMagazines.setBackground(Color.BLACK);
		btnMagazines.setFont(new Font("Verdana", Font.BOLD, 14));
		btnMagazines.setForeground(Color.WHITE);
		btnMagazines.setHorizontalAlignment(SwingConstants.LEFT);
		pnlLeft.add(btnMagazines);
		
		pnlRight = new JPanel();
		panel.add(pnlRight, BorderLayout.CENTER);
		
		btnBooks.addActionListener(this);
		btnCds.addActionListener(this);
		btnMagazines.addActionListener(this);

	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnBooks) {
			lblAddNew.setText("Add New Book");
		}
		if(e.getSource() == btnCds) {
			lblAddNew.setText("Add New CD");
		}
		if(e.getSource() == btnMagazines) {
			lblAddNew.setText("Add New Magazine");
		}
	}

}
