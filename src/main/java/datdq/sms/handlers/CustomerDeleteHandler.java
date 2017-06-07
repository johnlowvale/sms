/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Customer delete handler class file
 * @version 1.0
 * @author  Dat Dinhquoc
 */
package datdq.sms.handlers;

//java core classes
import com.sun.net.httpserver.*;

//library classes
import com.google.gson.Gson;

//in-project classes
import datdq.sms.App;
import datdq.sms.miscs.Algos;
import datdq.sms.miscs.Http;
import datdq.sms.miscs.Utils;
import datdq.sms.models.request.CustomerDeleteRequest;
import datdq.sms.models.response.CustomerDeleteResponse;
import datdq.sms.tools.CustomerComparator;

/**
 * Customer delete handler classs
 * @author johnlowvale
 */
@SuppressWarnings("restriction")
public class CustomerDeleteHandler implements HttpHandler {

    /**
     * Handle http requests (all methods)
     */
    public void handle(HttpExchange http) {
        
        //get request data
        Gson                  gson        = new Gson();
        String                requestBody = Http.getRequestBody(http);
        CustomerDeleteRequest requestData = gson.fromJson(requestBody,CustomerDeleteRequest.class);
        
        //delete customer from App.customers
        boolean customerFound = Algos.sequentialRemove(App.customers,requestData,new CustomerComparator());
        
        //response
        CustomerDeleteResponse responseData = new CustomerDeleteResponse();
        
        if (customerFound)
            responseData.message = "Customer with ccode "+requestData.ccode+" deleted successfully!";
        else
            responseData.message = "Customer with ccode "+requestData.ccode+" NOT found!";
        
        //send
        Http.sendJson(http, gson.toJson(responseData));
        Utils.log("Response sent.");
    }    
}

//end of file