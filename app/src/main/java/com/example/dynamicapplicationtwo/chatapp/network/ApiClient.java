package com.example.dynamicapplicationtwo.chatapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl("Fcm Api Key") //ENter your fcm api key here
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
