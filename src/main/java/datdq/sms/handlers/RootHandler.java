/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Root URL handler class file
 * @version 1.0
 * @author  datdqgt00570@fpt.edu.vn
 */
package datdq.sms.handlers;

//core java classes
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

//in-project classes
import datdq.sms.miscs.Http;
import datdq.sms.miscs.Utils;

/**
 * Root URL handler class
 * @author johnlowvale
 */
@SuppressWarnings("restriction")
public class RootHandler implements HttpHandler {

    /**
     * Handle request (all HTTP methods)
     */
    public void handle(HttpExchange http) {
        Http.sendHtml(http,Utils.getResourceUtf8("/index.html"));
        Utils.log("Response sent.");
    } 
}

//end of file