package com.sparkle.countrypicker;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sparkle.countrypicker.Models.FirstFragment;

public class FrameDemoActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_first, btn_second, btn_third;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_demo);

        btn_first = findViewById(R.id.btn_first);
        btn_second = findViewById(R.id.btn_second);
        btn_third = findViewById(R.id.btn_third);

        btn_first.setOnClickListener(this);
        btn_second.setOnClickListener(this);
        btn_third.setOnClickListener(this);
        setFirstFragment();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            int index = getSupportFragmentManager().getBackStackEntryCount() - 2;
            FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(index);
            String tag = backEntry.getName();
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
            if (fragment != null) {
                Class name = fragment.getClass();
                if (name == FirstFragment.class) {
                    setFirstFragment();
                } else if (name == SecondFragment.class) {
                    setSecondFragment();
                } else if (name == ThirdFragment.class) {
                    setThirdFragment();
                }

            }
        } else
            finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_first:
                setFirstFragment();
                break;
            case R.id.btn_second:
                setSecondFragment();
                break;
            case R.id.btn_third:
                setThirdFragment();
                break;
        }

    }

    public void setFirstFragment() {

        FirstFragment firstFragment = FirstFragment.newInstance("First one");
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(firstFragment.getClass().getName());
        if (fragment != null) {
            getSupportFragmentManager().popBackStackImmediate(firstFragment.getClass().getName(), 0);
        } else {
            ConstantMethod.replaceFragment(getSupportFragmentManager(), firstFragment, R.id.content_frame);
        }
    }

    public void setSecondFragment() {
        SecondFragment secondFragment = SecondFragment.newInstance("Second one");
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(secondFragment.getClass().getName());
        if (fragment != null) {
            getSupportFragmentManager().popBackStackImmediate(secondFragment.getClass().getName(), 0);
        } else {
            ConstantMethod.replaceFragment(getSupportFragmentManager(), secondFragment, R.id.content_frame);
        }
    }

    public void setThirdFragment() {
        ThirdFragment thirdFragment = ThirdFragment.newInstance("Third one");
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(thirdFragment.getClass().getName());
        if (fragment != null) {
            getSupportFragmentManager().popBackStackImmediate(thirdFragment.getClass().getName(), 0);
        } else {
            ConstantMethod.replaceFragment(getSupportFragmentManager(), thirdFragment, R.id.content_frame);
        }
    }
}
