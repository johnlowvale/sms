/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Main class file
 * @version 1.0
 * @author  datdqgt00570@fpt.edu.vn
 */
package datdq.sms;

//java core classes
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.util.*;

//in-project classes
import datdq.sms.miscs.*;
import datdq.sms.models.*;
import datdq.sms.handlers.*;

/**
 * Main class
 */
@SuppressWarnings("restriction")
public class App {
  
    //constants
    public static final int PORT = 80;
    
    //variables
    public static List<Product>  products;
    public static List<Customer> customers;
    public static List<Order>    orders;
  
    /**
     * Application instance entry point
     */
    public void run() {
        products  = new ArrayList<Product>();
        customers = new ArrayList<Customer>();
        orders    = new ArrayList<Order>();
      
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(PORT),0);
            
            //url handlers
            server.createContext("/",         new RootHandler());
            server.createContext("/resources",new ResourcesHandler());
            
            //main handlers
            server.createContext("/products/upload", new ProductsUploadHandler());
            server.createContext("/product/add",     new ProductAddHandler());
            server.createContext("/products/list",   new ProductsListHandler());
            
            //start server
            server.start();
            Utils.log("Simple Sale Management System started on port "+PORT);
            Utils.log("Point browser to http://localhost to view the web application!");
            Utils.log("\nWARNING:");
            Utils.log("PRODUCTS, CUSTOMERS, ORDERS DATA ARE STORED IN VARIABLES,");
            Utils.log("ALL DATA ARE RESET EVERY TIME THIS WEB APPLICATION RUNS!");
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
  
    /**
     * Main programme entry point
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        new App().run();
    }
}

//end of file