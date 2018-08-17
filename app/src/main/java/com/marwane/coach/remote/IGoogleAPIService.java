package com.marwane.coach.remote;

import com.marwane.coach.model.MyPlaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Bronnen:
 * https://www.youtube.com/watch?v=wKrYU97Wwg4&t=160s
 * https://www.youtube.com/playlist?list=PLzV8uWUcseN91J9bLuVLnlmWlE6wIWTiY
 * https://developers.google.com/places/web-service/search
 */
public interface IGoogleAPIService {
    @GET
    Call<MyPlaces>getNearByPlaces(@Url String url);
}
