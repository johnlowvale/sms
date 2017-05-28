/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Products sort http handler class file
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
import datdq.sms.models.response.ProductsSortResponse;
import datdq.sms.tools.ProductComparator;

/**
 * Products sort http handler class
 * @author johnlowvale
 */
@SuppressWarnings("restriction")
public class ProductsSortHandler implements HttpHandler {

    /**
     * Handle http request
     */
    public void handle(HttpExchange http) {
        Http.logRequest(http);
        Gson gson = new Gson();
        
        //sort 
        Algos.quicksort(App.products,0,App.products.size()-1,new ProductComparator());
        
        //make response
        ProductsSortResponse responseData = new ProductsSortResponse();
        responseData.products = App.products;
        
        Http.sendJson(http, gson.toJson(responseData));
        Utils.log("Response sent.");
    }
}

//end of file