package ca.skule.froshapplication;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class FTipsListActivity extends ListActivity {
	// Array of all locations used in this application
	static String[] NameList = new String[] {
		"F!rosh Week Tips",
		"Academic Tips",
		"Skule Life Tips"
	};
	private ListView lists;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final ListView lists = getListView();
		//Setting the OnClick
		lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		    @Override
		    public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
		        onListItemClick(v,pos,id);
		    }
		    protected void onListItemClick(View v, int pos, long id) {
		    	Intent intent = new Intent (v.getContext(), MapActivity.class);
		    	intent.putExtra("Category", lists.getItemIdAtPosition(pos));
				startActivity(intent);
		    }
		});
		
		//Setting up the actual ListView
		ListAdapter adapter = createAdapter();
		lists.setAdapter(adapter);
		
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		getActionBar().setDisplayShowTitleEnabled(false);
		return true;
	}	
	protected ListAdapter createAdapter()
	{
	ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,NameList);
	return adapter;
	}

	
}