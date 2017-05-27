/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Product add handler class file
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
 * Product add handler class
 * @author johnlowvale
 */
@SuppressWarnings("restriction")
public class ProductAddHandler implements HttpHandler {

    /**
     * Handle HTTP request
     */
    public void handle(HttpExchange http) {
        try {
            Http.logRequest(http);
          
            //get request data
            Gson              gson        = new Gson();      
            String            requestBody = Http.getRequestBody(http);
            ProductAddRequest requestData = gson.fromJson(requestBody,ProductAddRequest.class);
            
            //check if product is already existing
            String  message = "";
            Product product = Algos.sequencialFind(App.products,requestData,new ProductComparator());  
            
            if (product!=null) {
                product.quantity += requestData.quantity;
                message = "Product existing, added to warehouse "+requestData.quantity+" items!";
            }
            else {
                App.products.add(requestData);
                message = "New product added";
            }
            
            //make response
            ProductAddResponse responseData = new ProductAddResponse();
            responseData.message      = message;
            responseData.productCount = App.products.size();
            
            Http.sendJson(http,gson.toJson(responseData));
            Utils.log("Response sent.");
        }
        catch (Exception exception) {
            exception.printStackTrace();
            Http.sendJson(http,Utils.errorJson(exception.getMessage()));
        }
    }//handle
}

//end of file