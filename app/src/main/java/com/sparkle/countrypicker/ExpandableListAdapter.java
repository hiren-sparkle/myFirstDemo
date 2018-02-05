package com.sparkle.countrypicker;

import android.content.Context;
import android.graphics.Typeface;
import android.media.Image;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.sparkle.countrypicker.Models.SelectedCategories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Admin on 22-01-2018.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<Category> universalCategory;
    private List<Category> _listDataHeader;// header titles
    // child data in format of header title, child title
    private HashMap<Category, List<Category.SubCategory>> universalSubCategory;

    public ExpandableListAdapter(Context _context, List<Category> _listDataHeader, HashMap<Category, List<Category.SubCategory>> _listDataChild) {
        this._context = _context;
        universalCategory = new ArrayList<Category>();
        this._listDataHeader = _listDataHeader;
        universalCategory.addAll(_listDataHeader);
        this.universalSubCategory = _listDataChild;
    }

    public void selectGroup(int groupPosition, boolean isChecked) {
        universalCategory.get(groupPosition).setSelected(!isChecked);
        notifyDataSetChanged();
    }

    public void selectSubCategory(int groupPostion, int childPosition, boolean isChecked) {
        universalSubCategory.get(universalCategory.get(groupPostion)).get(childPosition).setSelected(!isChecked);
        notifyDataSetChanged();
    }

    public List<Category> getCategoryList() {
        return this.universalCategory;
    }

    public HashMap<Category, List<Category.SubCategory>> getSubCategoryList() {
        return this.universalSubCategory;
    }

    public void filterData(String queryText) {

        universalCategory.clear();

        if (queryText.isEmpty()) {
            universalCategory.addAll(_listDataHeader);
        } else {
            for (Category category : _listDataHeader) {
                if (category.getCategory_title().toLowerCase().contains(queryText) || isMatchSubcategory(category, queryText)) {
                    universalCategory.add(category);
                }
            }
        }
        notifyDataSetChanged();

    }

    public SelectedCategories getSelectedCategory() {
        List<String> categoryList = new ArrayList<>();
        List<String> subCategory = new ArrayList<>();
        for (int i = 0; i < _listDataHeader.size(); i++) {
            if (_listDataHeader.get(i).isSelected()) {
                for (int j = 0; j < _listDataHeader.get(i).getSubCategory().size(); j++) {
                    if (_listDataHeader.get(i).getSubCategory().get(j).isSelected()) {
                        subCategory.add(_listDataHeader.get(i).getSubCategory().get(j).getCategory_id());
                    }
                }
                categoryList.add(_listDataHeader.get(i).getCategory_id());
            }
        }

        String categories = TextUtils.join(",", categoryList);
        String subCategories = TextUtils.join(",", subCategory);

        return new SelectedCategories(categories, subCategories);

    }

    private boolean isMatchSubcategory(Category category, String queryText) {
        for (int i = 0; i < category.getSubCategory().size(); i++) {
            if (category.getSubCategory().get(i).getCategory_title().toLowerCase().contains(queryText))
                return true;
        }
        return false;
    }

    @Override
    public int getGroupCount() {
        return this.universalCategory.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.universalSubCategory.get(this.universalCategory.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.universalCategory.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.universalSubCategory.get(this.universalCategory.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final Category category = (Category) getGroup(groupPosition);
//        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);

        CheckBox checkBox = convertView.findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(category.isSelected());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                universalCategory.get(groupPosition).setSelected(isChecked);
                if (!isChecked) {
                    List<Category.SubCategory> subCategories = universalSubCategory.get(universalCategory.get(groupPosition));
                    for (int i = 0; i < subCategories.size(); i++) {
                        selectSubCategory(groupPosition, i, true);
                    }
                }
            }
        });
        lblListHeader.setText(category.getCategory_title());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final Category.SubCategory subCategory = (Category.SubCategory) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        CheckBox checkBox_child = convertView.findViewById(R.id.cb_sub_cat_selector);
        checkBox_child.setOnCheckedChangeListener(null);
        checkBox_child.setChecked(subCategory.isSelected());
        checkBox_child.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                universalSubCategory.get(universalCategory.get(groupPosition)).get(childPosition).setSelected(isChecked);
            }
        });
        txtListChild.setText(subCategory.getCategory_title());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
