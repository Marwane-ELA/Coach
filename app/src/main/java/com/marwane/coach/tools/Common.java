package com.marwane.coach.tools;

import com.marwane.coach.remote.IGoogleAPIService;
import com.marwane.coach.remote.RetrofitClient;

/**
 * Bronnen:
 * https://www.youtube.com/watch?v=wKrYU97Wwg4&t=160s
 * https://www.youtube.com/playlist?list=PLzV8uWUcseN91J9bLuVLnlmWlE6wIWTiY
 * https://developers.google.com/places/web-service/search
 */
public class Common {

    private static final String GOOGLE_API_URL = "https://maps.googleapis.com/";

    public static IGoogleAPIService getGoogleAPIService(){
        return RetrofitClient.getClient(GOOGLE_API_URL).create(IGoogleAPIService.class);
    }
}
