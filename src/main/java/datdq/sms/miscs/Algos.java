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
    public static <T> T sequentialFind(List<T> list,T item,Comparator<T> comparator) {
        for (T temp: list)
            if (comparator.compare(temp,item)==0)
                return temp;
        
        return null;
    }
    
    /**
     * Search for a list of matching item
     * @param list    The full list of item
     * @param item    Item to match
     * @param matcher Matcher
     * @return        A list of items
     */
    public static <T> List<T> sequentialSearch(List<T> list,T item,Comparator<T> matcher) {
        List<T> items = new ArrayList<T>();
        
        for (T temp: list)
            if (matcher.compare(temp, item)==0)
                items.add(temp);
        
        return items;
    }
    
    /**
     * Remove single item from list
     * @param list       List of items
     * @param item       Item to remove
     * @param comparator Item comparator
     * @return           True if the item is found
     */
    public static <T> boolean sequentialRemove(List<T> list,T item,Comparator<T> comparator) {
        for (T temp: list)
            if (comparator.compare(temp, item)==0) {
                list.remove(temp);
                return true;
            }
        
        return false;
    }
}

//end of file