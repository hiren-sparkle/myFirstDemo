package com.sparkle.countrypicker;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sparkle.countrypicker.Models.BusinessDetails;
import com.sparkle.countrypicker.Models.PhoneNumber;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SecondFragment extends Fragment implements View.OnClickListener, VolleyResponseListener {

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    //phone
    private RelativeLayout rl_phone_header;
    private ImageButton indicator_phone;
    private ImageButton btn_add_phone;
    private Button btn_save_phone;
    private LinearLayout ll_phone_editor;
    private TextView tv_index_phone;
    private EditText et_label_phone;
    private RelativeLayout rl_country_code;
    private ImageView img_flag_country;
    private TextView tv_country_code;
    private EditText et_phone_number;
    private ImageButton cancel_phone;

    //whatsapp
    private RelativeLayout rl_whatsapp_header;
    private ImageButton indicator_whatsapp;
    private ImageButton btn_add_whatsapp;
    private Button btn_save_whatsapp;
    private LinearLayout ll_whatsapp_editor;
    private TextView tv_index_whatsapp;
    private ImageView img_flag_country_whatsapp;
    private TextView tv_country_code_whatsapp;
    private EditText et_phone_number_whatsapp;
    private ImageButton cancel_whatsapp;

    //viber
    private RelativeLayout rl_viber_header;
    private ImageButton indicator_viber;
    private ImageButton btn_add_viber;
    private Button btn_save_viber;
    private LinearLayout ll_viber_editor;
    private TextView tv_index_viber;
    private ImageView img_flag_country_viber;
    private TextView tv_country_code_viber;
    private EditText et_phone_number_viber;
    private ImageButton cancel_viber;

    //website
    private RelativeLayout rl_website_header;
    private ImageButton indicator_website;
    private ImageButton btn_add_website;
    private Button btn_save_website;
    private LinearLayout ll_website_editor;
    private TextView tv_index_website;
    private EditText et_website;
    private ImageButton cancel_website;

    //email
    private RelativeLayout rl_email_header;
    private ImageButton indicator_email;
    private ImageButton btn_add_email;
    private Button btn_save_email;
    private LinearLayout ll_email_editor;
    private TextView tv_index_email;
    private EditText et_email;
    private ImageButton cancel_email;

    //social
    private RelativeLayout rl_social_header;
    private ImageButton indicator_social;
    private ImageButton btn_add_social;
    private Button btn_save_social;
    private LinearLayout ll_social_editor;
    private TextView tv_index_social;
    private TextView tv_social_select;
    private EditText et_social;
    private ImageButton cancel_social;

    private Animation antiClock, clock;
    private SelectedView selectView;
    private Gson gson;
    private MyLayoutOperation myLayoutOperation;

    public enum SelectedView {
        PHONE,
        WHATSAPP,
        VIBER,
        WEBSITE,
        EMAIL,
        SOCIAL
    }

    public SecondFragment() {
        // Required empty public constructor
    }

    public static SecondFragment newInstance(String param1) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //phone
        rl_phone_header = view.findViewById(R.id.rl_phone_header);
        indicator_phone = view.findViewById(R.id.indicator_phone);
        btn_add_phone = view.findViewById(R.id.btn_add_phone);
        btn_save_phone = view.findViewById(R.id.btn_save_phone);
        ll_phone_editor = view.findViewById(R.id.ll_phone_editor);
        tv_index_phone = view.findViewById(R.id.tv_index_phone);
        et_label_phone = view.findViewById(R.id.et_label_phone);
        rl_country_code = view.findViewById(R.id.rl_country_code);
        img_flag_country = view.findViewById(R.id.img_flag_country);
        tv_country_code = view.findViewById(R.id.tv_country_code);
        et_phone_number = view.findViewById(R.id.et_phone_number);
        cancel_phone = view.findViewById(R.id.cancel_phone);
        rl_phone_header.setOnClickListener(this);
        btn_add_phone.setOnClickListener(this);
        btn_save_phone.setOnClickListener(this);

        //whatsapp
        rl_whatsapp_header = view.findViewById(R.id.rl_whatsapp_header);
        indicator_whatsapp = view.findViewById(R.id.indicator_whatsapp);
        btn_add_whatsapp = view.findViewById(R.id.btn_add_whatsapp);
        btn_save_whatsapp = view.findViewById(R.id.btn_save_whatsapp);
        ll_whatsapp_editor = view.findViewById(R.id.ll_whatsapp_editor);
        tv_index_whatsapp = view.findViewById(R.id.tv_index_whatsapp);
        img_flag_country_whatsapp = view.findViewById(R.id.img_flag_country_whatsapp);
        tv_country_code_whatsapp = view.findViewById(R.id.tv_country_code_whatsapp);
        et_phone_number_whatsapp = view.findViewById(R.id.et_phone_number_whatsapp);
        cancel_whatsapp = view.findViewById(R.id.cancel_whatsapp);
        btn_add_whatsapp.setVisibility(View.INVISIBLE);
        btn_save_whatsapp.setVisibility(View.INVISIBLE);
        ll_whatsapp_editor.setVisibility(View.GONE);
        rl_whatsapp_header.setOnClickListener(this);
        btn_add_whatsapp.setOnClickListener(this);
        btn_save_whatsapp.setOnClickListener(this);


        //viber
        rl_viber_header = view.findViewById(R.id.rl_viber_header);
        indicator_viber = view.findViewById(R.id.indicator_viber);
        btn_add_viber = view.findViewById(R.id.btn_add_viber);
        btn_save_viber = view.findViewById(R.id.btn_save_viber);
        ll_viber_editor = view.findViewById(R.id.ll_viber_editor);
        tv_index_viber = view.findViewById(R.id.tv_index_viber);
        img_flag_country_viber = view.findViewById(R.id.img_flag_country_viber);
        tv_country_code_viber = view.findViewById(R.id.tv_country_code_viber);
        et_phone_number_viber = view.findViewById(R.id.et_phone_number_viber);
        cancel_viber = view.findViewById(R.id.cancel_viber);
        btn_add_viber.setVisibility(View.INVISIBLE);
        btn_save_viber.setVisibility(View.INVISIBLE);
        ll_viber_editor.setVisibility(View.GONE);
        rl_viber_header.setOnClickListener(this);
        btn_add_viber.setOnClickListener(this);
        btn_save_viber.setOnClickListener(this);

        //website
        rl_website_header = view.findViewById(R.id.rl_website_header);
        indicator_website = view.findViewById(R.id.indicator_website);
        btn_add_website = view.findViewById(R.id.btn_add_website);
        btn_save_website = view.findViewById(R.id.btn_save_website);
        ll_website_editor = view.findViewById(R.id.ll_website_editor);
        tv_index_website = view.findViewById(R.id.tv_index_website);
        et_website = view.findViewById(R.id.et_website);
        cancel_website = view.findViewById(R.id.cancel_website);
        btn_add_website.setVisibility(View.INVISIBLE);
        btn_save_website.setVisibility(View.INVISIBLE);
        ll_website_editor.setVisibility(View.GONE);
        rl_website_header.setOnClickListener(this);
        btn_add_website.setOnClickListener(this);
        btn_save_website.setOnClickListener(this);

        //email
        rl_email_header = view.findViewById(R.id.rl_email_header);
        indicator_email = view.findViewById(R.id.indicator_email);
        btn_add_email = view.findViewById(R.id.btn_add_email);
        btn_save_email = view.findViewById(R.id.btn_save_email);
        ll_email_editor = view.findViewById(R.id.ll_email_editor);
        tv_index_email = view.findViewById(R.id.tv_index_email);
        et_email = view.findViewById(R.id.et_email);
        cancel_email = view.findViewById(R.id.cancel_email);
        btn_add_email.setVisibility(View.INVISIBLE);
        btn_save_email.setVisibility(View.INVISIBLE);
        ll_email_editor.setVisibility(View.GONE);
        rl_email_header.setOnClickListener(this);
        btn_add_email.setOnClickListener(this);
        btn_save_email.setOnClickListener(this);

        //social
        rl_social_header = view.findViewById(R.id.rl_social_header);
        indicator_social = view.findViewById(R.id.indicator_social);
        btn_add_social = view.findViewById(R.id.btn_add_social);
        btn_save_social = view.findViewById(R.id.btn_save_social);
        ll_social_editor = view.findViewById(R.id.ll_social_editor);
        tv_index_social = view.findViewById(R.id.tv_index_social);
        tv_social_select = view.findViewById(R.id.tv_social_select);
        et_social = view.findViewById(R.id.et_social);
        cancel_social = view.findViewById(R.id.cancel_social);
        btn_add_social.setVisibility(View.INVISIBLE);
        btn_save_social.setVisibility(View.INVISIBLE);
        ll_social_editor.setVisibility(View.GONE);
        rl_social_header.setOnClickListener(this);
        btn_add_social.setOnClickListener(this);
        btn_save_social.setOnClickListener(this);
        tv_social_select.setOnClickListener(this);

        VolleyUtils.POST_METHOD(getContext(), ConstantMethod.URL, getRequestParam(), null, this, WebCallId.getBusinessDetails);

        clock = AnimationUtils.loadAnimation(getContext(), R.anim.clock);
        antiClock = AnimationUtils.loadAnimation(getContext(), R.anim.anticlock);
        selectView = SelectedView.PHONE;
        indicator_phone.startAnimation(antiClock);
        gson = new Gson();
        myLayoutOperation = new MyLayoutOperation(getActivity());
    }

    public JSONObject getRequestParam() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("eventName", "getBusinessDetails");
            jsonObject.put("userId", "348");
            jsonObject.put("businessId", "81972");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private HashMap<String, String> saveInfoParam() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("eventName", "contactInfo");
        hashMap.put("bussiness_id", "81972");
        hashMap.put("business_user_id", "348");
        return hashMap;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //phone
            case R.id.rl_phone_header:
                selectView(SelectedView.PHONE);

                break;
            case R.id.btn_add_phone:
                myLayoutOperation.addPhone(ll_phone_editor, "", "+91", "");
                break;
            case R.id.btn_save_phone:
//                String[] phones = myLayoutOperation.getPhone(ll_phone_editor);
                Map<String, String> phoneParam = saveInfoParam();
                phoneParam.put("phone", myLayoutOperation.getPhone(ll_phone_editor));
                VolleyUtils.POST_METHOD(getContext(), ConstantMethod.URL, null, phoneParam, this, WebCallId.saveContactInfo);
                break;

            //whatsapp
            case R.id.rl_whatsapp_header:
                selectView(SelectedView.WHATSAPP);

                break;
            case R.id.btn_add_whatsapp:
                myLayoutOperation.addWhatsapp(ll_whatsapp_editor, "+91", "");

                break;
            case R.id.btn_save_whatsapp:
//                List<String> whatsappNumbers = myLayoutOperation.getWhatsapp(ll_whatsapp_editor);
                HashMap<String, String> whatsappParams = saveInfoParam();
                whatsappParams.put("whatsappAddress", myLayoutOperation.getWhatsapp(ll_whatsapp_editor));

                VolleyUtils.POST_METHOD(getContext(), ConstantMethod.URL, null, whatsappParams, this, WebCallId.saveContactInfo);

                break;

            //viber
            case R.id.rl_viber_header:
                selectView(SelectedView.VIBER);
                break;
            case R.id.btn_add_viber:
                myLayoutOperation.addViber(ll_viber_editor, "+91", "");

                break;
            case R.id.btn_save_viber:

                HashMap<String, String> viberParams = saveInfoParam();
                viberParams.put("viberAddress", myLayoutOperation.getVibers(ll_viber_editor));

                VolleyUtils.POST_METHOD(getContext(), ConstantMethod.URL, null, viberParams, this, WebCallId.saveContactInfo);
                break;

            //website
            case R.id.rl_website_header:
                selectView(SelectedView.WEBSITE);
                break;
            case R.id.btn_add_website:
                myLayoutOperation.addWebsite(ll_website_editor, "");

                break;
            case R.id.btn_save_website:
//                List<String> websites = myLayoutOperation.getWebsites(ll_website_editor);
                Map<String, String> paramsWebsite = saveInfoParam();
                paramsWebsite.put("websiteAddress", myLayoutOperation.getWebsites(ll_website_editor));
//                if (websites.size() > 0) {
//                    for (int i = 0; i < websites.size(); i++) {
//                        String paramName = "websiteAddress[]";
//                        paramsWebsite.put(paramName, websites.get(i));
//                    }
//                }

                VolleyUtils.POST_METHOD(getContext(), ConstantMethod.URL, null, paramsWebsite, this, WebCallId.saveContactInfo);

                break;

            //email
            case R.id.rl_email_header:
                selectView(SelectedView.EMAIL);
                break;
            case R.id.btn_add_email:
                myLayoutOperation.addEmail(ll_email_editor, "");

                break;
            case R.id.btn_save_email:
//                List<String> emails = myLayoutOperation.getEmails(ll_email_editor);
                Map<String, String> paramsEmail = saveInfoParam();
//                if (emails.size() > 0) {
//                    for (int i = 0; i < emails.size(); i++) {
//                        String paramName = "emailAddress[]";
//                        paramsEmail.put(paramName, emails.get(i));
//                    }
                paramsEmail.put("emailAddress", myLayoutOperation.getEmails(ll_email_editor));
//                }
                VolleyUtils.POST_METHOD(getContext(), ConstantMethod.URL, null, paramsEmail, this, WebCallId.saveContactInfo);

                break;

            //social
            case R.id.rl_social_header:
                selectView(SelectedView.SOCIAL);
                break;
            case R.id.btn_add_social:
                myLayoutOperation.addSocial(ll_social_editor, MyLayoutOperation.SocialType.FACEBOOK, "");
                break;
            case R.id.tv_social_select:
                showSocialSelection(tv_social_select);
                break;
            case R.id.btn_save_social:
                Map<String, String> socialParams = saveInfoParam();

                Map<String, String> myMap = myLayoutOperation.getSocial(ll_social_editor);
                if (myMap.containsKey("facebook")) {
                    socialParams.put("facebookAddress", myMap.get("facebook"));
                }

                if (myMap.containsKey("twitter")) {
                    socialParams.put("twitterAddress", myMap.get("twitter"));
                }

                if (myMap.containsKey("linkedin")) {
                    socialParams.put("linkedinAddress", myMap.get("linkedin"));
                }

                if (myMap.containsKey("instagram")) {
                    socialParams.put("instagramAddress", myMap.get("instagram"));
                }

                VolleyUtils.POST_METHOD(getContext(), ConstantMethod.URL, null, socialParams, this, WebCallId.saveContactInfo);


                break;

        }
    }

    private void showSocialSelection(final TextView tv_social_select) {
        final String[] socials = {
                "Facebook",
                "Twitter",
                "Instagram",
                "LinkedIn"
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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

    public void selectView(SelectedView selectedView) {
        switch (selectedView) {
            case PHONE:
                if (selectView != SelectedView.PHONE) {
                    ConstantMethod.expand(ll_phone_editor);
                    indicator_phone.startAnimation(antiClock);
                    handleOptions(selectView);
                    selectView = SelectedView.PHONE;
                    btn_save_phone.setVisibility(View.VISIBLE);
                    btn_add_phone.setVisibility(View.VISIBLE);
                }
                break;

            case WHATSAPP:
                if (selectView != SelectedView.WHATSAPP) {
                    ConstantMethod.expand(ll_whatsapp_editor);
                    indicator_whatsapp.startAnimation(antiClock);
                    handleOptions(selectView);
                    selectView = SelectedView.WHATSAPP;
                    btn_save_whatsapp.setVisibility(View.VISIBLE);
                    btn_add_whatsapp.setVisibility(View.VISIBLE);
                }
                break;

            case VIBER:
                if (selectView != SelectedView.VIBER) {
                    ConstantMethod.expand(ll_viber_editor);
                    indicator_viber.startAnimation(antiClock);
                    handleOptions(selectView);
                    selectView = SelectedView.VIBER;
                    btn_save_viber.setVisibility(View.VISIBLE);
                    btn_add_viber.setVisibility(View.VISIBLE);
                }
                break;

            case WEBSITE:
                if (selectView != SelectedView.WEBSITE) {
                    ConstantMethod.expand(ll_website_editor);
                    indicator_website.startAnimation(antiClock);
                    handleOptions(selectView);
                    selectView = SelectedView.WEBSITE;
                    btn_save_website.setVisibility(View.VISIBLE);
                    btn_add_website.setVisibility(View.VISIBLE);
                }
                break;

            case EMAIL:
                if (selectView != SelectedView.EMAIL) {
                    ConstantMethod.expand(ll_email_editor);
                    indicator_email.startAnimation(antiClock);
                    handleOptions(selectView);
                    selectView = SelectedView.EMAIL;
                    btn_save_email.setVisibility(View.VISIBLE);
                    btn_add_email.setVisibility(View.VISIBLE);
                }
                break;

            case SOCIAL:
                if (selectView != SelectedView.SOCIAL) {
                    ConstantMethod.expand(ll_social_editor);
                    indicator_social.startAnimation(antiClock);
                    handleOptions(selectView);
                    selectView = SelectedView.SOCIAL;
                    btn_save_social.setVisibility(View.VISIBLE);
                    btn_add_social.setVisibility(View.VISIBLE);
                }
                break;
        }

    }

    private void handleOptions(SelectedView selectedView) {
        switch (selectedView) {
            case PHONE:
                ConstantMethod.collapse(ll_phone_editor);
                indicator_phone.startAnimation(clock);
                btn_save_phone.setVisibility(View.INVISIBLE);
                btn_add_phone.setVisibility(View.INVISIBLE);
                break;
            case WHATSAPP:
                ConstantMethod.collapse(ll_whatsapp_editor);
                indicator_whatsapp.startAnimation(clock);
                btn_save_whatsapp.setVisibility(View.INVISIBLE);
                btn_add_whatsapp.setVisibility(View.INVISIBLE);
                break;
            case VIBER:
                ConstantMethod.collapse(ll_viber_editor);
                indicator_viber.startAnimation(clock);
                btn_save_viber.setVisibility(View.INVISIBLE);
                btn_add_viber.setVisibility(View.INVISIBLE);
                break;
            case WEBSITE:
                ConstantMethod.collapse(ll_website_editor);
                indicator_website.startAnimation(clock);
                btn_save_website.setVisibility(View.INVISIBLE);
                btn_add_website.setVisibility(View.INVISIBLE);
                break;
            case EMAIL:
                ConstantMethod.collapse(ll_email_editor);
                indicator_email.startAnimation(clock);
                btn_save_email.setVisibility(View.INVISIBLE);
                btn_add_email.setVisibility(View.INVISIBLE);
                break;
            case SOCIAL:
                ConstantMethod.collapse(ll_social_editor);
                indicator_social.startAnimation(clock);
                btn_save_social.setVisibility(View.INVISIBLE);
                btn_add_social.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getContext(), "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(Object response, WebCallId webcallid) {
        if (response != null) {
            switch (webcallid) {
                case getBusinessDetails:
                    JSONObject responseObject = (JSONObject) response;
                    try {
                        BusinessDetails businessDetails = gson.fromJson(
                                responseObject.getJSONArray("data").get(0).toString(), BusinessDetails.class);
                        Toast.makeText(getContext(), "" + businessDetails.getBussiness_title(), Toast.LENGTH_SHORT).show();

                        //phone
                        String phoneNumber = businessDetails.getBussiness_phone();
                        List<PhoneNumber> phoneNumberList = ConstantMethod.GetNumberList(phoneNumber);
                        if (phoneNumberList != null && phoneNumberList.size() > 0) {
                            et_label_phone.setText(phoneNumberList.get(0).getKey());
                            et_phone_number.setText(phoneNumberList.get(0).getPhoneNumber());
                            for (int i = 1; i < phoneNumberList.size(); i++) {
                                myLayoutOperation.addPhone(ll_phone_editor, phoneNumberList.get(i).getKey(),
                                        "+91", phoneNumberList.get(i).getPhoneNumber());
                            }
                        }
                        //whatsapp
                        String[] whatsappNumbers = businessDetails.getBussiness_whatsapp();
                        if (whatsappNumbers.length > 0) {
                            et_phone_number_whatsapp.setText(whatsappNumbers[0]);
                            for (int i = 1; i < whatsappNumbers.length; i++) {
                                myLayoutOperation.addWhatsapp(ll_whatsapp_editor, "+91", whatsappNumbers[i]);
                            }
                        }

                        //viber
                        String[] vibers = businessDetails.getBusiness_viber();
                        if (vibers.length > 0) {
                            et_phone_number_viber.setText(vibers[0]);
                            for (int i = 1; i < vibers.length; i++) {
                                myLayoutOperation.addViber(ll_viber_editor, "+91", vibers[i]);
                            }
                        }

                        //website
                        String[] websites = businessDetails.getBussiness_website();
                        if (websites.length > 0) {
                            et_website.setText(websites[0]);
                            for (int i = 1; i < websites.length; i++) {
                                myLayoutOperation.addWebsite(ll_website_editor, websites[i]);
                            }
                        }

                        //email
                        String[] emails = businessDetails.getBussiness_email();
                        if (emails.length > 0) {
                            et_email.setText(emails[0]);
                            for (int i = 1; i < emails.length; i++) {
                                myLayoutOperation.addEmail(ll_email_editor, emails[i]);
                            }
                        }

                        //social
                        String[] facebooks = businessDetails.getBusiness_facebook();
                        String[] twitters = businessDetails.getBussiness_twitter();
                        String[] linkedIns = businessDetails.getBussiness_linkedin();
                        String[] instagrams = businessDetails.getBussiness_instagram();
                        boolean fb = false, tw = false, li = false, ig = false;

                        if (facebooks.length > 0) {
                            et_social.setText(facebooks[0]);
                            tv_social_select.setText("Facebook");
                            fb = true;
                        } else if (twitters.length > 0) {
                            et_social.setText(twitters[0]);
                            tv_social_select.setText("Twitter");
                            tw = true;
                        } else if (linkedIns.length > 0) {
                            et_social.setText(linkedIns[0]);
                            tv_social_select.setText("LinkedIn");
                            li = true;
                        } else if (instagrams.length > 0) {
                            et_social.setText(instagrams[0]);
                            tv_social_select.setText("Instagram");
                            ig = true;
                        }

                        if (fb) {
                            for (int i = 1; i < facebooks.length; i++) {
                                myLayoutOperation.addSocial(ll_social_editor, MyLayoutOperation.SocialType.FACEBOOK, facebooks[i]);
                            }
                            for (String twitter : twitters) {
                                myLayoutOperation.addSocial(ll_social_editor, MyLayoutOperation.SocialType.TWITTER, twitter);
                            }
                            for (String linkedIn : linkedIns) {
                                myLayoutOperation.addSocial(ll_social_editor, MyLayoutOperation.SocialType.LINKEDIN, linkedIn);
                            }
                            for (String instagram : instagrams) {
                                myLayoutOperation.addSocial(ll_social_editor, MyLayoutOperation.SocialType.INSTAGRAM, instagram);
                            }
                        } else if (tw) {
                            for (int i = 1; i < twitters.length; i++) {
                                myLayoutOperation.addSocial(ll_social_editor, MyLayoutOperation.SocialType.TWITTER, twitters[i]);
                            }
                            for (String linkedIn : linkedIns) {
                                myLayoutOperation.addSocial(ll_social_editor, MyLayoutOperation.SocialType.LINKEDIN, linkedIn);
                            }
                            for (String instagram : instagrams) {
                                myLayoutOperation.addSocial(ll_social_editor, MyLayoutOperation.SocialType.INSTAGRAM, instagram);
                            }
                        } else if (li) {
                            for (int i = 1; i < linkedIns.length; i++) {
                                myLayoutOperation.addSocial(ll_social_editor, MyLayoutOperation.SocialType.LINKEDIN, linkedIns[i]);
                            }
                            for (String instagram : instagrams) {
                                myLayoutOperation.addSocial(ll_social_editor, MyLayoutOperation.SocialType.INSTAGRAM, instagram);
                            }
                        } else if (ig) {
                            for (int i = 1; i < instagrams.length; i++) {
                                myLayoutOperation.addSocial(ll_social_editor, MyLayoutOperation.SocialType.INSTAGRAM, instagrams[i]);
                            }
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }

    }
}
