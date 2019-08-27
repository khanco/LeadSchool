package com.example.test.helperui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.test.R;

public class AddSmileyFragment extends Fragment implements SmileyAdapter.ISmileySelectedCallback {

    View view;
    private RecyclerView recyclerView;
    private LinearLayout llSmiley;
    SmileyAdapter adapter;
    SmileyAdapter.ISmileySelectedCallback callback;

    int[] smileyIds = {
            R.drawable.ic_happy_1,
            R.drawable.ic_happy_2,
            R.drawable.ic_happy_4,
            R.drawable.ic_bored,
            R.drawable.ic_angry,
            R.drawable.ic_ill,
            R.drawable.ic_kissing,
            R.drawable.ic_crying,
            R.drawable.ic_mad
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_smileys,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initViews();
        initData();
    }

    private void initData() {
        adapter = new SmileyAdapter(smileyIds);
        adapter.setInterface(this);
        recyclerView.setAdapter(adapter);
    }

    private void initViews() {
        recyclerView = view.findViewById(R.id.rvSmileys);
        llSmiley = view.findViewById(R.id.llSmiley);

        llSmiley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public void setInterface(SmileyAdapter.ISmileySelectedCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onSmileySelectedCallback(int smileyId) {
        callback.onSmileySelectedCallback(smileyId);
        dismiss();
    }

    public void dismiss() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}
