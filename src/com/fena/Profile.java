package com.fena;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends Activity {
	private int checkedItem = -1;
	private Person person = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		Intent intent = getIntent();
		int id = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, -1);

		for (Person p : LogIn.persons) {
			if (p.getId() == id) {
				person = p;
				break;
			}
		}
		getActionBar().setDisplayHomeAsUpEnabled(true);

		TextView name = (TextView) findViewById(R.id.tvName);
		TextView skills = (TextView) findViewById(R.id.tvSkills1);
		TextView expectation = (TextView) findViewById(R.id.tvExpectation);
		TextView description = (TextView) findViewById(R.id.tvDescription);
		Button contact = (Button) findViewById(R.id.bContactProj);
		ImageView pic = (ImageView) findViewById(R.id.pic);

		name.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		name.setText(person.getName());
		expectation.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		expectation.setText(Html.fromHtml("<b>" + "Expectation: " + "</b><br /> " + person.getExpectations()));
		skills.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		skills.setText(Html.fromHtml("<b>" + "Skills: " + "</b> <br /> " + person.getSkills()));
		description.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		description.setText(Html.fromHtml("<b>" + "Description: " + "</b> <br /> " + person.getDescription()));
		contact.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));

		switch (person.getImage()) {
		case 0: pic.setImageResource(R.drawable.prof_blue);
		break;
		case 1: pic.setImageResource(R.drawable.prof_red);
		break;
		case 2: pic.setImageResource(R.drawable.prof_orange);
		break;
		case 3: pic.setImageResource(R.drawable.prof_green);
		break;
		default: pic.setImageResource(R.drawable.prof_blue);
		break;
		}

		contact.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (LogIn.account == null) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							Profile.this);
					builder.setMessage(
							"You are not signed in and can therefore not contact anyone")
							.setPositiveButton("Sign in",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											Intent openMainPoint = new Intent(
													"android.intent.action.LOGIN");
											startActivity(openMainPoint);
										}
									})
							.setNegativeButton("Cancel",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
										}
									}).show();
				} else {
					final ArrayList<String> itemsDiag = new ArrayList<String>();
					final ArrayList<Object> items = new ArrayList<Object>();
					if (LogIn.account.getMyProfile() != null) {
						items.add(LogIn.account.getMyProfile());
						itemsDiag.add("Profile: " + LogIn.account.getMyProfile().getName());
					}
					if (LogIn.account.getMyProjects() != null) {
						for (Project p : LogIn.account.getMyProjects()) {
							items.add(p);
							itemsDiag.add("Project: " + p.getTitle());
						}
					}
					if (items.isEmpty()) {
						AlertDialog.Builder builder = new AlertDialog.Builder(
								Profile.this);
						builder.setMessage(
								"You have to make a Profile or Project to contact someone")
								.setPositiveButton("Ok",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int id) {
											}
										})
								.setNegativeButton("Cancel",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int id) {
											}
										}).show();
					} else {
						CharSequence[] cs = itemsDiag
								.toArray(new CharSequence[itemsDiag.size()]);
						AlertDialog.Builder builder = new AlertDialog.Builder(
								Profile.this);
						builder.setTitle("Select what you want to send");
						builder.setSingleChoiceItems(cs, checkedItem,
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int indexSelected) {
										checkedItem = indexSelected;
									}
								})

								.setPositiveButton("OK",
										new DialogInterface.OnClickListener() {
											@Override
											public void onClick(
													DialogInterface dialog,
													int id) {
												Mail mail = new Mail(
														Profile.this);
												if (checkedItem != -1) {
													mail.sendToPerson(person.getId(), items.get(checkedItem));
												} else {
													Toast.makeText(
															getApplicationContext(),"Mail not sent, did not select an item to send",Toast.LENGTH_LONG)
															.show();
												}
											}
										})
								.setNegativeButton("Cancel",
										new DialogInterface.OnClickListener() {
											@Override
											public void onClick(
													DialogInterface dialog,
													int id) {

											}
										});
						AlertDialog dialog = builder.create();
						dialog.show();
					}
				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
