package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import net.headlezz.jokesbackend.myApi.MyApi;
import net.headlezz.jokesbackend.myApi.model.Joke;

import java.io.IOException;

public class JokeLoaderTask extends AsyncTask<Void, Void, Joke>  {

    static final String ROOT_URL = "http://10.0.3.2:8080/_ah/api/";
    MyApi mApi;

    JokeLoaderCallback mCallback;

    interface JokeLoaderCallback {
        void onJokeLoaded(Joke joke);
        void onError();
    }

    public JokeLoaderTask(@NonNull JokeLoaderCallback cb) {
        mCallback = cb;
        mApi = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                .setRootUrl(ROOT_URL)
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                }).build();
    }

    @Override
    protected Joke doInBackground(Void... params) {
        try {
            return mApi.tellJoke().execute();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Joke joke) {
        if(isCancelled())
            return;

        if(joke != null) {
            mCallback.onJokeLoaded(joke);
        } else {
            mCallback.onError();
        }
    }
}
