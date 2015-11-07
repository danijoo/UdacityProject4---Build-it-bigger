package com.udacity.gradle.builditbigger;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import net.headlezz.jokesbackend.myApi.model.Joke;

public class DownloadDialogFragment extends DialogFragment {

    public static final String TAG = DownloadDialogFragment.class.getSimpleName();

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setIndeterminate(true);
        dialog.setMessage(getContext().getString(R.string.dialog_loading));
        return dialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        MyBus.getInstance().register(this);
    }

    @Override
    public void onPause() {
        MyBus.getInstance().unregister(this);
        super.onPause();
    }

    public void onEvent(Joke joke) {
        dismiss();
    }

    public void onEvent(JokeLoaderTask.JokeDownloadException e) {
        dismiss();
    }
}
