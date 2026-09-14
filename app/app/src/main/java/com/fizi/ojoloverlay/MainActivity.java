package com.fizi.ojoloverlay;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.content.Intent;
import android.net.Uri;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);
        layout.setGravity(Gravity.CENTER);

        TextView title = new TextView(this);
        title.setText("OJOL Overlay");
        title.setTextSize(28);
        title.setTextColor(Color.BLACK);
        title.setGravity(Gravity.CENTER);

        statusText = new TextView(this);
        statusText.setTextSize(16);
        statusText.setGravity(Gravity.CENTER);
        statusText.setPadding(0, 30, 0, 30);

        Button permissionButton = new Button(this);
        permissionButton.setText("IZINKAN OVERLAY");

        permissionButton.setOnClickListener(v -> {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(
                        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName())
                );
                startActivity(intent);
            }
            updateStatus();
        });

        layout.addView(title);
        layout.addView(statusText);
        layout.addView(permissionButton);

        setContentView(layout);

        updateStatus();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateStatus();
    }

    private void updateStatus() {
        if (Settings.canDrawOverlays(this)) {
            statusText.setText("✅ Izin overlay sudah aktif");
        } else {
            statusText.setText("⚠️ Izin overlay belum aktif");
        }
    }
}
