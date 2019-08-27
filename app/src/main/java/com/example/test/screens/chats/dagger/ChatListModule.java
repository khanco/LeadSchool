package com.example.test.screens.chats.dagger;

import com.example.test.screens.chats.mvvm.viewmodels.ChatListViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class ChatListModule {
    @Provides
    public ChatListViewModel provideViewModel() {
        return new ChatListViewModel();
    }
}
