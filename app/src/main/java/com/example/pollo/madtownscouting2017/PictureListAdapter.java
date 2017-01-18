package com.example.pollo.madtownscouting2017;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;

/**
 * Created by 19IsaacD740 on 1/17/2017.
 */
public class PictureListAdapter extends CursorAdapter{
    private LayoutInflater cursorInflater;
    private Context mContext;

    public PictureListAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mContext = context;
    }
    public void bindView(View view, Context context, Cursor cursor){
        try {
            ImageView picPreview = (ImageView) view.findViewById(R.id.imageView2);
            String pathToPic = cursor.getString(cursor.getColumnIndex("pic1"));
            Bitmap img = BitmapFactory.decodeFile(pathToPic);

            ExifInterface ei = new ExifInterface(pathToPic);
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, 6);
            switch(orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    img = rotateImage(img, 90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    img = rotateImage(img, 180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    img = rotateImage(img, 270);
                    break;
                case ExifInterface.ORIENTATION_NORMAL:
                default:
                    break;
            }

            picPreview.setImageBitmap(img);
        } catch (Exception e) {
            Log.d("ERROR", e.toString());
        }
    }
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // R.layout.list_row is your xml layout for each row
        cursorInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = cursorInflater.inflate(R.layout.picturelist, parent, false);
        bindView(v, context, cursor);
        return v;
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix,
                true);
    }
}
