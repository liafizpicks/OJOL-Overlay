package com.fizi.ojoloverlay;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String INDRIVE_PACKAGE =
            "sinet.startup.inDrver";

    int dark = Color.rgb(18, 18, 22);
    int green = Color.rgb(0, 190, 110);
    int blue = Color.rgb(45, 120, 220);
    int orange = Color.rgb(245, 150, 40);
    int red = Color.rgb(220, 60, 60);
    int white = Color.WHITE;

    private String inDrivePackage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildUI();
    }

    private void buildUI() {

        findInDrive();

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(28, 30, 28, 25);
        root.setBackgroundColor(
                Color.rgb(245, 246, 248)
        );

        TextView title = new TextView(this);

        title.setText("JADIOJOL-OVERLAY");
        title.setTextSize(26);
        title.setTextColor(Color.BLACK);
        title.setTypeface(null, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);

        root.addView(title);

        TextView status = new TextView(this);

        status.setTextSize(14);
        status.setGravity(Gravity.CENTER);
        status.setPadding(0, 15, 0, 15);

        if (inDrivePackage != null) {

            status.setText(
                    "● INDRIVE TERDETEKSI"
            );

            status.setTextColor(green);

        } else {

            status.setText(
                    "● INDRIVE TIDAK DITEMUKAN"
            );

            status.setTextColor(red);
        }

        root.addView(status);

        // =========================
        // BUKA INDRIVE
        // =========================

        Button buka = button(
                "🟢  BUKA INDRIVE FLOATING",
                green,
                white
        );

        buka.setOnClickListener(v -> {

            if (inDrivePackage == null) {

                toast(
                        "InDrive tidak ditemukan"
                );

                return;
            }

            startOverlayService();

            RootController.launchInDrive(
                    inDrivePackage,
                    20,
                    80,
                    700,
                    650
            );
        });

        root.addView(buka);

        // =========================
        // KONTROL FLOATING
        // =========================

        root.addView(
                sectionTitle(
                        "KONTROL FLOATING INDRIVE"
                )
        );

        Button kecil = button(
                "📐  UKURAN KECIL",
                dark,
                white
        );

        kecil.setOnClickListener(v -> {

            if (!checkInDrive()) return;

            RootController.launchInDrive(
                    inDrivePackage,
                    20,
                    100,
                    430,
                    520
            );

            toast("Ukuran kecil");
        });

        root.addView(kecil);

        Button sedang = button(
                "📱  UKURAN SEDANG",
                blue,
                white
        );

        sedang.setOnClickListener(v -> {

            if (!checkInDrive()) return;

            RootController.launchInDrive(
                    inDrivePackage,
                    20,
                    80,
                    700,
                    650
            );

            toast("Ukuran sedang");
        });

        root.addView(sedang);

        Button besar = button(
                "🖥️  UKURAN BESAR",
                orange,
                white
        );

        besar.setOnClickListener(v -> {

            if (!checkInDrive()) return;

            RootController.launchInDrive(
                    inDrivePackage,
                    10,
                    60,
                    710,
                    1000
            );

            toast("Ukuran besar");
        });

        root.addView(besar);

        Button geser = button(
                "↔️  GESER POSISI",
                dark,
                white
        );

        geser.setOnClickListener(v -> {

            if (!checkInDrive()) return;

            RootController.launchInDrive(
                    inDrivePackage,
                    280,
                    80,
                    700,
                    650
            );

            toast("Posisi digeser");
        });

        root.addView(geser);

        Button kiri = button(
                "⬅️  POSISI KIRI",
                dark,
                white
        );

        kiri.setOnClickListener(v -> {

            if (!checkInDrive()) return;

            RootController.launchInDrive(
                    inDrivePackage,
                    10,
                    80,
                    430,
                    650
            );

            toast("Posisi kiri");
        });

        root.addView(kiri);

        Button kanan = button(
                "➡️  POSISI KANAN",
                dark,
                white
        );

        kanan.setOnClickListener(v -> {

            if (!checkInDrive()) return;

            RootController.launchInDrive(
                    inDrivePackage,
                    290,
                    80,
                    710,
                    650
            );

            toast("Posisi kanan");
        });

        root.addView(kanan);

        Button lock = button(
                "🔒  LOCK POSISI",
                blue,
                white
        );

        lock.setOnClickListener(v -> {

            if (!checkInDrive()) return;

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            OverlayService.class
                    );

            intent.setAction(
                    OverlayService.ACTION_LOCK
            );

            startService(intent);

            toast("Posisi dikunci");
        });

        root.addView(lock);

        Button tutup = button(
                "❌  TUTUP OVERLAY",
                red,
                white
        );

        tutup.setOnClickListener(v -> {

            stopService(
                    new Intent(
                            MainActivity.this,
                            OverlayService.class
                    )
            );

            toast("Overlay ditutup");
        });

        root.addView(tutup);

        // =========================
        // STATUS
        // =========================

        root.addView(
                sectionTitle(
                        "STATUS INDRIVE"
                )
        );

        TextView info = new TextView(this);

        info.setText(
                "Package InDrive:\n\n" +
                (
                        inDrivePackage == null
                                ? "Tidak ditemukan"
                                : inDrivePackage
                )
        );

        info.setTextSize(14);
        info.setTextColor(Color.DKGRAY);
        info.setPadding(15, 12, 15, 12);

        root.addView(info);

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
    // CEK INDRIVE
    // =========================

    private boolean checkInDrive() {

        findInDrive();

        if (inDrivePackage == null) {

            toast(
                    "InDrive tidak ditemukan"
            );

            return false;
        }

        return true;
    }

    // =========================
    // DETEKSI INDRIVE
    // PACKAGE DIKUNCI
    // =========================

    private void findInDrive() {

        inDrivePackage = null;

        try {

            getPackageManager()
                    .getApplicationInfo(
                            INDRIVE_PACKAGE,
                            PackageManager.GET_META_DATA
                    );

            inDrivePackage =
                    INDRIVE_PACKAGE;

        } catch (
                PackageManager.NameNotFoundException e
        ) {

            inDrivePackage = null;
        }
    }

    // =========================
    // OVERLAY SERVICE
    // =========================

    private void startOverlayService() {

        if (!Settings.canDrawOverlays(this)) {

            Intent intent =
                    new Intent(
                            Settings.ACTION_MANAGE_OVERLAY_PERMISSION
                    );

            startActivity(intent);

            return;
        }

        Intent service =
                new Intent(
                        this,
                        OverlayService.class
                );

        startService(service);
    }

    // =========================
    // SECTION TITLE
    // =========================

    private TextView sectionTitle(
            String text
    ) {

        TextView t =
                new TextView(this);

        t.setText(text);
        t.setTextSize(12);
        t.setTextColor(Color.DKGRAY);
        t.setTypeface(
                null,
                Typeface.BOLD
        );

        t.setPadding(
                5,
                18,
                5,
                8
        );

        return t;
    }

    // =========================
    // BUTTON
    // =========================

    private Button button(
            String text,
            int background,
            int textColor
    ) {

        Button b =
                new Button(this);

        b.setText(text);
        b.setTextSize(13);
        b.setTextColor(textColor);
        b.setTypeface(
                null,
                Typeface.BOLD
        );

        b.setAllCaps(false);

        b.setGravity(
                Gravity.CENTER
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(background);
        bg.setCornerRadius(22);

        b.setBackground(bg);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        65
                );

        params.setMargins(
                0,
                4,
                0,
                4
        );

        b.setLayoutParams(params);

        return b;
    }

    // =========================
    // TOAST
    // =========================

    private void toast(
            String text
    ) {

        Toast.makeText(
                this,
                text,
                Toast.LENGTH_SHORT
        ).show();
    }
}
