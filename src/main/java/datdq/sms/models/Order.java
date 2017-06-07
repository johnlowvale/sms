/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Order model class file
 * @version 1.0
 * @author  Dat Dinhquoc
 */
package datdq.sms.models;

/**
 * Order model class
 * @author johnlowvale
 */
public class Order {

    //properties
    public String pcode; //product code
    public String ccode; //customer code
    public int    quantity; //number of product units purchased
    
    /**
     * Constructor
     */
    public Order() {
        pcode    = "";
        ccode    = "";
        quantity = 0;
    }
}

//end of file