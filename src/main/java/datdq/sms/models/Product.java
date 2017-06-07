/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Product model class file
 * @version 1.0
 * @author  datdqgt00570@fpt.edu.vn
 */
package datdq.sms.models;

/**
 * Product model class
 * @author johnlowvale
 */
public class Product {
  
    //properties
    public String pcode; //product code
    public String pro_name; //product name
    public int    quantity; //available quantity
    public int    sale; //sold quantity
    public double price; //product price
    public String pro_image_url; //product image url
    
    /**
     * Constructor
     */
    public Product() {
        pcode         = "";
        pro_name      = "";
        quantity      = 0;
        sale          = 0;
        price         = 0;
        pro_image_url = "";
    }
}

//end of file