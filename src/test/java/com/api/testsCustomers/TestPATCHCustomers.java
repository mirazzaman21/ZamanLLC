package com.api.testsCustomers;


import com.api.helpers.ServiceHelper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class TestPATCHCustomers {
    private ServiceHelper customerServiceHelper;

    @BeforeClass
    public void init() {
        customerServiceHelper = new ServiceHelper();
    }

    @Test
    public void testPatchCustomer() {
        String id = customerServiceHelper.updateCustomerWithSingleField(114).jsonPath().getString("id");
        System.out.println("Updated ID is : " + id);
        assertNotNull(id, "Bingo... Data is Updated!!");

    }
}
