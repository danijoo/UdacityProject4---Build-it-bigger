package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import net.headlezz.jokesbackend.myApi.MyApi;
import net.headlezz.jokesbackend.myApi.model.Joke;

import java.io.IOException;

public class JokeLoaderTask extends AsyncTask<Void, Void, Joke>  {

    public static final String TAG = JokeLoaderTask.class.getSimpleName();
    static final String ROOT_URL = "http://10.0.3.2:8080/_ah/api/";
    MyApi mApi;

    public JokeLoaderTask() {
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
        Log.d(TAG, "download started");
        try {
            return mApi.tellJoke().execute();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Joke joke) {
        if(isCancelled()) {
            Log.d(TAG, "download cancelled");
            return;
        }

        if(joke != null) {
            MyBus.getInstance().post(joke);
            Log.d(TAG, "download finished");
        } else {
            MyBus.getInstance().post(new JokeDownloadException());
            Log.d(TAG, "download finished with error");
        }
    }

    static class JokeDownloadException extends Exception {}

}
