package com.example.wrapping_linear_layout;

import android.content.Context;
import android.widget.LinearLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class WrappingLinearLayout {

    public WrappingLinearLayout(@NotNull final ArrayList<LinearLayout> views, @NotNull final LinearLayout verticalLinearLayout, @NotNull final Context context) {
        verticalLinearLayout.post(new Runnable() {
            @Override
            public void run() {
                execute(views, verticalLinearLayout, context);
            }
        });
    }

    private void execute(@NotNull ArrayList<LinearLayout> views, @NotNull final LinearLayout verticalLinearLayout, @NotNull final Context context) {

        ArrayList<LinearLayout> horizontalLinearLayouts = new ArrayList<>();
        LinearLayout horizontalLinearLayout = new LinearLayout(context);
        horizontalLinearLayouts.add(horizontalLinearLayout);
        int verticalLinearLayoutWidth = verticalLinearLayout.getMeasuredWidth()
                - (verticalLinearLayout.getPaddingLeft()
                        + verticalLinearLayout.getPaddingRight());

        int totalWidthOfViews = 0;

        for (LinearLayout view : views) {

            view.measure(0, 0);
            int currentViewWidth = view.getMeasuredWidth();
            if (totalWidthOfViews + view.getMeasuredWidth() > verticalLinearLayoutWidth) {
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

}
