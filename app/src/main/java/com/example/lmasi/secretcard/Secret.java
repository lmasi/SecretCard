package com.example.lmasi.secretcard;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.android.fingerprintdialog.FingerprintUiHelper;

/**
 * Created by lmasi on 2016-11-07.
 */

public class Secret extends Activity {

    RelativeLayout main;
    EditText editText;

    ImageView[] keypads;
    ImageView[] showNumber;

    static int number = 0;

    int s1, s2, s = 0;


    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secret);

        DbResource.conn = new DBConect(this, "Secret.db", null, 1);
        DbResource.db = DbResource.conn.getWritableDatabase();

        number = 0;

        main = (RelativeLayout)findViewById(R.id.main);
        main.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                finish();

                return true;
            }
        });





        keypads = new ImageView[12];
        for(int i=0; i<12; i++)
        {
            keypads[i] = new ImageView(getApplicationContext());
            keypads[i].setLayoutParams(new SecretParameter(187 * ScreenParameter.getScreenparam_x(), 187 * ScreenParameter.getScreenparam_y()).setMargin( (50 + 227 * (i%6)) * ScreenParameter.getScreenparam_x(), (i < 6 ? 2088 : 2327) * ScreenParameter.getScreenparam_y(), 0, 0));
            keypads[i].setBackground(getDrawable(R.drawable.keypad_01 + i));
            main.addView(keypads[i]);
            keypads[i].setId(i);
            keypads[i].setOnTouchListener(new View.OnTouchListener() {


                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    if(motionEvent.getAction() == MotionEvent.ACTION_UP)
                    {
                        if(flag)
                        {
                            Log.e("MESSS", Boolean.toString(flag));
                            for(int i=0; i<4; i++)
                            {
                                showNumber[i].setBackground(getDrawable(R.drawable.keypad_11));
                                showNumber[i].invalidate();
                            }
                            number = 0;
                            flag = false;
                        }

                        if(new Integer(view.getId()) == 5)
                        {
                            if(number > 0) {
                                showNumber[--number].setBackground(getDrawable(R.drawable.keypad_11));
                            }
                        }

                        else if(new Integer(view.getId())== 11)
                        {
//                            for(int i=0; i<4; i++)
//                                   showNumber[i].setBackground(getDrawable(R.drawable.keypad_11));

                            s1 = s/100;
                            s2 = s%100;
                            number = 0;
                            s = 0;

                            int[] a = new int[4];

                            a[0] = DbResource.get(s1)/1000;
                            a[1] = (DbResource.get(s1)/100)%10;
                            a[2] = (DbResource.get(s2)/10)%10;
                            a[3] = DbResource.get(s2)%10;


                            for(int i=0; i<4; i++)
                            {
                                if(a[i] < 6)
                                    a[i]--;

                                if(a[i] == -1)
                                    a[i] = 10;
                            }

                            for(int i=0; i<4; i++)
                                showNumber[i].setBackground(getDrawable(R.drawable.keypad_01 + a[i]));

                            flag = true;
                            Log.e("MESS", Boolean.toString(flag));
                        }

                        else if(number != 4){

                            showNumber[number].setBackground(getDrawable(R.drawable.keypad_01 + view.getId()));

                            int id = view.getId() < 5 ? view.getId()+1 : view.getId();
                            if(id==10) id = 0;
                            s += id * Math.pow(10, 3-number);

                            number++;

                        }

                    }

                    return true;
                }
            });
        }

        showNumber = new ImageView[4];
        for(int i=0; i<4; i++)
        {
            showNumber[i] = new ImageView(getApplicationContext());
            showNumber[i].setLayoutParams(new SecretParameter(187 * ScreenParameter.getScreenparam_x(), 187 * ScreenParameter.getScreenparam_y()).setMargin( (307 + (187+50) * i) * ScreenParameter.getScreenparam_x(), 991 * ScreenParameter.getScreenparam_y(), 0, 0));
            showNumber[i].setBackground(getDrawable(R.drawable.keypad_11));
            main.addView(showNumber[i]);
        }
    }


}