package com.example.test.screens.chats.mvvm.views;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.R;
import com.example.test.databinding.ListItemChatFromMeBinding;
import com.example.test.databinding.ListItemChatFromThemBinding;
import com.example.test.screens.chats.mvvm.models.ChatMessageModel;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {

    private List<ChatMessageModel> data;
    private LayoutInflater layoutInflater;
    private ISmileyOnClickCallback callback;

    private final int LAYOUT_MESSAGE_FROM_ME = 0;
    private final int LAYOUT_MESSAGE_FROM_THEM = 1;

    ChatListAdapter(List<ChatMessageModel> data) {
        this.data = data;
    }

    @NonNull @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        switch (viewType) {
            case LAYOUT_MESSAGE_FROM_ME:
                return new ChatMessageFromMeViewHolder((ListItemChatFromMeBinding) DataBindingUtil
                        .inflate(layoutInflater, R.layout.list_item_chat_from_me, parent, false));
            case LAYOUT_MESSAGE_FROM_THEM:
                return new ChatMessageFromThemViewHolder((ListItemChatFromThemBinding) DataBindingUtil
                        .inflate(layoutInflater, R.layout.list_item_chat_from_them, parent, false));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        ChatMessageModel item = data.get(position);
        holder.bindType(item);
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).isMessageFromMe()) {
            return LAYOUT_MESSAGE_FROM_ME;
        } else {
            return LAYOUT_MESSAGE_FROM_THEM;
        }
    }

    @Override public int getItemCount() {
        return data.size();
    }

    public abstract class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void bindType(ChatMessageModel item);
    }

    public class ChatMessageFromMeViewHolder extends ViewHolder {

        ListItemChatFromMeBinding binding;

        ChatMessageFromMeViewHolder(ListItemChatFromMeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bindType(ChatMessageModel item) {
            binding.setModel(item);
        }
    }

    public class ChatMessageFromThemViewHolder extends ViewHolder {

        ListItemChatFromThemBinding binding;

        ChatMessageFromThemViewHolder(ListItemChatFromThemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.imvSmiley.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onSmileyClickCallback(getAdapterPosition());
                }
            });
        }

        @Override
        public void bindType(ChatMessageModel item) {
            binding.setModel(item);
        }
    }

    public void setInterface(ISmileyOnClickCallback callback) {
        this.callback = callback;
    }

    public interface ISmileyOnClickCallback {
        void onSmileyClickCallback(int position);
    }
}
