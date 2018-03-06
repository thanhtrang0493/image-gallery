package com.mobiclixgroup.image_gallery.architecture;


import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule extends BaseModule {
    public ApplicationModule(Context context) {
        super(context);
    }

    @Singleton
    @Provides
    Context context(){
        return context;
    }
}

