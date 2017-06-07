/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Orders list response data model class file
 * @version 1.0
 * @author  Dat Dinhquoc
 */
package datdq.sms.models.response;

//java core classes
import java.util.*;

//in-project classes
import datdq.sms.models.*;

/**
 * Orders list response data model class
 * @author johnlowvale
 */
public class OrdersListResponse {

    //properties
    public List<Order> orders;
    public int         customerCount;
    public int         productCount;
    public int         quantitySum;
    
    /**
     * Constructor
     */
    public OrdersListResponse() {
        orders        = new ArrayList<Order>();
        customerCount = 0;
        productCount  = 0;
        quantitySum   = 0;
    }
}

//end of file