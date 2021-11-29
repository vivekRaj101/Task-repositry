package com.example.mydemoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="MainGalleryActivity";
    private static final int PERMISSION_REQUEST_CODE = 200;
    private List<String> imagePaths;
    private RecyclerView imagesRV;
    private ImageAdapter imageAdapter;
    private ArrayList<File> myFile;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.gallery_text);
        imagesRV = findViewById(R.id.recycler_view);

        imagePaths = new ArrayList<>();

        if(ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.READ_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED)  {

            // Todo : If Permission Granted Then Show SMS

        } else {
            // Todo : Then Set Permission
            final int REQUEST_CODE_ASK_PERMISSIONS = 123;
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, REQUEST_CODE_ASK_PERMISSIONS);
        }

        display();


    }

    private ArrayList<File> findImages(File file) {

        ArrayList<File> arrayList=new ArrayList<>();

        File[] imageFile=file.listFiles();

        if(imageFile!=null) {
            for (File singleImage : imageFile) {
                if (singleImage.isDirectory() && !singleImage.isHidden()) {
                    arrayList.addAll(findImages(singleImage));
                } else {
                    if (singleImage.getName().endsWith(".jpg") ||
                            singleImage.getName().endsWith(".png") ||
                            singleImage.getName().endsWith(".webp")) {
                        arrayList.add(singleImage);
                    }
                }
            }
        }
        else{
            textView.setVisibility(View.GONE);
            textView.setText("No Image");
        }

        return arrayList;
    }

    public void display(){
        myFile=findImages(Environment.getExternalStorageDirectory());

        for (int i=0;i<myFile.size();i++){
            imagePaths.add(String.valueOf(myFile.get(i)));

            imageAdapter=new ImageAdapter(imagePaths);
            imagesRV.setAdapter(imageAdapter);
            imagesRV.setLayoutManager(new GridLayoutManager(this,3));

        }
    }


}