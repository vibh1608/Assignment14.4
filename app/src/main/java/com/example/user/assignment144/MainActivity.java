package com.example.user.assignment144;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //creating reference of buttons
    Button checkpermission,requestpermission;

    //on create method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting reference with their ID's
        checkpermission = findViewById(R.id.check);
        requestpermission = findViewById(R.id.request);

        //on click of checkpermission button
        checkpermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ispermissionGranted())
                {
                    //displaying toast message
                    Toast.makeText(MainActivity.this,"Permission already granted",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //displaying toast message
                    Toast.makeText(MainActivity.this,"Please provide permission first to access the app",Toast.LENGTH_SHORT).show();
                }
            }

        });

        //on click of requestpermission button
        requestpermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //checking if permission is already granted
                if(!ispermissionGranted())
                {
                    //calling requestPermission  method to request permission
                    requestPermission();
                }
                else
                {
                    //displaying toast message
                    Toast.makeText(MainActivity.this,"Permission already granted",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //method to request permission
    private void requestPermission()
    {
        //requesting permission
        ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.CAMERA},101);
    }

    //method to check if permission is granted or not
    private boolean ispermissionGranted()
    {
        boolean ispermissionGranted=false;

        //storing permission status in result variable
        int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA);

        //checking if permission is granted or not
        if(result == PackageManager.PERMISSION_GRANTED )
        {
           ispermissionGranted=true;
        }

        //returning permission status
        return ispermissionGranted;
    }

    //on request permission action
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==101)
        {
            if(grantResults[0]==0)
            {
                //displaying toast message
                Toast.makeText(MainActivity.this,"Permission granted",Toast.LENGTH_SHORT).show();
            }
            else
            {
                //displaying toast message
                Toast.makeText(MainActivity.this,"Permission denied",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
