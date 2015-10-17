package com.nuttwarunyu.photochef;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.camera2.params.Face;
import android.media.FaceDetector;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import java.util.jar.Attributes;

/**
 * Created by Dell-NB on 5/10/2558.
 */
public class FaceView extends View {

    private Bitmap mBitmap;
    private SparseArray<com.google.android.gms.vision.face.Face> mFaces;


    public FaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }


    void setContent(Bitmap bitmap, SparseArray<com.google.android.gms.vision.face.Face> faces) {
        mBitmap = bitmap;
        mFaces = faces;
        Log.d("Tag :", "Set Content");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("Tag","onDraw");
       // if ((mBitmap != null) && (mFaces != null)) {
       //     double scale = drawBitmap(canvas);
       //   drawFaceRectangle(canvas, scale);

    }


    private void drawFaceRectangle(Canvas canvas, double scale) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        for (int i = 0; i < mFaces.size(); ++i) {
            com.google.android.gms.vision.face.Face face = mFaces.valueAt(i);
            float x1 = face.getPosition().x;
            float y1 = face.getPosition().y;

            float x2 = x1 + face.getWidth();
            float y2 = y1 + face.getHeight();

            canvas.drawRoundRect(new RectF(x1 * (float) scale,
                    y1 * (float) scale, x2 * (float) scale, y2 * (float) scale), 2, 2, paint);
        }
    }

    private double drawBitmap(Canvas canvas) {

        double viewWidth = canvas.getWidth();
        double viewHeight = canvas.getHeight();
        double imageWidth = mBitmap.getWidth();
        double imageHeight = mBitmap.getHeight();
        double scale = Math.min(viewWidth / imageWidth, viewHeight / imageHeight);

        Rect destBounds = new Rect(0, 0, (int) (imageWidth * scale), (int) (imageHeight * scale));
        canvas.drawBitmap(mBitmap, null, destBounds, null);
        return scale;
    }



}
