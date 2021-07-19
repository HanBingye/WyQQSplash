package com.bing.wyqqsplash.base.crash;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrashProtectManager {
    public static CrashProtectManager instance;
    private static Context mContext;

    private CrashProtectManager() {
    }

    public static CrashProtectManager getInstance(Context context) {
        if (instance == null) {
            instance = new CrashProtectManager();
            //context.getApplicationContext()防止内存泄漏
            mContext = context.getApplicationContext();

        }
        return instance;
    }

    public void init() {
        //没有被try-catch获取到的异常,都会调用uncaughtException,为了让app不崩溃,但会让页面卡顿
        //因为Looper.loop()停止工作,无法从消息队列里取消息,处理消息
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
                Log.d("CrashProtectManager", "崩溃?");
                handException(e);
                if (t == Looper.getMainLooper().getThread()) {

                    handMainThread();
                }
            }


        });

    }

    //把报错日志写入到手机外部存储
    private void handException(Throwable e) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        printWriter.close();
        String res = writer.toString();
        DateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String time = format.format(new Date());
        String fileName = "error--" + time + ".txt";


        //MEDIA_MOUNTED存储媒体已经挂载,并且挂载点可读可写
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File cacheDir = mContext.getCacheDir();
            if (!cacheDir.exists()) {

                cacheDir.mkdirs();

            }
            File file = new File(cacheDir.getAbsolutePath(), fileName);
            try {
                if (!file.exists()) {

                    file.createNewFile();

                }
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(res);
                fileWriter.close();
//                FileOutputStream fileOutputStream = new FileOutputStream(file);
//                fileOutputStream.write(res.getBytes());
//                fileOutputStream.close();
                writer.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
    }


    //处理页面卡顿,让 Looper.loop()一直工作
    private void handMainThread() {
        while (true) {
            try {
                Looper.loop();
            } catch (Throwable e) {
                Log.d("CrashProtectManager", "本Looper还在工作");
                handException(e);
            }
        }

    }
}
