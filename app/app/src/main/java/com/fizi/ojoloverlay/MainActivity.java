package com.fizi.ojoloverlay;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView text = new TextView(this);
        text.setText("OJOL OVERLAY\n\nAPK BERHASIL DIBUKA");
        text.setTextSize(28);
        text.setTextColor(Color.BLACK);
        text.setGravity(17);

        setContentView(text);
    }
}
