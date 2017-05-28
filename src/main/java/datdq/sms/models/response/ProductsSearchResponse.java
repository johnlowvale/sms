/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Product search response data model class file
 * @version 1.0
 * @author  Dat Dinhquoc
 */
package datdq.sms.models.response;

//java core classes
import java.util.*;

//in-project classes
import datdq.sms.models.*;

/**
 * Product search response data model class
 * @author johnlowvale
 */
public class ProductsSearchResponse {
    
    //properties
    public List<Product> products;
    
    /**
     * Constructor
     */
    public ProductsSearchResponse() {
        products = new ArrayList<Product>();
    }
}

//end of file