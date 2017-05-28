/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Customer search response data model class file
 * @version 1.0
 * @author  Dat Dinhquoc
 */
package datdq.sms.models.response;

//java core classes
import java.util.*;

//in-project classes
import datdq.sms.models.*;

/**
 * Customer search response data model class
 * @author johnlowvale
 */
public class CustomersSearchResponse {
    
    //properties
    public List<Customer> customers;
    
    /**
     * Constructor
     */
    public CustomersSearchResponse() {
        customers = new ArrayList<Customer>();
    }
}

//end of file