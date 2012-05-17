package in.shick.diode.filters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import in.shick.diode.R;
import in.shick.diode.settings.RedditSettings;
import java.util.ArrayList;

/**
 * Activity for configuring filters
 * @author tordo
 *
 */
public class FilterListActivity extends Activity 
{
    /** The display for the list of filters */
    ListView m_filterListView;
	/** The filters we are displaying */
	ArrayList<SubredditFilter> m_filters;
	/** The adapter showing our filters */
	FilterAdapter m_adapter;
	/** Reddit settings*/
	RedditSettings m_settings;
	
	@Override
	public void onCreate(Bundle b) 
	{
		super.onCreate(b);
        setContentView(R.layout.filter_list_activity);

        m_filterListView = (ListView) findViewById(R.id.filter_list);

        ((Button) findViewById(R.id.add_button)).setOnClickListener(
                new Button.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(FilterListActivity.this,
                    FilterEditActivity.class);
                startActivity(i);
            }
        });
		
		m_settings = new RedditSettings();
		m_settings.loadRedditPreferences(this,null);
	}
	
	@Override
	public void onPause() 
	{
		// Save filters on pause
		m_settings.setFilters(m_filters);
		m_settings.saveRedditPreferences(this);
		super.onPause();
	}
	
	@Override
	public void onResume() 
	{
		// Re-load filters on resume (i.e. after editing)
		m_settings.loadRedditPreferences(this,null);
		m_filters = m_settings.getFilters();
		m_adapter = new FilterAdapter(this,m_filters);
		m_filterListView.setAdapter(m_adapter);

		super.onResume();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		MenuInflater m = getMenuInflater();
		m.inflate(R.menu.filter_options_menu,menu);
		
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch(item.getItemId())
		{
        case R.id.clear:
            m_filters.clear();
            m_adapter.notifyDataSetChanged();
            return true;
		default:
			return false;
		}
	}

	
}
