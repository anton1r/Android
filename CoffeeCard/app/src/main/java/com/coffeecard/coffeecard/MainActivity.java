package com.coffeecard.coffeecard;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.widget.GridView;
import android.view.MotionEvent;

public class MainActivity extends Activity {

    GridView gridview;
    GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new StampAdapter(this));

        mDetector = new GestureDetectorCompat(this, new FlingListener());
    }

//    @Override
//        public boolean onTouchEvent(MotionEvent event){
//        Log.d("TONY","Touch Event detected");
//        this.mDetector.onTouchEvent(event);
//        return super.onTouchEvent(event);
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }

    class FlingListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d("TONY", "onDown: " + event.toString());
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            int MAJOR_MOVE = 100;
            Log.d("TONY", "onFling: " + event1.toString() + event2.toString());
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
        }

        /**
         * On swipe left.
         */
        public void moveLeft() {
        }
    }

}
