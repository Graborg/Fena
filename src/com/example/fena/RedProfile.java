package com.example.fena;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.app.Activity;
public class RedProfile extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.red_profile);
		
		EditText addPlayer = (EditText) findViewById(R.id.etName);
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		EditText Description1 = (EditText) findViewById(R.id.etDescription1);
		EditText Description2 = (EditText) findViewById(R.id.etDescription2);
		EditText Description3 = (EditText) findViewById(R.id.etDescription3);
		EditText Experience = (EditText) findViewById(R.id.etExper);
		Button Save = (Button) findViewById(R.id.bSave);
		
	}
}
