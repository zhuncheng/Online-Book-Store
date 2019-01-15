package com.ckcc.ass.OnlineBookStore1;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;

import entities.OrderDetail;

public class OrderDetailTest {
	
	@Test
	public void getSubTotaltest() {
		OrderDetail orderDetail = new OrderDetail(1, 10, 10, 10);
		assertEquals(90.0, orderDetail.getSubTotal(), 0.0);
	}

}
