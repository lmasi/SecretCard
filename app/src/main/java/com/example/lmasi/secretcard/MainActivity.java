package com.example.lmasi.secretcard;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v4.app.NotificationCompat;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompatApi23;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScreenParameter.setScreenparam_x(ScreenSize().x / ScreenParameter.getDefaultsizeX());
        ScreenParameter.setScreenparam_y(ScreenSize().y / ScreenParameter.getDefaultsizeY());
        ScreenParameter.setScreen_x(ScreenSize().x);
        ScreenParameter.setScreen_y(ScreenSize().y);

        try
        {
            DbResource.conn = new DBConect(this, "Secret.db", null, 1);
            DbResource.db = DbResource.conn.getWritableDatabase();

            DbResource.insert("신한", 1, 2233);
            DbResource.insert("신한",2, 9236);
            DbResource.insert("신한",3, 8594);
            DbResource.insert("신한",4, 9366);
            DbResource.insert("신한",5, 7844);
            DbResource.insert("신한",6, 2673);
            DbResource.insert("신한",7, 1409);
            DbResource.insert("신한",8, 6012);
            DbResource.insert("신한",9, 2234);
            DbResource.insert("신한",10, 1756);
            DbResource.insert("신한",11, 9665);
            DbResource.insert("신한",12, 5476);
            DbResource.insert("신한",13, 9585);
            DbResource.insert("신한",14, 4610);
            DbResource.insert("신한",15, 1523);
            DbResource.insert("신한",16, 4796);
            DbResource.insert("신한",17, 2938);
            DbResource.insert("신한",18, 4300);
            DbResource.insert("신한",19, 5843);
            DbResource.insert("신한",20, 8091);
            DbResource.insert("신한",21, 8149);
            DbResource.insert("신한",22, 7980);
            DbResource.insert("신한",23, 9800);
            DbResource.insert("신한",24, 1681);
            DbResource.insert("신한",25, 7799);
            DbResource.insert("신한",26, 2456);
            DbResource.insert("신한",27, 4239);
            DbResource.insert("신한",28, 8687);
            DbResource.insert("신한",29, 9308);
            DbResource.insert("신한",30, 0761);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            DbResource.initPrimaryBank();
        }

        startService(new Intent(getApplicationContext(), SecretService.class));


        finish();

    }

    public Point ScreenSize() { //현재 스크린의 사이즈를 가져오는 메서드. 정형화된 틀이다.

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        Point size = new Point(width, height);

        return size;

    }

    public int narrowSize() {
        int x = ScreenSize().x; //현재 스크린의 사이즈; 가로, 세로 화면.
        int y = ScreenSize().y;

        return (x > y ? y : x);
    }

    public int wideSize() {
        int x = ScreenSize().x;
        int y = ScreenSize().y;

        return (x < y ? y : x);
    }

}
