/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Utils class file
 * @version 1.0
 * @author  Dat Dinhquoc
 */
package datdq.sms.miscs;

//java core classes
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

//library classes
import org.apache.commons.io.IOUtils;

/**
 * Utility class
 * @author johnlowvale
 */
public class Utils {
  
    //properties
    public static Utils singleton = null;
  
    /**
     * Log to stdout
     * @param str String to print
     */
    public static void log(String str) {
        System.out.println(str);
    }
    
    /**
     * Log to stdout without newline
     * @param str The string to print
     */
    public static void pr(String str) {
        System.out.print(str);
    }
    
    /**
     * Write a newline character to stdout
     */
    public static void nl() {
        System.out.print("\n");
    }
    
    /**
     * Make error JSON string
     * @param error The error message
     * @return      A string which presents the error
     */
    public static String errorJson(String error) {
        return "{\"error\": \""+error+"\"}";
    }
    
    /**
     * Get a random real number in range [0..1)
     * @return A random number
     */
    public static double random() {
        Random generator = new Random();
        return generator.nextDouble();
    }
    
    /**
     * Read the whole input stream to string
     * @param inStream Input stream to be read
     * @return         String obtained from the input stream
     */
    public static String inputStreamToUtf8Str(InputStream inStream) {
        try {
            String text = IOUtils.toString(inStream,StandardCharsets.UTF_8.name());
            return text;
        }
        catch (Exception exception) {
            return null;
        }
    }
    
    /**
     * Get resource in .jar file as string
     * @param  pathInJar Path in .jar file with leading slash (root in jar)
     * @return File content as string
     */
    public static String getResourceUtf8(String pathInJar) {
        if (singleton==null)
            singleton = new Utils();
      
        String result = "";
  
        try {
            result = IOUtils.toString(
                singleton.getClass().getResourceAsStream(pathInJar),
                StandardCharsets.UTF_8.name()
            );
        } 
        catch (Exception exception) {
            log(exception.toString());
        }
  
        return result;
    }
    
    /**
     * Check if a file path is of certain file types
     * @param path  The path to file
     * @param types The file types (file extension without dot in lowercase)
     * @return      True if the file is of type in 'types'
     */
    public static boolean fileIsOfTypes(String path,String[] types) {
        String[] tokens = path.split("\\.");
        
        if (tokens.length==0)
            return false;
        
        String lastToken = tokens[tokens.length-1];
        
        for (int index=0; index<types.length; index++) {
            if (lastToken.toLowerCase().equals(types[index]))
                return true;
        }
        
        return false;
    }
    
    /**
     * Check if a file path is of text file
     * @param path The path to file
     * @return     True if the file is text file
     */
    public static boolean isTextFile(String path) {
        return fileIsOfTypes(path,new String[]{"txt"});
    }
    
    /**
     * Check if a file path is of html file
     * @param path The path to file
     * @return     True if the file is html file
     */
    public static boolean isHtmlFile(String path) {
        return fileIsOfTypes(path,new String[]{"html"});
    }
    
    /**
     * Check if a file path is of css file
     * @param path The path to file
     * @return     True if the file is css file
     */
    public static boolean isCssFile(String path) {
        return fileIsOfTypes(path,new String[]{"css"});
    }
    
    /**
     * Check if a file path is of js file
     * @param path The path to file
     * @return     True if the file is js file
     */
    public static boolean isJsFile(String path) {
        return fileIsOfTypes(path,new String[]{"js"});
    }
    
    /**
     * Check if a file path is of json file
     * @param path The path to file
     * @return     True if the file is json file
     */
    public static boolean isJsonFile(String path) {
        return fileIsOfTypes(path,new String[]{"json"});
    }
    
    /**
     * Check if a file path is of image file
     * @param path The path to file
     * @return     True if the file is image file
     */
    public static boolean isImageFile(String path) {
        return fileIsOfTypes(path,new String[]{"png","jpg","jpeg","gif"});
    }
}

//end of file