package com.xapo.challenge.androidtrending.adapter;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xapo.challenge.androidtrending.R;
import com.xapo.challenge.androidtrending.activity.DetailActivity;
import com.xapo.challenge.androidtrending.init.ApplicationInitiate;
import com.xapo.challenge.androidtrending.model.Item;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.DataObjectHolder> {
    private static String TAG = "ItemAdapter";
    private ArrayList<Item> mDataItem;

    public static class DataObjectHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail, img_stars_curr;
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


    public ItemAdapter(ArrayList<Item> myDataset) {
        mDataItem = myDataset;
    }

    @Override
    public ItemAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_content, parent, false);
        ItemAdapter.DataObjectHolder dataObjectHolder = new ItemAdapter.DataObjectHolder(view);
        return dataObjectHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(ItemAdapter.DataObjectHolder holder, final int position) {
        holder.author_name.setText(mDataItem.get(position).getFullName());
        holder.description.setText(mDataItem.get(position).getDescription());
        holder.stars.setText(mDataItem.get(position).getStarsCount());
        // holder.r_lanaguage.setVisibility(View.GONE);
        holder.language.setText(mDataItem.get(position).getLanguage());
        holder.img_stars_curr.setVisibility(View.GONE);
        // holder.language.setText(mDataItem.get(position).getLanguage());
        holder.forks.setText(mDataItem.get(position).getForks());
        holder.stars_curr.setText(mDataItem.get(position).getDateAgo());
        holder.thumbnail.setVisibility(View.VISIBLE);
        String url = mDataItem.get(position).getOwner().getAvatarUrl();
        if (!url.isEmpty()) {
            Picasso.with(ApplicationInitiate.getAppContext()).load(url).into(holder.thumbnail);
        }
        final String urlDefine = mDataItem.get(position).getUrl();
        final String fullName = mDataItem.get(position).getFullName();
        holder.repo_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ApplicationInitiate.getAppContext(), DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("detailObj",mDataItem.get(position));
                intent.putExtras(bundle);
                ApplicationInitiate.getAppContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataItem.size();
    }

}