package in.shick.diode.filters;

import in.shick.diode.R;

import java.util.ArrayList;


import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;	
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;


/** FilterAdapter class
 * Display a filter as a row in a list
 * @author tordo
 *
 */
public class FilterAdapter extends ArrayAdapter<SubredditFilter>  {
	public FilterAdapter(Context ctx, ArrayList<SubredditFilter> filters) {
		super(ctx,0,filters);
	}


	@Override
	public View getView(final int itemId, View arg1, ViewGroup arg2) {
		View view;
		if(arg1 == null) {
			view = View.inflate(getContext(), R.layout.config_filter_list,null);
		}
		else {
			view = arg1;
		}
		ToggleButton b = (ToggleButton)view.findViewById(R.id.enabled);
		TextView t = (TextView)view.findViewById(R.id.name);
		
		SubredditFilter filter = getItem(itemId);
		
		b.setChecked(filter.isEnabled());
		t.setText(filter.getName());
		b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				getItem(itemId).setEnabled(isChecked);			
			}
		});
		
		view.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

			@Override
			public void onCreateContextMenu(ContextMenu arg0, View arg1,
					ContextMenuInfo arg2) {
				
				MenuInflater m = new MenuInflater(getContext());
				m.inflate(R.menu.config_filter_context_menu, arg0);
				MenuItem item = arg0.getItem(0);
				
				
				item.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						FilterAdapter.this.remove(getItem(itemId));
						return true;
					}
				});
				
				item = arg0.getItem(1);
				Intent i = new Intent(getContext(),FilterEditActivity.class);
				i.putExtra(FilterEditActivity.INTENT_FILTERID,itemId);
				item.setIntent(i);
			}
			
		});
		
		return view;
	}

}
