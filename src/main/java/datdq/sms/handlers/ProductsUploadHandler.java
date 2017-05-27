/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Products upload handler class file
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
 * Products upload handler class
 * @author johnlowvale
 */
@SuppressWarnings("restriction")
public class ProductsUploadHandler implements HttpHandler {

    /**
     * Handle HTTP request
     */
    public void handle(HttpExchange http) {
        try {
            Http.logRequest(http);
          
            //get request data
            Gson                  gson        = new Gson();      
            String                requestBody = Http.getRequestBody(http);
            ProductsUploadRequest requestData = gson.fromJson(requestBody,ProductsUploadRequest.class);
            
            //save to variable
            App.products = requestData.products;
            
            //remove all url
            for (Product product: App.products)
                product.pro_image_url = "";
            
            //make response
            ProductsUploadResponse responseData = new ProductsUploadResponse();
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