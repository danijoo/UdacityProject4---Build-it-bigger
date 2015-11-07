package com.udacity.gradle.builditbigger;

import com.google.android.gms.ads.AdRequest;

public class AdRequestBuilder {

    public static AdRequest getNewRequest() {
        return new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("AD770D74C4E2AF0FA36FBFD60844FC9E")
                .build();
    }

}
