/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Customer add response model class file
 * @version 1.0
 * @author  Dat Dinhquoc
 */
package datdq.sms.models.response;

/**
 * Customer add response model class
 * @author johnlowvale
 */
public class CustomerAddResponse {

    //properties
    public String message;
    public int    customerCount;
    
    /**
     * Constructor
     */
    public CustomerAddResponse() {
        message       = "";
        customerCount = 0;
    }
}

//end of file