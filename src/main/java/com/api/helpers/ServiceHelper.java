package com.api.helpers;

import com.api.constants.EndPoints;
import com.api.model.Customers;
import com.api.model.Employees;
import com.api.utils.ConfigManager;
import com.fasterxml.jackson.core.type.TypeReference;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import javax.sql.rowset.Predicate;
import javax.swing.plaf.PanelUI;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;


public class ServiceHelper {
    // We need to read the config variables
    //Rest Assured about the URL, Port
    //Make a Get Request on this url and send the data to TestGETCustomer

    private static final String BASE_URI = ConfigManager.getInstance().getString("base_url");
    private static final String PORT = ConfigManager.getInstance().getString("port");
    private ConfigManager instance;
    public static Response response;

    public ServiceHelper() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = Integer.parseInt(PORT);
        RestAssured.useRelaxedHTTPSValidation();
    }

    // ********* CUSTOMERS **********//
    public List<Customers> getAllCustomers() {
        Response response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .get(EndPoints.GET_ALL_CUSTOMER)
                .then()
                .log().all()
                .extract().response();
        // .andReturn();
        Type type = new TypeReference<List<Customers>>() {
        }.getType();
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code is OK!!");
        List<Customers> customersList = response.as(type);
        return customersList;

    }

    public String getSingleCustomer(Integer id) {
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .pathParam("id", id)
                .get(EndPoints.GET_SINGLE_CUSTOMER)
                .then()
                .log().all()
                .extract().response();
        // .andReturn();
        System.out.println("Available Status Code is : " + response.getStatusCode());
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code is OK!!");


        return null;
    }

    public Response createCustomer() {

        Customers customer = new Customers();//Customers();
        // customer.setId(108);
        customer.setName("LZ Zaman");
        customer.setCity("Philly City");
        customer.setState("PA");
        customer.setZip(20918);



        // Make a Post Call
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .body(customer)
                .post(EndPoints.CREATE_CUSTOMER)
                .then()
                .log().all()
                .extract().response();
        // .andReturn();
        assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED, "New Record created!!");
        return response;
    }

    public Response updateCustomerWithSingleField(Integer id) {
        Customers customer = new Customers();
        customer.setName("Tokul");
        customer.setCity("Bronx");


        Response response = RestAssured.given().contentType(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .body(customer)
                .patch(EndPoints.UPDATE_SINGLE_FIELD_CUSTOMER)
                .then()
                .log().all()
                .extract().response();
        // .andReturn();

        assertTrue((response.getStatusCode() == HttpStatus.SC_OK));
        return response;
    }

    public Response deleteCustomer(Integer id) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .log().everything()
                .when()
                .delete(EndPoints.DELETE_CUSTOMER)
                .andReturn();
        assertTrue((response.getStatusCode() == HttpStatus.SC_OK));
        return response;
    }

    public Response updateCustomerWithMultipleFields(Integer id) {
        Customers customer = new Customers();

        customer.setName("Sadia Khanom");
        customer.setCity("Queens");
        customer.setState("NY");
        customer.setZip(11234);

        Response response = RestAssured.given().contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(customer)
                .put(EndPoints.UPDATE_MULTIPLE_FIELD_CUSTOMER)
                .then()
                .log().all()
                .extract().response();
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 200, " Opps!!! Status code is not match....");
        return response;

    }

    // ********* EMPLOYEES **********//


    // GET all Employees
    public List<Employees> getAllEmployees() {
        System.out.println("\n********** All Employee Details ********************");
        System.out.println("Utilized URL is : " + ServiceHelper.BASE_URI + EndPoints.GET_ALL_Employee);
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .get(EndPoints.GET_ALL_Employee)
                .then()
                .log().all()
                .extract().response();
        Type type = new TypeReference<List<Employees>>() {
        }
                .getType();
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status Code is Found!!");
        List<Employees> employeesList = response.as(type);
        return employeesList;
    }

    // GET Particular Employee Details
    public String getSingleEmployee(Integer id) {
        System.out.println("\n**********Employee Details Based on id is : '" + id + "' ********************");
        System.out.println("Utilized URL is : " + ServiceHelper.BASE_URI + EndPoints.GET_SINGLE_Employee + id);
        response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .pathParam("id", id)
                .get(EndPoints.GET_SINGLE_Employee)
                .then()
                .log().all()
                .extract().response();
        //  System.out.println("\n**********Employee Details Based on id is : '"+id+"' ********************");
        System.out.println("Available Status Code is : " + response.getStatusCode());
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code is OK!!");
        return null;
    }

    public void validateResonse() {
    try {
        int responseCode = response.getStatusCode();
        if (responseCode == 200) {
            System.out.println("Status code is 200 : 'Success OK'");
        } else if (responseCode == 400) {
            System.out.println("Response code 400 : 'Bad Request'");
        } else if (responseCode == 401) {
            System.out.println("Response code 401 : 'Unauthorized'");
        } else if (responseCode == 404) {
            System.out.println("Response code 404 : 'Not Found'");
        } else if (responseCode == 500) {
            System.out.println("Response code 500 : 'Internal Server Error'");
        } else {
            System.out.println("Invalid API call");

        }
    } catch (AssertionError e){
        System.out.println(e);
    }finally {
        System.out.println("Response Code : "+response.getStatusCode()+" is found!!!");
    }
    }

}
    
    
    
    
    
    
    
    
    


