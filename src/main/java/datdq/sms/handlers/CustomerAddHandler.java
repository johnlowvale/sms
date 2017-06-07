/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Customer add handler class file
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
import datdq.sms.tools.*;
import datdq.sms.miscs.*;
import datdq.sms.models.*;
import datdq.sms.models.request.*;
import datdq.sms.models.response.*;

/**
 * Customer add handler class
 * @author johnlowvale
 */
@SuppressWarnings("restriction")
public class CustomerAddHandler implements HttpHandler {

    /**
     * Handle HTTP request
     */
    public void handle(HttpExchange http) {
        try {
            Http.logRequest(http);
          
            //get request data
            Gson               gson        = new Gson();      
            String             requestBody = Http.getRequestBody(http);
            CustomerAddRequest requestData = gson.fromJson(requestBody,CustomerAddRequest.class);
            
            //check if product is already existing
            String   message  = "";
            Customer customer = Algos.sequentialFind(App.customers,requestData,new CustomerComparator());  
            
            if (customer!=null) {
                message = "Customer exists, can't add!";
            }
            else {
                App.customers.add(requestData);
                message = "New customer added";
            }
            
            //make response
            CustomerAddResponse responseData = new CustomerAddResponse();
            responseData.message       = message;
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