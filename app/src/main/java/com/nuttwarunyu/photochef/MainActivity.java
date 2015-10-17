package com.nuttwarunyu.photochef;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

public class MainActivity extends AppCompatActivity {

    private Bitmap currentImage;
    private ImageView select_image;

    private Button btn_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_load = (Button) findViewById(R.id.btn_effect);

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent photoPick_intent = new Intent(Intent.ACTION_PICK);
                photoPick_intent.setType("image/*");
                startActivityForResult(photoPick_intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Log.d("TAG : ", "RESULT_OK");
            Uri photoUri = data.getData();
            if (photoUri != null) {
                Intent sendUri = new Intent(getApplicationContext(), EditPhoto.class);
                sendUri.setData(photoUri);
                startActivity(sendUri);
            }
        }
    }
}
