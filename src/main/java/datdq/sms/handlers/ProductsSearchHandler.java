/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Product search handler class file
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
import datdq.sms.models.Product;
import datdq.sms.models.request.ProductsSearchRequest;
import datdq.sms.models.response.ProductsSearchResponse;
import datdq.sms.tools.ProductMatcher;

/**
 * Product search handler class
 * @author johnlowvale
 */
@SuppressWarnings("restriction")
public class ProductsSearchHandler implements HttpHandler {

    /**
     * Http request handle (all methods)
     */
    public void handle(HttpExchange http) {
        
        //get request data
        Gson   gson        = new Gson();
        String requestBody = Http.getRequestBody(http);
        ProductsSearchRequest requestData = gson.fromJson(requestBody,ProductsSearchRequest.class);
        
        //search for products with pcode including pcode in request data
        List<Product> products = Algos.sequentialSearch(App.products,requestData,new ProductMatcher());
        
        //make response
        ProductsSearchResponse responseData = new ProductsSearchResponse();
        responseData.products = products;
        
        //send to browser
        Http.sendJson(http, gson.toJson(responseData));
        Utils.log("Response sent.");
    }
}

//end of file