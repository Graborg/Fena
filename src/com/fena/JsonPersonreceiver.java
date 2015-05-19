package com.fena;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public abstract class JsonPersonreceiver extends
		AsyncTask<String, String, ArrayList<Person>> implements
		CallbackReceiver {
	private ProgressDialog mProgressDialog;
	Handler handler;
	Runnable callback;
	Activity activity;

	public JsonPersonreceiver(Activity activity) {
		this.activity = activity;
		mProgressDialog = new ProgressDialog(activity);
		mProgressDialog.setMessage("Loading Please Wait.");
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.setMax(50);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mProgressDialog.setCancelable(true);
	}

	protected void onPreExecute() {
		mProgressDialog = ProgressDialog.show(activity, "", "Please Wait",
				true, true);
		super.onPreExecute();
	}

	@Override
	protected ArrayList<Person> doInBackground(String... url) {
		InputStream source = retrieveStream(url[0]);
		if (source == null) {
			if (mProgressDialog != null || mProgressDialog.isShowing()) {
				mProgressDialog.dismiss();
			}
			return null;
		}
		Reader reader = new InputStreamReader(source);
		Gson gson = new Gson();
		JsonReader jreader = new JsonReader(reader);
		jreader.setLenient(true);
		PersonResults results = null;
		try {
			results = gson.fromJson(jreader, PersonResults.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<Person>) results.persons;
	}

	protected void onPostExecute(ArrayList<Person> persons) {
		if (mProgressDialog != null || mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
		if (persons != null) {
			receiveData(persons);
		}
		if(LogIn.persons == null){
			LogIn.persons = new ArrayList<Person>();
		}
		ArrayList<Person> p = new ArrayList<Person>();
		for(int i = 0; i < LogIn.persons.size(); i++){
			if(LogIn.persons.get(i).getShowProfile() != 0){
				p.add(LogIn.persons.get(i));
			}
		}
		Collections.sort(p, new Comparator<Person>() {
		    public int compare(Person one, Person other) {
		        return other.getUpdated().compareTo(one.getUpdated());
		    }
		});
		LogIn.showPersons = p;
		 
		
		if(MainActivity.adapter != null){
		MainActivity.adapter.clear();
		MainActivity.adapter.addAll(p);
		MainActivity.adapter.notifyDataSetChanged();
		}
		if(MainActivityLogin.adapter != null){
			MainActivityLogin.adapter.clear();
			MainActivityLogin.adapter.addAll(p);
			MainActivityLogin.adapter.notifyDataSetChanged();
		}
		if(LogIn.account != null && MyProfile.name != null){
			Person person = LogIn.account.getMyProfile();
			MyProfile.name.setText(person.getName());
			MyProfile.expectation.setText(Html.fromHtml("<b>" + "Expectation: " + "</b> <br /> " + person.getExpectations()));
			MyProfile.skills.setText(Html.fromHtml("<b>" + "Skills: " + "</b> <br /> " + person.getSkills()));
			MyProfile.description.setText(Html.fromHtml("<b>" + "Description: " + "</b> <br /> " + person.getDescription()));
		}

	}

	private InputStream retrieveStream(String url) {
		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		int timeoutConnection = 7000;
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				timeoutConnection);
		// Set the default socket timeout (SO_TIMEOUT)
		// in milliseconds which is the timeout for waiting for data.
		int timeoutSocket = 7000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		DefaultHttpClient client = new DefaultHttpClient(httpParameters);
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
		} catch (ConnectTimeoutException w) {
			System.out.println("ConnectTimeoutException, jsonPerson, timeout");
			return null;
		} catch (SocketTimeoutException x) {
			System.out.println("SocketTimeoutException, jsonPerson, timeout");
			return null;
		} catch (IOException e) {
			getRequest.abort();
			Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
		}
		return null;
	}

	public abstract void receiveData(Object object);
}