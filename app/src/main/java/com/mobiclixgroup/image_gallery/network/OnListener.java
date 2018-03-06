package com.mobiclixgroup.image_gallery.network;


public interface OnListener<Type> {
    void onResponse(Type event);

    void onError(int code, String message);
}
