package be.irisnet.cirb.fixmystreet.activity;

import android.app.Activity;
import be.irisnet.cirb.fixmystreet.application.FixMyStreetApplication;

/**
 * FixMyStreetActivity to give an easy access to FixMyStreet applications.
 * @author Tdesany
 *
 */
public class FixMyStreetActivity extends Activity {

	/**
	 * getFixMyStreetApplication returns the application managing those activities.
	 * @return our own application type.
	 */
	public FixMyStreetApplication getFixMyStreetApplication() {
		return (FixMyStreetApplication) getApplication();
	}
	
}
