package com.example.fena;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends Activity{
	private int checkedItem = -1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		
		Intent intent = getIntent();
		int position = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, -1);
		final Person person = LogIn.persons.get(position);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		TextView name = (TextView) findViewById(R.id.tvName);
		TextView skills = (TextView) findViewById(R.id.tvskills);
		TextView description = (TextView) findViewById(R.id.tvDescription);
		TextView expectation = (TextView) findViewById(R.id.tvExpectation);
		Button contact = (Button) findViewById(R.id.bContactProj);
		
		name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		name.setText(person.getName());
		expectation.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		expectation.setText(person.getExpectations());
		skills.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		skills.setText(person.getSkills());
		description.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		description.setText(person.getDescription());
		
		contact.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(LogIn.account == null){
					AlertDialog.Builder builder = new AlertDialog.Builder(
							Profile.this);
					builder.setMessage(
							"You are not signed in and can therefore not contact anyone")
							.setPositiveButton("Sign in",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											Intent i = new Intent(Profile.this, LogIn.class);
											// set the new task and clear flags
											i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
											startActivity(i);
											finish();
										}
									})
							.setNegativeButton("Cancel",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
										}
									}).show();
				}
				else{
					final ArrayList<String> itemsDiag = new ArrayList<String>();
					final ArrayList<Object> items = new ArrayList<Object>();
					if(LogIn.account.getMyProfile() != null){
						items.add(LogIn.account.getMyProfile());
						itemsDiag.add(LogIn.account.getMyProfile().getName());
					}
					if(LogIn.account.getMyProjects() != null){
						 for(Project p:LogIn.account.getMyProjects()){
							 items.add(p);	
							 itemsDiag.add(p.getTitle());
					        }
					}
			        CharSequence[] cs = itemsDiag.toArray(new CharSequence[itemsDiag.size()]);
		            AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
		            builder.setTitle("Select what you want to send");
		            builder.setSingleChoiceItems(cs, checkedItem, new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface dialog, int indexSelected) {
							checkedItem = indexSelected;
						}
					})

		         .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		             @Override
		             public void onClick(DialogInterface dialog, int id) {
		            	 Mail mail = new Mail(Profile.this);
		            	 if(checkedItem != -1){
		            	 mail.sendToPerson(person.getId(), items.get(checkedItem));
		            	 }
		            	 else{
		            		 Toast.makeText(getApplicationContext(), "Mail not sent, did not select an item to send", Toast.LENGTH_LONG).show();
		            	 }
		             }
		         })
		         .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		             @Override
		             public void onClick(DialogInterface dialog, int id) {
		              
		             }
		         });
		            AlertDialog dialog = builder.create();
		            dialog.show();
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
