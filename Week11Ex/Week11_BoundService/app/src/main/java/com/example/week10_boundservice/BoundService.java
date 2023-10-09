package com.example.week10_boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BoundService extends Service {
    public MyLocalBinder localBinder = new MyLocalBinder();
    public BoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public static String getSysTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss    dd/mm/yyyy", Locale.US);
        return simpleDateFormat.format(new Date());
    }

    public class MyLocalBinder extends Binder {
        BoundService getBoundService() {
            return BoundService.this;
        }
    }
}