package com.example.test.screens.chats.mvvm.viewmodels;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.example.test.R;
import com.example.test.screens.chats.mvvm.models.ChatMessageModel;
import com.example.test.utils.UtilMethods;

public class ChatListViewModel {

    public ObservableList<ChatMessageModel> chatObservableList = new ObservableArrayList<>();

    public ChatMessageModel sendChat(String message) {
        ChatMessageModel chatMessageModel = new ChatMessageModel();
        chatMessageModel.setMessageFromMe(true);
        chatMessageModel.setText(message);
        chatMessageModel.setDateTime(UtilMethods.getInstance().getDateTime(System.currentTimeMillis()));

        chatObservableList.add(chatMessageModel);
        chatObservableList.add(getBotReplyMessage(message));

        return chatMessageModel;
    }

    private ChatMessageModel getBotReplyMessage(String message) {
        ChatMessageModel chatMessageModel = new ChatMessageModel();
        chatMessageModel.setSmileyResourceId(R.drawable.ic_happy_1);
        chatMessageModel.setMessengerName("Chat Bot");
        chatMessageModel.setMessageFromMe(false);
        chatMessageModel.setText(message);
        chatMessageModel.setDateTime(UtilMethods.getInstance().getDateTime(System.currentTimeMillis()));

        return chatMessageModel;
    }

    public void putSomeDummyData() {

        String[] msg = {"Hi", "How are you?", "I am fine, how about you?", "Nothing just completing the assigment", "Ok, alright", "Lets finish this once for all", "Hey Aman I am sorry for delay.", "Its Ok, Shahbaz!", "Thanks Aman"};

        for (String message: msg) {

            ChatMessageModel chatMessageModel = new ChatMessageModel();
            chatMessageModel.setMessageFromMe(true);
            chatMessageModel.setText(message);
            chatMessageModel.setDateTime(UtilMethods.getInstance().getDateTime(System.currentTimeMillis()));

            chatObservableList.add(chatMessageModel);
            chatObservableList.add(getBotReplyMessage(message));
        }
    }
}
