package com.example.utils;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class FileUtils {

    private static volatile FileUtils sInstance;

    public static FileUtils getInstance() {
        if (sInstance == null) {
            synchronized (NumUtils.class) {
                if (sInstance == null) {
                    sInstance = new FileUtils();
                }
            }
        }
        return sInstance;
    }

    public boolean isExist(@NonNull String filePath) {
        File file = new File(filePath);
        if (!file.exists())
            return false;
        else
            return true;
    }

    public String copyFile2Directory(@NonNull Context context, @NonNull String filePath, DirectoryType directoryType) {

//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
//            File rootPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
//        } else {
//
//        }

        ContentResolver resolver = context.getContentResolver();
        ContentValues     values = new ContentValues();
        values.put(MediaStore.MediaColumns.DISPLAY_NAME, "CuteKitten001");
        values.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM);

        Uri uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        try {
            resolver.openOutputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return "";
    }

    public boolean copy(@NonNull String srcPath, @NonNull String dstPath) {
        File srcFile = new File(srcPath);
        File dstFile = new File(dstPath);

        if (!srcFile.exists() || !dstFile.exists())
            return false;

        if (srcFile.getAbsolutePath().equals(dstFile.getAbsolutePath()))
            return false;

        try {
            InputStream in = new FileInputStream(srcFile);
            FileOutputStream out = new FileOutputStream(dstFile);
            byte[] buf = new byte[1024];

            int len;
            while ((len = in.read(buf)) >= 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(@NonNull String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return file.delete();
        } else {
            return false;
        }
    }

}
