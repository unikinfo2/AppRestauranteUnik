package br.com.appmobile.unikinfo.rest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Sergey Shustikov (pandarium.shustikov@gmail.com) at 2015.
 */
public class ImagemClient extends AsyncTask<String, Void, Bitmap> {
    private ImageView img;
    private Bitmap b;
    private String pUrl;

    public ImagemClient(String urlImg, ImageView pImg){
        pUrl = urlImg;
        img  = pImg;
    }

    @Override
    protected Bitmap doInBackground(String... arg0) {
        try {
            URL url = new URL(pUrl);
            InputStream is = new BufferedInputStream(url.openStream());
            b = BitmapFactory.decodeStream(is);
            return b;
        } catch(Exception e){}
        return null;
    }

     @Override
     protected void onPostExecute(Bitmap bitmap) {
         img.setImageBitmap(bitmap);
    }
}