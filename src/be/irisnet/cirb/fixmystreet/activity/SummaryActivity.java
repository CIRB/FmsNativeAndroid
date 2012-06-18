package be.irisnet.cirb.fixmystreet.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import be.irisnet.cirb.fixmystreet.R;
import be.irisnet.cirb.fixmystreet.constants.IntentAction;
import be.irisnet.cirb.fixmystreet.constants.IntentCategory;

import com.google.android.maps.GeoPoint;

/**
 * FixmyStreet activity designed to display user session content summary
 * @author Tdesany
 *
 */
public class SummaryActivity extends FixMyStreetActivity implements AdapterView.OnItemClickListener {
    
	private ArrayAdapter<String> adapter;
	private static final int POSITION_TYPE 			= 0;
	private static final int POSITION_GEO 			= 1; 
	private static final int POSITION_PICTURE 		= 2;
	private static final int POSITION_DESCRIPTION 	= 3;
	 
	
	/**
	 * Click listener implementation for list of summary elements.
	 * @param l the list
	 * @param v the view
	 * @param position the selected position
	 * @param id the id.
	 */	
    public void onItemClick(AdapterView l, View v, int position, long id) {
        Log.i(this.getClass().getName(),"Item Clicked at position: "+position);
        if (position == POSITION_TYPE) {
        	Log.i(this.getClass().getName(), "Returning to type activity");
			Intent intentToSummary = new Intent(IntentAction.TYPE);
			intentToSummary.addCategory(IntentCategory.CIRB_FIXMYSTREET);
			startActivity(intentToSummary);
        } if (position == POSITION_PICTURE) {
        	Log.i(this.getClass().getName(), "Returning to picture activity");
			Intent intentToSummary = new Intent(IntentAction.PICTURE);
			intentToSummary.addCategory(IntentCategory.CIRB_FIXMYSTREET);
			startActivity(intentToSummary);
        } else if (position == POSITION_GEO) {
        	Log.i(this.getClass().getName(), "Returning to geo activity");
			Intent intentToSummary = new Intent(IntentAction.GEO);
			intentToSummary.addCategory(IntentCategory.CIRB_FIXMYSTREET);
			startActivity(intentToSummary);
        } else if (position == POSITION_DESCRIPTION) {
        	Log.i(this.getClass().getName(), "Returning to description activity");
			Intent intentToSummary = new Intent(IntentAction.DESCRIPTION);
			intentToSummary.addCategory(IntentCategory.CIRB_FIXMYSTREET);
			startActivity(intentToSummary);
        }
    }
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(this.getClass().getName(),"SummaryActivity is in cycle onCreate");
        //Set content view xml structure
        setContentView(R.layout.fixmystreet_summary);
        //Link an adapter to the ViewList.
        ArrayList<String> entries = new ArrayList<String>(Arrays.asList(""));
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, entries);
        Map<String, Object> current = new HashMap<String, Object>();
        List<Map<String, Object>> values = new ArrayList<Map<String,Object>>();        
        //Add type row
        current.put("title", (this.getFixMyStreetApplication().getDossier().getType()!=null)?this.getFixMyStreetApplication().getDossier().getType():" ");
        current.put("image", R.drawable.category);
        values.add(current);
        //Add picture row
        current = new HashMap<String, Object>();
        current.put("title", (this.getFixMyStreetApplication().getDossier().getPicture()!=null)?this.getFixMyStreetApplication().getDossier().getPicture():" ");
        current.put("image", R.drawable.photo);
        values.add(current);
        //Add geo row
        current = new HashMap<String, Object>();
        current.put("title", (this.getFixMyStreetApplication().getDossier().getLocation()!=null)?this.getFixMyStreetApplication().getDossier().getLocation():" ");
        current.put("image", R.drawable.map);
        values.add(current);
        //Add description row
        current = new HashMap<String, Object>();
        current.put("title", (this.getFixMyStreetApplication().getDossier().getDescription()!=null)?this.getFixMyStreetApplication().getDossier().getDescription():" ");
        current.put("image", R.drawable.description);
        values.add(current);
        
        
        SimpleAdapter simpleAdapter = new SimpleAdapter(
        		this.getApplicationContext(),         		
        		values,
        		R.layout.fixmystreet_summary_listitem, 
        		new String[]{"title", "image"},
        		new int[]{R.id.summary_item_text, R.id.summary_item_image});
        ListView listView = (ListView) findViewById(R.id.fixmystreet_summary_List);
        listView.setAdapter(simpleAdapter);
        //Add item selection listener
        listView.setOnItemClickListener(this);
    }

    /** Called when the activity is destroyed. */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(this.getClass().getName(),"SummaryActivity is in cycle onDestroy");
	}

	/** Called when the activity is triggered by a new intent. */
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		Log.i(this.getClass().getName(),"SummaryActivity is in cycle onNewIntent");
	}

	/** Called when the activity is paused. */
	@Override
	protected void onPause() {
		super.onPause();
		Log.i(this.getClass().getName(),"SummaryActivity is in cycle onPause");
	}

	/** Called when the activity is restarted. */
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i(this.getClass().getName(),"SummaryActivity is in cycle onRestart");
	}

	/** Called when the activity is resumed after a pause. */
	@Override
	protected void onResume() {
		super.onResume();
		Log.i(this.getClass().getName(),"SummaryActivity is in cycle onResume");
		
		adapter.setNotifyOnChange(true);		
		adapter.clear();
		
		String type 		= this.getFixMyStreetApplication().getDossier().getType();
		GeoPoint location 	= this.getFixMyStreetApplication().getDossier().getLocation();
		File picture 		= this.getFixMyStreetApplication().getDossier().getPicture();
		String description 	= this.getFixMyStreetApplication().getDossier().getDescription();
		
		adapter.add((type!=null)?type:"No Type Selected");
		adapter.add((location!=null)?location.toString():"No Location Selected");
		adapter.add((picture!=null)?picture.toString():"No Picture Selected");
		adapter.add((description!=null)?description:"No Description Selected");
		
		//ListView listView = (ListView) findViewById(R.id.fixmystreet_summary_List);
		//listView.setAdapter(adapter);
	}

	/** Called when the activity is started. */
	@Override
	protected void onStart() {
		super.onStart();
		Log.i(this.getClass().getName(),"SummaryActivity is in cycle onStart");
	}

	/** Called when the activity is stopped. */
	@Override
	protected void onStop() {
		super.onStop();
		Log.i(this.getClass().getName(),"SummaryActivity is in cycle onStop");
	}
    
    
    
}