package com.sparkle.countrypicker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sparkle.countrypicker.Models.ListBusiness;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Admin on 23-01-2018.
 */

public class ListBusinessAdapter extends RecyclerView.Adapter<ListBusinessAdapter.ViewHolder> {

    Context mContext;
    List<ListBusiness> listBusinesses;

    public ListBusinessAdapter(Context mContext, List<ListBusiness> listBusinesses) {
        this.mContext = mContext;
        this.listBusinesses = listBusinesses;
    }

    @Override
    public ListBusinessAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_business_list, parent, false);
        return new ViewHolder(view);
    }

    public void notify(List<ListBusiness> businessList) {
        this.listBusinesses = businessList;
    }

    @Override
    public void onBindViewHolder(ListBusinessAdapter.ViewHolder holder, int position) {
        ListBusiness listBusiness = listBusinesses.get(position);
        holder.tv_business_title.setText(listBusiness.getBussiness_title());
        if (listBusiness.getBussiness_address() != null && !listBusiness.getBussiness_address().isEmpty()) {
            String addressText = "<b>Address: </b>" + listBusiness.getBussiness_address();
            holder.tv_business_address.setText(ConstantMethod.fromHtml(addressText));
        } else {
            holder.tv_business_address.setText("");
        }
        if (listBusiness.getParent_cat() != null && !listBusiness.getParent_cat().isEmpty()) {
            String categoryText = "<b>Category: </b>" + listBusiness.getParent_cat();
            holder.tv_business_category.setText(ConstantMethod.fromHtml(categoryText));
        } else {
            holder.tv_business_category.setText("");
        }
        if (listBusiness.getChild_cat() != null && !listBusiness.getChild_cat().isEmpty()) {
            String subCategory = "<b>Sub Category: </b>" + listBusiness.getChild_cat();
            holder.tv_business_sub_category.setText(ConstantMethod.fromHtml(subCategory));
        } else
            holder.tv_business_sub_category.setText("");
        holder.tv_business_status.setText(listBusiness.getBussiness_status());
        if (listBusiness.getBusiness_logo() != null && !listBusiness.getBusiness_logo().isEmpty()) {
            Glide.with(mContext).load(ConstantMethod.ConvertProperUl(listBusiness.getBusiness_logo())).into(holder.iv_business_profile);
        } else
            Glide.with(mContext).load(R.drawable.starbucks).into(holder.iv_business_profile);

        if (position == getItemCount() - 1) {
            holder.divider_view.setVisibility(View.INVISIBLE);
            Log.d(ListBusinessAdapter.class.getSimpleName(), "this must be hidden now");

        } else {
            holder.divider_view.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return listBusinesses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView iv_business_profile;
        TextView tv_business_title;
        TextView tv_business_address;
        TextView tv_business_category;
        TextView tv_business_sub_category;
        TextView tv_business_status;
        View divider_view;
        CheckBox cb_business_selector;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_business_profile = itemView.findViewById(R.id.iv_business_profile);
            tv_business_title = itemView.findViewById(R.id.tv_business_title);
            tv_business_address = itemView.findViewById(R.id.tv_business_address);
            tv_business_category = itemView.findViewById(R.id.tv_business_category);
            tv_business_sub_category = itemView.findViewById(R.id.tv_business_sub_category);
            tv_business_status = itemView.findViewById(R.id.tv_business_status);
            cb_business_selector = itemView.findViewById(R.id.cb_business_selector);
            divider_view = itemView.findViewById(R.id.divider_view);
        }
    }
}
