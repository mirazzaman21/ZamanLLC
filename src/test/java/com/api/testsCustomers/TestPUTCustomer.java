package com.api.testsCustomers;

import static org.testng.Assert.assertNotNull;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.helpers.ServiceHelper;

public class TestPUTCustomer {
	
	private ServiceHelper customerServiceHelper;

    @BeforeClass
    public void init() {
        //this.customerServiceHelper();
        customerServiceHelper = new ServiceHelper();

    }
    @Test
    public void testUpdateMultipleFields() {
    	int id =customerServiceHelper.updateCustomerWithMultipleFields(102).jsonPath().get("id");
    	 System.out.println("Updated ID is : " + id);
         assertNotNull(id, "Bingo... Data is Updated!!");
    	
    }

}
