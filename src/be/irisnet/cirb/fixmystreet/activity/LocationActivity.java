package be.irisnet.cirb.fixmystreet.activity;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import be.irisnet.cirb.fixmystreet.R;
import be.irisnet.cirb.fixmystreet.constants.IntentAction;
import be.irisnet.cirb.fixmystreet.constants.IntentCategory;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class LocationActivity extends FixMyStreetMapActivity implements LocationListener, LocationChangeListener {
	private MapView map;
	private OverlayItem pointer;
	private LocationItemizedOverlay pointers;
	private LocationManager lm;
	private GeoPoint currLocation;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);
        
        map = (MapView) findViewById(R.id.mapView);
        map.setBuiltInZoomControls(true);
        map.getController().setZoom(24);
        
        lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
    }
	
	public void nextActivity(View v) {
		// currLocation
        Intent intentToSummary = new Intent(IntentAction.DESCRIPTION);
		intentToSummary.addCategory(IntentCategory.CIRB_FIXMYSTREET);
		startActivity(intentToSummary);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	public void onLocationChanged(Location location) {
		Log.i("app", "location changed");

		currLocation = new GeoPoint(
				(int)(location.getLatitude()*1000000),
				(int)(location.getLongitude()*1000000));

		moveCursorTo(currLocation);
		map.getController().animateTo(currLocation);
		
        if (lm != null) {
        	lm.removeUpdates(this);
        	lm = null;
        }
	}

	private void moveCursorTo(GeoPoint point) {
		if (pointer != null) {
			Log.i("app", "move cursor");
			pointers.clear();
		} else {
			Log.i("app", "construct cursor");
	        pointers = new LocationItemizedOverlay(getResources().getDrawable(R.drawable.ic_marker), this);
	        pointers.setLocationChangeListener(this);
	        map.getOverlays().add(pointers);
		}

        pointer = new OverlayItem(point, "Here", "You are here!");
        pointers.addOverlay(pointer);
	}

	@Override
	public void onLocationChange(GeoPoint p) {
		currLocation = p;
		moveCursorTo(currLocation);
        
    	map.getController().animateTo(currLocation);
	}

	@Override public void onProviderDisabled(String provider) {Log.i("app", "provider disabled");}
	@Override public void onProviderEnabled(String provider) {Log.i("app", "provider enabled");}
	@Override public void onStatusChanged(String provider, int status, Bundle extras) {Log.i("app", "provider status changed");}

	
	private class LocationItemizedOverlay extends ItemizedOverlay<OverlayItem> {
		private ArrayList<OverlayItem> pointers = new ArrayList<OverlayItem>();
		private Context context;
		private LocationChangeListener touchListener;
		private Drawable defaultMarker;
		private boolean dragging = false;

		public LocationItemizedOverlay(Drawable m) {
			super(boundCenterBottom(m));
			defaultMarker = m;
		}

		public void clear() {
			pointers.clear();
		}

		public LocationItemizedOverlay(Drawable defaultMarker, Context c) {
			this(defaultMarker);
			context = c;
		}
		public void addOverlay(OverlayItem overlay) {
			pointers.add(overlay);
		    populate();
		}
		
		@Override
		protected OverlayItem createItem(int i) {
		  return pointers.get(i);
		}

		@Override
		public int size() {
			return pointers.size();
		}

		public LocationChangeListener getLocationChangeListener() {
			return touchListener;
		}


		public void setLocationChangeListener(LocationChangeListener touchListener) {
			this.touchListener = touchListener;
		}
		
		public boolean onTap(GeoPoint gp, MapView mv) {
	    	if (touchListener != null) {
	    		touchListener.onLocationChange(gp);
	    		return true;
	    	}
			return super.onTap(gp, mv);
		}
	    @Override
	    public boolean onTouchEvent(MotionEvent event, MapView map) {
	    	if (touchListener != null) {
		        final int x=(int)event.getX();
		        final int y=(int)event.getY();
				GeoPoint p = map.getProjection().fromPixels((int)event.getX(), (int)event.getY());
		        
		        if (dragging && event.getAction() == MotionEvent.ACTION_UP) {
		        	dragging = false;
		        	touchListener.onLocationChange(p);
		    		return true;
		        }

		        if (dragging && event.getAction() == MotionEvent.ACTION_MOVE) {
		        	touchListener.onLocationChange(p);
			    	return true;
		        }
		        if (event.getAction() == MotionEvent.ACTION_DOWN) {
					for (OverlayItem item : pointers) {
						Point point=new Point(0,0);
					    
					    map.getProjection().toPixels(item.getPoint(), point);
					    
					    if (hitTest(item, defaultMarker, x-point.x, y-point.y)) {
					    	dragging = true;
					    	Log.i("app", "hit cursor");
					    	touchListener.onLocationChange(p);
					    	return true;
					    }
					}
		        }
	    	}
	        return false;
	    }
	}
}
