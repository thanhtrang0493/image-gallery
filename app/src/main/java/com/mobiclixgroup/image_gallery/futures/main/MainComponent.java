package com.mobiclixgroup.image_gallery.futures.main;


import android.content.Context;

import com.mobiclixgroup.image_gallery.architecture.ApplicationModule;
import com.mobiclixgroup.image_gallery.architecture.BaseComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface MainComponent extends BaseComponent {
    Context context();

    void inject(MainActivity mainActivity);
}
