package com.android.busroutes.sample.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import com.android.busroutes.sample.R;
import com.android.busroutes.sample.fragment.ImageListFragment;
import com.android.busroutes.sample.model.ImageInfo;
import com.android.busroutes.sample.rest.ApiClient;
import com.android.busroutes.sample.rest.ApiInterface;
import com.android.busroutes.sample.rest.response.ImagesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ImageListFragment.onRouteSelectedListener {

    private final String ARG_IMAGE_INFO = "image_info";
    private List<ImageInfo> infoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadData();
        } else if (savedInstanceState.containsKey(ARG_IMAGE_INFO)){
            infoList = savedInstanceState.getParcelableArrayList(ARG_IMAGE_INFO);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        if (infoList != null && !infoList.isEmpty()) {
            outState.putParcelableArrayList(ARG_IMAGE_INFO, new ArrayList<Parcelable>(infoList));
        }
    }

    private void loadData() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ImagesResponse> responseCall = apiService.getImages();
        responseCall.enqueue(new Callback<ImagesResponse>() {
            @Override
            public void onResponse(Call<ImagesResponse> call, Response<ImagesResponse> response) {
                infoList = response.body().getImages();
                if (infoList != null) {
                    Fragment fragment = ImageListFragment.newInstance(infoList);
                    replaceFragment(fragment, false);
                }
            }

            @Override
            public void onFailure(Call<ImagesResponse> call, Throwable t) {

            }
        });
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStackCheck){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(R.id.routes_container, fragment);
        if(addToBackStackCheck) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onRouteSelected(ImageInfo imageInfo) {
        Intent intent = new Intent(this, ImageDetailsActivity.class);
        intent.putExtra(ImageDetailsActivity.ARG_ROUTE, imageInfo);
        startActivity(intent);
    }
}
