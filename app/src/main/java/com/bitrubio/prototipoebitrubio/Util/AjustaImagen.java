package com.bitrubio.prototipoebitrubio.Util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.widget.ImageView;

import java.io.IOException;

/**
 * Created by Orion on 15/08/2016.
 * Ajusta la imagen en tamaño y rotacion para subirla a servidor
 */
public class AjustaImagen {

    private Uri uri;
    private int orientacion;
    private Bitmap bitmap , newBitmap;
    private Context ctx;
    private ImageView imgView;
    public AjustaImagen() {
        super();
    }
    private static final String[] CONTENT_ORIENTATION = new String[] {
            MediaStore.Images.ImageColumns.ORIENTATION
    };

    public AjustaImagen(Context ctx, ImageView imgView, Uri uri) {
        this.ctx = ctx;
        this.imgView = imgView;
        this.uri = uri;
    }

    public Bitmap ajustarSize50() throws IOException {

        bitmap = MediaStore.Images.Media.getBitmap(ctx.getContentResolver(),uri);
        newBitmap = redimensionarImagenMaximo(bitmap,bitmap.getWidth()-(float) (bitmap .getWidth()*0.5)
                , bitmap .getHeight()-(float) (bitmap .getHeight()*0.5));

        return newBitmap;
    }
    public ImageView rotateImagen(){
        orientacion = getExifOrientation(ctx,uri);
        if (orientacion==90){
            imgView.setRotation(90);
        }else if(orientacion == 180){
            imgView.setRotation(180);
        }else if(orientacion == 270){
            imgView.setRotation(270);
        }else{
            imgView.setRotation(0);
        }
        return imgView;
    }

    public Bitmap redimensionarImagenMaximo(Bitmap mBitmap, float newWidth, float newHeigth) {
        //Redimensionamos
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        float scaleWidth = newWidth / width;
        float scaleHeight =  newHeigth / height;
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
        // recreate the new Bitmap
        return Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, false);
    }
    static int getExifOrientation(Context context, Uri uri) {
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = null;
        try {
            String id = DocumentsContract.getDocumentId(uri);
            id = id.split(":")[1];
            cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    CONTENT_ORIENTATION, MediaStore.Images.Media._ID + " = ?", new String[] { id }, null);
            if (cursor == null || !cursor.moveToFirst()) {
                return 0;
            }

            return cursor.getInt(0);
        } catch (RuntimeException ignored) {
            // If the orientation column doesn't exist, assume no rotation.
            return 0;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }


}
