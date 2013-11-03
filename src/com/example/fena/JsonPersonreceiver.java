package com.example.fena;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;

<<<<<<< HEAD
public abstract class JsonPersonreceiver extends
		AsyncTask<String, String, ArrayList<Person>> implements
		CallbackReceiver {
=======
public abstract class JsonPersonreceiver extends AsyncTask<String, String, ArrayList<Person>> implements CallbackReceiver {
>>>>>>> f8444c16c1b2dbc19b91528c88c7cab557731f62
	private ProgressDialog mProgressDialog;
	Handler handler;
	Runnable callback;
	Activity activity;
<<<<<<< HEAD

	public JsonPersonreceiver(Activity activity) {
=======
	
	
	public JsonPersonreceiver(Activity activity){
>>>>>>> f8444c16c1b2dbc19b91528c88c7cab557731f62
		this.activity = activity;
		mProgressDialog = new ProgressDialog(activity);
		mProgressDialog.setMessage("Loading Please Wait.");
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.setMax(100);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mProgressDialog.setCancelable(true);
	}
<<<<<<< HEAD

=======
>>>>>>> f8444c16c1b2dbc19b91528c88c7cab557731f62
	protected void onPreExecute() {
		mProgressDialog = ProgressDialog.show(activity, "", "Please Wait",
				true, false);
		super.onPreExecute();
	}
<<<<<<< HEAD

	@Override
	protected ArrayList<Person> doInBackground(String... url) {
		// InputStream source = retrieveStream(url);
		// Reader reader = new InputStreamReader(source);

=======
	@Override
	protected ArrayList<Person> doInBackground(String... url) {
		//InputStream source = retrieveStream(url);
		//Reader reader = new InputStreamReader(source);
		
>>>>>>> f8444c16c1b2dbc19b91528c88c7cab557731f62
		Gson gson = new Gson();
		String s = "{\"1\":[{\"name\":\"Micke\",\"expectations\":\"\",\"skills\":\"\",\"description\":\"\",\"experience\":\"\"}],\"2\":[{\"name\":\"Micke\",\"expectations\":\"\",\"skills\":\"\",\"description\":\"\",\"experience\":\"\"}],\"3\":[{\"name\":\"Micke\",\"expectations\":\"None\",\"skills\":\"Many\",\"description\":\"Yes I want to want\",\"experience\":\"From everywhere\"}]}";
		Reader reader = new StringReader(s);
		PersonResults results = gson.fromJson(reader, PersonResults.class);
		return results.persons;
	}

<<<<<<< HEAD
	protected void onPostExecute(ArrayList<Person> persons) {
		if (mProgressDialog != null || mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
		if (persons != null) {
			receiveData(persons);
		}
	}

	public abstract void receiveData(Object object);

=======
>>>>>>> f8444c16c1b2dbc19b91528c88c7cab557731f62
	private InputStream retrieveStream(String url) {

		DefaultHttpClient client = new DefaultHttpClient();

		HttpGet getRequest = new HttpGet(url);

		try {

			HttpResponse getResponse = client.execute(getRequest);
			final int statusCode = getResponse.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {
				Log.w(getClass().getSimpleName(), "Error " + statusCode
						+ " for URL " + url);
				return null;
			}

			HttpEntity getResponseEntity = getResponse.getEntity();
			return getResponseEntity.getContent();

		} catch (IOException e) {
			getRequest.abort();
			Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
		}

		return null;

	}
<<<<<<< HEAD
=======
	
	protected void onPostExecute(String jsondata) {
		if (mProgressDialog != null || mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
		if (jsondata != null) {
			receiveData(jsondata);
		}
	}

	public abstract void receiveData(Object object);

>>>>>>> f8444c16c1b2dbc19b91528c88c7cab557731f62
}
