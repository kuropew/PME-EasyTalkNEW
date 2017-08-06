package com.pme.michel.easytalknew;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    private static final int DELAY = 10000;
    private ChatArrayAdapter chatArrayAdapter;
    private ChatArrayAdapter chatArrayAdapter2;
    private Handler handler = new Handler();
    private boolean alreadyReceived = false;
    private int receivedMessage = 0;                                                                    //Temporary variable to save the ID from the last received message
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        final EditText eTChatMessage = (EditText) findViewById(R.id.eTChatMessage);
        final Button bSend = (Button) findViewById(R.id.bSend);
        ListView listViewSend = (ListView) findViewById(R.id.lVSentMessage);
        ListView listViewRecieve = (ListView) findViewById(R.id.lVRecievedMessage);
        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        final int user_id = intent.getIntExtra("user_id", -1);
        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.right);
        listViewSend.setAdapter(chatArrayAdapter);
        chatArrayAdapter2 = new ChatArrayAdapter(getApplicationContext(),R.layout.left);
        listViewRecieve.setAdapter(chatArrayAdapter2);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //Listener for the Send-Button
        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String message = eTChatMessage.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                String showedMessage = jsonResponse.getString("message");
                                boolean side = true;                                                    //setting which Listview Side is used to display the message in the App
                                chatArrayAdapter.add(new ChatMessage(side,showedMessage, username));    //adding the ChatArrayAdapter Object a new message
                                eTChatMessage.setText("");                                              //after sending the message clear the EditText

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(ChatActivity.this);
                                builder.setMessage("Sending Message Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                                                }
                    }
                };
                SendRequest sendRequest = new SendRequest(username, message, user_id, responseListener);
                RequestQueue queue = Volley.newRequestQueue(ChatActivity.this);
                queue.add(sendRequest);
            }
        });
        //Send every 10 Seconds a ReceiveRequest to the Web-Server for the last new message
        handler.postDelayed(new Runnable(){
            public void run(){

                Response.Listener<String> responseListener2 = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                int message_id = jsonResponse.getInt("message_id");                     //get the message id from the latest message from the JSONObject
                                alreadyReceived = false;                                                //set the variable on false -> this variable is used to check if the message already displayed in the app
                                if(message_id != receivedMessage)                                       //if the last received message and the current received message different set the receivedMessage on the current message id and let the boolean variable on false
                                {
                                    receivedMessage = message_id;
                                }else{                                                                  //if the last received message and the current received message the same set the boolean variable on true
                                    alreadyReceived = true;
                                }
                                if(!alreadyReceived) {                                                  //if the boolean variable false display the message on the app
                                    String showedMessage = jsonResponse.getString("message");
                                    String senderUsername = jsonResponse.getString("username");
                                    boolean side = false;
                                    //Date messageTime = jsonResponse.("timestamp");
                                    chatArrayAdapter2.add(new ChatMessage(side, showedMessage, senderUsername));
                                }

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ChatActivity.this);
                                builder.setMessage("Receiving Message Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RecieveRequest recieveRequest = new RecieveRequest(user_id, responseListener2);
                RequestQueue queue = Volley.newRequestQueue(ChatActivity.this);
                queue.add(recieveRequest);
                handler.postDelayed(this, DELAY);
            }
        }, DELAY);
    }
}
