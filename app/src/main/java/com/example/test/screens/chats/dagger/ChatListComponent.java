package com.example.test.screens.chats.dagger;

import com.example.test.screens.chats.mvvm.views.ChatListActivity;

import dagger.Component;

@Component(modules = {ChatListModule.class})
public interface ChatListComponent {
    void inject(ChatListActivity activity);
}
