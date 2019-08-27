package com.example.test;

import com.example.test.dagger.DaggerTestComponent;
import com.example.test.dagger.modules.ApiMangerModule;
import com.example.test.screens.starcharacterlist.dagger.StartCharacterListModule;
import com.example.test.screens.starcharacterlist.mvvm.viewmodels.StarCharacterListViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.schedulers.TestScheduler;

@RunWith(MockitoJUnitRunner.class)
public class StarCharacterListActivityApiCallTest {

    @Inject StarCharacterListViewModel viewModel;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        DaggerTestComponent.builder()
                .apiMangerModule(new ApiMangerModule())
                .startCharacterListModule(new StartCharacterListModule())
                .build()
                .inject(this);
    }

    @Test
    public void testApiCall() {

        assert(viewModel.showLoader.get());

        viewModel.fetchData();

        TestScheduler testScheduler = new TestScheduler();
        testScheduler.triggerActions();

        assert(viewModel.showLoader.get());
        if (viewModel.success.get()) {
            assert(Objects.requireNonNull(viewModel.getData().getValue()).size() > 0);
        } else {
            assert(Objects.requireNonNull(viewModel.getData().getValue()).size() == 0);
        }
    }
}
