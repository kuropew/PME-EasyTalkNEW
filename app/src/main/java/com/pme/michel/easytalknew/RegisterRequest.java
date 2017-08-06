package com.pme.michel.easytalknew;

/**
 * Created by Michel on 03.07.2017.
 */
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
//work like the login request with a other url set
public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL ="https://app-1501871252.000webhostapp.com/Register.php"; // Device must be in the same network like the server for this example it is in a localnetwork
    private Map<String, String> params;

    public RegisterRequest(String username, String password, Response.Listener<String> listener){

        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
