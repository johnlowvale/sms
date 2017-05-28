/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Customer matcher class file
 * @version 1.0
 * @author  Dat Dinhquoc
 */
package datdq.sms.tools;

//java core classes
import java.util.Comparator;

//in-project classes
import datdq.sms.models.Customer;

/**
 * Customer matcher class
 * @author johnlowvale
 */
public class CustomerMatcher implements Comparator<Customer> {

    /**
     * Match 2 customers
     * @return 0 if c1.ccode contains c2.ccode
     */
    public int compare(Customer c1, Customer c2) {
        if (c1.ccode.toLowerCase().contains(c2.ccode.toLowerCase()))
            return 0;
        
        return 1;
    }
}

//end of file