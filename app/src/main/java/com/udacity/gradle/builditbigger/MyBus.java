package com.udacity.gradle.builditbigger;

import de.greenrobot.event.EventBus;

public class MyBus {

    private static EventBus bus = new EventBus();

    public static EventBus getInstance() {
        return bus;
    }
}
