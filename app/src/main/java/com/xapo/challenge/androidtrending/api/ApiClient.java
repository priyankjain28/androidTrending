package com.xapo.challenge.androidtrending.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xapo.challenge.androidtrending.model.ItemResponse;
import com.xapo.challenge.androidtrending.model.LanguageItem;
import com.xapo.challenge.androidtrending.model.UserProfile;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //public static final String BASE_URL = "https://api.github.com/";
    public static final String BASE_URL = "https://github-trending-api.now.sh";
    private static final Object mLock = new Object();
    private static ApiClient apiClient;
    static OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build();

    public static Retrofit retrofit = null;

    public static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public static ApiClient getSingletonApiClient() {
        synchronized (mLock) {
            if (apiClient == null)
                apiClient = new ApiClient();

            return apiClient;
        }
    }

    public void getTrendingLanguageData(String language, String time, Callback<List<LanguageItem>> callback) {
        Call<List<LanguageItem>> call = null;
        try {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

            call = apiService.getTrendingLanguage(language, time);

            call.enqueue(callback);
        } catch (Throwable e) {
            Log.d("API Failure", e.getMessage());
            callback.onFailure(call, e);
        }
    }
    public void getTrendingAndroid(Callback<ItemResponse> callback) {
        Call<ItemResponse> call = null;
        try {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            String url = "https://api.github.com/search/repositories?q=android&sort=&order=desc";
            call = apiService.getTrendingAndroid(url);
            call.enqueue(callback);
        } catch (Throwable e) {
            Log.d("API Failure", e.getMessage());
            callback.onFailure(call, e);
        }
    }

    public void getProfileDetail(String url, Callback<UserProfile> callback) {
        Call<UserProfile> call = null;
        try {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            call = apiService.getProfile(url);
            call.enqueue(callback);
        } catch (Throwable e) {
            Log.d("API Failure", e.getMessage());
            callback.onFailure(call, e);
        }
    }
}
