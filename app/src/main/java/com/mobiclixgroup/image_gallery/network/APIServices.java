package com.mobiclixgroup.image_gallery.network;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIServices {

    @Headers({
            "Accept: application/json",
            "User-Agent: Your-App-Name",
            "Cache-Control: max-age=640000"
    })
    @GET("search.naver?where=image&sm=tab_jum")
    Call<String> getAllImage(@Query("query") String name);

}
