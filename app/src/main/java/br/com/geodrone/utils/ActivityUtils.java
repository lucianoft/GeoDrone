package br.com.geodrone.utils;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.os.Build;

import java.io.File;

/**
 * Created by fernandes on 30/05/2018.
 */

public class ActivityUtils {

    public static void openDownloads(@NonNull Activity activity) {
        if (isSamsung()) {
            Intent intent = activity.getPackageManager()
                    .getLaunchIntentForPackage("com.sec.android.app.myfiles");
            intent.setAction("samsung.myfiles.intent.action.LAUNCH_MY_FILES");
            intent.putExtra("samsung.myfiles.intent.extra.START_PATH",
                    getDownloadsFile().getPath());
            activity.startActivity(intent);
        }
        else activity.startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));
    }

    public static boolean isSamsung() {
        String manufacturer = Build.MANUFACTURER;
        if (manufacturer != null) return manufacturer.toLowerCase().equals("samsung");
        return false;
    }

    public static File getDownloadsFile() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    }
}
