package com.pme.michel.easytalknew;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michel on 15.07.2017.
 */
//work like the login request with a other url set
public class SendRequest extends StringRequest{

    private static final String SEND_REQUEST_URL="https://app-1501871252.000webhostapp.com/Send.php";
    private Map<String, String> params;

    public SendRequest(String username, String message, int user_id, Response.Listener<String> listener){
        super(Method.POST, SEND_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("message", message);
        params.put("user_id", user_id+"");

    }
    public Map<String, String> getParams(){
        return params;
    }

}
