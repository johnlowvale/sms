/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Product matcher class file
 * @version 1.0
 * @author  Dat Dinhquoc
 */
package datdq.sms.tools;

//java core classes
import java.util.Comparator;

//in-project classes
import datdq.sms.models.Product;

/**
 * Product matcher class
 * @author johnlowvale
 */
public class ProductMatcher implements Comparator<Product> {

    /**
     * Match 2 products
     * @return 0 if p1.pcode contains p2.pcode
     */
    public int compare(Product p1, Product p2) {
        if (p1.pcode.toLowerCase().contains(p2.pcode.toLowerCase()))
            return 0;
        
        return 1;
    }
}

//end of file