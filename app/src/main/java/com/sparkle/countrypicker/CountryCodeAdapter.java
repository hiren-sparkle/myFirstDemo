package com.sparkle.countrypicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Admin on 19-01-2018.
 */

public class CountryCodeAdapter extends RecyclerView.Adapter<CountryCodeAdapter.CountryCodeViewHolder> {

    private List<Country> countryList;
    Activity context;
    private ArrayList<Country> univeruserDataList;

    public CountryCodeAdapter(List<Country> countryList, Activity context) {
        this.countryList = countryList;
        this.context = context;
        univeruserDataList = new ArrayList<>();
        univeruserDataList.addAll(countryList);
    }

    public void filter(String charText) {

        univeruserDataList.clear();

        if (charText.length() == 0) {
            univeruserDataList.addAll(countryList);
        } else {
            for (Country wp : countryList) {
                if (wp.getName().toLowerCase().contains(charText) || wp.getPhoneCode().toLowerCase().contains(charText)) {
                    univeruserDataList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public CountryCodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_country_tile, parent, false);
        return new CountryCodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryCodeViewHolder holder, int position) {
        Country country = univeruserDataList.get(position);
        holder.textView_name.setText(country.getName());
        holder.textView_code.setText(country.getPhoneCode());
        holder.imageViewFlag.setImageResource(country.getFlagID());

    }

    @Override
    public int getItemCount() {
        return univeruserDataList.size();
    }

    class CountryCodeViewHolder extends RecyclerView.ViewHolder {
        TextView textView_name, textView_code;
        ImageView imageViewFlag;
        LinearLayout linearFlagHolder;
        View divider;

        public CountryCodeViewHolder(View itemView) {
            super(itemView);
            textView_name = (TextView) itemView.findViewById(R.id.textView_countryName);
            textView_code = (TextView) itemView.findViewById(R.id.textView_code);
            imageViewFlag = (ImageView) itemView.findViewById(R.id.image_flag);
            linearFlagHolder = (LinearLayout) itemView.findViewById(R.id.linear_flag_holder);
            divider = itemView.findViewById(R.id.preferenceDivider);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent resultIntent = new Intent();
// TODO Add extras or a data URI to this intent as appropriate.
                    resultIntent.putExtra("some_key", univeruserDataList.get(getAdapterPosition()).getPhoneCode());
                    context.setResult(Activity.RESULT_OK, resultIntent);
                    context.finish();
                }
            });

        }
    }
}
