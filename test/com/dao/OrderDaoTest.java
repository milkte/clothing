/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dao;

import com.model.Item;
import com.model.Order;
import com.model.Status;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author shruti
 */
public class OrderDaoTest {
    OrderDao orderDao=new OrderDao();
    public OrderDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findbyID method, of class OrderDao.
     */
    @Test
    public void testFindbyID() {
        System.out.println("findbyID");
        String id = "";
        OrderDao instance = new OrderDao();
        Item expResult = null;
        Item result = instance.findbyID(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addInitialOrder method, of class OrderDao.
     */
    @Test
    public void testAddInitialOrder() {
//        System.out.println("addInitialOrder");
//        String address = "";
//        String itemid = "";
//        String color = "";
//        String size = "";
//        String user = "";
//        String payment = "";
//        String quantity = "";
//        OrderDao instance = new OrderDao();
//        boolean expResult = false;
//        boolean result = instance.addInitialOrder(address, itemid, color, size, user, payment, quantity);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrderBySeller method, of class OrderDao.
     */
    @Test
    public void testGetOrderBySeller_String() {
        System.out.println("getOrderBySeller");
        String sellerid = "1";
        OrderDao instance = new OrderDao();
        List<Order> expResult = null;
        List<Order> result = instance.getOrderBySeller(sellerid);
        System.out.println("result: " + result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatusById method, of class OrderDao.
     */
    @Test
    public void testGetStatusById() {
        System.out.println("getStatusById");
        int status = 1;
        OrderDao instance = new OrderDao();
        Status expResult = null;
        Status result = instance.getStatusById(status);
        System.out.println("result: " + result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
//
//    /**
//     * Test of getOrderBySeller method, of class OrderDao.
//     */
//    @Test
//    public void testGetOrderBySeller_int() {
//        System.out.println("getOrderBySeller");
//        int sellerId = 1;
//        OrderDao instance = new OrderDao();
//        List<Order> expResult = null;
//        List<Order> result = instance.getOrderBySeller(sellerId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
