/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Customer model class file
 * @version 1.0
 * @author  Dat Dinhquoc
 */
package datdq.sms.models;

/**
 * Customer model class
 * @author johnlowvale
 */
public class Customer {

    //properties
    public String ccode; //customer code
    public String cus_name; //customer name
    public String phone; //customer phone
    
    /**
     * Constructor
     */
    public Customer() {
        ccode    = "";
        cus_name = "";
        phone    = "";
    }
}

//end of file