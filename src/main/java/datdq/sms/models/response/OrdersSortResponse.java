/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Orders sort response model class file
 * @version 1.0
 * @author  Dat Dinhquoc
 */
package datdq.sms.models.response;

//java core classes
import java.util.*;

//in-project classes
import datdq.sms.models.*;

/**
 * Orders sort response model class
 * @author johnlowvale
 */
public class OrdersSortResponse {

    //properties
    public List<Order> orders;
    public int         customerCount;
    public int         productCount;
    public int         quantitySum;
    
    /**
     * Constructor
     */
    public OrdersSortResponse() {
        orders        = new ArrayList<Order>();
        customerCount = 0;
        productCount  = 0;
        quantitySum   = 0;
    }
}

//end of file
