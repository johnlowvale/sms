/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Product comparator class file
 * @version 1.0
 * @author  Dat Dinhquoc
 */
package datdq.sms.tools;

//java core classes
import java.util.*;

//in-project classes
import datdq.sms.models.*;

/**
 * Product comparator
 * @author johnlowvale
 */
public class ProductComparator implements Comparator<Product> {

    /**
     * Compare
     */
    public int compare(Product p1,Product p2) {
        return p1.pcode.compareTo(p2.pcode);
    }
}

//end of file