package com.fizi.ojoloverlay;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class OverlayService extends Service {

    public static final String ACTION_LOCK =
            "LOCK";

    private WindowManager windowManager;

    private LinearLayout panel;

    private WindowManager.LayoutParams params;

    private boolean locked = false;

    @Override
    public void onCreate() {

        super.onCreate();

        windowManager =
                (WindowManager)
                        getSystemService(
                                WINDOW_SERVICE
                        );

        createPanel();
    }

    private void createPanel() {

        panel =
                new LinearLayout(this);

        panel.setOrientation(
                LinearLayout.HORIZONTAL
        );

        panel.setPadding(
                8,
                8,
                8,
                8
        );

        panel.setBackgroundColor(
                Color.argb(
                        220,
                        20,
                        20,
                        20
                )
        );

        Button small =
                createButton("S");

        Button medium =
                createButton("M");

        Button large =
                createButton("L");

        Button close =
                createButton("X");

        small.setOnClickListener(v -> {

            RootController.launchInDrive(
                    getInDrivePackage(),
                    10,
                    80,
                    430,
                    520
            );
        });

        medium.setOnClickListener(v -> {

            RootController.launchInDrive(
                    getInDrivePackage(),
                    20,
                    80,
                    700,
                    650
            );
        });

        large.setOnClickListener(v -> {

            RootController.launchInDrive(
                    getInDrivePackage(),
                    10,
                    60,
                    710,
                    1000
            );
        });

        close.setOnClickListener(v -> {

            stopSelf();
        });

        panel.addView(small);
        panel.addView(medium);
        panel.addView(large);
        panel.addView(close);

        params =
                new WindowManager.LayoutParams(
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                        PixelFormat.TRANSLUCENT
                );

        params.gravity =
                Gravity.TOP | Gravity.LEFT;

        params.x = 20;
        params.y = 20;

        windowManager.addView(
                panel,
                params
        );
    }

    private Button createButton(
            String text) {

        Button b =
                new Button(this);

        b.setText(text);
        b.setTextColor(Color.WHITE);
        b.setTextSize(12);

        return b;
    }

    private String getInDrivePackage() {

        return "sinet.startup.inDrver";
    }

    @Override
    public int onStartCommand(
            Intent intent,
            int flags,
            int startId) {

        if (intent != null &&
                ACTION_LOCK.equals(
                        intent.getAction())) {

            locked = true;
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {

        if (panel != null) {

            windowManager.removeView(
                    panel
            );
        }

        super.onDestroy();
    }

    @Override
    public IBinder onBind(
            Intent intent) {

        return null;
    }
}
