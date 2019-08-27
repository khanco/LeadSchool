package com.example.test.dagger;

import com.example.test.StarCharacterListActivityApiCallTest;
import com.example.test.dagger.modules.ApiMangerModule;
import com.example.test.screens.starcharacterlist.dagger.StartCharacterListModule;

import dagger.Component;

@Component(modules = { ApiMangerModule.class, StartCharacterListModule.class})
public interface TestComponent {
    void inject(StarCharacterListActivityApiCallTest activity);
}
