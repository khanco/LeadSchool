package com.example.test.helperui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.test.R;

public class SmileyAdapter extends RecyclerView.Adapter<SmileyAdapter.ViewHolder> {

    private int[] data;
    private LayoutInflater layoutInflater;
    private ISmileySelectedCallback callback;

    SmileyAdapter(int[] data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        View listItem = layoutInflater.inflate(R.layout.list_item_smiley, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.imageView.setImageResource(data[position]);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSmileySelectedCallback(data[position]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imvSmiley);
        }
    }

    public void setInterface(ISmileySelectedCallback callback) {
        this.callback = callback;
    }

    public interface ISmileySelectedCallback {
        void onSmileySelectedCallback(int smileyId);
    }
}
