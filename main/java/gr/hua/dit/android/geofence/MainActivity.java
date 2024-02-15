package gr.hua.dit.android.geofence;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.location.Location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {
    private GoogleMap myMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Button1 = findViewById(R.id.button1);
        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a Uri for the location (latitude and longitude)
                // Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194?q=San+Francisco");

                // Create an Intent to open Google Maps with the specified location
                // Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                // Set the package explicitly to ensure it opens in the Google Maps app
                // mapIntent.setPackage("com.google.android.apps.maps");

                // Check if there is an app to handle the Intent before starting
                // if (mapIntent.resolveActivity(getPackageManager()) != null) {
                // startActivity(mapIntent);
               /* SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                if (mapFragment != null) {
                    mapFragment.getMapAsync(MainActivity.this);
                }*/
                openMapActivity();

            }
        });

    }
    private void openMapActivity(){
       /* Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194?q=San+Francisco");
         Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(MainActivity.this);
        }*/
        Intent mapIntent = new Intent(this,MapActivity.class);
        startActivity(mapIntent);
    }}
    /*
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        myMap = googleMap;

        LatLng sydney = new LatLng(-34, 151);
        myMap.addMarker(new MarkerOptions().position(sydney).title("Sydney"));
        myMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
*/