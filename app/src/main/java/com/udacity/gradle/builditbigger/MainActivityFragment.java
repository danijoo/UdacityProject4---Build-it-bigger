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
public abstract class MainActivityFragment extends Fragment implements View.OnClickListener {

    JokeLoaderTask mJokeLoaderTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyBus.getInstance().register(this);
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

    /**
     * starts the async task to download and show a new joke
     * cancels old downloads if theres already a running one
     */
    private void downloadNewJoke() {
        mJokeLoaderTask = new JokeLoaderTask();
        mJokeLoaderTask.execute();
        DownloadDialogFragment frag = new DownloadDialogFragment();
        frag.show(getFragmentManager(), DownloadDialogFragment.TAG);
    }

    /**
     * Shows the joke activity with the given joke
     * @param joke Joke to show
     */
    public void onEvent(Joke joke) {
        Intent i = new Intent(getContext(), JokePresenterActivity.class);
        i.putExtra(JokePresenterActivity.BUNDLE_ARG_JOKE, joke.getJoke());
        startActivity(i);
    }

    /**
     * Prints an error message on the screen if we have a problem with the download
     * @param e Exception
     */
    public void onEvent(JokeLoaderTask.JokeDownloadException e) {
        if (getActivity() != null)
            Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.joke_download_error, Snackbar.LENGTH_LONG)
                    .setAction(R.string.retry, this)
                    .show();
    }

}
