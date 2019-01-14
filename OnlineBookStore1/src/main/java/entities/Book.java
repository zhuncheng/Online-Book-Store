package entities;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title, publisher, publishedYear, ISBN;
	private double price;
	private String author;
	private int edition, volume;
	
	//1.1 btw Book and Stock
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "book")
	private Stock stock;
	
	public Book(String title, String publisher, String publishedYear, String ISBN, double price, String author, int edition, int volume) {
		this.title = title;
		this.publisher = publisher;
		this.publishedYear = publishedYear;
		this.ISBN = ISBN;
		this.price = price;
		this.author = author;
		this.edition = edition;
		this.volume = volume;
	}
	
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public String getPublishedYear() {
		return publishedYear;
	}


	public void setPublishedDate(String publishedYear) {
		this.publishedYear = publishedYear;
	}


	public String getISBN() {
		return ISBN;
	}


	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public int getEdition() {
		return edition;
	}


	public void setEdition(int edition) {
		this.edition = edition;
	}


	public int getValume() {
		return volume;
	}


	public void setValume(int volume) {
		this.volume = volume;
	}
	
	public Book() {	}
	
	
	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Object[] getBookModel() {
		return new Object[] { id, title, publisher, publishedYear, ISBN, price, author, edition, volume}; 
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", publisher=" + publisher + ", publishedYear=" + publishedYear
				+ ", ISBN=" + ISBN + ", price=" + price + ", author=" + author + ", edition=" + edition + ", volume="
				+ volume + "]";
	}
	
}
