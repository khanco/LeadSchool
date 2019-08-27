package com.example.test.screens.chats.mvvm.models;

public class ChatMessageModel {
    private String text;
    private String dateTime;
    private boolean isMessageFromMe;
    private String messengerName;
    private int smileyResourceId;

    public int getSmileyResourceId() {
        return smileyResourceId;
    }

    public void setSmileyResourceId(int smileyResourceId) {
        this.smileyResourceId = smileyResourceId;
    }

    public String getText() {
        return text;
    }

    public String getNameWithDateTime() {
        return String.format("%s, %s", this.getMessengerName(), this.getDateTime());
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isMessageFromMe() {
        return isMessageFromMe;
    }

    public void setMessageFromMe(boolean messageFromMe) {
        isMessageFromMe = messageFromMe;
    }

    public String getMessengerName() {
        return messengerName;
    }

    public void setMessengerName(String messengerName) {
        this.messengerName = messengerName;
    }

    public String getInitial() {
        return String.valueOf(getMessengerName().charAt(0));
    }
}
