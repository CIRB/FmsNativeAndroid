package be.irisnet.cirb.fixmystreet.activity;

import android.app.ListActivity;
import be.irisnet.cirb.fixmystreet.R;
import be.irisnet.cirb.fixmystreet.application.FixMyStreetApplication;

/**
 * FixMyStreetListActivity to give an easy access to FixMyStreet applications.
 * @author Tdesany
 *
 */
public abstract class FixMyStreetListActivity extends ListActivity {

	/**
	 * getFixMyStreetApplication returns the application managing those activities.
	 * @return our own application type.
	 */
	public FixMyStreetApplication getFixMyStreetApplication() {
		return (FixMyStreetApplication) getApplication();
	}
	
	@Override
	protected void onPause() {		
		this.overridePendingTransition(R.anim.right2left, R.anim.left2right);
		super.onDestroy();
	}
	
}
