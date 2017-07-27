package com.example.android.galleryapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView simpleListView;
    String Result;
    String[] directories = {"Animals", "Architecture", "Food", "Posters", "Scenery"};
    int[] images = {R.drawable.animals1, R.drawable.architecture1, R.drawable.food1, R.drawable.posters1, R.drawable.scenery1};
    ViewPager viewPager;
    CustomSwipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        simpleListView = (ListView) findViewById(R.id.simpleListView);


        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        for (int i = 0; i < directories.length; i++) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("name", directories[i]);
            hashMap.put("image", images[i] + "");
            arrayList.add(hashMap);
        }
        String[] from = {"name", "image"};
        int[] to = {R.id.textView, R.id.ImageView};
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, arrayList, R.layout.custom_list_items, from, to);
        simpleListView.setBackgroundResource(R.drawable.customshape);


        simpleListView.setAdapter(simpleAdapter);


        simpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent k = new Intent(MainActivity.this, DisplayActivity.class);
                k.putExtra("name", directories[i]);
                startActivity(k);
            }
        });

        simpleListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           int index, long arg3) {
                // TODO Auto-generated method stub

                Result = simpleListView.getItemAtPosition(index).toString();
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.viewpagerlayout);


                viewPager = (ViewPager) dialog.findViewById(R.id.view_pager);
                adapter = new CustomSwipeAdapter(MainActivity.this, Result);
                viewPager.setAdapter(adapter);
                dialog.show();


                return true;
            }
        });


    }


}