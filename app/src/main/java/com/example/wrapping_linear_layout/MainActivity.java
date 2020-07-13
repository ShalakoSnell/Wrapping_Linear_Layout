package com.example.wrapping_linear_layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout verticalLinearLayout = findViewById(R.id.verticalLinearLayout);
        wrappingLinearLayout(
                        viewAdapterArrayList(textViewArrayListForExample()),
                        verticalLinearLayout,
                        MainActivity.this);
    }

    void wrappingLinearLayout(@NotNull ArrayList<LinearLayout> views, LinearLayout verticalLinearLayout, Context context) {
        ArrayList<LinearLayout> horizontalLinearLayouts = new ArrayList<>();
        LinearLayout horizontalLinearLayout = new LinearLayout(context);
        horizontalLinearLayouts.add(horizontalLinearLayout);

        verticalLinearLayout.measure(0,0);

        int totalWidthOfViews = 0;

        for (LinearLayout view : views) {

            view.measure(0, 0);
            int currentViewWidth  = view.getMeasuredWidth();

            if (totalWidthOfViews + view.getMeasuredWidth() > verticalLinearLayout.getMeasuredWidth()) {
                horizontalLinearLayout = new LinearLayout(context);
                horizontalLinearLayouts.add(horizontalLinearLayout);
                totalWidthOfViews = 0;
            }

            totalWidthOfViews += currentViewWidth;

            horizontalLinearLayout.addView(view);
        }

        for (LinearLayout linearLayout : horizontalLinearLayouts) {
            verticalLinearLayout.addView(linearLayout);
        }
    }

    private ArrayList<LinearLayout> viewAdapterArrayList(ArrayList<TextView> textViews) {
        ArrayList<LinearLayout> views = new ArrayList<>();
        for (TextView textView : textViews) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.addView(textView);
            views.add(linearLayout);
        }
        return views;
    }

    private ArrayList<TextView> textViewArrayListForExample() {
        ArrayList<TextView> textViews = new ArrayList<>();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        for (int i = 0; i < 40; i++) {
            TextView textView = new TextView(this);
            textView.setText("View " + i + " |");
            if (i < 20) {
                if (i % 5 == 0) {
                    textView.setText("View longer view " + i + " |");
                } else if (i % 7 == 0) {
                    textView.setText("View different length view " + i + " |");
                } else if (i % 9 == 0) {
                    textView.setText("View very long view that is so long it's really long " + i + " |");
                }
            }
            textView.setMaxLines(1);
            textView.setBackground(new ColorDrawable(Color.BLUE));
            textView.setTextColor(Color.WHITE);
            textView.setLayoutParams(layoutParams);
            textView.setPadding(20, 2, 20, 2);
            layoutParams.setMargins(10, 2, 10, 2);
            textViews.add(textView);
        }
        return textViews;
    }
}