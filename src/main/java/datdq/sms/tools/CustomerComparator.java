/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Customer comparator class file
 * @version 1.0
 * @author  Dat Dinhquoc
 */
package datdq.sms.tools;

//java core classes
import java.util.*;

//in-project classes
import datdq.sms.models.*;

/**
 * Customer comparator
 * @author johnlowvale
 */
public class CustomerComparator implements Comparator<Customer> {

    /**
     * Compare
     */
    public int compare(Customer c1,Customer c2) {
        return c1.ccode.compareTo(c2.ccode);
    }
}

//end of file