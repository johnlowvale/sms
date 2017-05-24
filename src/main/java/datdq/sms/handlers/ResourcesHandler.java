/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Resource handler class file
 * @version 1.0
 * @author  Dat Dinhquoc
 */
package datdq.sms.handlers;

//core java classes
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

//in-project classes
import datdq.sms.miscs.*;

/**
 * Resource handler class
 * @author johnlowvale
 */
@SuppressWarnings("restriction")
public class ResourcesHandler implements HttpHandler {
  
    /**
     * Handle HTTP request
     */
    public void handle(HttpExchange http) {
        Http.logRequest(http);
      
        //get resource file path
        String resourcePath = http.getRequestURI().toString();
        resourcePath = resourcePath.replace("/resources","");
        
        //get content
        String responseText = Utils.getResourceUtf8(resourcePath); 
            
        if (Utils.isTextFile(resourcePath))
            Http.sendText(http, responseText);
        else
        if (Utils.isHtmlFile(resourcePath))
            Http.sendHtml(http, responseText);
        else
        if (Utils.isCssFile(resourcePath))
            Http.sendCss(http, responseText);
        else
        if (Utils.isJsFile(resourcePath))
            Http.sendJs(http, responseText);
        else
        if (Utils.isJsonFile(resourcePath))
            Http.sendJson(http, responseText);
        else
        if (Utils.isImageFile(resourcePath))
            Http.sendImage(http, responseText);
        else
            Http.sendHtml(http, responseText);
    }
}

//end of file