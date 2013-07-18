package ca.skule.froshapplication;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
//import java.util.HashMap;
//import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListsActivity extends ListActivity {
	static String[] NameList = new String[] {
		"Bahen Institute of Technology",
		"Convocation Hall",
		"Engineering Annex",
		"Front Campus",
		"Galbraith Building",
		"Lassonde Mining Building",
		"Mechanical Engineering Building",
		"Queen's Park",
		"Sandford Fleming Building",
		"Wallberg Buildling"	
	};
/*	static ArrayList<String> CodeList = new ArrayList<String>();
	{
		CodeList.add("BA");
		CodeList.add("CH");
		CodeList.add("EA");
		CodeList.add("FC");
		CodeList.add("GB");
		CodeList.add("MB");
		CodeList.add("MC");
		CodeList.add("QP");
		CodeList.add("SF");
		CodeList.add("WB");
	}
	static HashMap<String,String> CoMap = new HashMap<String,String>();
	{
		CoMap.put("BA", "325,1040");
		CoMap.put("CH", "564,979");
		CoMap.put("EA", "530,1109");
		CoMap.put("GB", "488,1048");
		CoMap.put("FC", "630,887");
		CoMap.put("MB", "694,1160");
		CoMap.put("MC", "659,1082");
		CoMap.put("QP", "957,635");
		CoMap.put("SF", "618,1048");
		CoMap.put("WB", "530,1163");	
	}*/
private ListView lists;
private static final String TAG = "ListsActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final ListView lists = getListView();
		lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		    @Override
		    public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
		        onListItemClick(v,pos,id);
		    }
		    protected void onListItemClick(View v, int pos, long id) {
		    	AlertDialog.Builder adb = new AlertDialog.Builder(
		    			ListsActivity.this);
		    			adb.setTitle("ListView OnClick");
		    			adb.setMessage("Selected Item is = "
		    			+ lists.getItemAtPosition(pos));
		    			adb.setPositiveButton("Ok", null);
		    			adb.show();  
		    }
		});
		
		
		ListAdapter adapter = createAdapter();
		setListAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}	
	protected ListAdapter createAdapter()
	{
	ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,NameList);
	return adapter;
	}

	
}
