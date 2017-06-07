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
    
    /**
     * Find partition divider index, swap entries a long the way
     * @param list       List of type T
     * @param left       Left index
     * @param right      Right index
     * @param comparator Entry comparator
     * @return           Partition divider index
     */
    public static <T> int partition(List<T> list, int left, int right,Comparator<T> comparator) {
      
        //esasy pivot value at mid
        int index      = left;
        int jndex      = right;
        T   pivotValue = list.get((left+right)/2);
    
        //sort, at least 2 entries, nothing to swap with 0 or 1 entry
        while (index<jndex) {
          
            //skip left value < pivotValue
            //don't skip value==pivotValue
            while (comparator.compare(list.get(index),pivotValue)<0)
                index++;
            
            //skip right value > pivotValue
            //don't skip value==pivotValue
            while (comparator.compare(pivotValue,list.get(jndex))<0)
                jndex--;
            
            //index,jndex not crossing yet? swap
            if (index<=jndex) {
                T temp = list.get(index);
                list.set(index,list.get(jndex));
                list.set(jndex,temp);
                
                //value at i,j are in correct order, next
                index++;
                jndex--;
            }
        }//while
    
        return index;
    }

    /**
     * Quicksort (partition-exchange sort) a list
     * @param list       The list to be sorted
     * @param left       Low inbound index (min 0)
     * @param right      High inbound index (at most length-1)
     * @param comparator Entry comparator
     */
    public static <T> void quicksort(List<T> list,int left,int right,Comparator<T> comparator) {
        int index = partition(list,left,right,comparator);
        
        //sort left side
        if (left < index-1)
            quicksort(list,left,index-1,comparator);
        
        //sort right side
        if (index < right)
            quicksort(list,index,right,comparator);
    }//quicksort
}

//end of file