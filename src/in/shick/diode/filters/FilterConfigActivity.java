package in.shick.diode.filters;


import in.shick.diode.R;
import in.shick.diode.settings.RedditSettings;
import java.util.ArrayList;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;



/**
 * Activity for configuring filters
 * @author tordo
 *
 */
public class FilterConfigActivity extends ListActivity {
	
	/** The filters we are displaying */
	ArrayList<SubredditFilter> m_filters;
	/** The adapter showing our filters */
	FilterAdapter m_adapter;
	/** Reddit settings*/
	RedditSettings m_settings;
	
	
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		
		m_settings = new RedditSettings();
		m_settings.loadRedditPreferences(this,null);
	}
	
	@Override
	public void onPause() {
		m_settings.setFilters(m_filters);
		m_settings.saveRedditPreferences(this);
		super.onPause();
	}
	@Override
	public void onResume() {
		m_settings.loadRedditPreferences(this,null);
		m_filters = m_settings.getFilters();
		m_adapter = new FilterAdapter(this,m_filters);
		setListAdapter(m_adapter);

		super.onResume();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater m = getMenuInflater();
		m.inflate(R.menu.filter_options_menu,menu);
		
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
		case R.id.add_filter:
			Intent i = new Intent(this,FilterEditActivity.class);
			startActivity(i);
			return true;
		default:
			return false;
		}
	}

	
}
