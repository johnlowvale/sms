/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Products upload request model class file
 * @version 1.0
 * @author  Dat Dinhquoc
 */
package datdq.sms.models.request;

//java core classes
import java.util.*;

//in-project classes
import datdq.sms.models.*;

/**
 * Products upload request model class
 * @author johnlowvale
 */
public class ProductsUploadRequest {

    //properties
    public List<Product> products;
    
    /**
     * Constructor
     */
    public ProductsUploadRequest() {
        products = new ArrayList<Product>();
    }
}

//end of file
