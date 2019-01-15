package com.ckcc.ass.OnlineBookStore1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import entities.Order;
import entities.OrderDetail;

public class OrderTest {

	private List<OrderDetail> orderList;
	private Order order;
	private Double total = 0.0;
	
	@Before
	public void init() {
		order = new Order();
		orderList = new ArrayList<OrderDetail>(); 
	}
	
	@Test
	public void getOrderTotalTest() {
		OrderDetail order1 = new OrderDetail(1, 10, 10, 10);
		OrderDetail order2 = new OrderDetail(1, 10, 10, 10);
		OrderDetail order3 = new OrderDetail(1, 10, 10, 10);
		
		orderList.add(order1);
		orderList.add(order2);
		orderList.add(order3);
		
		for(OrderDetail orderDetail : orderList) {
			total += orderDetail.getSubTotal();
		}
		
		order.setOrderList(orderList);
		order.setTotal(total);
		assertEquals(270.0, order.getTotal(), 0.0);
	}

}
