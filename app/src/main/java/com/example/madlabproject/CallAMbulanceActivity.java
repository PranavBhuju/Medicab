package com.example.madlabproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import android.view.Window;
public class CallAMbulanceActivity extends AppCompatActivity implements LocationListener {

    TextView addr;
    TextView latitude;
    TextView longitude;
    Button call;
    int flag=0;
    LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_call_ambulance);

        addr=findViewById(R.id.address);
        latitude=findViewById(R.id.latitude);
        longitude=findViewById(R.id.longitude);
        call=findViewById(R.id.callbtn);


        if(ContextCompat.checkSelfPermission(CallAMbulanceActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(CallAMbulanceActivity.this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation();
                flag++;
                if(flag==2){
                    call.setText("Proceed");
                }else if (flag==3){
                    String dblat = latitude.getText().toString();
                    String dblon = longitude.getText().toString();
                    String dbadd = addr.getText().toString();
                    Data dbs = new Data(getApplicationContext(),"location",null,1);
                    dbs.register(dblat,dblon,dbadd);
                    startActivity(new Intent(CallAMbulanceActivity.this,WaitActivity.class));
                    Toast.makeText(getApplicationContext(), "Ambulance is on its way. Please check some first aid tips while we reach you.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @SuppressLint("MissingPermission")
    private void getLocation(){
        try{
            locationManager=(LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,CallAMbulanceActivity.this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        String ab= String.valueOf(location.getLatitude());
        String bc = String.valueOf(location.getLongitude());
        try {
            Geocoder geocoder = new Geocoder(CallAMbulanceActivity.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            String address = addresses.get(0).getAddressLine(0);
            addr.setText(address);
            latitude.setText(ab.toString());
            longitude.setText(bc.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }
}