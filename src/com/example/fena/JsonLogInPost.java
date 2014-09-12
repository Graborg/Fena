package com.example.fena;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public abstract class JsonLogInPost extends AsyncTask<String, String, Account>
		implements CallbackReceiver {
	private ProgressDialog mProgressDialog;
	Handler handler;
	Runnable callback;
	Activity activity;
	private String jsonAccount;
	final Toast toast; 

	public JsonLogInPost(Activity activity, String jsonAccount) {
		this.activity = activity;
		this.jsonAccount = jsonAccount;
		toast = Toast.makeText(activity.getApplicationContext(), "Wrong username or password\nPlease try again", Toast.LENGTH_LONG);
		mProgressDialog = new ProgressDialog(activity);
		mProgressDialog.setMessage("Loading Please Wait.");
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.setMax(50);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mProgressDialog.setCancelable(true);
	}

	protected void onPreExecute() {
		mProgressDialog = ProgressDialog.show(activity, "", "Please Wait", true, true);
		super.onPreExecute();
	}

	@Override
	protected Account doInBackground(String... url) {
		String source = retrieveStream(url[0], jsonAccount);
		if (source == null) {
			if (mProgressDialog != null || mProgressDialog.isShowing()) {
				mProgressDialog.dismiss();
			}
			toast.show();
			return null;
		}
		JSONObject json = null;
		String token = null;
		String account_id = null;
		try {
			json = new JSONObject(source);
			token = json.getString("token");
			account_id = json.getString("account_id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if (mProgressDialog != null || mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
		activity.startActivity(new Intent("android.intent.action.MAINFENALOGIN"));
		activity.finish();
		return new Account(token, account_id);
	}

	protected void onPostExecute(Account account) {
		if (mProgressDialog != null || mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
			if (account != null) {
				receiveData(account);
			}
		}

	private String retrieveStream(String url, String jsonAccount) {

		HttpClient client = new DefaultHttpClient();

		HttpPost httpPost = new HttpPost(url);

		try {
			StringEntity entity = new StringEntity(jsonAccount, "UTF8");
			httpPost.setEntity(entity);
			httpPost.setHeader("Content-type", "application/json");
			HttpResponse response = client.execute(httpPost);
			final int statusCode = response.getStatusLine().getStatusCode();
			System.out.println(statusCode);

			if (statusCode != HttpStatus.SC_OK) {
				Log.w(getClass().getSimpleName(), "Error " + statusCode
						+ " for URL " + url);
				toast.setText("Wrong username or password\nPlease try again");
				return null;
			}

			HttpEntity getResponseEntity = response.getEntity();
			return EntityUtils.toString(getResponseEntity);

		} catch (IOException e) {
			Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
			Toast.makeText(activity.getApplicationContext(), "Internal Error",
					Toast.LENGTH_LONG).show();
		}

		return null;

	}

	public abstract void receiveData(Object object);
}
