package com.api.testsEmployees;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import com.api.helpers.ServiceHelper;
import com.api.model.Employees;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.sql.rowset.Predicate;

import static org.testng.Assert.*;
import static org.testng.Assert.assertNotNull;


import java.util.ArrayList;
import java.util.List;

public class TestGETEmployees {
    private ServiceHelper serviceHelper;

    @BeforeClass
    public void init(){
        serviceHelper = new ServiceHelper();
    }
    @Test
    public void testAllEmployees(){
        List<Employees> employeesList=serviceHelper.getAllEmployees();
       assertNotNull(employeesList, "Employee List is not Empty");
      assertFalse(employeesList.isEmpty(), "Employees records are available");

    }
    @Test
    public <em> void testParticularEmployee(){
       String employee= serviceHelper.getSingleEmployee(100);

       assertNotNull(employee==null, "Employee is found!!");
       serviceHelper.validateResonse();
    }

}
