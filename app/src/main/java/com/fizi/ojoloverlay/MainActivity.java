package com.fizi.ojoloverlay;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
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
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.setPadding(30, 40, 30, 30);
        layout.setBackgroundColor(Color.WHITE);

        TextView title = new TextView(this);
        title.setText("OJOL OVERLAY");
        title.setTextSize(28);
        title.setTextColor(Color.BLACK);
        title.setTypeface(null, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        layout.addView(title);

        TextView subtitle = new TextView(this);
        subtitle.setText("CONTROL PANEL");
        subtitle.setTextSize(14);
        subtitle.setTextColor(Color.GRAY);
        subtitle.setGravity(Gravity.CENTER);
        layout.addView(subtitle);

        Button buka = new Button(this);
        buka.setText("🟢 BUKA INDRIVE");
        layout.addView(buka);

        Button kecil = new Button(this);
        kecil.setText("📐 UKURAN KECIL");
        layout.addView(kecil);

        Button sedang = new Button(this);
        sedang.setText("📱 UKURAN SEDANG");
        layout.addView(sedang);

        Button besar = new Button(this);
        besar.setText("🖥️ UKURAN BESAR");
        layout.addView(besar);

        Button geser = new Button(this);
        geser.setText("↔️ GESER POSISI");
        layout.addView(geser);

        Button lock = new Button(this);
        lock.setText("🔒 LOCK POSISI");
        layout.addView(lock);

        Button tutup = new Button(this);
        tutup.setText("❌ TUTUP");
        layout.addView(tutup);

        setContentView(layout);
    }
}
