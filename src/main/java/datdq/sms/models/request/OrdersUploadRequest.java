/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Orders upload request model class file
 * @version 1.0
 * @author  Dat Dinhquoc
 */
package datdq.sms.models.request;

//java core classes
import java.util.*;

//in-project classes
import datdq.sms.models.*;

/**
 * Orders upload request model class
 * @author johnlowvale
 */
public class OrdersUploadRequest {

    //properties
    public List<Order> orders;
    
    /**
     * Constructor
     */
    public OrdersUploadRequest() {
        orders = new ArrayList<Order>();
    }
}

//end of file
