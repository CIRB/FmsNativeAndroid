package be.irisnet.cirb.fixmystreet.activity;

import java.util.Arrays;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import be.irisnet.cirb.fixmystreet.constants.IntentAction;
import be.irisnet.cirb.fixmystreet.constants.IntentCategory;

public class ListReportsActivity extends FixMyStreetListActivity{

	private static final String TAG = "ListReportsActivity";

	String[] listItems = { "accident", "meurtre", "hug", "nid de poule", "tué",
			"dégats", "excès de vitesse", "trou dans le mur", "effondrement",
			"meurtre", "hug", "nid de poule", "tué", "dégats",
			"j'ai marché dans un caca de chien", "excès de vitesse",
			"trou dans le mur", "effondrement", "meurtre", "hug",
			"nid de poule", "perdu", "tué", "dégats", "excès de vitesse",
			"un policier m'a regardé de travers", "trou dans le mur",
			"effondrement", "pluie", "y a une route de barrée", "meurtre",
			"hug", "nid de poule", "tué", "dégats", "excès de vitesse",
			"trou dans le mur", "effondrement", "kikoo", "lol", ":)" };

	private ArrayAdapter<String> mAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v(TAG, "ListReportsActivity");

		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, listItems);
		Arrays.sort(listItems);

		getListView().setFastScrollEnabled(true);
		getListView().setScrollingCacheEnabled(true);

		MyIndexerAdapter adapter = new MyIndexerAdapter(
				getApplicationContext(), android.R.layout.simple_list_item_1,
				listItems);
		setListAdapter(adapter);
		mAdapter = adapter;
	}

	@Override
	protected void onListItemClick(ListView list, View view, int position,
			long id) {
		Log.v(TAG, "onListItemClick");
		final String report = mAdapter.getItem(position);
		final Intent intent = new Intent("jonathan");
		intent.putExtra("type", report);
		
		//Store the selected type
		this.getFixMyStreetApplication().getDossier().setType(report);
		
		Intent intentToSummary = new Intent(IntentAction.PICTURE);
		intentToSummary.addCategory(IntentCategory.CIRB_FIXMYSTREET);
		startActivity(intentToSummary);
	}

}
