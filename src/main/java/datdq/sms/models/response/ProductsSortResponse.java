/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Products sort response model class file
 * @version 1.0
 * @author  Dat Dinhquoc
 */
package datdq.sms.models.response;

//java core classes
import java.util.*;

//in-project classes
import datdq.sms.models.*;

/**
 * Products sort response model class
 * @author johnlowvale
 */
public class ProductsSortResponse {

    //properties
    public List<Product> products;
    
    /**
     * Constructor
     */
    public ProductsSortResponse() {
        products = new ArrayList<Product>();
    }
}

//end of file
