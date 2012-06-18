package be.irisnet.cirb.fixmystreet.activity;

import com.google.android.maps.GeoPoint;

public interface LocationChangeListener {
	public void onLocationChange(GeoPoint p);
}