package com.example.android.galleryapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.R.id.list;
import static com.example.android.galleryapp.R.id.simpleListView;

public class DisplayActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
          Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        }

        ListView secondListView;

        Intent intent = getIntent();
        String directory = intent.getStringExtra("name").toLowerCase();


        Field[] fields = R.drawable.class.getFields();
        List<Integer> drawables = new ArrayList<Integer>();
        for (Field field : fields) {

            if (field.getName().startsWith(directory)) {
                try
                {
                drawables.add(field.getInt(null));
            }
                catch (Exception e)
                {
                    Log.w("gallery app","exception");
                }
        }

    }

        String[] fileName = new String[drawables.size()];
        for(int j=1,i=0;i<drawables.size();i++) {

            fileName[i] = j + ".jpg";
            j = j+1;

        }
        Integer[] wrapperArr = drawables.toArray(new Integer[drawables.size()]);
        int[] images = ArrayUtils.toPrimitive(wrapperArr);
        Log.w("gallery app",drawables.toString());


        secondListView=(ListView)findViewById(R.id.second_list);
        secondListView.setOnItemClickListener(null);
        ArrayList<HashMap<String,String>> arrayList=new ArrayList<>();
        for (int i=0;i<fileName.length;i++)
        {
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("name",fileName[i]);
            hashMap.put("image",images[i]+"");
            arrayList.add(hashMap);
        }
        String[] from={"name","image"};//string array
        int[] to={R.id.second_text,R.id.second_image};
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,arrayList,R.layout.second_list_items,from,to);
        secondListView.setAdapter(simpleAdapter);


}
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }


}
