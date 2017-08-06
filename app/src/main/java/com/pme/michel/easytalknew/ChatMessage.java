package com.pme.michel.easytalknew;

/**
 * Created by Michel on 20.07.2017.
 */
import java.util.Date;
//create a object which is getting the message, the username from the message, the message Timestamp and a boolean variable to decide which side will be used to display on the app
public class ChatMessage {

    private String messageText;
    private String messageUser;
    private long messageTime;
    public boolean left;

    //constructor for the class
    public ChatMessage(boolean left, String messageText, String messageUser){ //create the constructor
        this.messageText = messageText; //get the data from the constructor into the local variables
        this.messageUser = messageUser;
        messageTime = new Date().getTime(); //get local Time of the device
    }

    public ChatMessage(){
    }

    //getter and setter
    public String getMessageText(){
            return messageText;
    }

    public void setMessageText(String messageText){
        this.messageText = messageText;
    }

    public String getMessageUser(){
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public void setMessageUser(){
        this.messageUser = messageUser;
    }

    public long getMessageTime(){
        return messageTime;
    }

    public void setMessageTime(){
        this.messageTime = messageTime;
    }

}
