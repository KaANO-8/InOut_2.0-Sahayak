package com.pyrospiral.rahul.sahayak;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class OverlayObjects extends Service implements View.OnTouchListener, View.OnClickListener {

    String from;
    private View topLeftView;

    private Button overlayedButton, overlayedText, overlayedTransparent;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

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

        overlayedButton = new Button(this);
        //overlayedButton.setText("Overlay b");
        overlayedButton.setAlpha(0.8f);
        //overlayedButton.setBackgroundColor(0x55fe4444);
        overlayedButton.setBackground(getResources().getDrawable(R.drawable.arrow_down));
        overlayedButton.setRotation(180);
        overlayedButton.setVisibility(View.VISIBLE);

        ImageView image = new ImageView(this);

        int id = getResources().getIdentifier("com.pyrospiral.rahul.sahayak:drawable/arrow_down", null, null);

        image.setImageResource(id);

        Animation animation
                = AnimationUtils.loadAnimation(this, R.anim.animation);

        //image.startAnimation(animation);

        overlayedText = new Button(this);
        overlayedText.setText(R.string.hindi);
        //overlayedButton.setOnTouchListener(this);
        overlayedText.setAlpha(1f);
        overlayedText.setBackgroundColor(getResources().getColor(R.color.white));
        overlayedText.setTextColor(getResources().getColor(R.color.black));

        overlayedTransparent = new Button(this);
        overlayedTransparent.setBackgroundColor(Color.TRANSPARENT);
        overlayedTransparent.setOnClickListener(this);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.LEFT | Gravity.TOP;
        params.x = 150;
        params.y = 470;
        params.width = 100;

        WindowManager.LayoutParams params2 = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);
        params2.gravity = Gravity.LEFT | Gravity.TOP;
        params2.x = 300;
        params2.y = 440;
        //params.width = 100;

        WindowManager.LayoutParams params_trans = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);
        params_trans.gravity = Gravity.LEFT | Gravity.TOP;
        params_trans.x = 43;
        params_trans.y = 550;
        params_trans.width = 650;
        params_trans.height = 80;

        wm.addView(image, params);
        wm.addView(overlayedText,params2);
        wm.addView(overlayedTransparent, params_trans);



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
        if (overlayedButton != null) {
            wm.removeView(overlayedButton);
            wm.removeView(topLeftView);
            overlayedButton = null;
            topLeftView = null;
        }
    }
    @Override
    public void onClick(View v) {
        wm.removeView(overlayedTransparent);

    }


    @Override
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
    }
}
