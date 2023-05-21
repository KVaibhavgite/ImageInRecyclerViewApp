package com.example.imageinrecyclerviewapp;



import static android.Manifest.permission.READ_MEDIA_IMAGES;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManagerRecyclerView;
    private RecyclerViewCustomAdapter recyclerViewCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,
                new String[]{READ_MEDIA_IMAGES},
                PackageManager.PERMISSION_GRANTED);
        recyclerView = findViewById(R.id.recyclerView);
        // Liner Layout
        layoutManagerRecyclerView = new LinearLayoutManager(MainActivity.this);
        // GRID Layout
        //layoutManagerRecyclerView = new GridLayoutManager(MainActivity.this, 2);

        recyclerView.setLayoutManager(layoutManagerRecyclerView);
    }

    public void buttonRecyclerViewUpdate(View view){
        StorageManager storageManager = (StorageManager) getSystemService(STORAGE_SERVICE);
        StorageVolume storageVolume = storageManager.getStorageVolumes().get(0); // internal memory/ storage

        File fileImage = new File(storageVolume.getDirectory().getPath() + "/Download/image.jpg");


        File fileImage1 = new File(storageVolume.getDirectory().getPath() + "/Download/images.jpg");



        Bitmap bitmap = BitmapFactory.decodeFile(fileImage.getPath());
        Bitmap bitmap1 = BitmapFactory.decodeFile(fileImage1.getPath());

        Bitmap[] bitmaps = {bitmap, bitmap1};
        recyclerViewCustomAdapter = new RecyclerViewCustomAdapter(bitmaps);
        recyclerView.setAdapter(recyclerViewCustomAdapter);
    }
}