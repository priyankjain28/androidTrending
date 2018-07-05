package com.xapo.challenge.androidtrending.api;

import com.xapo.challenge.androidtrending.model.ItemResponse;
import com.xapo.challenge.androidtrending.model.LanguageItem;
import com.xapo.challenge.androidtrending.model.UserProfile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiInterface {


    //https://github-trending-api.now.sh/repositories?language=javascript&since=weekly
    @GET("/repositories")
    Call<List<LanguageItem>> getTrendingLanguage(@Query("language") String language, @Query("since") String time);


    @GET
    Call<ItemResponse> getTrendingAndroid(@Url String url);

    @GET
    Call<UserProfile> getProfile(@Url String url);
}
