package com.example.java.utils;

/**
 * yyyy: year
 * MM: month
 * dd: day
 * hh: 12-hour
 * HH: 24-hour
 * mm: minute
 * ss: second
 * S: millisecond
 * E: week
 * D: day of the year
 * F: week of the month
 * w：week of the year
 * W: week of the month
 * a: am/pm
 * k: 24-hour
 * K: 12-hour
 * z: timezone
 */

import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeUtils {

    private static TimeUtils sInstance;

    public static TimeUtils getInstance() {
        if (sInstance == null) {
            synchronized (TimeUtils.class) {
                if (sInstance == null) {
                    sInstance = new TimeUtils();
                }
            }
        }
        return sInstance;
    }

    public Date getCurrentTime() {
        return Calendar.getInstance().getTime();
    }

    public Date toDate(long timestamp) {
        return new Date(timestamp);
    }

    public long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    public Date toDate(String dateStr, String format) {
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = fmt.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public String toString(long timeStamp, String format) {
        Date date = new Date(timeStamp);
        DateFormat sdf = SimpleDateFormat.getDateInstance();
        return sdf.format(date);
    }

    public String toString(Date date, String format) {
        if (date == null) return null;
        return toString(date.getTime(), format);
    }

    public long toTimestamp(String dateStr, String format) {
        Date date = toDate(dateStr, format);
        return toTimestamp(date);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getCurrentDateNew() {
        LocalDate.now();
    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public Date toDateNew(String dateStr, String format) {
//        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(format).withZone(ZoneOffset.UTC);
//    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public String toStringNew(long timeStamp, String format) {
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
//    }


    public void delay1(long millis, Handler handler, Message msg) {
        new Thread(() -> {
            try {
                Thread.sleep(millis);
                handler.sendMessage(msg);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void delay2(long millis, Handler handler, Message msg) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.sendMessage(msg);
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, millis);
    }

    public void delay3(long millis, Handler handler, Message msg) {
        new Handler().postDelayed(() -> {
            handler.sendMessage(msg);
        }, millis);
    }

    public void delay4(long millis, Handler handler, Message msg) {
        new CountDownTimer(millis, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                handler.sendMessage(msg);
            }

            @Override
            public void onFinish() {
                Log.i("", "onFinish");
            }
        }.start();
    }
}
