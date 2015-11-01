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
import android.widget.Button;
import android.widget.Toast;

public class OverlayObjects extends Service implements View.OnTouchListener, View.OnClickListener {


    int counter_main=0,counter;
    String from;
    private View topLeftView;
    int coordis[][]=new int[100][100];
    private Button overlayedButton, overlayedText, overlayedTransparent;
    private float offsetX;
    private float offsetY;
    private int originalXPos;
    private int originalYPos;
    private boolean moving;
    private WindowManager wm;
    String message;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        //Toast.makeText(this, "Starting..", Toast.LENGTH_SHORT).show();
        try {
            message = intent.getStringExtra("123");
        }
        catch (NullPointerException e)
        {
            message = "3";
        }

        String ChromeTut=message;
        counter=ChromeTut.charAt(0)-48;
        int k=0;

        /*
        Arrow direction
        Text
        Text x
        Text y
        Arrow X
        Arrow Y
        Transparent Width
        Transparent height
        Trnasparent X
        Transparent Y
         */



        int j=0;
        int val=0;
        for(int i=2;i<ChromeTut.length();i++){

            if(ChromeTut.charAt(i)=='$') {
                coordis[k][j]=val;
                val=0;
                k++;
                j=0;
            }
            else if(ChromeTut.charAt(i)=='#'){
                coordis[k][j]=val;
                j++;
                val=0;

            }
            else
            {
                val=val*10+(ChromeTut.charAt(i)-48);
            }

        }
        for(int i=0;i<3;i++){

            for(j=0;j<10;j++){
                System.out.print(""+coordis[i][j]+" ");

            }
            System.out.println("");
        }







        /*Intent myIntent = getIntent(); // this getter is just for example purpose, can differ
        if (myIntent !=null && myIntent.getExtras()!=null)
            String value = myIntent.getExtras().getString(key);*/

        wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);


        overlayedButton = new Button(this);
        overlayedButton.setAlpha(0.8f);
        overlayedButton.setBackground(getResources().getDrawable(R.drawable.arrow_down));
        if(coordis[0][0]==1)
            overlayedButton.setRotation(180);
        else
            overlayedButton.setRotation(0);

        overlayedText = new Button(this);
        overlayedText.setText(R.string.hindi);
        overlayedText.setTextSize(20);
        overlayedText.setAlpha(0.5f);
        overlayedText.setBackgroundColor(0x88ffffff);
        overlayedText.setTextColor(getResources().getColor(R.color.black));

        overlayedTransparent = new Button(this);
        overlayedTransparent.setBackgroundColor(Color.TRANSPARENT);
        // overlayedTransparent.setBackgroundColor(Color.BLACK);
        overlayedTransparent.setOnClickListener(this);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.LEFT | Gravity.TOP;
        params.x = coordis[0][4];
        params.y = coordis[0][5];
        params.width = 100;

        WindowManager.LayoutParams params2 = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);
        params2.gravity = Gravity.LEFT | Gravity.TOP;
        params2.x = coordis[0][2];
        params2.y = coordis[0][3];


        WindowManager.LayoutParams params_trans = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);
        params_trans.gravity = Gravity.LEFT | Gravity.TOP;
        params_trans.x = coordis[0][8];
        params_trans.y = coordis[0][9];
        params_trans.width = coordis[0][6];
        params_trans.height = coordis[0][7];

        wm.addView(overlayedButton, params);
        wm.addView(overlayedText,params2);
        wm.addView(overlayedTransparent, params_trans);

        return START_STICKY; // or whatever your flag
    }

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





        /*
        topLeftView = new View(this);
        WindowManager.LayoutParams topLeftParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);
        topLeftParams.gravity = Gravity.LEFT | Gravity.TOP;
        topLeftParams.x = 0;
        topLeftParams.y = 0;
        topLeftParams.width = 0;
        topLeftParams.height = 0;
        wm.addView(topLeftView, topLeftParams);
        */

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

        counter_main++;
        if(counter_main>=counter)
        {
            Toast.makeText(getApplicationContext(),"HELLO",Toast.LENGTH_SHORT).show();
            wm.removeView(overlayedTransparent);
            wm.removeView(overlayedText);
            wm.removeView(overlayedButton);
            counter_main=0;
        }
        else {
            WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);
            params.gravity = Gravity.LEFT | Gravity.TOP;

            overlayedButton.setAlpha(0.8f);
            overlayedButton.setBackground(getResources().getDrawable(R.drawable.arrow_down));
            if (coordis[counter_main][0] == 1)
                overlayedButton.setRotation(180);
            else
                overlayedButton.setRotation(0);


            params.x = coordis[counter_main][4];
            params.y = coordis[counter_main][5];
            wm.updateViewLayout(overlayedButton, params);


            WindowManager.LayoutParams params2 = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);
            params2.gravity = Gravity.LEFT | Gravity.TOP;
            overlayedText.setText(R.string.hindi2);
            overlayedText.setTextSize(30);
            overlayedText.setAlpha(1f);
            overlayedText.setBackgroundColor(getResources().getColor(R.color.white));
            overlayedText.setTextColor(getResources().getColor(R.color.black));
            params2.x = coordis[counter_main][2];
            params2.y = coordis[counter_main][3];
            Toast.makeText(OverlayObjects.this, Integer.toString(counter_main), Toast.LENGTH_SHORT).show();
            wm.updateViewLayout(overlayedText, params2);

            WindowManager.LayoutParams params3= new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);
            params3.gravity = Gravity.LEFT | Gravity.TOP;
            overlayedTransparent.setBackgroundColor(Color.TRANSPARENT);
            //  overlayedTransparent.setBackgroundColor(Color.BLACK);
            overlayedTransparent.setOnClickListener(this);
            params3.width = coordis[counter_main][6];
            params3.height = coordis[counter_main][7];
            params3.x = coordis[counter_main][8];
            params3.y = coordis[counter_main][9];

            wm.updateViewLayout(overlayedTransparent, params3);
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return false;
    }
}