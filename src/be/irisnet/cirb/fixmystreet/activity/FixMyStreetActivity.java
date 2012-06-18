package be.irisnet.cirb.fixmystreet.activity;

import android.app.Activity;
import be.irisnet.cirb.fixmystreet.R;
import be.irisnet.cirb.fixmystreet.application.FixMyStreetApplication;

/**
 * FixMyStreetActivity to give an easy access to FixMyStreet applications.
 * @author Tdesany
 *
 */
public abstract class FixMyStreetActivity extends Activity {

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
		super.onPause();
	}
	
}
