package com.coffeecard.coffeecard.Activities;

import android.content.Intent;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.coffeecard.coffeecard.Abstract.IActionCallbacks;

public class FlingListener extends GestureDetector.SimpleOnGestureListener {

    IActionCallbacks callback;

    @Override
    public boolean onDown(MotionEvent event) {
        Log.d("TONY", "onDown: " + event.toString());
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {

        int MAJOR_MOVE = 50;
        Log.d("onFling: " , event1.toString() + event2.toString());
        int dx = (int) (event2.getX() - event1.getX());
        // don't accept the fling if it's too short
        // as it may conflict with a button push
        if (Math.abs(dx) > MAJOR_MOVE && Math.abs(velocityX) > Math.abs(velocityY)) {
            if (velocityX > 0) {
                moveRight();
            } else {
                moveLeft();
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * On swipe right.
     */
    public void moveRight() {
        Log.d("TONY:","Listener RIGHT");
        callback.swipeRight();
    }


    /**
     * On swipe left.
     */
    public void moveLeft() {
        Log.d("TONY"," Listener LEFT");
        callback.swipeLeft();
    }
}

