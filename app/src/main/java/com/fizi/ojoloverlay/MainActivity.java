package com.fizi.ojoloverlay;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView text = new TextView(this);
        text.setText("OJOL OVERLAY\n\nBERHASIL TERBUKA");
        text.setTextSize(30);
        text.setTextColor(Color.WHITE);
        text.setGravity(Gravity.CENTER);
        text.setBackgroundColor(Color.RED);

        setContentView(text);
    }
}
