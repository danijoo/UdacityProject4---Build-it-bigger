package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

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

    public void onEvent(Joke joke) {
        assertNotNull(joke);
        assertTrue(joke.getJoke() != null && !joke.getJoke().isEmpty());
        talker.doNotify();
    }

    public void onEvent(JokeLoaderTask.JokeDownloadException e) {
        assertTrue(false);
        talker.doNotify();
    }
}
