/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Algorithms class file
 * @version 1.0
 * @author  datdqgt00570@fpt.edu.vn
 */
package datdq.sms.miscs;

//java core classes
import java.util.*;

/**
 * Algorithms class file
 * @author johnlowvale
 */
public class Algos {
  
    /**
     * Find an item in list
     * @param list       List of items
     * @param item       Item to check
     * @param comparator Comparator of item type
     * @return           Item, or null if not found
     */
    public static <T> T sequencialFind(List<T> list,T item,Comparator<T> comparator) {
        for (T temp: list)
            if (comparator.compare(temp,item)==0)
                return temp;
        
        return null;
    }
}

//end of file