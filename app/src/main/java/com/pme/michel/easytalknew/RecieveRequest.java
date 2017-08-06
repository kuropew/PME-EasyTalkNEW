package com.pme.michel.easytalknew;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michel on 25.07.2017.
 */
//work like the login request with a other url set
public class RecieveRequest extends StringRequest{

    private static final String RECIEVE_REQUEST_URL="https://app-1501871252.000webhostapp.com/Recieve.php";
    private Map<String, String> params;

    public RecieveRequest(int user_id, Response.Listener<String> listener){
        super(Method.POST, RECIEVE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("user_id", user_id+"");

    }
    public Map<String, String> getParams(){
        return params;
    }

}
