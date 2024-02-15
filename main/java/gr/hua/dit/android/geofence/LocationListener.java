package gr.hua.dit.android.geofence;

import android.content.Intent;
import android.location.Location;

public interface LocationListener {
    public void onLocationChanged(Location location);

    public void onStatusChanged(String provider, int status);

    public void onProviderEnabled(String provider);

    public void onProviderDisabled(String provider);

    public void onStartCommand(Intent intent, int flags, int startId);
}
