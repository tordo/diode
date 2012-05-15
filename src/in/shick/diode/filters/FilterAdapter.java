package in.shick.diode.filters;

import in.shick.diode.R;

import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;	
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class FilterAdapter extends ArrayAdapter<SubredditFilter>  {
	public FilterAdapter(Context ctx, ArrayList<SubredditFilter> filters) {
		super(ctx,0,filters);
	}
	
	@Override
	public int getViewTypeCount() {
		return 1;
	}
	@Override
	public int getItemViewType(int pos) {
		if(pos == ListView.INVALID_POSITION) {
			return ArrayAdapter.IGNORE_ITEM_VIEW_TYPE;
		}
		return 0;
	}

	@Override
	public View getView(final int arg0, View arg1, ViewGroup arg2) {
		View view;
		if(arg1 == null) {
			view = View.inflate(getContext(), R.layout.config_filter_list,null);
		}
		else {
			view = arg1;
		}
		ToggleButton b = (ToggleButton)view.findViewById(R.id.enabled);
		TextView t = (TextView)view.findViewById(R.id.name);
		
		SubredditFilter filter = getItem(arg0);
		
		b.setChecked(filter.isEnabled());
		t.setText(filter.getName());
		b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				getItem(arg0).setEnabled(isChecked);			
			}
		});
		
		
		return view;
	}

}
