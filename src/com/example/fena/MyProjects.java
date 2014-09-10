package com.example.fena;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MyProjects extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.fena.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_main);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		List<Project> projects = LogIn.account.getMyProjects();
		final ListView listview = (ListView) this.findViewById(R.id.listview);

		final ProjectArrayAdapter adapter = new ProjectArrayAdapter(
				this.getApplicationContext(),
				android.R.layout.simple_list_item_1, projects);
		
		if (projects != null) {
			listview.setAdapter(adapter);
			listview
					.setOnItemClickListener(new AdapterView.OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							Intent openMainPoint = new Intent(
									"android.intent.action.MYPROJECT");
							Project project = LogIn.account.getMyProjects().get(position);
							
							openMainPoint.putExtra(EXTRA_MESSAGE, project.getId());
							startActivity(openMainPoint);

						}
					});
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.myproject, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_new:
	        	Intent openMainPoint = new Intent("android.intent.action.CREATEPROJECT");
				startActivity(openMainPoint);
	            return true;
	        case R.id.action_settings:
	            return true;
		    case android.R.id.home:
		        NavUtils.navigateUpFromSameTask(this);
		        return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	static class ProjectArrayAdapter extends ArrayAdapter<Project> {

		Context context;
		List<Project> projects;

		public ProjectArrayAdapter(Context context, int textViewResourceId,
				List<Project> objects) {
			super(context, textViewResourceId, objects);
			this.context = context;
			projects = objects;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater
					.inflate(R.layout.list_layout, parent, false);
			TextView label = (TextView) rowView.findViewById(R.id.label);
			label.setText(projects.get(position).getTitle());
			TextView secondLine = (TextView) rowView
					.findViewById(R.id.secondLine);
			secondLine.setText(projects.get(position).getSubheading());
			String[] day = projects.get(position).getUpdated().split("T");
			String[] time = day[1].split("\\.");
			TextView date = (TextView) rowView.findViewById(R.id.date);
			date.setText(day[0] + "\n" + time[0]);
			return rowView;
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

	}
	

}
