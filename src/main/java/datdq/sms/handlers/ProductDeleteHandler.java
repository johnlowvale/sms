/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Product delete handler class file
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
import datdq.sms.models.request.ProductDeleteRequest;
import datdq.sms.models.response.ProductDeleteResponse;
import datdq.sms.tools.ProductComparator;

/**
 * Product delete handler classs
 * @author johnlowvale
 */
@SuppressWarnings("restriction")
public class ProductDeleteHandler implements HttpHandler {

    /**
     * Handle http requests (all methods)
     */
    public void handle(HttpExchange http) {
        
        //get request data
        Gson                 gson        = new Gson();
        String               requestBody = Http.getRequestBody(http);
        ProductDeleteRequest requestData = gson.fromJson(requestBody,ProductDeleteRequest.class);
        
        //delete product from App.products
        boolean productFound = Algos.sequentialRemove(App.products,requestData,new ProductComparator());
        
        //response
        ProductDeleteResponse responseData = new ProductDeleteResponse();
        
        if (productFound)
            responseData.message = "Product with pcode "+requestData.pcode+" deleted successfully!";
        else
            responseData.message = "Product with pcode "+requestData.pcode+" NOT found!";
        
        //send
        Http.sendJson(http, gson.toJson(responseData));
        Utils.log("Response sent.");
    }    
}

//end of file