package com.coffeecard.coffeecard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class StampAdapter extends BaseAdapter {
    private Context mContext;
    private List<Drawable> stampList = new ArrayList<>();

    public StampAdapter(Context c) {
        mContext = c;
        buildStampList();
    }

    private void buildStampList(){
        int visits = 3;

        for (int i = 0; i < visits; i++)
        {
            stampList.add(validateStamp(R.drawable.coffeecup, Integer.toString(i)));
        }
        for(int i=visits; i < 9; i++)
        {
            stampList.add(mContext.getResources().getDrawable(R.drawable.coffeecup));
        }
    }

    private LayerDrawable validateStamp(int drawableId, String text){

        Drawable d = mContext.getResources().getDrawable(drawableId);

        final int IMAGE_WIDTH = d.getIntrinsicWidth();
        final int IMAGE_HEIGHT = d.getIntrinsicHeight();

        Bitmap canvasBitmap = Bitmap.createBitmap(IMAGE_WIDTH, IMAGE_HEIGHT,
                Bitmap.Config.ARGB_4444);
        // Create a canvas, that will draw on to canvasBitmap.
        Canvas imageCanvas = new Canvas(canvasBitmap);

        // Set up the paint for use with our Canvas
        Paint imagePaint = new Paint();
        imagePaint.setTextAlign(Paint.Align.CENTER);
        imagePaint.setColor(Color.BLUE);
        imagePaint.setTextSize(200);

        // Draw the image to our canvas
        d.draw(imageCanvas);

        // Draw the text on top of our image
        imageCanvas.drawText(text,
                IMAGE_WIDTH / 2,
                IMAGE_HEIGHT / 2,
                imagePaint);

        // Combine background and text to a LayerDrawable
        return new LayerDrawable(
                new Drawable[]{d, new BitmapDrawable(canvasBitmap)});
    }

    public int getCount() {
        return stampList.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageDrawable(stampList.get(position));
        return imageView;
    }
}