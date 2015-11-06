package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.headlezz.androidjokepresenter.JokePresenterActivity;
import net.headlezz.jokesbackend.myApi.model.Joke;


/**
 * A placeholder fragment containing a simple view.
 */
public abstract class MainActivityFragment extends Fragment implements View.OnClickListener, JokeLoaderTask.JokeLoaderCallback {

    JokeLoaderTask mJokeLoaderTask;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        root.findViewById(R.id.btShowJoke).setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        downloadNewJoke();
    }

    private void downloadNewJoke() {
        mJokeLoaderTask = new JokeLoaderTask(this);
        mJokeLoaderTask.execute();
    }

    @Override
    public void onJokeLoaded(Joke joke) {
        Intent i = new Intent(getContext(), JokePresenterActivity.class);
        i.putExtra(JokePresenterActivity.BUNDLE_ARG_JOKE, joke.getJoke());
        startActivity(i);
    }

    @Override
    public void onError() {
        if (getView() != null)
            Snackbar.make(getView(), R.string.joke_download_error, Snackbar.LENGTH_LONG)
                    .setAction(R.string.retry, this)
                    .show();
    }

    @Override
    public void onStop() {
        if(mJokeLoaderTask != null && !mJokeLoaderTask.isCancelled())
            mJokeLoaderTask.cancel(true);
        super.onStop();
    }
}
