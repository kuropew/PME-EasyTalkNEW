package com.pme.michel.easytalknew;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //set variables for the Objects in the Activity
        final EditText eTUsername = (EditText) findViewById(R.id.eTUsername);
        final EditText eTPassword = (EditText) findViewById(R.id.eTPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView tVRegisterHere = (TextView) findViewById(R.id.tVRegisterHere);

        //create a Listener for the Register-Link
        tVRegisterHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);                                           //change the Activity to the RegisterActivity
            }

        });
        //create a Listener for the Login-Button
        bLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //get the data from the EditText
                final String username = eTUsername.getText().toString();
                final String password = eTPassword.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){
                        try {
                            JSONObject jsonResponse = new JSONObject(response);                             //create a new JSONObject with the response
                            boolean success = jsonResponse.getBoolean("success");                           //get the boolean variable from the JSONObject from the PHP-Script
                            //if the login Attempt is a success -> Log into the ChatActivity
                            if(success){
                                String username = jsonResponse.getString("username");                       //get the username from the response
                                int user_id = jsonResponse.getInt("user_id");                               //get the user_id from the response
                                Intent chatIntent = new Intent(LoginActivity.this, ChatActivity.class);
                                chatIntent.putExtra("username", username);                                  //commit the username to the ChatActivity
                                chatIntent.putExtra("user_id", user_id);                                    //commit the user_id to the ChatActivity
                                LoginActivity.this.startActivity(chatIntent);
                            }else {                                                                         //if the login Attempt failed get a negativ message and retry
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {                                                         //catch script exceptions
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(username, password,responseListener);          //create a new LoginRequest, used for the Php-Server
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);                                                                    //add this Request to the Volley-Queue
            }
        });
    }
}
