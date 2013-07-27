package ca.skule.froshapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import ca.skule.froshapplication.item.ItemContent;

/**
 * A fragment representing a single FTip detail screen. This fragment is either
 * contained in a {@link FTipListActivity} in two-pane mode (on tablets) or a
 * {@link FTipDetailActivity} on handsets.
 */
public class FTipDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private ItemContent.TipItem mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public FTipDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = ItemContent.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_ftip_detail,
				container, false);

		// Show the dummy content as text in a TextView.
		if (mItem != null) {
			((ListView) rootView.findViewById(R.id.ftip_detail))
					.setAdapter(createAdapter(mItem.content));
		}

		return rootView;
	}
	protected ListAdapter createAdapter(String[] name)
	{
	ListAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, name);
	return adapter;
	}
}
