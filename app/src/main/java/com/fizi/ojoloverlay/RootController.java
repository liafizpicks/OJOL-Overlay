package com.fizi.ojoloverlay;

public class RootController {

    public static void launchInDrive(
            String packageName,
            int left,
            int top,
            int right,
            int bottom) {

        if (packageName == null) {
            return;
        }

        try {

            String command =
                    "am start " +
                    "--windowingMode 5 " +
                    "--activity-bounds " +
                    left + "," +
                    top + "," +
                    right + "," +
                    bottom +
                    " -n " +
                    packageName;

            Process process =
                    Runtime.getRuntime()
                            .exec(
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
