/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Order comparator class file
 * @version 1.0
 * @author  Dat Dinhquoc
 */
package datdq.sms.tools;

//java core classes
import java.util.*;

//in-project classes
import datdq.sms.models.*;

/**
 * Order comparator
 * @author johnlowvale
 */
public class OrderComparator implements Comparator<Order> {

    //properties
    public String compareField; //must be 'code' or 'pcode'
  
    /**
     * Constructor
     * @param compareField
     */
    public OrderComparator(String compareField) {
        this.compareField = compareField;
    }
    
    /**
     * Compare
     */
    public int compare(Order o1,Order o2) {
        if (compareField.equals("ccode"))
            return o1.ccode.compareTo(o2.ccode);
        
        if (compareField.equals("pcode"))
            return o1.pcode.compareTo(o2.pcode);
        
        return 0; //no meaning 0
    }
}

//end of file