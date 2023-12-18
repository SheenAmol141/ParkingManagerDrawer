package com.sheenjoshuaamol.parkingmanagerdrawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sheenjoshuaamol.parkingmanagerdrawer.ui.ObAdapter;

public class OnboardingActivity extends AppCompatActivity {


    private ViewPager pager;

    CardView nextBT;
    private LinearLayout dotslayout;
    private ImageView arrow;





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
        arrow = findViewById(R.id.arrow);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (position < 2) {
                    arrow.setImageResource(R.drawable.baseline_chevron_right_24);
                } else {
                    arrow.setImageResource(R.drawable.baseline_keyboard_double_arrow_right_24);
                }

                dotsFunction(position);
                nextBT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position < 2) {
                            pager.setCurrentItem(position + 1, true);
                        } else {
                            startActivity(new Intent(OnboardingActivity.this, SwitchMode.class));
                            SharedPreferences SP = getSharedPreferences("First Open", MODE_PRIVATE);
                            SP.edit().putBoolean("opened", true).apply();
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

//DOTS ---------------------------------------------------------------------------------------------------------------------------------
        dotslayout = findViewById(R.id.dots);
        dotsFunction(0);
    }


    private void dotsFunction(int pos) {
        TextView[] dots = new TextView[3];
            dotslayout.removeAllViews();
            for (int i = 0; i < dots.length ; i ++) {
                dots[i] = new TextView(this);
                dots[i].setText(Html.fromHtml("."));
                dots[i].setTextColor(getColor(R.color.ashgrey));
                dots[i].setTextSize(100);
                dotslayout.addView(dots[i]);
            }
            if (dots.length > 0) {
                dots[pos].setTextColor(getColor(R.color.mainblue));
                dots[pos].setTextSize(100);
            }
    }
    //DOTS ---------------------------------------------------------------------------------------------------------------------------------
}