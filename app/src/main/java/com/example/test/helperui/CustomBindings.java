package com.example.test.helperui;

import android.databinding.BindingAdapter;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.example.test.R;

public class CustomBindings {

    @BindingAdapter("multiColorGradientBackground")
    public static void setChatHeaderBackground(View view, boolean multipleGradient) {
        if (multipleGradient) {
            GradientDrawable gradientDrawable = new GradientDrawable(
                    GradientDrawable.Orientation.LEFT_RIGHT,
                    new int[]{
                            ContextCompat.getColor(view.getContext(), R.color.header_start_color_blue),
//                            Color.parseColor("#82c7bf"),
//                            Color.parseColor("#70bcc4"),
//                            Color.parseColor("#70bcc4"),
//                            Color.parseColor("#82c7bf"),
                            ContextCompat.getColor(view.getContext(), R.color.header_start_color_greenish),
                    });
            view.setBackground(gradientDrawable);
        }
    }

    @BindingAdapter("imgResource")
    public static void setImageResource(ImageView imageView, int resourceId) {
        imageView.setImageResource(resourceId);
    }
}
