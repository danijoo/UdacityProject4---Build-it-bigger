package net.headlezz.androidjokepresenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class JokePresenterActivity extends AppCompatActivity {

    public static final String BUNDLE_ARG_JOKE = "joke";

    TextView tvJokes;
    String joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_presenster_activity);
        tvJokes = (TextView) findViewById(R.id.tvJokes);

        if (savedInstanceState != null)
            setJokeFromBundle(savedInstanceState);
        else
            setJokeFromBundle(getIntent().getExtras());
    }

    /**
     * sets the joke from the bundle to the activity
     *
     * @param b undle
     */
    private void setJokeFromBundle(Bundle b) {
        if (b.containsKey(BUNDLE_ARG_JOKE)) {
            joke = b.getString(BUNDLE_ARG_JOKE);
            tvJokes.setText(joke);
        } else
            throw new RuntimeException(JokePresenterActivity.class.getSimpleName() + " started without a joke!");
    }

    /**
     * Updated the share intent of the share action provider
     */
    private void updateShareIntent(ShareActionProvider provider) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, joke);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_intent_title));
        provider.setShareIntent(shareIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.joke_presenter_activity_menu, menu);
        MenuItem item = menu.findItem(R.id.menu_item_share);
        ShareActionProvider mShareProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        updateShareIntent(mShareProvider);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(BUNDLE_ARG_JOKE, joke);
        super.onSaveInstanceState(outState);
    }
}
