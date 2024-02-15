package gr.hua.dit.android.geofence;

import static android.app.Service.START_STICKY;

import static androidx.constraintlayout.widget.Constraints.TAG;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MyService extends AppCompatActivity implements LocationListener{
    private LocationManager locationManager;
    private Location lastLocation;
    private ContentResolver contentResolver;
    private List<Center_regions> centerRegions;






    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onStartCommand(Intent intent, int flags, int startId) {
        startLocationUpdates();
    }



    private List<Center_regions> loadCenterRegionsFromProvider() {
        List<Center_regions> regions = new ArrayList<>();
        Uri uri = Uri.parse("content://your_content_provider_uri/center_regions");
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
//                String regionName = cursor.getString(cursor.getColumnIndex("region_name"));
//                double latitude = cursor.getDouble(cursor.getColumnIndex("latitude"));
//                double longitude = cursor.getDouble(cursor.getColumnIndex("longitude"));
//                regions.add(new Center_regions(regionName, latitude, longitude));
            }
            cursor.close();
        }
        return regions;
    }

    private void startLocationUpdates() {
        try {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 50, locationListener);
        } catch (SecurityException e) {

            Log.e(TAG, "Location permission not granted");
        }
    }

    private void checkLocationWithCenterRegions(Location location) {
        for (Center_regions centerRegion : centerRegions) {
            double distance = calculateDistance(location.getLatitude(), location.getLongitude(),
                    centerRegion.getLatitude(), centerRegion.getLongitude());
            if (distance <= 50) {
                // Device entered the region
                // Log or save the entry time and region details
                Log.d(TAG, "Device entered region with id: " + centerRegion.getId());
            }
        }
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Implementation of Haversine formula
        double earthRadius = 6371000; // meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadius * c;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}