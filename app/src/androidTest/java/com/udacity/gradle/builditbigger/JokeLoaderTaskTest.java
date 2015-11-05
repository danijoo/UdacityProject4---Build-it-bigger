package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import net.headlezz.jokesbackend.myApi.model.Joke;

public class JokeLoaderTaskTest extends AndroidTestCase implements JokeLoaderTask.JokeLoaderCallback {

    SyncronizeTalker talker;

    public void testJokeLoader() {
        JokeLoaderTask task = new JokeLoaderTask(this);
        talker = new SyncronizeTalker();

        task.execute();
        talker.doWait();

    }

    @Override
    public void onJokeLoaded(Joke joke) {
        assertNotNull(joke);
        assertTrue(joke.getJoke() != null && !joke.getJoke().isEmpty());
        talker.doNotify();
    }

    @Override
    public void onError() {
        assertTrue(false);
        talker.doNotify();
    }
}
