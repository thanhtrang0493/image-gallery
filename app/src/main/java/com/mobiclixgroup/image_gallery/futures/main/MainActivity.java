package com.mobiclixgroup.image_gallery.futures.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.mobiclixgroup.image_gallery.R;
import com.mobiclixgroup.image_gallery.adapter.ListImageAdapter;
import com.mobiclixgroup.image_gallery.architecture.ApplicationModule;
import com.mobiclixgroup.image_gallery.architecture.BaseActivity;
import com.mobiclixgroup.image_gallery.architecture.BasePresenter;
import com.mobiclixgroup.image_gallery.architecture.BaseRouter;
import com.mobiclixgroup.image_gallery.network.OnListener;
import com.mobiclixgroup.image_gallery.enums.TypeActionBar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnTextChanged;

public class MainActivity extends BaseActivity implements MainView, OnListener<String> {

    @Inject
    MainPresenter mainPresenter;

    @Inject
    MainRouter mainRouter;

    MainComponent mainComponent;

    ListImageAdapter imageAdapter;
    List<String> imageList = new ArrayList<>();
    String searchName = "kimtaehee";

    @BindView(R.id.rvListImage)
    RecyclerView rvListImage;

    @BindView(R.id.edtSearch)
    EditText edtSearch;

    @OnTextChanged(R.id.edtSearch)
    public void onSearchTextChanged(final CharSequence s) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String data = searchName;
                if (!s.toString().isEmpty())
                    data = s.toString();
                mainPresenter.getListImage(MainActivity.this, data);
                imageList=new ArrayList<>();
                imageAdapter.updateAdapter(imageList);
            }
        }, 600);
    }

    @Override
    protected void initializeView(Bundle savedInstanceState) {
        initImageAdapter();
        mainPresenter.getListImage(this, searchName);
    }

    private void initImageAdapter() {
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        imageAdapter = new ListImageAdapter(this, imageList);
        rvListImage.setLayoutManager(manager);
        rvListImage.setAdapter(imageAdapter);
    }

    @Override
    protected void inject() {
        mainComponent = DaggerMainComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
        mainComponent.inject(this);
    }

    @Override
    protected int getViewResource() {
        return R.layout.activity_main;
    }

    @Override
    public TypeActionBar[] getTypeActionBar() {
        return new TypeActionBar[0];
    }

    @Override
    public String getTitleActionBar() {
        return null;
    }

    @Override
    protected BasePresenter getPresenter() {
        return mainPresenter;
    }

    @Override
    protected BaseRouter getRouter() {
        return mainRouter;
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showProgressIndicator(boolean isShow) {

    }

    @Override
    public void onResponse(String event) {
        imageList = mainPresenter.readHTML(event);
        imageAdapter.updateAdapter(imageList);
    }

    @Override
    public void onError(int code, String message) {

    }

}
