package com.sheenjoshuaamol.parkingmanagerdrawer.ui;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ObAdapter  extends PagerAdapter {

    private Context context;




    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}
