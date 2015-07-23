package com.coffeecard.coffeecard;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private List<Drawable> mThumbIds = new ArrayList<>();

    public ImageAdapter(Context c) {
        mContext = c;
        buildImages();

    }

    private void buildImages()
    {
        int visits = 3;

        Drawable purchasedCoffee = mContext.getResources().getDrawable(R.drawable.coffeecup);
        Drawable notPurchasedCoffee = mContext.getResources().getDrawable(R.drawable.xbutton);
        for (int i = 0; i < visits; i++)
        {
            mThumbIds.add(writeOnDrawable(R.drawable.coffeecup, Integer.toString(i)));
            //mThumbIds.add(purchasedCoffee);
        }
        for(int i=visits; i < 10; i++)
        {
            mThumbIds.add(writeOnDrawable(R.drawable.xbutton, Integer.toString(i)));
            //mThumbIds.add(notPurchasedCoffee);
        }
    }

    private LayerDrawable writeOnDrawable(int drawableId, String text){

        Bitmap canvasBitmap = Bitmap.createBitmap(250, 250,
                Bitmap.Config.ARGB_8888);
        // Create a canvas, that will draw on to canvasBitmap.
        Canvas imageCanvas = new Canvas(canvasBitmap);

        // Set up the paint for use with our Canvas
        Paint imagePaint = new Paint();
        imagePaint.setTextAlign(Paint.Align.CENTER);
        imagePaint.setColor(Color.BLUE);
        imagePaint.setTextSize(16f);

        // Draw the image to our canvas
        Drawable d = mContext.getResources().getDrawable(drawableId);
        d.draw(imageCanvas);

        // Draw the text on top of our image
        imageCanvas.drawText(text, 0, 0, imagePaint);

        // Combine background and text to a LayerDrawable
        return new LayerDrawable(
                new Drawable[]{d, new BitmapDrawable(canvasBitmap)});
    }

    public int getCount() {
        return mThumbIds.size();
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

        imageView.setImageDrawable(mThumbIds.get(position));
        return imageView;
    }






}
