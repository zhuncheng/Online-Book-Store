package helper;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entities.Book;

public class DBHelper {
	
	public static List<Book> getBookFromDB(){
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
	
	public static void fillBookToTable(JTable table, DefaultTableModel model) {
		List<Book> books = DBHelper.getBookFromDB();
		if(books.size() > 0) {
			if(model.getRowCount() > 0) {
				model.getDataVector().removeAllElements();
			}
			
			for(Book book : books) {
				model.addRow(book.getBookModel());
				model.addRow(new Object[] {book.getId(), book.getTitle(), book.getPublisher(), book.getPublishedYear(), book.getISBN()
						, book.getPrice(), book.getAuthor(), book.getEdition(), book.getValume()});
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "No Book in stock", "Alert", JOptionPane.WARNING_MESSAGE);
		}
	}
	
}
