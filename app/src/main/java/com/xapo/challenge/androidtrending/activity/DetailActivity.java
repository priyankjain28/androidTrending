package com.xapo.challenge.androidtrending.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xapo.challenge.androidtrending.R;
import com.xapo.challenge.androidtrending.api.ApiClient;
import com.xapo.challenge.androidtrending.init.ApplicationInitiate;
import com.xapo.challenge.androidtrending.model.Item;
import com.xapo.challenge.androidtrending.model.UserProfile;
import com.xapo.challenge.androidtrending.utils.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.repoImg)
    ImageView repoImg;
    @BindView(R.id.detail_name)
    TextView detailName;
    @BindView(R.id.login_name)
    TextView loginName;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.followers)
    TextView followers;
    @BindView(R.id.following)
    TextView following;
    @BindView(R.id.full_name)
    TextView fullName;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.blog)
    TextView blog;
    @BindView(R.id.company)
    TextView company;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.stars)
    TextView stars;
    @BindView(R.id.forks)
    TextView forks;
    @BindView(R.id.createdAt)
    TextView createdAt;
    @BindView(R.id.updatedAt)
    TextView updatedAt;
    @BindView(R.id.license)
    TextView license;
    @BindView(R.id.tv_repo)
    TextView repoLink;
    Item item;
    UserProfile profile;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        //showProgressDialog();

        item = (Item) bundle.getSerializable("detailObj");
        //Log.d("DetailActivity", "Item : " + item.toString());
        apiCallToGetData(item.getOwner().getUrl());
    }

    /*private void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Repositories...\nGenius is patience. - Newton");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }*/
    private void apiCallToGetData(String url) {
        ApiClient.getSingletonApiClient().getProfileDetail(url, new Callback<UserProfile>() {

            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.code() == 200 && response.isSuccessful()) {
                    profile = response.body();
                    Log.d("DetailActivty", "Profile Data :" + profile.toString());
                    //progressDialog.dismiss();
                    setLayoutValue();
                }

            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                //progressDialog.dismiss();
            }
        });

    }

    private void setLayoutValue() {
        Picasso.with(this).load(item.getOwner().getAvatarUrl()).into(repoImg);
        if (profile.getUserName() != null) {
            detailName.setText(profile.getUserName());
        } else {
            detailName.setText(profile.getLogin());
        }
        loginName.setText(profile.getLogin());
        if (profile.getLocation() != null)
            location.setText(profile.getLocation());
        followers.append(profile.getFollowers());
        following.append(profile.getFollowing());

        fullName.setText(item.getFullName());
        if (item.getDescription() != null)
            description.setText(item.getDescription());
        if (profile.getBlog() != null)
            blog.setText(profile.getBlog());
        if (profile.getCompany() != null)
            company.setText(profile.getCompany());

        if (profile.getType() != null)
            type.setText(profile.getType());
        stars.setText(item.getStarsCount());
        forks.setText(item.getForks());
        createdAt.setText(Method.dateFormatter(item.getCreatedAt()));
        updatedAt.setText(Method.dateFormatter(item.getUpdatedTime()));
        repoLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplicationInitiate.getAppContext(), WebViewActivity.class);
                intent.putExtra("url", item.getUrl());
                intent.putExtra("name", item.getFullName());
                ApplicationInitiate.getAppContext().startActivity(intent);
            }
        });
        if (item.getLicense() != null)
            license.setText(item.getLicense().getName());
    }
}
