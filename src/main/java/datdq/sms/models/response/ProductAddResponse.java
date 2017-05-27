/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Product add response model class file
 * @version 1.0
 * @author  Dat Dinhquoc
 */
package datdq.sms.models.response;

/**
 * Product add response model class
 * @author johnlowvale
 */
public class ProductAddResponse {

    //properties
    public String message;
    public int    productCount;
    
    /**
     * Constructor
     */
    public ProductAddResponse() {
        message      = "";
        productCount = 0;
    }
}

//end of file