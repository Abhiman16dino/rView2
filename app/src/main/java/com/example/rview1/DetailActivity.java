package com.example.rview1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.bluetooth.BluetoothGattDescriptor.PERMISSION_WRITE;
import static com.example.rview1.MainActivity.EXTRA_CREATOR;
import static com.example.rview1.MainActivity.EXTRA_LIKES;
import static com.example.rview1.MainActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {
Button download;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        download = findViewById(R.id.download);

        Intent intent = getIntent();
        final String imageURL = intent.getStringExtra(EXTRA_URL);
        String createrName = intent.getStringExtra(EXTRA_CREATOR);
        String likeCount = intent.getStringExtra(EXTRA_LIKES);

        ImageView imageView = findViewById(R.id.detailImage);
        TextView textViewCreator = findViewById(R.id.detailuser);
        TextView textViewLikes = findViewById(R.id.detailLikes);

        Picasso.get().load(imageURL).fit().centerInside().into(imageView);
        textViewCreator.setText("Creator: "+createrName);
        textViewLikes.setText("Likes: "+likeCount);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //later



            }
        });

    }

    }




