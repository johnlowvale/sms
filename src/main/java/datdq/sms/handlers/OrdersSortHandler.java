/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Order sort http handler class file
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
import datdq.sms.models.request.OrdersSortRequest;
import datdq.sms.models.response.OrdersSortResponse;
import datdq.sms.tools.OrderComparator;
import datdq.sms.utils.*;

/**
 * Order sort http handler class
 * @author johnlowvale
 */
@SuppressWarnings("restriction")
public class OrdersSortHandler implements HttpHandler {

    /**
     * Handle http request
     */
    public void handle(HttpExchange http) {
        Http.logRequest(http);
        
        //get request data
        Gson              gson        = new Gson();
        String            requestBody = Http.getRequestBody(http);
        OrdersSortRequest requestData = gson.fromJson(requestBody,OrdersSortRequest.class);
        String            sortField   = requestData.sortBy;
        
        //nothing to sort
        if (App.orders.size()==0) {
            Http.sendJson(http,Utils.errorJson("Nothing to sort!"));
            Utils.log("Response sent.");
        }
        
        //sort 
        Algos.quicksort(App.orders,0,App.orders.size()-1,new OrderComparator(sortField));
        
        //make response
        OrdersSortResponse responseData = new OrdersSortResponse();
        responseData.orders        = App.orders;
        responseData.customerCount = OrderUtils.countUniqueCustomers(App.orders);
        responseData.productCount  = OrderUtils.countUniqueProducts(App.orders);
        responseData.quantitySum   = OrderUtils.getQuantitySum(App.orders);
        
        Http.sendJson(http, gson.toJson(responseData));
        Utils.log("Response sent.");
    }
}

//end of file