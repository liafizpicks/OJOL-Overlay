package com.fizi.ojoloverlay;

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
             * Cari activity utama InDrive secara otomatis,
             * lalu jalankan dalam mode freeform.
             */

            String command =
                    "COMP=$(cmd package resolve-activity " +
                    "--brief " +
                    packageName +
                    " | tail -n 1); " +

                    "am start " +
                    "--windowingMode 5 " +
                    "--activity-bounds " +
                    left + "," +
                    top + "," +
                    right + "," +
                    bottom +
                    " -n $COMP";

            Process process =
                    Runtime.getRuntime().exec(
                            new String[]{
                                    "su",
                                    "-c",
                                    command
                            }
                    );

            process.waitFor();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
