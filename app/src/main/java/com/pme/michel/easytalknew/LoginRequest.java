package com.pme.michel.easytalknew;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michel on 06.07.2017.
 */
//the class is used to send a StringRequest with needed params to the Web-Server
public class LoginRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL ="https://app-1501871252.000webhostapp.com/Login.php"; //Set the url for the Request which is call a url Request later
    private Map<String, String> params;                                                                  //create a map for the following params

    //constructor for the class
    public LoginRequest(String username, String password, Response.Listener<String> listener){

        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();                                                                         //setup the params in a HashMap
        params.put("username", username);                                                                 //add the params
        params.put("password", password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
