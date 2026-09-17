package com.fizi.ojoloverlay;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;

public class MainActivity extends Activity {

    int dark = Color.rgb(18, 18, 22);
    int green = Color.rgb(0, 190, 110);
    int white = Color.WHITE;
    int gray = Color.rgb(130, 130, 140);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(28, 35, 28, 28);
        root.setBackgroundColor(Color.rgb(245, 246, 248));

        // HEADER
        LinearLayout header = new LinearLayout(this);
        header.setOrientation(LinearLayout.VERTICAL);
        header.setGravity(Gravity.CENTER);
        header.setPadding(20, 25, 20, 25);
        header.setBackground(round(dark, 28));

        TextView logo = new TextView(this);
        logo.setText("🛵");
        logo.setTextSize(42);
        logo.setGravity(Gravity.CENTER);

        TextView title = new TextView(this);
        title.setText("OJOL OVERLAY");
        title.setTextSize(26);
        title.setTextColor(white);
        title.setTypeface(null, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);

        TextView subtitle = new TextView(this);
        subtitle.setText("SMART FLOATING CONTROL");
        subtitle.setTextSize(11);
        subtitle.setTextColor(Color.LTGRAY);
        subtitle.setGravity(Gravity.CENTER);

        header.addView(logo);
        header.addView(title);
        header.addView(subtitle);

        root.addView(header);

        // STATUS
        TextView status = new TextView(this);
        status.setText("●  INDRIVE READY");
        status.setTextSize(14);
        status.setTextColor(green);
        status.setTypeface(null, Typeface.BOLD);
        status.setGravity(Gravity.CENTER);
        status.setPadding(0, 25, 0, 15);

        root.addView(status);

        // BUKA
        Button buka = button("🟢  BUKA INDRIVE", green, white);
        buka.setOnClickListener(v -> {

            Intent intent = getPackageManager()
                    .getLaunchIntentForPackage("sinet.startup.inDriver");

            if (intent != null) {
                startActivity(intent);
            }
        });

        root.addView(buka);

        // SECTION
        TextView ukuranTitle = sectionTitle("UKURAN FLOATING WINDOW");
        root.addView(ukuranTitle);

        Button kecil = button("📐  KECIL", dark, white);
        Button sedang = button("📱  SEDANG", dark, white);
        Button besar = button("🖥️  BESAR", dark, white);

        root.addView(kecil);
        root.addView(sedang);
        root.addView(besar);

        // SECTION
        TextView kontrolTitle = sectionTitle("KONTROL POSISI");
        root.addView(kontrolTitle);

        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);

        Button geser = button("↔️  GESER", dark, white);
        Button lock = button("🔒  LOCK", dark, white);

        row.addView(geser, new LinearLayout.LayoutParams(
                0, 58, 1));

        row.addView(lock, new LinearLayout.LayoutParams(
                0, 58, 1));

        root.addView(row);

        // TUTUP
        Button tutup = button("❌  TUTUP OVERLAY",
                Color.rgb(220, 60, 60), white);

        root.addView(tutup);

        // FOOTER
        TextView footer = new TextView(this);
        footer.setText("\nOJOL OVERLAY • v1.0");
        footer.setTextSize(11);
        footer.setTextColor(gray);
        footer.setGravity(Gravity.CENTER);

        root.addView(footer);

        setContentView(root);
    }

    private TextView sectionTitle(String text) {
        TextView t = new TextView(this);
        t.setText(text);
        t.setTextSize(12);
        t.setTextColor(Color.DKGRAY);
        t.setTypeface(null, Typeface.BOLD);
        t.setPadding(5, 20, 5, 8);
        return t;
    }

    private Button button(String text, int background, int textColor) {

        Button b = new Button(this);

        b.setText(text);
        b.setTextSize(14);
        b.setTextColor(textColor);
        b.setTypeface(null, Typeface.BOLD);
        b.setAllCaps(false);

        b.setGravity(Gravity.CENTER);

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(background);
        bg.setCornerRadius(22);

        b.setBackground(bg);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        58);

        params.setMargins(0, 5, 0, 5);

        b.setLayoutParams(params);

        return b;
    }

    private GradientDrawable round(int color, float radius) {

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(color);
        bg.setCornerRadius(radius);

        return bg;
    }
            }
