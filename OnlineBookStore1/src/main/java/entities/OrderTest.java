package entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OrderTest {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Order.class)
				.addAnnotatedClass(Customer.class)
				.addAnnotatedClass(OrderDetail.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			System.out.println("Saving Order to DB.");
			
			Order order = new Order(10.0, 100.0, "remark");
			
			Customer customer = new Customer("aa", "gmail", "06789");
		//	customer.setBillingAddress(new Address("1", "1", "1", "1", "11111"));
		//	customer.setShippingAddress(new Address("2", "2", "2", "2", "222222"));
			
			OrderDetail orderDetail1 = new OrderDetail(1, 10, 10, 100, 1000);
			OrderDetail orderDetail2 = new OrderDetail(1, 10, 10, 100, 1000);
			List<OrderDetail> orderList = new ArrayList<OrderDetail>();
			
			order.setCustomer(customer);
			order.setOrderList(orderList);
			session.save(order);
			session.getTransaction().commit();
			session.close();
			System.out.println("Done!!!!");
		}
		finally {
			factory.close();
		}
	}
}
