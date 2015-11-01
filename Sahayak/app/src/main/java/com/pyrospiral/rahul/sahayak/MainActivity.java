package com.pyrospiral.rahul.sahayak;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String PREF_FILE = "testPrefs";

    Intent mIntent;
    int doing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mIntent = new Intent(getApplicationContext(), OverlayObjects.class);


        String check=null;
        Intent intent = this.getIntent();

        if(intent != null && intent.hasExtra(Intent.EXTRA_TEXT))
        {
            check =intent.getStringExtra(Intent.EXTRA_TEXT);
            Toast.makeText(this, check, Toast.LENGTH_SHORT).show();
        }




        final ListView list = (ListView) findViewById(R.id.ListView);

        String[] titles = {"इंटरनेट का उपयोग", "sms का उपयोग","परिगनक का उपयोग","कैलेंडर का उपयोग", "नक्शे का उपयोग"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.single_row, R.id.title, titles);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;

                // ListView Clicked item value
                String  itemValue    = (String) list.getItemAtPosition(position);
                String key = "123";

                Intent mIntent = new Intent(getApplicationContext(), OverlayObjects.class);
                mIntent.putExtra(key, "3#1#1#300#930#200#930#550#90#44#1000$1#2#300#350#10#400#600#340#10#700$1#1#10#350#500#400#80#70#600#470$");
                startService(mIntent);

                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("smsto:9408748316"));
                startActivity(sendIntent);
            }
        });


        saveToPreferences(getApplicationContext(), "Refresh_State_Controller", "0");



//        Intent svc = new Intent(this, OverlayObjects.class);
//        startService(svc);
//        finish();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mIntent = new Intent(getApplicationContext(), TutorialService.class);
                startService(mIntent);
                finish();

               /* Intent intent = new Intent(getApplicationContext(), TutorialActivity.class);
                startActivity(intent);
                Intent intent = new Intent(getApplicationContext(), TutorialActivity.class);
                startActivity(intent);*/
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          //              .setAction("Action", null).show();
            }
        });


    }

    @Override
    public void onResume() {

        super.onResume();
        doing=Integer.parseInt(readFromPreferences(getApplicationContext(), "Refresh_State_Controller",""));

        if(doing==1)
        {
            finish();
        }


    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(preferenceName, preferenceValue);
        edit.apply();
    }


    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void onClick(View v) {

    }

}
