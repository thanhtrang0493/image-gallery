package com.mobiclixgroup.image_gallery.architecture;


import android.content.Context;

public abstract class BaseRouter {
    protected Context context;

    public BaseRouter(Context context) {
        this.context = context;
    }
}
