package com.xapo.challenge.androidtrending.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xapo.challenge.androidtrending.R;
import com.xapo.challenge.androidtrending.activity.WebViewActivity;
import com.xapo.challenge.androidtrending.init.ApplicationInitiate;
import com.xapo.challenge.androidtrending.model.LanguageItem;

import java.util.ArrayList;
import java.util.List;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.DataObjectHolder> {
    private static String TAG = "LanguageViewData";
    private List<LanguageItem> mDataset = new ArrayList<LanguageItem>();
    private String filterType;

    public static class DataObjectHolder extends RecyclerView.ViewHolder{

        ImageView thumbnail,img_stars_curr;
        TextView author_name, description, language, stars, forks, stars_curr;
        RelativeLayout r_lanaguage;
        LinearLayout repo_layout;

        public DataObjectHolder(View itemView) {
            super(itemView);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            img_stars_curr = (ImageView) itemView.findViewById(R.id.img_stars_curr);
            author_name = (TextView) itemView.findViewById(R.id.author_name);
            description = (TextView) itemView.findViewById(R.id.description);
            r_lanaguage = (RelativeLayout) itemView.findViewById(R.id.r_lang);
            language = (TextView) itemView.findViewById(R.id.language);
            stars = (TextView) itemView.findViewById(R.id.stars);
            forks = (TextView) itemView.findViewById(R.id.forks);
            stars_curr = (TextView) itemView.findViewById(R.id.stars_curr);
            repo_layout = (LinearLayout) itemView.findViewById(R.id.repo_layout);
        }
    }
    public LanguageAdapter(List<LanguageItem> myDataset, String filterType) {
        this.mDataset = myDataset;
        this.filterType = filterType;
    }

    @Override
    public LanguageAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_content, parent, false);
        LanguageAdapter.DataObjectHolder dataObjectHolder = new LanguageAdapter.DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataObjectHolder holder, final int position) {
        holder.author_name.setText(mDataset.get(position).getAuthor() + "/" + mDataset.get(position).getName());
        holder.description.setText(mDataset.get(position).getDescription());
        holder.stars.setText(mDataset.get(position).getStars().toString());
        holder.language.setText(mDataset.get(position).getLanguage());
        holder.forks.setText(mDataset.get(position).getForks().toString());
        holder.stars_curr.setText(mDataset.get(position).getCurrentPeriodStars().toString() + " stars " + filterType);
        holder.repo_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ApplicationInitiate.getAppContext(), WebViewActivity.class);
                intent.putExtra("url",mDataset.get(position).getUrl());
                intent.putExtra("name", mDataset.get(position).getAuthor() + "/" + mDataset.get(position).getName());
                ApplicationInitiate.getAppContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
            return mDataset.size();
    }


}