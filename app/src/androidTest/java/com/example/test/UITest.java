package com.example.test;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.test.screens.starcharacterlist.mvvm.views.StarCharacterAdapter;
import com.example.test.screens.starcharacterlist.mvvm.views.StarCharacterListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UITest {

    @Rule
    public ActivityTestRule<StarCharacterListActivity>
            activityTestRule = new ActivityTestRule<>(StarCharacterListActivity.class);

    @Test
    public void testUI() {
        if (activityTestRule.getActivity().viewModel.showLoader.get()) {
            Espresso.onView(ViewMatchers.withId(R.id.progressBar))
                    .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(
                            ViewMatchers.Visibility.VISIBLE)));
        } else {
            Espresso.onView(ViewMatchers.withId(R.id.progressBar))
                    .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(
                            ViewMatchers.Visibility.GONE)));
        }
    }

    @Test
    public void recyclerViewClick() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.<StarCharacterAdapter.Item>actionOnItemAtPosition(0, ViewActions.click()));
    }
}
