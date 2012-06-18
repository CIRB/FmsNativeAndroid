package be.irisnet.cirb.fixmystreet.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

class MyIndexerAdapter extends ArrayAdapter<String> implements SectionIndexer {

	private static final String TAG = "MyIndexerAdapter";

	String[] myElements;
	HashMap<String, Integer> alphaIndexer;
	Context mContext;
	int mTextViewResourceId;

	String[] sections;

	public MyIndexerAdapter(Context context, int textViewResourceId,
			String[] objects) {
		super(context, textViewResourceId, objects);
		mContext = context;
		myElements = objects;
		// here is the tricky stuff
		mTextViewResourceId = textViewResourceId;
		alphaIndexer = new HashMap<String, Integer>();
		// in this hashmap we will store here the positions for
		// the sections

		int size = objects.length;
		for (int i = size - 1; i >= 0; i--) {
			String element = objects[i];
			alphaIndexer.put(element.substring(0, 1), i);
			// We store the first letter of the word, and its index.
			// The Hashmap will replace the value for identical keys are
			// putted in
			Log.v(TAG, element.substring(0, 1));
		}

		// now we have an hashmap containing for each first-letter
		// sections(key), the index(value) in where this sections begins

		// we have now to build the sections(letters to be displayed)
		// array .it must contains the keys, and must (I do so...) be
		// ordered alphabetically

		Set<String> keys = alphaIndexer.keySet();

		Iterator<String> it = keys.iterator();
		ArrayList<String> keyList = new ArrayList<String>();

		while (it.hasNext()) {
			String key = it.next();
			keyList.add(key);
			Log.v(TAG, key);
		}

		Collections.sort(keyList);

		sections = new String[keyList.size()];
		keyList.toArray(sections);
	}

	@Override
	public int getPositionForSection(int section) {
		String letter = sections[section];

		Log.v(TAG, "getPositionForSection : " + alphaIndexer.get(letter));

		return alphaIndexer.get(letter);
	}

	@Override
	public int getSectionForPosition(int position) {
		// you will notice it will be never called (right?)
		Log.v(TAG,
				"getSectionForPosition : "
						+ alphaIndexer.get(sections[position]));
		// return 0;
		return alphaIndexer.get(sections[position]);

	}

	@Override
	public Object[] getSections() {
		Log.v(TAG, "getSections");
		return sections;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		Log.v(TAG, "getView : " + position);
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(mTextViewResourceId, parent, false);
		}

		TextView textView = (TextView) view.findViewById(android.R.id.text1);
		String letter = myElements[position];
		textView.setText(letter);

		return view;
	}

}