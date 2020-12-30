package com.example.weatherapp;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.MainActivity.MainActivity;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class SplashScreen extends AppCompatActivity {

    private static final int LOCATION_PERMISSION = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        requestPermissions();
    }

    @AfterPermissionGranted(LOCATION_PERMISSION)
    private void requestPermissions() {
        String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION};
        if (EasyPermissions.hasPermissions(this, perms)) {
            startActivity(new Intent(SplashScreen.this, MainActivity.class));
        } else {
            //if permission is denied
            EasyPermissions.requestPermissions(this, getString(R.string.location_error), LOCATION_PERMISSION, perms);
        }
    }
}