package com.coffeecard.coffeecard.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.widget.GridView;
import android.view.MotionEvent;

import com.coffeecard.coffeecard.Abstract.IActionCallbacks;
import com.coffeecard.coffeecard.R;
import com.coffeecard.coffeecard.Adapters.StampAdapter;

public class MainActivity extends Activity{

    GridView gridview;
    GestureDetectorCompat mDetector;
    FlingListener flingListener;
    Intent infoActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new StampAdapter(this));

        flingListener = new FlingListener();
        mDetector = new GestureDetectorCompat(this, flingListener);
        infoActivity = new Intent(this, InfoActivity.class);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);


    flingListener.callback = new IActionCallbacks() {
        public void swipeLeft() {
            Log.d("onFling:", " Left");
        }

        public void swipeRight() {

            startActivity(infoActivity);
        }
    };
        return super.dispatchTouchEvent(event);
    }

}
