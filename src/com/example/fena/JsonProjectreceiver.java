package com.example.fena;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Scanner;
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
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public abstract class JsonProjectreceiver extends
		AsyncTask<String, String, ArrayList<Project>> implements
		CallbackReceiver {
	private ProgressDialog mProgressDialog;
	Handler handler;
	Runnable callback;
	Activity activity;
	final Toast toast;

	public JsonProjectreceiver(Activity activity) {
		this.activity = activity;
		toast = Toast.makeText(activity.getApplicationContext(),
				"Connection error", Toast.LENGTH_LONG);
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
	protected ArrayList<Project> doInBackground(String... url) {
		InputStream source = retrieveStream(url[0]);
		if (source == null) {
			return null;
		}
		Reader reader = new InputStreamReader(source);
		Gson gson = new Gson();
		JsonReader jreader = new JsonReader(reader);
		jreader.setLenient(true);
		ProjectResults results = null;
		try {
			results = gson.fromJson(jreader, ProjectResults.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<Project>) results.projects;
	}

	protected void onPostExecute(ArrayList<Project> projects) {
		if (mProgressDialog != null || mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
		if (projects != null) {
			receiveData(projects);
		}
		if(LogIn.projects == null){
			LogIn.projects = new ArrayList<Project>();
		}
		if(MainActivity.adapter2 != null){
		MainActivity.adapter2.clear();
		MainActivity.adapter2.addAll(LogIn.projects);
		MainActivity.adapter2.notifyDataSetChanged();
		}
		if(MainActivityLogin.adapter2 != null){
			MainActivityLogin.adapter2.clear();
			MainActivityLogin.adapter2.addAll(LogIn.projects);
			MainActivityLogin.adapter2.notifyDataSetChanged();
		}
		if(MyProjects.adapter != null && LogIn.account != null){
			MyProjects.adapter.clear();
			MyProjects.adapter.addAll(LogIn.account.getMyProjects());
			MyProjects.adapter.notifyDataSetChanged();
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
				toast.setText("Internal Error, Please try again later");
				toast.show();
				return null;
			}
			HttpEntity getResponseEntity = getResponse.getEntity();
			return getResponseEntity.getContent();
		} catch (ConnectTimeoutException w) {
			toast.setText("No Or Bad Internet Connection, Please Check The Settings And Try Again");
			toast.show();
			System.out.println("ConnectTimeoutException, jsonProject, timeout");
		} catch (SocketTimeoutException x) {
			toast.setText("Internal Error, Please try again later");
			toast.show();
			System.out.println("SocketTimeoutException, jsonProject, timeout");
		} catch (IOException e) {
			getRequest.abort();
			Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
		}
		return null;
	}

	public abstract void receiveData(Object object);
}