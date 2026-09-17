package com.fizi.ojoloverlay;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {

    int dark = Color.rgb(18, 18, 22);
    int green = Color.rgb(0, 190, 110);
    int white = Color.WHITE;
    int orange = Color.rgb(245, 150, 40);
    int blue = Color.rgb(45, 120, 220);
    int red = Color.rgb(220, 60, 60);

    private String inDrivePackage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildUI();
    }

    private void buildUI() {

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(28, 35, 28, 28);
        root.setBackgroundColor(Color.rgb(245, 246, 248));

        TextView title = new TextView(this);
        title.setText("OJOL-OVERLAY");
        title.setTextSize(26);
        title.setTextColor(Color.BLACK);
        title.setTypeface(null, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);

        root.addView(title);

        TextView status = new TextView(this);
        status.setTextSize(14);
        status.setGravity(Gravity.CENTER);
        status.setPadding(0, 15, 0, 15);

        findInDrive();

        if (inDrivePackage != null) {
            status.setText("●  INDRIVE TERDETEKSI");
            status.setTextColor(green);
        } else {
            status.setText("●  INDRIVE TIDAK DITEMUKAN");
            status.setTextColor(red);
        }

        root.addView(status);

        // =========================
        // BUKA INDRIVE
        // =========================

        Button buka = button(
                "🟢  BUKA INDRIVE",
                green,
                white
        );

        buka.setOnClickListener(v -> {

            if (inDrivePackage != null) {

                Intent intent =
                        getPackageManager()
                                .getLaunchIntentForPackage(
                                        inDrivePackage
                                );

                if (intent != null) {
                    startActivity(intent);
                } else {
                    buka.setText("❌ GAGAL MEMBUKA");
                }

            } else {
                buka.setText("❌ INDRIVE TIDAK DITEMUKAN");
            }
        });

        root.addView(buka);

        // =========================
        // KONTROL OVERLAY
        // =========================

        root.addView(
                sectionTitle("KONTROL FLOATING INDRIVE")
        );

        Button kecil = button(
                "📐  UKURAN KECIL",
                dark,
                white
        );

        kecil.setOnClickListener(v -> {
            // Akan digunakan untuk mengatur ukuran floating window
        });

        root.addView(kecil);

        Button sedang = button(
                "📱  UKURAN SEDANG",
                blue,
                white
        );

        sedang.setOnClickListener(v -> {
            // Akan digunakan untuk mengatur ukuran floating window
        });

        root.addView(sedang);

        Button besar = button(
                "🖥️  UKURAN BESAR",
                orange,
                white
        );

        besar.setOnClickListener(v -> {
            // Akan digunakan untuk mengatur ukuran floating window
        });

        root.addView(besar);

        Button geser = button(
                "↔️  GESER POSISI",
                dark,
                white
        );

        geser.setOnClickListener(v -> {
            // Akan digunakan untuk mode geser floating window
        });

        root.addView(geser);

        Button lock = button(
                "🔒  LOCK POSISI",
                blue,
                white
        );

        lock.setOnClickListener(v -> {
            // Akan digunakan untuk mengunci posisi floating window
        });

        root.addView(lock);

        Button tutup = button(
                "❌  TUTUP OVERLAY",
                red,
                white
        );

        tutup.setOnClickListener(v -> {
            // Akan digunakan untuk menutup floating window
        });

        root.addView(tutup);

        // =========================
        // INFO INDRIVE
        // =========================

        root.addView(
                sectionTitle("APLIKASI INDRIVE")
        );

        if (inDrivePackage != null) {

            TextView found = new TextView(this);

            found.setText(
                    "✓ InDrive ditemukan\n\n" +
                    "Package:\n" +
                    inDrivePackage
            );

            found.setTextSize(14);
            found.setTextColor(Color.DKGRAY);
            found.setPadding(15, 12, 15, 12);

            root.addView(found);

        } else {

            TextView notFound = new TextView(this);

            notFound.setText(
                    "InDrive belum ditemukan."
            );

            notFound.setTextSize(14);
            notFound.setTextColor(red);
            notFound.setPadding(15, 12, 15, 12);

            root.addView(notFound);
        }

        // =========================
        // SCAN ULANG
        // =========================

        Button refresh = button(
                "🔄  SCAN ULANG",
                dark,
                white
        );

        refresh.setOnClickListener(v -> {
            buildUI();
        });

        root.addView(refresh);

        setContentView(root);
    }

    // =========================
    // DETEKSI INDRIVE
    // =========================

    private void findInDrive() {

        inDrivePackage = null;

        PackageManager pm = getPackageManager();

        List<ApplicationInfo> apps =
                pm.getInstalledApplications(
                        PackageManager.GET_META_DATA
                );

        for (ApplicationInfo app : apps) {

            String name =
                    app.loadLabel(pm)
                            .toString();

            if (name.toLowerCase()
                    .contains("indrive")) {

                inDrivePackage =
                        app.packageName;

                break;
            }
        }
    }

    // =========================
    // JUDUL SECTION
    // =========================

    private TextView sectionTitle(String text) {

        TextView t = new TextView(this);

        t.setText(text);
        t.setTextSize(12);
        t.setTextColor(Color.DKGRAY);
        t.setTypeface(null, Typeface.BOLD);
        t.setPadding(5, 20, 5, 8);

        return t;
    }

    // =========================
    // BUTTON
    // =========================

    private Button button(
            String text,
            int background,
            int textColor) {

        Button b = new Button(this);

        b.setText(text);
        b.setTextSize(13);
        b.setTextColor(textColor);
        b.setTypeface(null, Typeface.BOLD);
        b.setAllCaps(false);
        b.setGravity(Gravity.CENTER);

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(background);
        bg.setCornerRadius(22);

        b.setBackground(bg);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        70
                );

        params.setMargins(0, 5, 0, 5);

        b.setLayoutParams(params);

        return b;
    }
}
