package com.example.fena;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.MenuInflater;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

	public final static String EXTRA_MESSAGE = "com.example.fena.MESSAGE";
	public static PersonArrayAdapter adapter;
	public static ProjectArrayAdapter adapter2;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mDrawerTitles;
	static Activity activity;
	private Database db;

	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		activity = MainActivity.this;
		db = new Database();

		mTitle = mDrawerTitle = getTitle();
		mDrawerTitles = getResources().getStringArray(R.array.drawer_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// set up the drawer's list view with items and click listener
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mDrawerTitles));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		db.update(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// MenuInflater inflater = getMenuInflater();
		// inflater.inflate(R.menu.main, menu);
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		// menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action buttons
		switch (item.getItemId()) {
		case R.id.action_refresh:
			adapter.clear();
			adapter2.clear();
			db.update(this);
		case R.id.action_search:
			return true;
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	private void selectItem(int position) {
		// update the main content by replacing fragments
		Fragment fragment = new DrawerFragment();
		Bundle args = new Bundle();
		args.putInt(DrawerFragment.ARG_INDEX_NUMBER, position);
		fragment.setArguments(args);

		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		// update selected item and title, then close the drawer
		mDrawerList.setItemChecked(position, true);
		setTitle(mDrawerTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	public void setTitle(CharSequence title) {
		// mTitle = title;
		// getActionBar().setTitle(mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	/**
	 * Fragment that appears in the "content_frame", shows a planet
	 */
	public static class DrawerFragment extends Fragment {
		public static final String ARG_INDEX_NUMBER = "index_number";

		public DrawerFragment() {
			// Empty constructor required for fragment subclasses
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// View rootView = inflater.inflate(R.layout.fragment_planet,
			// container, false);
			int i = getArguments().getInt(ARG_INDEX_NUMBER);
			View rootView = null;
			if (i == 0) {
				rootView = null;
				return rootView;
			}
			if (i == 1) {
				Intent openMainPoint = new Intent("android.intent.action.LOGIN");
				rootView = null;
				startActivity(openMainPoint);
				activity.finish();
				return rootView;
			}
			rootView = null;
			return rootView;
		}
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
		}

		@Override
		public android.support.v4.app.Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			android.support.v4.app.Fragment fragment = new TabSectionFragment();
			Bundle args = new Bundle();
			args.putInt(TabSectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class TabSectionFragment extends
			android.support.v4.app.Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public TabSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View rootView = inflater.inflate(R.layout.fragment_main_tab,
					container, false);
			if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
				ArrayList<Person> person;
				if (LogIn.persons == null) {
					person = new ArrayList<Person>();
				} else {
					person = LogIn.persons;
				}
				rootView = inflater.inflate(R.layout.list_main, container,
						false);

				final ListView listview = (ListView) rootView
						.findViewById(R.id.listview);

				adapter = new PersonArrayAdapter(getActivity()
						.getApplicationContext(),
						android.R.layout.simple_list_item_1, person);

				listview.setAdapter(adapter);

				listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Intent openMainPoint = new Intent(
								"android.intent.action.PROFILE");
						openMainPoint.putExtra(EXTRA_MESSAGE, position);
						startActivity(openMainPoint);

					}
				});

			}
			if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
				ArrayList<Project> project;
				if (LogIn.projects == null) {
					project = new ArrayList<Project>();
				} else {
					project = LogIn.projects;
				}
				rootView = inflater.inflate(R.layout.list_main, container,
						false);

				final ListView listview2 = (ListView) rootView
						.findViewById(R.id.listview);

				adapter2 = new ProjectArrayAdapter(getActivity()
						.getApplicationContext(),
						android.R.layout.simple_list_item_1, project);

				listview2.setAdapter(adapter2);

				listview2
						.setOnItemClickListener(new AdapterView.OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {
								Intent openMainPoint = new Intent(
										"android.intent.action.PROJECT");
								openMainPoint.putExtra(EXTRA_MESSAGE, position);
								startActivity(openMainPoint);

							}
						});

			}
			if (getArguments().getInt(ARG_SECTION_NUMBER) == 3) {
				rootView = null;
			}
			return rootView;
		}
	}

	static class PersonArrayAdapter extends ArrayAdapter<Person> {

		Context context;
		List<Person> persons;

		public PersonArrayAdapter(Context context, int textViewResourceId,
				List<Person> objects) {
			super(context, textViewResourceId, objects);
			this.context = context;
			persons = objects;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater
					.inflate(R.layout.list_layout, parent, false);
			TextView label = (TextView) rowView.findViewById(R.id.label);
			label.setText(persons.get(position).getName());
			TextView secondLine = (TextView) rowView
					.findViewById(R.id.secondLine);
			secondLine.setText(persons.get(position).getMail());
			String[] day = persons.get(position).getUpdated().split("T");
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
