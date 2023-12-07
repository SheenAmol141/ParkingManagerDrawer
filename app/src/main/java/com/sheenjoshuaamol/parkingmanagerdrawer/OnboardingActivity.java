package com.sheenjoshuaamol.parkingmanagerdrawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sheenjoshuaamol.parkingmanagerdrawer.ui.ObAdapter;

public class OnboardingActivity extends AppCompatActivity {


    private ViewPager pager;

    CardView nextBT;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        getSupportActionBar().hide();



//PAGER ------------------------------------------------------------------------------------------------------------------------------------------------------
        pager = findViewById(R.id.vpOnboard);
        ObAdapter ob = new ObAdapter(this);
        pager.setAdapter(ob);
//PAGER ------------------------------------------------------------------------------------------------------------------------------------------------------

//NEXTPAGEFUNCITONALITY ---------------------------------------------------------------------------------------------------------------------------------
        nextBT = findViewById(R.id.cdNextOnboard);
        nextBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(1, true);
            }
        });
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                nextBT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position < 2) {
                            pager.setCurrentItem(position + 1, true);
                        } else {
                            startActivity(new Intent(OnboardingActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//NEXTPAGEFUNCITONALITY ---------------------------------------------------------------------------------------------------------------------------------






    }
}