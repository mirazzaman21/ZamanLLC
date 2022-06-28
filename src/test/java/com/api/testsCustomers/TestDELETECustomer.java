package com.api.testsCustomers;

import com.api.helpers.ServiceHelper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestDELETECustomer {
    private ServiceHelper customerServiceHelper;

    @BeforeClass
    public void init() {
        //this.customerServiceHelper();
        customerServiceHelper = new ServiceHelper();

    }

    @Test
    public void testDeleteCustomer() {
        String id = customerServiceHelper.deleteCustomer(113).jsonPath().getString("id");
        System.out.println("Deleted ID is : " + id);

    }
}
