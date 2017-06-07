/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Customer search handler class file
 * @version 1.0
 * @author  Dat Dinhquoc
 */
package datdq.sms.handlers;

//java core classes
import com.sun.net.httpserver.*;

import java.util.List;

//library classes
import com.google.gson.Gson;

//in-project classes
import datdq.sms.App;
import datdq.sms.miscs.Algos;
import datdq.sms.miscs.Http;
import datdq.sms.miscs.Utils;
import datdq.sms.models.Customer;
import datdq.sms.models.request.CustomersSearchRequest;
import datdq.sms.models.response.CustomersSearchResponse;
import datdq.sms.tools.CustomerMatcher;

/**
 * Customer search handler class
 * @author johnlowvale
 */
@SuppressWarnings("restriction")
public class CustomersSearchHandler implements HttpHandler {

    /**
     * Http request handle (all methods)
     */
    public void handle(HttpExchange http) {
        
        //get request data
        Gson                   gson        = new Gson();
        String                 requestBody = Http.getRequestBody(http);
        CustomersSearchRequest requestData = gson.fromJson(requestBody,CustomersSearchRequest.class);
        
        //search for customers with ccode containing ccode in request data
        List<Customer> customers = Algos.sequentialSearch(App.customers,requestData,new CustomerMatcher());
        
        //make response
        CustomersSearchResponse responseData = new CustomersSearchResponse();
        responseData.customers = customers;
        
        //send to browser
        Http.sendJson(http, gson.toJson(responseData));
        Utils.log("Response sent.");
    }
}

//end of file