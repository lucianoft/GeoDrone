package br.com.geodrone.utils;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;

import br.com.geodrone.BuildConfig;
import br.com.geodrone.ui.base.BaseActivity;

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



    public static  Intent openDownloadPdf(Context context, File outputFile) {
        Intent intent = new Intent(Intent.ACTION_VIEW);

        // set flag to give temporary permission to external app to use your FileProvider
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            uri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", outputFile);
        }else {
            uri = Uri.fromFile(outputFile);
        }

        intent.setDataAndType(uri, "application/pdf");
        context.startActivity(intent);
        return intent;
    }

   /* public static void openDownloadPdf(@NonNull BaseActivity activity, String fileName){

        File outputFile = new File(activity.getFilesDir(), "arquivos/" + fileName);
               


        *//*try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            activity.startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            ex.printStackTrace();
            activity.onError(ex);
        }*//*

        try{
                Intent intent = new Intent(Intent.ACTION_VIEW);

        // set flag to give temporary permission to external app to use your FileProvider
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        // generate URI, I defined authority as the application ID in the Manifest, the last param is file I want to open
                Uri uri = FileProvider.getUriForFile(activity, BuildConfig.APPLICATION_ID, outputFile);

        // I am opening a PDF file so I give it a valid MIME type
                intent.setDataAndType(uri, "application/pdf");

        // validate that the device can open your File!
                PackageManager pm = activity.getPackageManager();
                if (intent.resolveActivity(pm) != null) {
                    activity.startActivity(intent);
                }
        } catch (Exception ex) {
            ex.printStackTrace();
            activity.onError(ex);
        }

    }*/


    public static void openFile(BaseActivity activity, File pathFile) {
        File file = new File(pathFile.getAbsolutePath());
        String fileExtension = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("."));
        switch (fileExtension) {
            case ".gif":case ".bmp":case ".tiff":case ".svg":case ".png":case ".jpg":case ".JPG":case ".jpeg":
                ActivityUtils.openFile(activity, pathFile, "image/*");
                break;
            case ".m3u8":case ".mp3":case ".wma":case ".midi":case ".wav":case ".aac":case ".aif":case ".amp3":case ".weba":
                ActivityUtils.openFile(activity, pathFile, "audio/*");
                break;
            case ".mpeg":case ".mp4":case ".ogg":case ".webm":case ".qt":case ".3gp":case ".3g2":case ".avi":case ".f4v":
            case ".flv":case ".h261":case ".h263":case ".h264":case ".asf":case ".wmv":
                ActivityUtils.openFile(activity, pathFile, "video/*");
                break;
            case ".rtx":case ".csv":case ".txt":case ".vcs":case ".vcf":case ".css":case ".ics":case ".conf":case ".config":case ".java":
                ActivityUtils.openFile(activity, pathFile, "text/*");
                break;
            case ".html":
                ActivityUtils.openFile(activity, pathFile, "text/html");
                break;
            case ".apk":
                ActivityUtils.openFile(activity, pathFile, "application/vnd.android.package-archive");
                break;
            case ".pdf":
                ActivityUtils.openFile(activity, pathFile, "application/pdf");
                break;
            case ".doc":
                ActivityUtils.openFile(activity, pathFile, "application/msword");
                break;
            case ".xls":
                ActivityUtils.openFile(activity, pathFile, "application/vnd.ms-excel");
                break;
            case ".ppt":
                ActivityUtils.openFile(activity, pathFile, "application/vnd.ms-powerpoint");
                break;
            case ".docx":
                ActivityUtils.openFile(activity, pathFile, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                break;
            case ".pptx":
                ActivityUtils.openFile(activity, pathFile, "application/vnd.openxmlformats-officedocument.presentationml.presentation");
                break;
            case ".xlsx":
                ActivityUtils.openFile(activity, pathFile, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                break;
            case ".odt":
                ActivityUtils.openFile(activity, pathFile, "application/vnd.oasis.opendocument.text");
                break;
            case ".ods":
                ActivityUtils.openFile(activity, pathFile, "application/vnd.oasis.opendocument.spreadsheet");
                break;
            case ".odp":
                ActivityUtils.openFile(activity, pathFile, "application/vnd.oasis.opendocument.presentation");
                break;
            case ".zip":
                ActivityUtils.openFile(activity, pathFile, "application/zip");
                break;
            case ".rar":
                ActivityUtils.openFile(activity, pathFile, "application/x-rar-compressed");
                break;
            case ".epub":
                ActivityUtils.openFile(activity, pathFile, "application/epub+zip");
                break;
            case ".cbz":
                ActivityUtils.openFile(activity, pathFile, "application/x-cbz");
                break;
            case ".cbr":
                ActivityUtils.openFile(activity, pathFile, "application/x-cbr");
                break;
            case ".fb2":
                ActivityUtils.openFile(activity, pathFile, "application/x-fb2");
                break;
            case ".rtf":
                ActivityUtils.openFile(activity, pathFile, "application/rtf");
                break;
            case ".opml":
                ActivityUtils.openFile(activity, pathFile, "application/opml");
                break;

            default:
               break;
        }
    }

    public static void openFile(BaseActivity activity, File file, String string) {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(activity, activity.getApplicationContext().getPackageName() + ".provider", file);
            intent.setDataAndType(contentUri,"application/pdf");

        } else {
            intent.setDataAndType(Uri.fromFile(file),"application/pdf");
        }

        try {
            activity.startActivity (intent);
        } catch (Exception ex) {
            activity.onError(ex);
        }
    }
}
