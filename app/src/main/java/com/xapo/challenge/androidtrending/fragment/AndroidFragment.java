package com.xapo.challenge.androidtrending.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.xapo.challenge.androidtrending.activity.MainActivity;
import com.xapo.challenge.androidtrending.adapter.ItemAdapter;
import com.xapo.challenge.androidtrending.api.ApiClient;
import com.xapo.challenge.androidtrending.model.ItemResponse;
import com.xapo.challenge.androidtrending.utils.DateComparator;
import com.xapo.challenge.androidtrending.utils.ForksComparator;
import com.xapo.challenge.androidtrending.utils.StarsComparator;

import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AndroidFragment extends Fragment {
    private static String TAG = "Android";
    ProgressDialog progressDialog;
    RecyclerView mRecyclerView;
    String filter= "Best match";
    ItemResponse repositories;
    private ItemAdapter itemAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setActionBarTitle("Android Trending");
        View view = inflater.inflate(R.layout.fragment_android, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        filter = "Best match";
        setHasOptionsMenu(true);
        initLayoutVaraible(view);
        apiCallToGetData(filter);
    }

    private void initLayoutVaraible(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        showProgressDialog();
        initRecycleView(view);
    }

    private void initRecycleView(View view) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        menu.clear();
        inflater.inflate(R.menu.filter_android, menu);
        MenuItem mitem = menu.findItem(R.id.item);
        Spinner spin = (Spinner) mitem.getActionView();
        setupSpinner(spin);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading Repositories...\nGenius is patience. - Newton");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void setupSpinner(final Spinner spin) {
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getContext(),
                R.layout.custom_spinner_item, getResources().getStringArray(R.array.android_filter));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(myAdapter);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                filter = spin.getSelectedItem().toString();
                checkFilterValue(filter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void checkFilterValue(String filter) {
        Log.d(TAG, "Android Filter : " + filter);
        switch (filter) {
            case "Best match":
                Toast.makeText(getContext(), filter, Toast.LENGTH_SHORT).show();
                apiCallToGetData("sort");
                break;
            case "Most stars":
                Toast.makeText(getContext(), filter, Toast.LENGTH_SHORT).show();
                Collections.sort(repositories.getItems(), new StarsComparator());
                itemAdapter.notifyDataSetChanged();
                break;
            case "Fewest stars":
                Toast.makeText(getContext(), filter, Toast.LENGTH_SHORT).show();
                Collections.sort(repositories.getItems(), Collections.reverseOrder(new StarsComparator()));
                itemAdapter.notifyDataSetChanged();
                break;
            case "Most forks":
                Toast.makeText(getContext(), filter, Toast.LENGTH_SHORT).show();
                Collections.sort(repositories.getItems(), new ForksComparator());
                itemAdapter.notifyDataSetChanged();
                break;
            case "Fewest forks":
                Toast.makeText(getContext(), filter, Toast.LENGTH_SHORT).show();
                Collections.sort(repositories.getItems(), Collections.reverseOrder(new ForksComparator()));
                itemAdapter.notifyDataSetChanged();
                    break;
            case "Recently updated":
                Toast.makeText(getContext(), filter, Toast.LENGTH_SHORT).show();
                Collections.sort(repositories.getItems(), new DateComparator());
                itemAdapter.notifyDataSetChanged();
                break;

        }

    }

    private void apiCallToGetData(final String filter) {
        ApiClient.getSingletonApiClient().getTrendingAndroid(new Callback<ItemResponse>() {

            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                if (response.code() == 200 && response.isSuccessful()) {
                    repositories = response.body();
                    itemAdapter = new ItemAdapter(repositories.getItems());
                    mRecyclerView.setAdapter(itemAdapter);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

    }
}