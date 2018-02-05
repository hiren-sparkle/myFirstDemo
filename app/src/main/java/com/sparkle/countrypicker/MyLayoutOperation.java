package com.sparkle.countrypicker;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 31-01-2018.
 */

public class MyLayoutOperation {
    private Activity mActivity;

    public MyLayoutOperation(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public enum SocialType {
        FACEBOOK,
        INSTAGRAM,
        TWITTER,
        LINKEDIN
    }

    public void addPhone(final LinearLayout ll_phone_editor, String label, String countryCode, String phoneNuber) {

        int margin_16 = ConstantMethod.dpToPx(mActivity, 16);
        final RelativeLayout phoneView = (RelativeLayout) mActivity.getLayoutInflater().inflate(R.layout.item_edit_phone, null);
        LinearLayout.LayoutParams myParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        myParams.setMargins(0, margin_16, 0, 0);
        phoneView.setLayoutParams(myParams);

        EditText et_label_phone = (EditText) phoneView.findViewById(R.id.et_label_phone);
        TextView tv_country_code = (TextView) phoneView.findViewById(R.id.tv_country_code);
        EditText et_phone_number = (EditText) phoneView.findViewById(R.id.et_phone_number);

        et_label_phone.setText(label);
        tv_country_code.setText(countryCode);
        et_phone_number.setText(phoneNuber);

        ImageButton btnRemove = (ImageButton) phoneView.findViewById(R.id.cancel_phone);
        btnRemove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ll_phone_editor.removeView(phoneView);
                updateIndex(ll_phone_editor, SecondFragment.SelectedView.PHONE);
            }
        });

        ll_phone_editor.addView(phoneView);
        updateIndex(ll_phone_editor, SecondFragment.SelectedView.PHONE);

    }

    public String getPhone(LinearLayout ll_phone_editor) {
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < ll_phone_editor.getChildCount(); i++) {
            RelativeLayout innerLayout = (RelativeLayout) ll_phone_editor.getChildAt(i);
            EditText et_label_phone = (EditText) innerLayout.findViewById(R.id.et_label_phone);
            TextView tv_country_code = (TextView) innerLayout.findViewById(R.id.tv_country_code);
            EditText et_phone_number = (EditText) innerLayout.findViewById(R.id.et_phone_number);
            String key = et_label_phone.getText().toString().trim();
            String value = tv_country_code.getText().toString().trim() + et_phone_number.getText().toString().trim();

            if (key.isEmpty()) {
                key = "Contacts";
            }
            try {
                jsonObject.put(key, value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject.toString();

    }


    public void addWhatsapp(final LinearLayout ll_whatsapp_editor, String countryCode, String phoneNumber) {

        int margin_16 = ConstantMethod.dpToPx(mActivity, 16);
        final RelativeLayout whatsappView = (RelativeLayout) mActivity.getLayoutInflater().inflate(R.layout.item_edit_whatsapp, null);
        LinearLayout.LayoutParams myParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        myParams.setMargins(0, margin_16, 0, 0);
        whatsappView.setLayoutParams(myParams);

        TextView tv_country_code_whatsapp = (TextView) whatsappView.findViewById(R.id.tv_country_code_whatsapp);
        EditText et_phone_number_whatsapp = (EditText) whatsappView.findViewById(R.id.et_phone_number_whatsapp);

        tv_country_code_whatsapp.setText(countryCode);
        et_phone_number_whatsapp.setText(phoneNumber);
        ImageButton btnRemove = (ImageButton) whatsappView.findViewById(R.id.cancel_whatsapp);
        btnRemove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ll_whatsapp_editor.removeView(whatsappView);
                updateIndex(ll_whatsapp_editor, SecondFragment.SelectedView.WHATSAPP);
            }
        });

        ll_whatsapp_editor.addView(whatsappView);
        updateIndex(ll_whatsapp_editor, SecondFragment.SelectedView.WHATSAPP);

    }

    public String getWhatsapp(LinearLayout ll_whatsapp_editor) {
//        List<String> whatsappList = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < ll_whatsapp_editor.getChildCount(); i++) {
            RelativeLayout innerLayout = (RelativeLayout) ll_whatsapp_editor.getChildAt(i);
            TextView tv_country_code_whatsapp = (TextView) innerLayout.findViewById(R.id.tv_country_code_whatsapp);
            EditText et_phone_number_whatsapp = (EditText) innerLayout.findViewById(R.id.et_phone_number_whatsapp);

            String whatsappNumber = tv_country_code_whatsapp.getText().toString() + et_phone_number_whatsapp.getText().toString();
            try {
                jsonObject.put("" + i, whatsappNumber);
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            whatsappList.add(whatsappNumber);
        }

        return jsonObject.toString();


    }

    public void addViber(final LinearLayout ll_viber_editor, String countryCode, String phoneNumber) {

        int margin_16 = ConstantMethod.dpToPx(mActivity, 16);
        final RelativeLayout viberView = (RelativeLayout) mActivity.getLayoutInflater().inflate(R.layout.item_edit_viber, null);
        LinearLayout.LayoutParams myParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        myParams.setMargins(0, margin_16, 0, 0);
        viberView.setLayoutParams(myParams);

        TextView tv_country_code_viber = (TextView) viberView.findViewById(R.id.tv_country_code_viber);
        EditText et_phone_number_viber = (EditText) viberView.findViewById(R.id.et_phone_number_viber);

        tv_country_code_viber.setText(countryCode);
        et_phone_number_viber.setText(phoneNumber);

        ImageButton btnRemove = (ImageButton) viberView.findViewById(R.id.cancel_viber);
        btnRemove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ll_viber_editor.removeView(viberView);
                updateIndex(ll_viber_editor, SecondFragment.SelectedView.VIBER);
            }
        });

        ll_viber_editor.addView(viberView);
        updateIndex(ll_viber_editor, SecondFragment.SelectedView.VIBER);

    }

    public String getVibers(LinearLayout ll_viber_editor) {
//        List<String> vibers = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < ll_viber_editor.getChildCount(); i++) {
            RelativeLayout innerLayout = (RelativeLayout) ll_viber_editor.getChildAt(i);

            TextView tv_country_code_viber = (TextView) innerLayout.findViewById(R.id.tv_country_code_viber);
            EditText et_phone_number_viber = (EditText) innerLayout.findViewById(R.id.et_phone_number_viber);

            String whatsappNumber = tv_country_code_viber.getText().toString() + et_phone_number_viber.getText().toString();
            try {
                jsonObject.put("" + i, whatsappNumber);
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            vibers.add(whatsappNumber);
        }
        return jsonObject.toString();

    }

    public void addWebsite(final LinearLayout ll_website_editor, String webURL) {

        int margin_16 = ConstantMethod.dpToPx(mActivity, 16);
        final RelativeLayout websiteView = (RelativeLayout) mActivity.getLayoutInflater().inflate(R.layout.item_edit_website, null);
        LinearLayout.LayoutParams myParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        myParams.setMargins(0, margin_16, 0, 0);
        websiteView.setLayoutParams(myParams);

        EditText et_website = (EditText) websiteView.findViewById(R.id.et_website);

        et_website.setText(webURL);

        ImageButton btnRemove = (ImageButton) websiteView.findViewById(R.id.cancel_website);
        btnRemove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ll_website_editor.removeView(websiteView);
                updateIndex(ll_website_editor, SecondFragment.SelectedView.WEBSITE);
            }
        });

        ll_website_editor.addView(websiteView);
        updateIndex(ll_website_editor, SecondFragment.SelectedView.WEBSITE);


    }

    public String getWebsites(LinearLayout ll_website_editor) {
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < ll_website_editor.getChildCount(); i++) {
            RelativeLayout innerLayout = (RelativeLayout) ll_website_editor.getChildAt(i);
            EditText et_website = (EditText) innerLayout.findViewById(R.id.et_website);

            try {
                jsonObject.put("" + i, et_website.getText().toString().trim());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject.toString();
    }

    public void addEmail(final LinearLayout ll_email_editor, String email) {

        int margin_16 = ConstantMethod.dpToPx(mActivity, 16);
        final RelativeLayout websiteView = (RelativeLayout) mActivity.getLayoutInflater().inflate(R.layout.item_edit_email, null);
        LinearLayout.LayoutParams myParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        myParams.setMargins(0, margin_16, 0, 0);
        websiteView.setLayoutParams(myParams);


        EditText et_email = (EditText) websiteView.findViewById(R.id.et_email);
        et_email.setText(email);
        ImageButton btnRemove = (ImageButton) websiteView.findViewById(R.id.cancel_email);
        btnRemove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ll_email_editor.removeView(websiteView);
                updateIndex(ll_email_editor, SecondFragment.SelectedView.EMAIL);
            }
        });

        ll_email_editor.addView(websiteView);
        updateIndex(ll_email_editor, SecondFragment.SelectedView.EMAIL);
    }

    public String getEmails(LinearLayout ll_email_editor) {
//        List<String> emails = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < ll_email_editor.getChildCount(); i++) {
            RelativeLayout innerLayout = (RelativeLayout) ll_email_editor.getChildAt(i);
            EditText et_email = (EditText) innerLayout.findViewById(R.id.et_email);
            try {
                jsonObject.put("" + i, et_email.getText().toString().trim());
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            emails.add(et_email.getText().toString().trim());
        }
        return jsonObject.toString();
    }

    public void addSocial(final LinearLayout ll_email_editor, SocialType socialType, String socialURL) {
        int margin_16 = ConstantMethod.dpToPx(mActivity, 16);
        final RelativeLayout websiteView = (RelativeLayout) mActivity.getLayoutInflater().inflate(R.layout.item_edit_social, null);
        LinearLayout.LayoutParams myParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        myParams.setMargins(0, margin_16, 0, 0);
        websiteView.setLayoutParams(myParams);

        final TextView tv_social_select = (TextView) websiteView.findViewById(R.id.tv_social_select);
        EditText et_social = (EditText) websiteView.findViewById(R.id.et_social);
        et_social.setText(socialURL);

        tv_social_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSocialSelection(tv_social_select);
            }
        });

        switch (socialType) {
            case FACEBOOK:
                tv_social_select.setText("Facebook");
                break;
            case TWITTER:
                tv_social_select.setText("Twitter");
                break;
            case LINKEDIN:
                tv_social_select.setText("LinkedIn");
                break;
            case INSTAGRAM:
                tv_social_select.setText("Instagram");
                break;
        }


        ImageButton btnRemove = (ImageButton) websiteView.findViewById(R.id.cancel_social);
        btnRemove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ll_email_editor.removeView(websiteView);
                updateIndex(ll_email_editor, SecondFragment.SelectedView.SOCIAL);
            }
        });

        ll_email_editor.addView(websiteView);
        updateIndex(ll_email_editor, SecondFragment.SelectedView.SOCIAL);
    }

    private void showSocialSelection(final TextView tv_social_select) {
        final String[] socials = {
                "Facebook",
                "Twitter",
                "Instagram",
                "LinkedIn"
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("Select a social account");
        builder.setItems(socials, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv_social_select.setText(socials[which]);
                dialog.dismiss();
            }
        });
        builder.show();
    }


    public Map<String, String> getSocial(LinearLayout ll_social_editor) {
        Map<String, String> myMap = new HashMap<>();

        JSONObject fbObject = new JSONObject();
        JSONObject twObject = new JSONObject();
        JSONObject liObject = new JSONObject();
        JSONObject igObject = new JSONObject();
        int fbPos = 0;
        int twPos = 0;
        int liPos = 0;
        int igPos = 0;

        for (int i = 0; i < ll_social_editor.getChildCount(); i++) {
            RelativeLayout innerLayout = (RelativeLayout) ll_social_editor.getChildAt(i);
            TextView tv_social_select = (TextView) innerLayout.findViewById(R.id.tv_social_select);
            EditText et_social = (EditText) innerLayout.findViewById(R.id.et_social);
            String socialType = tv_social_select.getText().toString();
            try {
                switch (socialType) {
                    case "Facebook":
                        fbObject.put("" + fbPos, et_social.getText().toString().trim());
                        fbPos++;
                        break;
                    case "Twitter":
                        twObject.put("" + twPos, et_social.getText().toString().trim());
                        twPos++;
                        break;
                    case "LinkedIn":
                        liObject.put("" + liPos, et_social.getText().toString().trim());
                        liPos++;
                        break;
                    case "Instagram":
                        igObject.put("" + igPos, et_social.getText().toString().trim());
                        igPos++;
                        break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        myMap.put("facebook", fbObject.toString());
        myMap.put("twitter", twObject.toString());
        myMap.put("linkedin", liObject.toString());
        myMap.put("instagram", igObject.toString());
        return myMap;
    }

    private void updateIndex(LinearLayout ll_phone_editor, SecondFragment.SelectedView selectedView) {
        for (int i = 0; i < ll_phone_editor.getChildCount(); i++) {
            RelativeLayout innerLayout = (RelativeLayout) ll_phone_editor.getChildAt(i);
            TextView tv_index_phone = null;
            switch (selectedView) {
                case PHONE:
                    tv_index_phone = (TextView) innerLayout.findViewById(R.id.tv_index_phone);
                    break;
                case WHATSAPP:
                    tv_index_phone = (TextView) innerLayout.findViewById(R.id.tv_index_whatsapp);
                    break;
                case VIBER:
                    tv_index_phone = (TextView) innerLayout.findViewById(R.id.tv_index_viber);
                    break;
                case WEBSITE:
                    tv_index_phone = (TextView) innerLayout.findViewById(R.id.tv_index_website);
                    break;
                case EMAIL:
                    tv_index_phone = (TextView) innerLayout.findViewById(R.id.tv_index_email);
                    break;
                case SOCIAL:
                    tv_index_phone = (TextView) innerLayout.findViewById(R.id.tv_index_social);
                    break;
            }

            tv_index_phone.setText("" + (i + 1));
        }
    }
}
