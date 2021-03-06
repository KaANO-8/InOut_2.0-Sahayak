package com.pyrospiral.android.templateapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class AppDrawer extends ActionBarActivity {

    private RecyclerView mrecyclerView;
    private RecyclerViewAdapter mAdapter;
    private List<RecyclerData> data;
    private PackageManager manager;
    private List<RecyclerData> apps;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_drawer);
        data = new ArrayList<>();
        manager = getPackageManager();
        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = manager.queryIntentActivities(i, 0);
        for(ResolveInfo ri:availableActivities){
            RecyclerData app = new RecyclerData();
            app.label = ri.loadLabel(manager);
            app.name = ri.activityInfo.packageName;
            app.icon = ri.activityInfo.loadIcon(manager);
            data.add(app);
        }


        mrecyclerView = (RecyclerView) findViewById(R.id.list);
        mrecyclerView.setHasFixedSize(true);
        mAdapter = new RecyclerViewAdapter(this,data);
        mrecyclerView.setAdapter(mAdapter);

        //Staggered Grid Layout Manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //mrecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mrecyclerView.setLayoutManager(linearLayoutManager);
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());

        int id=0;

        for(RecyclerData dat:data)
        {
            if(dat.name == "com.google.android.music")
            {
                //asd
            }
        }

        linearLayoutManager.scrollToPositionWithOffset(5, 20);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_app_drawer, menu);
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





    public class RecyclerData {
        CharSequence label;
        CharSequence name;
        Drawable icon;
    }
}
