package com.api.testsCustomers;

import com.api.helpers.ServiceHelper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class TestPOSTCustomers {
    private ServiceHelper customerServiceHelper;

    @BeforeClass
    public void init() {

        customerServiceHelper = new ServiceHelper();

    }

    @Test
    public void testPOSTCreateCustomer() {
        String id = customerServiceHelper.createCustomer().jsonPath().getString("id");
        System.out.println(" New Customer is Created for : " + id);
        assertNotNull(id, "Customer name is not null!!");

    }
}
