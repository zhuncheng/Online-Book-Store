package entities;

import javax.persistence.*;

@Entity
@Table(name = "orderDetail")
public class OrderDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "product_id")
	private int product_id;
	
	@Column(name = "qty")
	private double qty;
	
	@Column(name = "discount")
	private double discount;
	
	@Column(name = "subtotal")
	private double subtotal;
	
	@Column(name = "price")
	private double price;
	
	public OrderDetail() {}
	
	
	public OrderDetail(int product_id, double qty, double discount, double price) {
		this.product_id = product_id;
		this.qty = qty;
		this.discount = discount;
		this.price = price;
	}


	public OrderDetail(int product_id, double qty, double discount, double subtotal, double price) {
		this.product_id = product_id;
		this.qty = qty;
		this.discount = discount;
		this.subtotal = subtotal;
		this.price = price;
	}

	public int getId() {
		return id;
	}


	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getSubTotal() {
		double total = qty * price;
		double dis = discount/100;
		return total - dis * total; 
	}

	public Object[] getOrderDetailModel() {
		return new Object[] {id, product_id, qty, discount, price, subtotal};
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", product_id=" + product_id + ", qty=" + qty + ", discount=" + discount
				+ ", subtotal=" + subtotal + ", price=" + price + "]";
	}
	
}
