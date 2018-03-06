package com.mobiclixgroup.image_gallery.network;

import android.content.Context;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public abstract class BaseInteractor<ResultType> {
    Context context;
    APIServices apiServices;
    String DOMAIN = "https://search.naver.com/";

    public BaseInteractor(Context context) {
        this.context = context;
    }

    protected abstract Call<ResultType> buildObservable();

    protected APIServices getApiServices() {
        return apiServices;
    }

    protected abstract OnListener getOnListener() ;

    private void setUpRequest() {
        try {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
            okHttpClientBuilder
                    .addInterceptor(logging)
                    .readTimeout(60 * 1000, TimeUnit.MILLISECONDS);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(DOMAIN)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(okHttpClientBuilder.build())
                    .build();
            apiServices = retrofit.create(APIServices.class);

        } catch (Exception e) {

        }
    }

    protected void run() {
        setUpRequest();
        Call<ResultType> result = buildObservable();
        result.enqueue(new Callback<ResultType>() {
            @Override
            public void onResponse(Call<ResultType> call, Response<ResultType> response) {
                if(response.isSuccessful()|| response.code()==200){
                    if(getOnListener()!=null){
                        getOnListener().onResponse((ResultType)response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultType> call, Throwable t) {
                if (getOnListener() != null) {
                    getOnListener().onError(0, t.getMessage().toString());
            }
            }
        });
    }
}
