/*
 * Simple Sale Management System
 * DSA Assignment 1
 * @file    Http utilities class file
 * @version 1.0
 * @author  datdqgt00570@fpt.edu.vn
 */
package datdq.sms.miscs;

//core java classes
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;

/**
 * Http utilities class
 * @author johnlowvale
 */
@SuppressWarnings("restriction")
public class Http {
  
    /**
     * Allow Cross-Origin Resource Sharing (CORS)
     * @param http The HttpExchange object
     */
    public static void allowCors(HttpExchange http) {      
        String origin = getHeader(http,"Origin");
      
        if (origin==null)
            origin = "*";
        
        setHeader(http,"Access-Control-Allow-Origin",origin);
        setHeader(http,"Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
        setHeader(http,"Access-Control-Allow-Credentials","true");
        
        if (isOptionsMethod(http))
            sendHtml(http,"");
    }
    
    /**
     * Get request method in lowercase
     * @param http The HttpExchange object
     * @return     The HTTP request method in lowercase
     */
    public static String getMethod(HttpExchange http) {
        return http.getRequestMethod().toLowerCase();
    }
    
    /**
     * Check if HTTP request method is OPTIONS
     * @param http The HttpExchange object
     * @return     True if the method is OPTIONS
     */
    public static boolean isOptionsMethod(HttpExchange http) {
        if (getMethod(http).equals("options"))
            return true;
        else
            return false;
    }
    
    /**
     * Check if HTTP request method is GET
     * @param http The HttpExchange object
     * @return     True if the method is GET
     */
    public static boolean isGetMethod(HttpExchange http) {
        if (getMethod(http).equals("get"))
            return true;
        else
            return false;
    }
    
    /**
     * Check if HTTP request method is POST
     * @param http The HttpExchange object
     * @return     True if the method is POST
     */
    public static boolean isPostMethod(HttpExchange http) {
        if (getMethod(http).equals("post"))
            return true;
        else
            return false;
    }
  
    /**
     * Log request to stdout
     * @param http The HttpExchange object
     */
    public static void logRequest(HttpExchange http) {
        Utils.log("\n"+http.getRemoteAddress().toString());
        Utils.log(http.getRequestMethod()+" "+http.getRequestURI());
    }
    
    /**
     * Get request body as string
     * @param http The HttpExchange object
     * @return     The string obtained from the request
     */
    public static String getRequestBody(HttpExchange http) {
        return Utils.inputStreamToUtf8Str(http.getRequestBody());
    }

    /**
     * Get a header from request headers
     * @param http       The HttpExchange object
     * @param headerName The header name in request
     * @return           The first value of of the header
     */
    public static String getHeader(HttpExchange http,String headerName) {
        return http.getRequestHeaders().getFirst(headerName);
    }
    
    /**
     * Set header for HTTP response
     * @param http  The HttpExchange object
     * @param key   The key of header
     * @param value The value of header
     */
    public static void setHeader(HttpExchange http,String key,String value) {
        Headers headers = http.getResponseHeaders();
        headers.add(key,value);
    }
    
    /**
     * Send HTTP response with a specific status
     * @param http         The HttpExchange object
     * @param status       The HTTP response status
     * @param responseText Response text to send
     */
    public static void send(HttpExchange http,int status,String responseText) {
        try {
            http.sendResponseHeaders(status,responseText.length());
            OutputStream bodyWriter = http.getResponseBody();
            bodyWriter.write(responseText.getBytes());
            bodyWriter.close();
        }
        catch (Exception exception) {
            Utils.log(exception.toString());
        }
    }
    
    /**
     * Send HTTP response with status 200
     * @param http         The HttpExchange object
     * @param responseText Response text to send
     */
    public static void send(HttpExchange http,String responseText) {
        send(http,200,responseText);
    }
    
    /**
     * Send HTTP response with status 200/plain text
     * @param http         The HttpExchange object
     * @param responseText Response text to send
     */
    public static void sendText(HttpExchange http,String responseText) {
        setHeader(http,"Content-Type","text/plain; charset=utf-8");
        send(http,200,responseText);
    }
    
    /**
     * Send HTTP response with status 200/html
     * @param http         The HttpExchange object
     * @param responseText Response text to send
     */
    public static void sendHtml(HttpExchange http,String responseText) {
        setHeader(http,"Content-Type","text/html; charset=utf-8");
        send(http,200,responseText);
    }
    
    /**
     * Send HTTP response with status 200/css
     * @param http         The HttpExchange object
     * @param responseText Response text to send
     */
    public static void sendCss(HttpExchange http,String responseText) {
        setHeader(http,"Content-Type","text/css; charset=utf-8");
        send(http,200,responseText);
    }
    
    /**
     * Send HTTP response with status 200/js
     * @param http         The HttpExchange object
     * @param responseText Response text to send
     */
    public static void sendJs(HttpExchange http,String responseText) {
        setHeader(http,"Content-Type","text/javascript; charset=utf-8");
        send(http,200,responseText);
    }
    
    /**
     * Send HTTP response with status 200/json
     * @param http         The HttpExchange object
     * @param responseText Response text to send
     */
    public static void sendJson(HttpExchange http,String responseText) {
        setHeader(http,"Content-Type","application/json; charset=utf-8");
        send(http,200,responseText);
    }
    
    /**
     * Send HTTP response with status 200/image
     * @param http         The HttpExchange object
     * @param responseText Response text to send
     */
    public static void sendImage(HttpExchange http,String responseText) {
        setHeader(http,"Content-Type","image");
        send(http,200,responseText);
    }
    
    /**
     * Redirect browser to url
     * @param http The HttpExchange object
     * @param url  The target URL to redirect to
     */
    public static void redirect(HttpExchange http,String url) {
        setHeader(http,"Location",url);
        send(http,303,""); //303, no caching in browser
    }
}

//end of file