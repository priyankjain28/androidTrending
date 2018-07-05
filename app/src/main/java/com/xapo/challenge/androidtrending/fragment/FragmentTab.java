package com.xapo.challenge.androidtrending.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.xapo.challenge.androidtrending.R;
import com.xapo.challenge.androidtrending.adapter.LanguageAdapter;
import com.xapo.challenge.androidtrending.api.ApiClient;
import com.xapo.challenge.androidtrending.model.LanguageItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("ValidFragment")
public class FragmentTab extends Fragment {
    private static String TAG = "Language";
    private int position;

    public FragmentTab(int position) {
        this.position = position;
    }

    public FragmentTab() {
    }

    List<LanguageItem> repositories = new ArrayList<LanguageItem>();
    RecyclerView mRecyclerView;
    ProgressDialog progressDialog;
    Spinner spin;
    LanguageAdapter adapter;
    private String language;
    private String time = "daily";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        //ButterKnife.bind(getContext());


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        initLayoutVaraible(view);
        apiCallToGetData(language, time);
    }

    // Creating a new Fragment through newInstance.
    public static FragmentTab newInstance(int position) {
        return new FragmentTab(position);
    }

    private void initLayoutVaraible(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        setLanguageTrend();
        initRecycleView(view);
        showProgressDialog();
    }

    private void setLanguageTrend() {
        switch (position) {
            case 0:
                language = "Kotlin";
                break;
            case 1:
                language = "Java";
                break;
            case 2:
                language = "Dart";
                break;
        }

    }

    private void initRecycleView(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading Repositories...\nGenius is patience. - Newton");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.filter_options, menu);
        MenuItem mitem = menu.findItem(R.id.item1);
        spin = (Spinner) mitem.getActionView();
        setupSpinner(spin);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void setupSpinner(final Spinner spin) {
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getContext(),
                R.layout.custom_spinner_item, getResources().getStringArray(R.array.language_filter));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(myAdapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                time = spin.getSelectedItem().toString();
                apiCallToGetData(language, time);
                Toast.makeText(getContext(), time, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                time = spin.getSelectedItem().toString();
            }
        });

    }

    private void apiCallToGetData(String language, final String time) {
        repositories = new ArrayList<LanguageItem>();
        ApiClient.getSingletonApiClient().getTrendingLanguageData(language, time, new Callback<List<LanguageItem>>() {

            @Override
            public void onResponse(Call<List<LanguageItem>> call, Response<List<LanguageItem>> response) {
                progressDialog.dismiss();
                if (response.code() == 200 && response.isSuccessful()) {
                    repositories = response.body();
                    adapter = new LanguageAdapter(repositories, time);
                    mRecyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<List<LanguageItem>> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

    }
}