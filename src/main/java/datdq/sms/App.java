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

//in-project classes
import datdq.sms.miscs.*;
import datdq.sms.handlers.*;

/**
 * Main class
 */
@SuppressWarnings("restriction")
public class App {
  
    //constants
    public static final int PORT = 80;
  
    /**
     * Application instance entry point
     */
    public void run() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(PORT),0);
            
            //url handlers
            server.createContext("/",         new RootHandler());
            server.createContext("/resources",new ResourcesHandler());
            
            //start server
            server.start();
            Utils.log("Simple Sale Management System started on port "+PORT);
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