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

public class ProjectView extends Activity{
	private int checkedItem = -1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.project);
				
		Intent intent = getIntent();
		int position = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, -1);
		final Project project = LogIn.projects.get(position);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		TextView title = (TextView) findViewById(R.id.tvTitel);
		TextView subtitle = (TextView) findViewById(R.id.tvSubtitel);
		TextView reqskills = (TextView) findViewById(R.id.tvReqskills);
		TextView description = (TextView) findViewById(R.id.tvDescription_proj);
		TextView gain = (TextView) findViewById(R.id.tvGains);
		Button contact = (Button) findViewById(R.id.bContactProj);
		
		title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		title.setText(project.getTitle());
		subtitle.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		subtitle.setText(project.getSubheading());
		reqskills.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		reqskills.setText(project.getRequested_skills());
		description.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		description.setText(project.getDescription());
		gain.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		gain.setText("Gains\n" + project.getGains() +"Time\n" + project.getTimePlan() + "Wanted\n");
		
		contact.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(LogIn.account == null){
					AlertDialog.Builder builder = new AlertDialog.Builder(
							ProjectView.this);
					builder.setMessage(
							"You are not signed in and can therefore not contact anyone")
							.setPositiveButton("Sign in",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											Intent i = new Intent(ProjectView.this, LogIn.class);
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
		            AlertDialog.Builder builder = new AlertDialog.Builder(ProjectView.this);
		            builder.setTitle("Select what you want to send");
		            builder.setSingleChoiceItems(cs, checkedItem, new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface dialog, int indexSelected) {
							checkedItem = indexSelected;
						}
					})

		         .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		             @Override
		             public void onClick(DialogInterface dialog, int id) {
		            	 Mail mail = new Mail(ProjectView.this);
		            	 if(checkedItem != -1){
		            		 mail.sendToProject(project.getId(), items.get(checkedItem));
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
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.project, menu);
		return super.onCreateOptionsMenu(menu);
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
