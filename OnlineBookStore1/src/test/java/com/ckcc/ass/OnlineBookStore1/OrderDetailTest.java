package com.ckcc.ass.OnlineBookStore1;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;

import entities.OrderDetail;

public class OrderDetailTest {
	
	OrderDetail order;
	@Before
	public void init() {
		order = new OrderDetail();
	}
	
	
	@Test
	public void getTotaltest() {
		OrderDetail orderDetail = new OrderDetail(1, 10, 10, 10);
		assertEquals(90.0, orderDetail.getSubTotal(), 0.0);
	}

//	@Test
//	public void test1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException {
//		final Field field1 = order.getClass().getDeclaredField("qty");
//		field1.setAccessible(true);
//		final Field field2 = order.getClass().getDeclaredField("qty");
//		field2.setAccessible(true);
//		final Field field3 = order.getClass().getDeclaredField("qty");
//		field3.setAccessible(true);
//		
//		final Double qty = 10.0;
//		final Double price = 10.0;
//		final Double discount = 10.0;
//		
//		field1.set(order, qty);
//		field2.set(order, price);
//		field3.set(order, discount);
//		
//		final Method method = order.getClass().getDeclaredMethod("total", null);
//		method.setAccessible(true);
//	}
}
