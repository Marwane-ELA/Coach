package com.marwane.coach.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * https://www.youtube.com/watch?v=wKrYU97Wwg4&t=160s
 * https://www.youtube.com/playlist?list=PLzV8uWUcseN91J9bLuVLnlmWlE6wIWTiY
 * https://developers.google.com/places/web-service/search
 */
public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl)
    {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
