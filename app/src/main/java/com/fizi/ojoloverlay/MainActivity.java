package com.fizi.ojoloverlay;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView text = new TextView(this);
        text.setText("OJOL Overlay");
        text.setTextSize(24);

        setContentView(text);
    }
}
