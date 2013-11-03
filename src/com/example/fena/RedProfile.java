package com.example.fena;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.app.Activity;

public class RedProfile extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.red_profile);
		
		final EditText name = (EditText) findViewById(R.id.etName);
		final Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		final EditText description1 = (EditText) findViewById(R.id.etDescription1);
		final EditText description2 = (EditText) findViewById(R.id.etDescription2);
		final EditText description3 = (EditText) findViewById(R.id.etDescription3);
		final EditText experience = (EditText) findViewById(R.id.etExper);
		Button save = (Button) findViewById(R.id.bSave);
		Button cancel = (Button) findViewById(R.id.bCancel);
		
		//for the limit of words
		description1.addTextChangedListener(new TextWatcher() {
		    public void onTextChanged(CharSequence s, int start, int before, int count) {
		        String[] words = s.toString().split(" "); // Get all words
		        if (words.length > 1) {
		        	Toast.makeText(getApplicationContext(), "One Word Only", Toast.LENGTH_SHORT).show();
		        	description1.setText(words[0]);
		        }
		    }
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}
		});
		
		description2.addTextChangedListener(new TextWatcher() {
		    public void onTextChanged(CharSequence s, int start, int before, int count) {
		        String[] words = s.toString().split(" "); // Get all words
		        if (words.length > 1) {
		        	Toast.makeText(getApplicationContext(), "One Word Only", Toast.LENGTH_SHORT).show();
		        	description2.setText(words[0]);
		        }
		    }
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}
		});
		
		description3.addTextChangedListener(new TextWatcher() {
		    public void onTextChanged(CharSequence s, int start, int before, int count) {
		        String[] words = s.toString().split(" "); // Get all words
		        if (words.length > 1) {
		        	Toast.makeText(getApplicationContext(), "One Word Only", Toast.LENGTH_SHORT).show();
		        	description3.setText(words[0]);
		        }
		    }
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}
		});
		
		experience.addTextChangedListener(new TextWatcher() {
		    public void onTextChanged(CharSequence s, int start, int before, int count) {
		        String[] words = s.toString().split(" "); // Get all words
		        if (words.length > 50) {
		        	Toast.makeText(getApplicationContext(), "50 Words Only", Toast.LENGTH_SHORT).show();
		        	String str = "";
		        	for(int i = 0; i < 50; i++){
		        		str += words[i] + " ";
		        	}
		        	experience.setText(str);
		        }
		    }
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}
		});
		
		save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Person person = new Person();
				name.getText().toString();
				spinner.getSelectedItem().toString();
				description1.getText().toString();
				description2.getText().toString();
				description3.getText().toString();
				experience.getText().toString();
			}
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
	}
}
