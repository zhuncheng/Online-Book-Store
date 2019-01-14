package entities;

import java.awt.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orderBook")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "discount")
	private double discount;
	
	@Column(name = "total")
	private double total;
	
	@Column(name = "remark")
	private String remark;

	//Mapping Order with Customer 1->1 
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private Customer customer; //mappedBy
	
	//Mapping Order with OrderDetail 1 -> M
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "order_id")
	private java.util.List<OrderDetail> orderList = new ArrayList<OrderDetail>();
	
	public Order(double discount, double total, String remark) {
		
		this.discount = discount;
		this.total = total;
		this.remark = remark;
	}
	
	public Order() {}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public java.util.List<OrderDetail> getOrderList() {
		return orderList;
	}

	public void setOrderList(java.util.List<OrderDetail> orderList) {
		this.orderList = orderList;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", discount=" + discount + ", total=" + total + ", remark=" + remark + ", customer="
				+ customer.toString() + ", orderList=" + orderList + "]";
	}
	
	
}
