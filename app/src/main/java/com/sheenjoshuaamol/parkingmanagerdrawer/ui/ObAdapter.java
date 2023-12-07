package com.sheenjoshuaamol.parkingmanagerdrawer.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.sheenjoshuaamol.parkingmanagerdrawer.R;

public class ObAdapter  extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;

    public ObAdapter (Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    private int[] titles = {
            R.string.onboard_title_1,
            R.string.onboard_title_2,
            R.string.onboard_title_3
    };

    private int[] desc = {
            R.string.onboard_desc_1,
            R.string.onboard_desc_2,
            R.string.onboard_desc_3
    };



    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {



        return view == (ConstraintLayout) object;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    container.removeView((ConstraintLayout) object);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = inflater.inflate(R.layout.onboard_page, container, false);

        TextView tvtitle, tvdesc;
        tvtitle = v.findViewById(R.id.title);
        tvdesc = v.findViewById(R.id.desc);


        tvtitle.setText(titles[position]);
        tvdesc.setText(desc[position]);


        container.addView(v);
        return v;
    }
}
