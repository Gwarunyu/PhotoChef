package com.nuttwarunyu.photochef;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;

public class EditPhoto extends AppCompatActivity {


    Uri photoUri;
    private Button btn_changeColor1, btn_changeColor2, btn_changeColor3, btn_changeColor4, btn_changeColor5, btn_save;
    private Bitmap currentImage;
    private ImageView img_onShow;


    void BindWidget() {
        btn_changeColor1 = (Button) findViewById(R.id.btn1);
        btn_changeColor2 = (Button) findViewById(R.id.btn2);
        btn_changeColor3 = (Button) findViewById(R.id.btn3);
        btn_changeColor4 = (Button) findViewById(R.id.btn4);
        btn_changeColor5 = (Button) findViewById(R.id.btn5);
        btn_save = (Button) findViewById(R.id.btn_save);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_photo);


        photoUri = getIntent().getData();

        try {
            currentImage = MediaStore.Images.Media.getBitmap(getContentResolver(), photoUri);

        } catch (IOException e) {
            e.printStackTrace();
        }

        img_onShow = (ImageView) findViewById(R.id.imgView);
        img_onShow.setImageBitmap(currentImage);


    }

    @Override
    protected void onStart() {
        super.onStart();

        BindWidget();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                OutputStream outputStream = null;
                File file = new File(extStorageDirectory, "PhotoChef.PNG");
                Bitmap mutableBitmap = currentImage.copy(Bitmap.Config.ARGB_8888, true);

                try {
                    outputStream = new FileOutputStream(file);
                    mutableBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (outputStream != null)
                            outputStream.close();
                        Log.d("OutputStream Close","Filename . ."+ file + "Save Complete");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        btn_changeColor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentImage != null) {

                    Bitmap mutableBitmap = currentImage.copy(Bitmap.Config.ARGB_8888, true);
                    Canvas canvas = new Canvas(mutableBitmap);
                    canvas.drawColor(Color.argb(80, 40, 20, 40));

                    ImageView imageView = (ImageView) findViewById(R.id.imgView);
                    imageView.setImageBitmap(mutableBitmap);
                }


            }
        });

        btn_changeColor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentImage != null) {
                    Bitmap mutableBitmap = currentImage.copy(Bitmap.Config.ARGB_8888, true);
                    Canvas canvas = new Canvas(mutableBitmap);
                    canvas.drawColor(Color.argb(80, 200, 100, 100));

                    ImageView imageView = (ImageView) findViewById(R.id.imgView);
                    imageView.setImageBitmap(mutableBitmap);
                }


            }
        });

    }

}
