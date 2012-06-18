package be.irisnet.cirb.fixmystreet.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import be.irisnet.cirb.fixmystreet.R;

public class ExerciceFMSActivity extends FixMyStreetActivity {
	/** Called when the activity is first created. */
	
	private static final String TAG = "ExerciceFMSActivity";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.v(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void listReports(View view) {
		Intent intent = new Intent(getBaseContext(), ListReportsActivity.class);
		startActivity(intent);
	}
}