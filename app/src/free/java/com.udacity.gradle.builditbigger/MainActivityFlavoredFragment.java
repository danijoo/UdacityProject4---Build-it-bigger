package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import net.headlezz.jokesbackend.myApi.model.Joke;

public class MainActivityFlavoredFragment extends MainActivityFragment {

    public static String TAG = MainActivityFlavoredFragment.class.getSimpleName();

    InterstitialAd mInterstitial;
    Joke mJoke;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mInterstitial = new InterstitialAd(getContext());
        mInterstitial.setAdUnitId(getString(R.string.banner_ad_unit_id));
        mInterstitial.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                loadNextInterstitial();
                MainActivityFlavoredFragment.super.showJoke(mJoke);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }
        });
        loadNextInterstitial();
    }

    private void loadNextInterstitial() {
        mInterstitial.loadAd(AdRequestBuilder.getNewRequest());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout view = (RelativeLayout) super.onCreateView(inflater, container, savedInstanceState);
        attachBannerAd(view);
        return view;
    }

    private void attachBannerAd(RelativeLayout rootView) {
        AdView adView = (AdView) LayoutInflater.from(rootView.getContext()).inflate(R.layout.adview, rootView, false);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        adView.setLayoutParams(params);
        rootView.addView(adView);
        adView.loadAd(AdRequestBuilder.getNewRequest());
    }

    @Override
    public void showJoke(Joke joke) {
        mJoke = joke;
        if(mInterstitial.isLoaded())
            mInterstitial.show();
        else
            super.showJoke(joke);
    }
}
