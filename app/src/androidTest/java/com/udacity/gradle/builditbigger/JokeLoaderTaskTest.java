package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import com.google.common.eventbus.Subscribe;

import net.headlezz.jokesbackend.myApi.model.Joke;

public class JokeLoaderTaskTest extends AndroidTestCase {

    SyncronizeTalker talker;

    public void testJokeLoader() {
        JokeLoaderTask task = new JokeLoaderTask();
        talker = new SyncronizeTalker();
        MyBus.getInstance().register(this);
        task.execute();
        talker.doWait();
        MyBus.getInstance().unregister(this);

    }

    @Subscribe
    public void onJokeLoaded(Joke joke) {
        assertNotNull(joke);
        assertTrue(joke.getJoke() != null && !joke.getJoke().isEmpty());
        talker.doNotify();
    }

    @Subscribe
    public void onError() {
        assertTrue(false);
        talker.doNotify();
    }
}
