package com.cumulations.adapterviewdemo.Model;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class RemoteAPI {
    public static final String URL = "https://api.myjson.com/bins/";

    public static Service service=null;

    public static Service getService(){
        if(service==null){
            Retrofit retrofit=new Retrofit.Builder().baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
            service=retrofit.create(Service.class);
        }
        return service;
    }

    public interface Service{
        @GET
        Call<List<Data>> getDataList(@Url String url);
    }
}
