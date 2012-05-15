package in.shick.diode.filters;


import in.shick.diode.R;
import in.shick.diode.settings.RedditSettings;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FilterConfigActivity extends ListActivity {
	ListView m_listview;
	ArrayList<SubredditFilter> m_filters;
	FilterAdapter m_adapter;
	RedditSettings m_settings;
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		
		m_settings = new RedditSettings();
		m_settings.loadRedditPreferences(this,null);
		m_filters = m_settings.getFilters();
		m_listview = getListView();
		m_adapter = new FilterAdapter(this,m_filters);
		
		setListAdapter(m_adapter);
		m_adapter.notifyDataSetChanged();

	}
	
	@Override
	public void onPause() {
		m_settings.setFilters(m_filters);
		m_settings.saveRedditPreferences(this);
		super.onPause();
	}
}
