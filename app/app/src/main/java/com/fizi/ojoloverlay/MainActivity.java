package com.fizi.ojoloverlay;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 50, 40, 40);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView title = new TextView(this);
        title.setText("OJOL-Overlay");
        title.setTextSize(28);
        title.setTextColor(Color.BLACK);
        title.setGravity(Gravity.CENTER);

        layout.addView(title);

        Button open = new Button(this);
        open.setText("🟢 Buka InDrive");

        open.setOnClickListener(v -> {
            Intent intent = getPackageManager()
                    .getLaunchIntentForPackage("sinet.startup.inDriver");

            if (intent != null) {
                startActivity(intent);
            }
        });

        layout.addView(open);

        Button small = new Button(this);
        small.setText("📐 Ukuran Kecil");
        layout.addView(small);

        Button medium = new Button(this);
        medium.setText("📱 Ukuran Sedang");
        layout.addView(medium);

        Button large = new Button(this);
        large.setText("🖥️ Ukuran Besar");
        layout.addView(large);

        Button close = new Button(this);
        close.setText("❌ Tutup");
        layout.addView(close);

        setContentView(layout);
    }
}
