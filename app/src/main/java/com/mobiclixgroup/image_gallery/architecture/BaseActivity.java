package com.mobiclixgroup.image_gallery.architecture;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.mobiclixgroup.image_gallery.enums.TypeActionBar;

import butterknife.ButterKnife;

public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewResource());
        ButterKnife.bind(this);

        inject();
        setUpActionBar();
        initializeView(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getPresenter() != null) {
            getPresenter().setView(this);
            getPresenter().setRouter(getRouter());
        }
    }

    private void setUpActionBar(){

    }

    protected abstract void initializeView(Bundle savedInstanceState);

    protected abstract void inject();

    protected abstract int getViewResource();

    public abstract TypeActionBar[] getTypeActionBar();

    public abstract String getTitleActionBar();

    protected abstract BasePresenter getPresenter();

    protected abstract BaseRouter getRouter();
}
