package com.coffeecard.coffeecard.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.MotionEvent;

import com.coffeecard.coffeecard.Abstract.IActionCallbacks;
import com.coffeecard.coffeecard.R;


public class InfoActivity extends Activity{

    GestureDetectorCompat mDetector;
    FlingListener listener;
    Intent mainActivityIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        listener = new FlingListener();
        mDetector = new GestureDetectorCompat(this, listener);
        mainActivityIntent = new Intent(this, MainActivity.class);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        listener.callback = new IActionCallbacks() {
            public void swipeLeft() {
                startActivity(mainActivityIntent);
            }

            public void swipeRight() {
                Log.d("onFling:", " Right");

            }
        };
        return super.dispatchTouchEvent(event);
    }

}
