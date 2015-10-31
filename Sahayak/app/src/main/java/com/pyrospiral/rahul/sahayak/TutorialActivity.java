package com.pyrospiral.rahul.sahayak;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class TutorialActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {



    private float offsetX;
    private float offsetY;
    private int originalXPos;
    private int originalYPos;
    private boolean moving;
    private WindowManager wm;
    private View oView;

    private Button mNextButton;
    private Button mEndButton;
    private Button mPlaceButton;
    private LinearLayout mButtonBar;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        mButtonBar = new LinearLayout(this);
        mButtonBar.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        mNextButton  = new Button(this);
        mNextButton.setText("Next");
        params.gravity = Gravity.RIGHT;
        mNextButton.setLayoutParams(params);
        mEndButton  = new Button(this);
        mEndButton.setText("End Tutorial");
        params.gravity = Gravity.LEFT;
        mEndButton.setLayoutParams(params);
        mPlaceButton  = new Button(this);
        mPlaceButton.setText("Place Arrow");
        params.gravity = Gravity.CENTER;
        mPlaceButton.setLayoutParams(params);

        mButtonBar.addView(mEndButton);
        mButtonBar.addView(mNextButton);
        mButtonBar.addView(mPlaceButton);

        oView = new LinearLayout(this);
        oView.setBackgroundColor(0x88ff0000); // The translucent red color

        WindowManager.LayoutParams ALERTparams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                0 | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);


        WindowManager.LayoutParams OVERLAYparams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                0 | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        WindowManager.LayoutParams BUTTONBARparams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                0 | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        BUTTONBARparams.gravity = Gravity.BOTTOM;

        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        wm.addView(oView, ALERTparams);
        //wm.addView(mButtonBar, BUTTONBARparams);

        oView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){

                    Log.e("  "+ event.getX(),"   "+event.getY());
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tutorial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        Log.e("SADKJSAD","sad");

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getRawX();
            float y = event.getRawY();

            Log.e("Tut Activity ",x+"  "+y);


        }

        else if (event.getAction() == MotionEvent.ACTION_UP) {
            return true;
        }
        return false;
    }
}
