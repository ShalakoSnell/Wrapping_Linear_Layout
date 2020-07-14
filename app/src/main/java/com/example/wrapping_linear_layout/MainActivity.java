package com.example.wrapping_linear_layout;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new WrappingLinearLayout(
                viewAdapterArrayList(),
                (LinearLayout) findViewById(R.id.verticalLinearLayout),
                this);
    }

    private ArrayList<LinearLayout> viewAdapterArrayList() {
        ArrayList<LinearLayout> linearLayouts = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.text_view_reusable, null);
            TextView textView = (TextView) linearLayout.findViewById(R.id.text_view_reuseable);
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
            linearLayouts.add(linearLayout);
        }
        return linearLayouts;
    }
}