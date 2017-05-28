/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Order matcher class file
 * @version 1.0
 * @author  Dat Dinhquoc
 */
package datdq.sms.tools;

//java core classes
import java.util.Comparator;

//in-project classes
import datdq.sms.models.Order;

/**
 * Order matcher class
 * @author johnlowvale
 */
public class OrderMatcher implements Comparator<Order> {

    /**
     * Match 2 products
     * @return 0 if o1.ocode contains o2.ocode, 1 otherwise
     */
    public int compare(Order o1, Order o2) {
      
        //no order code (ocode), can't match
        return 1;
    }
}

//end of file