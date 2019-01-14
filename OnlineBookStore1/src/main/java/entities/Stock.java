package entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "qty")
	private int qty;
	
	//Mapping Stock with Book 1->1
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "book_id")
	private Book book;//mappedBy

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	
	public Stock() {	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", qty=" + qty + ", book=" + book + "]";
	}
	
	public Object[] getStockModel() {
		return new Object[] {this.getBook().getId(), this.getBook().getTitle(), this.getBook().getPublisher(), this.getBook().getPublishedYear(),
				this.getBook().getISBN(), this.getBook().getPrice(), this.getBook().getAuthor(), this.getBook().getEdition(), this.getBook().getValume(),
				qty};
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
