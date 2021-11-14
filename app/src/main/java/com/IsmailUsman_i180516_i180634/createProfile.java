package com.IsmailUsman_i180516_i180634;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class createProfile extends AppCompatActivity {

    Button save ;
    private CircleImageView profilePhoto;
    private static final int pick_Image = 1;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        save = findViewById(R.id.save);
        // add Profile Photo
        profilePhoto = (CircleImageView) findViewById(R.id.profilePhoto);

        // Old Method of Uploading Picture
        profilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // for Uploading Image from Gallery

                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_OPEN_DOCUMENT);

                startActivityForResult(Intent.createChooser(gallery, "select Picture"), pick_Image);
            }
        });

        final TextView male, female, noInterest;
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        noInterest = findViewById(R.id.notInterested);

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                male.setTextColor(Color.RED);
                male.setBackgroundColor(Color.BLUE);

                female.setTextColor(Color.GREEN);
                female.setBackgroundColor(Color.WHITE);

                noInterest.setTextColor(Color.GREEN);
                noInterest.setBackgroundColor(Color.WHITE);
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                female.setTextColor(Color.RED);
                female.setBackgroundColor(Color.BLUE);

                male.setTextColor(Color.GREEN);
                male.setBackgroundColor(Color.WHITE);

                noInterest.setTextColor(Color.GREEN);
                noInterest.setBackgroundColor(Color.WHITE);
            }
        });

        noInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noInterest.setTextColor(Color.RED);
                noInterest.setBackgroundColor(Color.BLUE);

                female.setTextColor(Color.GREEN);
                female.setBackgroundColor(Color.WHITE);

                male.setTextColor(Color.GREEN);
                male.setBackgroundColor(Color.WHITE);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == pick_Image && resultCode == RESULT_OK){

            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profilePhoto.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}