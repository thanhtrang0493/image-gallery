package com.mobiclixgroup.image_gallery.interactor;


import android.content.Context;

import com.mobiclixgroup.image_gallery.network.BaseInteractor;
import com.mobiclixgroup.image_gallery.network.OnListener;

public class GetAllImageByNameInteractor extends BaseInteractor<String> {
    String query;
    OnListener<String> onListener;

    public GetAllImageByNameInteractor(Context context) {
        super(context);
    }

    @Override
    protected retrofit2.Call<String> buildObservable() {
        return getApiServices().getAllImage(query);
    }

    @Override
    protected OnListener getOnListener() {
        return onListener;
    }

    public GetAllImageByNameInteractor execute(String query) {
        this.query = query;
        run();
        return this;
    }

    public GetAllImageByNameInteractor onResponse(OnListener<String> onListener) {
        this.onListener = onListener;
        return this;
    }
}
