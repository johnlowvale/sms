/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Order utils class file
 * @version 1.0
 * @author  Dat Dinhquoc
 */
package datdq.sms.utils;

//java core classes
import java.util.*;

//in-project classes
import datdq.sms.models.Order;

/**
 * Order utils class
 * @author johnlowvale
 */
public class OrderUtils {

    /**
     * Count unique customer codes in all orders
     * @param orders
     * @return
     */
    public static int countUniqueCustomers(List<Order> orders) {
        Set<String> codes = new HashSet<String>();
        
        for (Order order: orders)
            codes.add(order.ccode);
        
        return codes.size();
    }
    
    /**
     * Count unique product codes in all orders
     * @param orders
     * @return
     */
    public static int countUniqueProducts(List<Order> orders) {
        Set<String> codes = new HashSet<String>();
        
        for (Order order: orders)
            codes.add(order.pcode);
        
        return codes.size();
    }
    
    /**
     * Get sum of all ordered quantities
     * @param orders
     * @return
     */
    public static int getQuantitySum(List<Order> orders) {
        int sum = 0;
        
        for (Order order: orders)
            sum += order.quantity;
        
        return sum;
    }
}

//end of file