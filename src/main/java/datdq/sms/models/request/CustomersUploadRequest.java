/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Customer upload request model class file
 * @version 1.0
 * @author  Dat Dinhquoc
 */
package datdq.sms.models.request;

//java core classes
import java.util.*;

//in-project classes
import datdq.sms.models.*;

/**
 * Customer upload request model class
 * @author johnlowvale
 */
public class CustomersUploadRequest {

    //properties
    public List<Customer> customers;
    
    /**
     * Constructor
     */
    public CustomersUploadRequest() {
        customers = new ArrayList<Customer>();
    }
}

//end of file
