package com.api.testsCustomers;

import com.api.helpers.ServiceHelper;
import com.api.model.Customers;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;
import static org.testng.Assert.assertNotNull;


public class TestGETCustomers {

    private ServiceHelper customerServiceHelper;

    @BeforeClass
    public void init() {
        //this.customerServiceHelper();
        customerServiceHelper = new ServiceHelper();

    }

    @Test
    public void testAllCustomers() {
        List<Customers> customersList = customerServiceHelper.getAllCustomers();

        assertNotNull(customersList, " Person List is not Empty");
        assertFalse(customersList.isEmpty(), " Person List is not true");

    }
    @Test
    public void testParticulerCustomer() {
        String customer = customerServiceHelper.getSingleCustomer(100);

        assertNotNull(customer==null, "Person is not Empty");
       // assertEquals(customer, customerServiceHelper.getSingleCustomer(120), "Bingo!!!");
       // System.out.println(customer.toString());
    }

}
// getSingleCustomer