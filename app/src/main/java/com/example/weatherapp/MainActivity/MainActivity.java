package com.example.weatherapp.MainActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.example.weatherapp.Base.BaseActivity;
import com.example.weatherapp.Pojo.WeatherResponse;
import com.example.weatherapp.R;
import com.example.weatherapp.Utilities.Constants;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Locale;

public class MainActivity extends BaseActivity<MainActivityContracter.View, MainActivityContracter.Presenter> implements MainActivityContracter.View {
    private FusedLocationProviderClient mFusedLocationClient;
    int PERMISSION_ID = 44;
    private Location currentLocation;
    private TextView location_txt, temperature_txt, humidity_txt, rain_txt, wind_txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            getLastLocation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected MainActivityContracter.Presenter initPresenter() {
        return new MainActivityPresenter();
    }

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                try {
                    getLastLocation();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        task -> {
                            Location location = task.getResult();
                            if (location == null) {
                                requestNewLocationData();
                            } else {
                                currentLocation = location;
                                getUserLocation(currentLocation);
                            }
                        }
                );
            } else {
                showDialog();
                Toast.makeText(this, getString(R.string.turnon_location), Toast.LENGTH_LONG).show();
            }
        } else {
            requestPermissions();
        }
    }

    private void getUserLocation(Location currentLocation) {
        presenter.getWeatherReport(currentLocation.getLatitude(), currentLocation.getLongitude());
        getLocationName(currentLocation.getLatitude(), currentLocation.getLongitude());
    }

    private void getLocationName(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            Address obj = addresses.get(0);
            String currentCity = obj.getSubAdminArea();
            location_txt.setText(currentCity);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            currentLocation = mLastLocation;
            getUserLocation(currentLocation);
        }
    };

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(R.string.weatherrepot_error);
        builder.setPositiveButton(R.string.yes, (dialog, which) -> {
            dialog.dismiss();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);

        });
        builder.setNegativeButton(R.string.abort, (dialog, which) -> {
            dialog.dismiss();
            finish();
        });
        builder.show();
    }

    @Override
    public void setViews() {
        location_txt = findViewById(R.id.location_txt);
        temperature_txt = findViewById(R.id.temperature_txt);
        humidity_txt = findViewById(R.id.humidity_txt);
        rain_txt = findViewById(R.id.rain_txt);
        wind_txt = findViewById(R.id.wind_txt);
    }


    @Override
    public void onCompplete(WeatherResponse weatherResponse) {
        Double temperature = weatherResponse.getMain().getTemp() / 10;
        temperature_txt.setText(getString(R.string.temperature) + String.valueOf(temperature));
        humidity_txt.setText(getString(R.string.humidity) + weatherResponse.getMain().getHumidity());
        rain_txt.setText(getString(R.string.rainchance) + weatherResponse.getClouds().getAll() + " %");
        wind_txt.setText(getString(R.string.wind) + weatherResponse.getWind().getSpeed());
    }

    @Override
    public void error(Throwable error) {

    }



}