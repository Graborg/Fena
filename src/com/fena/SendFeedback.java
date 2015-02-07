package com.fena;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SendFeedback extends Activity{
private Activity activity;

	public SendFeedback(){
		activity = this;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_feedback);
		final EditText edfeedback = (EditText) findViewById(R.id.edfeedback);

		
		Button bsf = (Button) findViewById(R.id.bSendFeedback);
		edfeedback.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		bsf.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		bsf.setOnClickListener(new View.OnClickListener() {
		JSONObject jsonObj = new JSONObject();	
		
			@Override
			public void onClick(View v) {
				try {
					jsonObj.put("feedback", edfeedback.getText().toString());
					jsonObj.put("OS", 3);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				String url = "http://54.191.168.116:3001/mail/mail_feedback";
				System.out.println("SendFeed, Token: " + LogIn.account.getToken());
				System.out.println("SendFeed, string: " + jsonObj.toString());
				JsonMailPost callbackservice3 = new JsonMailPost(activity, LogIn.account.getToken() , jsonObj.toString()) {
					@SuppressWarnings("unchecked")
					@Override
					public void receiveData(Object object) {
					}
				};
				callbackservice3.execute(url, null, null);
				
			}
		});
	}

}
