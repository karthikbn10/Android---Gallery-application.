package com.example.android.galleryapp;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karthik on 4/5/2017.
 */

public class CustomSwipeAdapter extends PagerAdapter {

    public CustomSwipeAdapter(Context ctx, String file)

    {
        this.ctx = ctx;


        String fl = file.substring(6,8).toLowerCase();

        Field[] fields = R.drawable.class.getFields();
        List<Integer> drawables = new ArrayList<Integer>();
        for (Field field : fields) {

            if (field.getName().startsWith(fl)) {
                try {
                    drawables.add(field.getInt(null));
                } catch (Exception e) {
                    Log.w("gallery app", "exception");
                }
            }
        }
        Integer[] wrapperArr = drawables.toArray(new Integer[drawables.size()]);
        images = ArrayUtils.toPrimitive(wrapperArr);

    }

    private Context ctx;
    private LayoutInflater layoutInflater;
    int[] images;

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view ==(LinearLayout)object  ;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swiplelayout,container,false);
        ImageView imageView = (ImageView)item_view.findViewById(R.id.image_view);
        imageView.setImageResource(images[position]);
        container.addView(item_view);


        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}

