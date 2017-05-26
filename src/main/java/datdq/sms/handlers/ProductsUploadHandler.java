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

//in-project classes
import datdq.sms.miscs.Http;
import datdq.sms.miscs.Utils;
import datdq.sms.models.request.ProductsUploadRequest;

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
      
        //get request data
        Gson                  gson        = new Gson();      
        String                requestBody = Http.getRequestBody(http);
        ProductsUploadRequest requestData = gson.fromJson(requestBody,ProductsUploadRequest.class);
        
        Utils.log(""+requestData.products.size());
    }
}

//end of file