package gr.hua.dit.android.geofence;// MapActivity.java

import android.Manifest;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import gr.hua.dit.android.geofence.R;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener{

    private GoogleMap myMap;
    private List<Circle> circles = new ArrayList<>();
    FloatingActionButton add_loc;
    Button cancel_button;
    Button start_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_maps);
        cancel_button = findViewById(R.id.cancell_button);
        start_button = findViewById(R.id.start_button);

        try {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            start_button.setOnClickListener(view->{
                ContentResolver resolver = this.getContentResolver();
                Uri uri = Uri.parse("content://gr.hua.dit.android.geofence/center_regions/");
            });


        }catch (Exception e) {
    // Handle the exception
    e.printStackTrace(); // Print the stack trace to the console for debugging

            }

    }

    @Override
    protected void onStart(){
        super.onStart();
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            getLastLocation();
        }else{
            askLocationPermission();
        }
    }

    private void getLastLocation() {

    }

    private void askLocationPermission(){}
    @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;
        myMap.setOnMapLongClickListener(this);
        // Get the current location (you need to implement this method)

        LatLng currentLocation = getCurrentLocation();

        if (currentLocation != null) {
            myMap.addMarker(new MarkerOptions().position(currentLocation).title("Current Location"));
            myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
        }
//        RegionsDB dbHelper = new RegionsDB(getApplicationContext());
//        add_loc = findViewById(R.id.add_loc_button);
//        add_loc.setOnClickListener(view->{
//            String region = currentLocation.toString();
//
//            SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//            db.execSQL("INSERT VALUES INTO " + dbHelper);
//        });
    }

    // Implement a method to get the current location (you can use Google Location Services or other methods)
    private LatLng getCurrentLocation() {
        // Implement the logic to get the current location here
        // For example, you can use FusedLocationProviderClient
        MapActivity task = new MapActivity();
        // Return a LatLng object with the current latitude and longitude
        return new LatLng(37.7749, -122.4194);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }


    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        Circle circle = myMap.addCircle(new CircleOptions()
                .center(latLng)
                .radius(100)
                .strokeColor(Color.RED)
                .fillColor(Color.TRANSPARENT));
        circles.add(circle);
//        cancel_button.setVisibility(View.INVISIBLE);
//        start_button.setVisibility(View.INVISIBLE);

    }


}
