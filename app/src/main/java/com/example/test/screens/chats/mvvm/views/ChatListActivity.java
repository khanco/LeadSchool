package com.example.test.screens.chats.mvvm.views;

import android.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.test.R;
import com.example.test.databinding.ActivityChatListBinding;
import com.example.test.helperui.AddSmileyFragment;
import com.example.test.helperui.SmileyAdapter;
import com.example.test.screens.chats.dagger.ChatListModule;
import com.example.test.screens.chats.dagger.DaggerChatListComponent;
import com.example.test.screens.chats.mvvm.models.ChatMessageModel;
import com.example.test.screens.chats.mvvm.viewmodels.ChatListViewModel;
import com.example.test.utils.UtilMethods;

import javax.inject.Inject;

public class ChatListActivity extends AppCompatActivity implements ChatListAdapter.ISmileyOnClickCallback, SmileyAdapter.ISmileySelectedCallback {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ImageButton btnSend;
    private EditText edtMessage;
    ChatListAdapter adapter;
    public @Inject
    ChatListViewModel viewModel;
    int currentItemPosition;
    AddSmileyFragment addSmileyFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDagger();
        initBinding();
        initUI();
        initObservation();
        putSomeDummyData();
    }

    private void putSomeDummyData() {
        viewModel.putSomeDummyData();
    }

    private void initObservation() {

        viewModel.chatObservableList.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<ChatMessageModel>>() {
            @Override
            public void onChanged(ObservableList<ChatMessageModel> sender) {

            }

            @Override
            public void onItemRangeChanged(ObservableList<ChatMessageModel> sender, int positionStart, int itemCount) {

            }

            @Override
            public void onItemRangeInserted(ObservableList<ChatMessageModel> sender, int positionStart, int itemCount) {
                recyclerView.getLayoutManager().scrollToPosition(positionStart);
            }

            @Override
            public void onItemRangeMoved(ObservableList<ChatMessageModel> sender, int fromPosition, int toPosition, int itemCount) {

            }

            @Override
            public void onItemRangeRemoved(ObservableList<ChatMessageModel> sender, int positionStart, int itemCount) {

            }
        });

        adapter.setInterface(this);
    }

    private void initUI() {
        recyclerView = findViewById(R.id.rvChats);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setEnabled(false);
        edtMessage = findViewById(R.id.edtMessage);

        UtilMethods.getInstance().setStatusBarGradient(this);
        setUpToolbar();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            viewModel.sendChat(edtMessage.getText().toString());
            edtMessage.setText("");
            }
        });

        edtMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btnSend.setEnabled(charSequence.length() > 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setUpToolbar() {
        toolbar = findViewById(R.id.chatHeader);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initBinding() {
        ActivityChatListBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_chat_list);
        binding.setViewModel(viewModel);
        adapter = new ChatListAdapter(viewModel.chatObservableList);
        binding.setMyAdapter(adapter);
    }

    private void initDagger() {
        DaggerChatListComponent.builder()
                .chatListModule(new ChatListModule())
                .build()
                .inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_list_menu, menu);
        return true;
    }

    @Override
    public void onSmileyClickCallback(int position) {
        currentItemPosition = position;
        addSmileyFragment = new AddSmileyFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        addSmileyFragment.setInterface(this);
        ft.add(R.id.frameSmiley, addSmileyFragment).commit();
    }

    @Override
    public void onSmileySelectedCallback(int smileyId) {
        ChatMessageModel chatMessageModel = viewModel.chatObservableList.get(currentItemPosition);
        if (chatMessageModel.getSmileyResourceId() != smileyId) {
            chatMessageModel.setSmileyResourceId(smileyId);
            adapter.notifyItemChanged(currentItemPosition);
        }
    }

    @Override
    public void onBackPressed() {
        if (addSmileyFragment != null && addSmileyFragment.isVisible()) {
            addSmileyFragment.dismiss();
            return;
        }
        super.onBackPressed();
    }
}
