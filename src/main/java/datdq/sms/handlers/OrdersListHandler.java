/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Order list handler class file
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
import datdq.sms.miscs.*;
import datdq.sms.models.response.OrdersListResponse;
import datdq.sms.utils.*;

/**
 * Order list handler class
 * @author johnlowvale
 */
@SuppressWarnings("restriction")
public class OrdersListHandler implements HttpHandler {

    /**
     * Handle HTTP request
     */
    public void handle(HttpExchange http) {
        try {
            Http.logRequest(http);
          
            //get request data
            Gson gson = new Gson();
            
            //make response            
            OrdersListResponse responseData = new OrdersListResponse();            
            responseData.orders = App.orders;
            responseData.customerCount = OrderUtils.countUniqueCustomers(App.orders);
            responseData.productCount  = OrderUtils.countUniqueProducts(App.orders);
            responseData.quantitySum   = OrderUtils.getQuantitySum(App.orders);
            
            //send
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