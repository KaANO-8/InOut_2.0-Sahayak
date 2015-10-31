package com.pyrospiral.rahul.sahayak;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TutorialService extends Service implements View.OnTouchListener, View.OnClickListener {

    String from;
    private View topLeftView;

    private Button End, Place_Arrow, Next;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    LinearLayout mButtonBar,oView;
    private float offsetX;
    private float offsetY;
    private int originalXPos;
    private int originalYPos;
    private boolean moving;
    private WindowManager wm;


    @Override
    public IBinder onBind(Intent intent) {
        Bundle extras = intent.getExtras();

        Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_SHORT).show();
        if(extras == null)
            Log.d("Service", "null");
        else
        {
            Log.d("Service","not null");
            from = (String) extras.get("123");
            if(from!=null)
                Toast.makeText(getApplicationContext(),from+"hello",Toast.LENGTH_SHORT).show();
        }
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        String key = "123";
        Animation mAnimation;



        /*Intent myIntent = getIntent(); // this getter is just for example purpose, can differ
        if (myIntent !=null && myIntent.getExtras()!=null)
            String value = myIntent.getExtras().getString(key);*/

        wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        mButtonBar = new LinearLayout(this);
        mButtonBar.setOrientation(LinearLayout.HORIZONTAL);
        mButtonBar.setBackgroundColor(0x88ffffff);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);


        End = new Button(this);
        End.setText("End");
        //overlayedButton.setOnTouchListener(this);
        End.setAlpha(1f);
        End.setBackgroundColor(getResources().getColor(R.color.white));
        End.setTextColor(getResources().getColor(R.color.black));
        params.gravity = Gravity.LEFT;
        End.setLayoutParams(params);
        End.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Yu clicked", Toast.LENGTH_SHORT).show();
            }
        });

        Place_Arrow = new Button(this);
        Place_Arrow.setText("Place Arrow");
        //overlayedButton.setOnTouchListener(this);
        Place_Arrow.setAlpha(1f);
        Place_Arrow.setBackgroundColor(getResources().getColor(R.color.white));
        Place_Arrow.setTextColor(getResources().getColor(R.color.black));
        params.gravity = Gravity.CENTER;
        Place_Arrow.setLayoutParams(params);
        Place_Arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Yu clicked", Toast.LENGTH_SHORT).show();
            }
        });


        Next = new Button(this);
        Next.setText("Next");
        //overlayedButton.setOnTouchListener(this);
        Next.setAlpha(1f);
        Next.setBackgroundColor(getResources().getColor(R.color.white));
        Next.setTextColor(getResources().getColor(R.color.black));
        params.gravity = Gravity.RIGHT;
        Next.setLayoutParams(params);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Yu clicked", Toast.LENGTH_SHORT).show();
            }
        });

        mButtonBar.addView(End);
        mButtonBar.addView(Place_Arrow);
        mButtonBar.addView(Next);

        WindowManager.LayoutParams params2 = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);
        params2.gravity = Gravity.LEFT | Gravity.BOTTOM;
        params2.x = 300;
        //params.width = 100;


        WindowManager.LayoutParams BUTTONBARparams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);
        BUTTONBARparams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;


        wm.addView(mButtonBar,BUTTONBARparams);


        oView = new LinearLayout(this);
        oView.setBackgroundColor(0x88ff0000); // The translucent red color

        WindowManager.LayoutParams ALERTparams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);
                ALERTparams.height = 1040;
                ALERTparams.gravity = Gravity.LEFT | Gravity.TOP;



        WindowManager.LayoutParams OVERLAYparams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);
                OVERLAYparams.height = 1040;
                OVERLAYparams.gravity = Gravity.LEFT | Gravity.TOP;

        wm.addView(oView,ALERTparams);

        topLeftView = new View(this);
        WindowManager.LayoutParams topLeftParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);
        topLeftParams.gravity = Gravity.LEFT | Gravity.TOP;
        topLeftParams.x = 0;
        topLeftParams.y = 0;
        topLeftParams.width = 0;
        topLeftParams.height = 0;
        wm.addView(topLeftView, topLeftParams);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    @Override
    public void onClick(View v) {
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }


   /* @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getRawX();
            float y = event.getRawY();

            moving = false;

            int[] location = new int[2];
            overlayedButton.getLocationOnScreen(location);

            originalXPos = location[0];
            originalYPos = location[1];

            offsetX = originalXPos - x;
            offsetY = originalYPos - y;

        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            int[] topLeftLocationOnScreen = new int[2];
            topLeftView.getLocationOnScreen(topLeftLocationOnScreen);


            float x = event.getRawX();
            float y = event.getRawY();

            WindowManager.LayoutParams params = (WindowManager.LayoutParams) overlayedButton.getLayoutParams();

            int newX = (int) (offsetX + x);
            int newY = (int) (offsetY + y);

            if (Math.abs(newX - originalXPos) < 1 && Math.abs(newY - originalYPos) < 1 && !moving) {
                return false;
            }

            params.x = newX - (topLeftLocationOnScreen[0]);
            params.y = newY - (topLeftLocationOnScreen[1]);

            wm.updateViewLayout(overlayedButton, params);
            moving = true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (moving) {
                return true;
            }
        }
        return false;
    }*/
}
