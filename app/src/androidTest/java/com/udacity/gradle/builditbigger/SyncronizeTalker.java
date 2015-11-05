package com.udacity.gradle.builditbigger;

/**
 * Helper class to wait and release in a testcase
 * Source by Maxim Shoustin
 * http://stackoverflow.com/questions/2321829/android-asynctask-testing-with-android-test-framework
 */
public class SyncronizeTalker {
    public void doWait(long l){
        synchronized(this){
            try {
                this.wait(l);
            } catch(InterruptedException e) {
            }
        }
    }



    public void doNotify() {
        synchronized(this) {
            this.notify();
        }
    }


    public void doWait() {
        synchronized(this){
            try {
                this.wait();
            } catch(InterruptedException e) {
            }
        }
    }
}