/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Customers upload handler class file
 * @version 1.0
 * @author  Dat Dinhquoc
 */
package datdq.sms.handlers;

//core java classes
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

//library classes
import com.google.gson.Gson;

import datdq.sms.App;
//in-project classes
import datdq.sms.miscs.Http;
import datdq.sms.miscs.Utils;
import datdq.sms.models.Product;
import datdq.sms.models.request.*;
import datdq.sms.models.response.*;

/**
 * Customers upload handler class
 * @author johnlowvale
 */
@SuppressWarnings("restriction")
public class CustomersUploadHandler implements HttpHandler {

    /**
     * Handle HTTP request
     */
    public void handle(HttpExchange http) {
        try {
            Http.logRequest(http);
          
            //get request data
            Gson                   gson        = new Gson();      
            String                 requestBody = Http.getRequestBody(http);
            CustomersUploadRequest requestData = gson.fromJson(requestBody,CustomersUploadRequest.class);
            
            //save to variable
            App.customers = requestData.customers;
            
            //make response
            CustomersUploadResponse responseData = new CustomersUploadResponse();
            responseData.customerCount = App.customers.size();
            
            Http.sendJson(http,gson.toJson(responseData));
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