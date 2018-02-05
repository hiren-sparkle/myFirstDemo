package com.sparkle.countrypicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sparkle.countrypicker.Models.SelectedCategories;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableActivity extends AppCompatActivity implements VolleyResponseListener,
        TextWatcher, View.OnClickListener {

    private static final String URL = "http://midlal.com/webservice";
    CategoryArr categoryArr;
    Gson gson;
    EditText et_search_category;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<Category> listDataHeader;
    HashMap<Category, List<Category.SubCategory>> listDataChild;
    private int lastExpandedPosition = -1;
    private Button btn_get_selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);
        et_search_category = findViewById(R.id.et_search_category);
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        btn_get_selected = findViewById(R.id.btn_get_selected);
        btn_get_selected.setOnClickListener(this);
//        prepareListData();

        gson = new Gson();
        VolleyUtils.POST_METHOD(this, URL, getRequestParam(),null, this, WebCallId.getCategories);
    }

    private JSONObject getRequestParam() {
        JSONObject requestJson = new JSONObject();
        try {
            requestJson.put("eventName", "getCategories");
            requestJson.put("country", "kuwait");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return requestJson;
    }

//    private void prepareListData() {
//        listDataHeader = new ArrayList<String>();
//        listDataChild = new HashMap<String, List<String>>();
//
//        // Adding child data
//        listDataHeader.add("Top 250");
//        listDataHeader.add("Now Showing");
//        listDataHeader.add("Coming Soon..");
//
//        // Adding child data
//        List<String> top250 = new ArrayList<String>();
//        top250.add("The Shawshank Redemption");
//        top250.add("The Godfather");
//        top250.add("The Godfather: Part II");
//        top250.add("Pulp Fiction");
//        top250.add("The Good, the Bad and the Ugly");
//        top250.add("The Dark Knight");
//        top250.add("12 Angry Men");
//
//        List<String> nowShowing = new ArrayList<String>();
//        nowShowing.add("The Conjuring");
//        nowShowing.add("Despicable Me 2");
//        nowShowing.add("Turbo");
//        nowShowing.add("Grown Ups 2");
//        nowShowing.add("Red 2");
//        nowShowing.add("The Wolverine");
//
//        List<String> comingSoon = new ArrayList<String>();
//        comingSoon.add("2 Guns");
//        comingSoon.add("The Smurfs 2");
//        comingSoon.add("The Spectacular Now");
//        comingSoon.add("The Canyons");
//        comingSoon.add("Europa Report");
//
//        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
//        listDataChild.put(listDataHeader.get(1), nowShowing);
//        listDataChild.put(listDataHeader.get(2), comingSoon);
//    }

    @Override
    public void onError(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResponse(Object response, WebCallId webcallid) {
        try {
            if (response != null) {
                String status;
                switch (webcallid) {
                    case getCategories:
                        JSONObject responseObject = (JSONObject) response;
                        status = responseObject.getString("status");
                        if (status.equalsIgnoreCase("Success")) {
                            categoryArr = gson.fromJson(responseObject.toString(), CategoryArr.class);
                            if (categoryArr != null && categoryArr.getData().size() > 0) {
                                listDataHeader = categoryArr.getData();
                                listDataChild = new HashMap<Category, List<Category.SubCategory>>();
                                for (int i = 0; i < listDataHeader.size(); i++) {
                                    listDataHeader.get(i).setSelected(false);
                                    for (int j = 0; j < listDataHeader.get(i).getSubCategory().size(); j++) {
                                        listDataHeader.get(i).getSubCategory().get(j).setSelected(false);
                                    }
                                    listDataChild.put(listDataHeader.get(i), listDataHeader.get(i).getSubCategory());
                                }

                                listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

                                // setting list adapter
                                expListView.setAdapter(listAdapter);
                                et_search_category.addTextChangedListener(this);

                                // Listview Group click listener
                                expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                                    @Override
                                    public boolean onGroupClick(ExpandableListView parent, View v,
                                                                int groupPosition, long id) {
                                        int childCount = parent.getExpandableListAdapter().getChildrenCount(groupPosition);
                                        if (childCount == 0) {
                                            List<Category> categoryList = listAdapter.getCategoryList();
                                            listAdapter.selectGroup(groupPosition, categoryList.get(groupPosition).isSelected());
                                        }
                                        // Toast.makeText(getApplicationContext(),
                                        // "Group Clicked " + listDataHeader.get(groupPosition),
                                        // Toast.LENGTH_SHORT).show();
                                        return false;
                                    }
                                });

                                // Listview Group expanded listener
                                expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                                    @Override
                                    public void onGroupExpand(int groupPosition) {
//                                        Toast.makeText(getApplicationContext(),
//                                                listDataHeader.get(groupPosition).getCategory_title() + " Expanded",
//                                                Toast.LENGTH_SHORT).show();

                                        if (lastExpandedPosition != -1
                                                && groupPosition != lastExpandedPosition) {
                                            expListView.collapseGroup(lastExpandedPosition);
                                        }
                                        lastExpandedPosition = groupPosition;
                                    }
                                });

                                // Listview Group collasped listener
                                expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

                                    @Override
                                    public void onGroupCollapse(int groupPosition) {
//                                        Toast.makeText(getApplicationContext(),
//                                                listDataHeader.get(groupPosition).getCategory_title() + " Collapsed",
//                                                Toast.LENGTH_SHORT).show();

                                    }
                                });

                                // Listview on child click listener
                                expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

                                    @Override
                                    public boolean onChildClick(ExpandableListView parent, View v,
                                                                int groupPosition, int childPosition, long id) {
                                        HashMap<Category, List<Category.SubCategory>> child_list = listAdapter.getSubCategoryList();
                                        listAdapter.selectSubCategory(groupPosition, childPosition,
                                                child_list.get(listAdapter.getCategoryList().get(groupPosition)).get(childPosition).isSelected());
                                        listAdapter.selectGroup(groupPosition, false);
//                                        Toast.makeText(
//                                                getApplicationContext(),
//                                                listDataHeader.get(groupPosition).getCategory_title()
//                                                        + " : "
//                                                        + listDataChild.get(
//                                                        listDataHeader.get(groupPosition)).get(
//                                                        childPosition).getCategory_title(), Toast.LENGTH_SHORT)
//                                                .show();
                                        return false;
                                    }
                                });
                            }
                        }
                        break;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String queryText = et_search_category.getText().toString();
        if (listAdapter != null) {
            listAdapter.filterData(queryText.toLowerCase());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_selected:
                if (listAdapter != null) {
                    SelectedCategories selectedCategories = listAdapter.getSelectedCategory();
                    Log.d(ExpandableActivity.class.getSimpleName(), selectedCategories.getCategories());
                    Log.d(ExpandableActivity.class.getSimpleName(), selectedCategories.getSubCategories());
                }
                break;
        }

    }
}
