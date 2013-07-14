package ca.skule.froshapplication;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.ArrayAdapter;

public class ListsActivity extends ListActivity {
	static ArrayList<String> NameList = new ArrayList<String>();
	{
		NameList.add("Bahen Institute of Technology");
		NameList.add("Convocation Hall");
		NameList.add("Engineering Annex");
		NameList.add("Front Campus");
		NameList.add("Galbraith Building");
		NameList.add("Lassonde Mining Building");
		NameList.add("Mechanical Engineering Building");
		NameList.add("Queen's Park");
		NameList.add("Sandford Fleming Building");
		NameList.add("Wallberg Buildling");	
	}
	static ArrayList<String> CodeList = new ArrayList<String>();
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
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.id.text1,NameList);
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}


}
