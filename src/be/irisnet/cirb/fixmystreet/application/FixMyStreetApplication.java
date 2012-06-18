package be.irisnet.cirb.fixmystreet.application;

import be.irisnet.cirb.fixmystreet.model.FMSDossier;
import android.app.Application;

/**
 * FixMyStreetApplication is a custom made implementation used to store session
 * objects.
 * 
 * @author Tdesany
 * 
 */
public class FixMyStreetApplication extends Application {

	// The object containing the encoded data.
	private FMSDossier dossier;
	
	@Override
	public void onCreate() {
		super.onCreate();
		dossier = new FMSDossier();		
	}

	public FMSDossier getDossier() {
		return dossier;
	}

}
