package be.irisnet.cirb.fixmystreet.activity;

import be.irisnet.cirb.fixmystreet.R;
import be.irisnet.cirb.fixmystreet.application.FixMyStreetApplication;

import com.google.android.maps.MapActivity;

/**
 * FixMyStreetActivity to give an easy access to FixMyStreet applications.
 * @author Tdesany
 *
 */
public abstract class FixMyStreetMapActivity extends MapActivity {

	/**
	 * getFixMyStreetApplication returns the application managing those activities.
	 * @return our own application type.
	 */
	public FixMyStreetApplication getFixMyStreetApplication() {
		return (FixMyStreetApplication) getApplication();
	}
	
	@Override
	protected void onPause() {		
		this.overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		super.onDestroy();
	}
	
}
