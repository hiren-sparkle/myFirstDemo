package com.sparkle.countrypicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sparkle.countrypicker.Models.ListBusiness;
import com.sparkle.countrypicker.Models.ListBusinessArr;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class BusinessListActivity extends AppCompatActivity implements VolleyResponseListener, TextWatcher {

    private RecyclerView rv_business_list;
    private Gson gson;
    private ListBusinessArr listBusinessArr;
    private ListBusinessAdapter listBusinessAdapter;
    private EndlessRecyclerViewScrollListener listener;
    private EditText et_search_business;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_list);
        rv_business_list = findViewById(R.id.rv_business_list);
        rv_business_list.setHasFixedSize(true);
        et_search_business = findViewById(R.id.et_search_business);

        et_search_business.addTextChangedListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_business_list.setLayoutManager(layoutManager);
        listener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                VolleyUtils.POST_METHOD(BusinessListActivity.this, ConstantMethod.URL,
                        getRequestParams(listBusinessArr.getNext_page(), ""), null,BusinessListActivity.this, WebCallId.getBusinessList);
            }
        };


        gson = new Gson();

        VolleyUtils.POST_METHOD(this, ConstantMethod.URL, getRequestParams("1", ""),null, this, WebCallId.getBusinessList);

    }

    private JSONObject getRequestParams(String pageNum, String searchText) {
        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("eventName", "listingbusiness");
            requestObject.put("userId", "5");
            requestObject.put("page", pageNum);
            requestObject.put("searchBusiness", searchText);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(BusinessListActivity.class.getSimpleName(), requestObject.toString());
        return requestObject;
    }


    @Override
    public void onError(String message) {

    }

    @Override
    public void onResponse(Object response, WebCallId webcallid) {
        if (response != null) {
            String status;
            switch (webcallid) {
                case getBusinessList:
                    try {
                        JSONObject jsonResponse = (JSONObject) response;
                        status = jsonResponse.getString("status");
                        if (status.equalsIgnoreCase("Success")) {
                            Log.d(BusinessListActivity.class.getSimpleName(), "Response: " + jsonResponse);
                            if (listBusinessArr == null)
                                listBusinessArr = gson.fromJson(jsonResponse.toString(), ListBusinessArr.class);
                            else {
                                ListBusinessArr singalongObject = gson.fromJson(jsonResponse.toString(), ListBusinessArr.class);
                                if (singalongObject != null) {
                                    listBusinessArr.setNext_page(singalongObject.getNext_page());
                                    listBusinessArr.getData().addAll(singalongObject.getData());
                                }
                            }
                            if (listBusinessAdapter != null && rv_business_list.getAdapter() != null) {
                                listBusinessAdapter.notify(listBusinessArr.getData());
                            } else {
                                listBusinessAdapter = new ListBusinessAdapter(this, listBusinessArr.getData());
                                rv_business_list.setAdapter(listBusinessAdapter);
                                rv_business_list.addOnScrollListener(listener);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        final String searchText = et_search_business.getText().toString();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // do your actual work here
                listBusinessAdapter = null;
                VolleyUtils.POST_METHOD(BusinessListActivity.this, ConstantMethod.URL,
                        getRequestParams("1", searchText),null,
                        BusinessListActivity.this, WebCallId.getBusinessList);
            }
        }, 600);

    }
}
