package com.fizi.ojoloverlay;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RootController {

    public static void launchInDrive(
            String packageName,
            int left,
            int top,
            int right,
            int bottom) {

        if (packageName == null || packageName.length() == 0) {
            return;
        }

        try {

            /*
             * 1. Jalankan aplikasi berdasarkan PACKAGE.
             *    Cara ini tidak membutuhkan nama Activity.
             */
            String launchCommand =
                    "monkey -p " +
                    packageName +
                    " -c android.intent.category.LAUNCHER 1";

            Process launch =
                    Runtime.getRuntime().exec(
                            new String[]{
                                    "su",
                                    "-c",
                                    launchCommand
                            }
                    );

            launch.waitFor();

            /*
             * 2. Tunggu sebentar agar Activity InDrive
             *    benar-benar terbuka.
             */
            Thread.sleep(1200);

            /*
             * 3. Coba ubah Activity yang sedang aktif
             *    menjadi freeform.
             */
            String resizeCommand =
                    "am stack list";

            Process stack =
                    Runtime.getRuntime().exec(
                            new String[]{
                                    "su",
                                    "-c",
                                    resizeCommand
                            );

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    stack.getInputStream()
                            )
                    );

            String line;
            StringBuilder result =
                    new StringBuilder();

            while ((line = reader.readLine()) != null) {

                result.append(line)
                      .append("\n");
            }

            stack.waitFor();

            /*
             * Untuk sementara kita prioritaskan
             * memastikan InDrive benar-benar terbuka.
             */
            System.out.println(
                    "STACK INFO:\n" +
                    result.toString()
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
