/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Product list handler class file
 * @version 1.0
 * @author  Dat Dinhquoc
 */
package datdq.sms.handlers;

//core java classes
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

//library classes
import com.google.gson.Gson;

//in-project classes
import datdq.sms.*;
import datdq.sms.miscs.*;
import datdq.sms.models.response.CustomersListResponse;

/**
 * Customer list handler class
 * @author johnlowvale
 */
@SuppressWarnings("restriction")
public class CustomersListHandler implements HttpHandler {

    /**
     * Handle HTTP request
     */
    public void handle(HttpExchange http) {
        try {
            Http.logRequest(http);
          
            //get request data
            Gson gson = new Gson();
            
            //make response            
            CustomersListResponse responseData = new CustomersListResponse();            
            responseData.customers = App.customers;
            String responseJson = gson.toJson(responseData);
            
            Http.sendJson(http,responseJson);
            Utils.log("Response sent.");
        }
        catch (Exception exception) {
            exception.printStackTrace();
            Http.sendJson(http,Utils.errorJson(exception.getMessage()));
            Utils.log("Response sent.");
        }
    }//handle
}

//end of file