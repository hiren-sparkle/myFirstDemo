package com.sparkle.countrypicker;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.sparkle.countrypicker.Models.PhoneNumber;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Admin on 23-01-2018.
 */

public class ConstantMethod {

    private static final String ImagePath = "http://midlal.com/";
    public static final String URL = "https://midlal.com/prowebservice";

    public static String ConvertProperUl(String url) {
        if (url != null && url.length() > 5) {
            String substr = url.substring(0, 4);
            if (substr.equals("http"))
                return url;
            else
                return ImagePath + url;
        } else
            return "";
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    public static void replaceFragment(FragmentManager manager, Fragment fragment, int view) {
        String fragmentTag = fragment.getClass().getName();

        boolean fragmentPopped;
        try {
            fragmentPopped = manager.popBackStackImmediate(fragmentTag, 0);
        } catch (Exception e) {
            fragmentPopped = false;
        }
        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null) {
            FragmentTransaction ft = manager.beginTransaction();
//            ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.left_to_right,
//                    R.anim.right_to_left);
            ft.replace(view, fragment, fragmentTag);
            ft.addToBackStack(fragmentTag);
            ft.commit();
        }
    }

    public static void expand(final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }


    public static List<PhoneNumber> GetNumberList(String phoneNumber) {
        List<PhoneNumber> numbers = new ArrayList<>();
        if (phoneNumber == null) {
            numbers.add(new PhoneNumber("", ""));
            return numbers;
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(phoneNumber);
            Iterator<String> iter = jsonObject.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                numbers.add(new PhoneNumber(key, jsonObject.getString(key)));

            }
        } catch (JSONException e) {
            numbers.add(new PhoneNumber("Contacts", phoneNumber));
            return numbers;
        }

        return numbers;
    }

    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
