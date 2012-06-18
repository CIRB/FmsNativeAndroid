package be.irisnet.cirb.fixmystreet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import be.irisnet.cirb.fixmystreet.R;
import be.irisnet.cirb.fixmystreet.constants.IntentAction;
import be.irisnet.cirb.fixmystreet.constants.IntentCategory;

public class DescriptionActivity extends FixMyStreetActivity {
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Log.i(this.getClass().getName(),"DescriptionActivity is in cycle onCreate");
        //Set content view xml structure
        setContentView(R.layout.fixmystreet_description);
        //Setup event listeners
        setupEventListeners();        
    }
    
    /** Method initializing button listeners. */
    private void setupEventListeners() {
    	Button nextButton = (Button) findViewById(R.id.fixmystreet_description_button);
    	if (nextButton != null) {
    		nextButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					//Store current values
					EditText editText = (EditText) findViewById(R.id.fixmystreet_description_textarea);
					getFixMyStreetApplication().getDossier().setDescription(editText.getText().toString());
					Log.i(this.getClass().getName(), "DescriptionActivity is initializing event listeners");
					Intent intentToSummary = new Intent(IntentAction.SUMMARY);
					intentToSummary.addCategory(IntentCategory.CIRB_FIXMYSTREET);
					startActivity(intentToSummary);					
				}
			});
    	}
    }

    /** Called when the activity is destroyed. */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(this.getClass().getName(),"DescriptionActivity is in cycle onDestroy");
	}

	/** Called when the activity is triggered by a new intent. */
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		Log.i(this.getClass().getName(),"DescriptionActivity is in cycle onNewIntent");
	}

	/** Called when the activity is paused. */
	@Override
	protected void onPause() {
		super.onPause();
		Log.i(this.getClass().getName(),"DescriptionActivity is in cycle onPause");
	}

	/** Called when the activity is restarted. */
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i(this.getClass().getName(),"DescriptionActivity is in cycle onRestart");
	}

	/** Called when the activity is resumed after a pause. */
	@Override
	protected void onResume() {
		super.onResume();
		Log.i(this.getClass().getName(),"DescriptionActivity is in cycle onResume");
	}

	/** Called when the activity is started. */
	@Override
	protected void onStart() {
		super.onStart();
		Log.i(this.getClass().getName(),"DescriptionActivity is in cycle onStart");
	}

	/** Called when the activity is stopped. */
	@Override
	protected void onStop() {
		super.onStop();
		Log.i(this.getClass().getName(),"DescriptionActivity is in cycle onStop");
	}
    
    
    
}