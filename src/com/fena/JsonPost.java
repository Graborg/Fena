package com.fena;

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
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public abstract class JsonPost extends AsyncTask<String, String, Integer>
		implements CallbackReceiver {
	private ProgressDialog mProgressDialog;
	Handler handler;
	Runnable callback;
	Activity activity;
	private String jsonProfile;
	private String token;
	final Toast toast; 

	public JsonPost(Activity activity, String jsonProfile, String token) {
		this.token = token;
		this.activity = activity;
		this.jsonProfile = jsonProfile;
		toast = Toast.makeText(activity.getApplicationContext(), "Connection Error", Toast.LENGTH_LONG);
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
	protected Integer doInBackground(String... url) {
		String source = retrieveStream(url[0], jsonProfile, token);
		if (source == null) {
			if (mProgressDialog != null || mProgressDialog.isShowing()) {
				mProgressDialog.dismiss();
			}
			toast.show();
			return null;
		}
		toast.setText("Update Successful");
		toast.show();
		System.out.println("JsonProfilePost:" + source);
		JSONObject json = null;
		String profile_id = null;
		try {
			json = new JSONObject(source);
			profile_id = json.getString("id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if (mProgressDialog != null || mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
		activity.finish();
		return Integer.parseInt(profile_id);
	}

	protected void onPostExecute(Account account) {
		if (mProgressDialog != null || mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
			if (account != null) {
				receiveData(account);
			}
		}

	private String retrieveStream(String url, String jsonProfile, String token) {

		HttpClient client = new DefaultHttpClient();

		HttpPost httpPost = new HttpPost(url);

		try {
			StringEntity entity = new StringEntity(jsonProfile, "UTF8");
			httpPost.setEntity(entity);
			httpPost.setHeader("Content-type", "application/json");
			httpPost.addHeader("Authorization", "Token token=\"" + token + "\"");
			HttpResponse response = client.execute(httpPost);
			final int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				Log.w(getClass().getSimpleName(), "Error " + statusCode
						+ " for URL " + url);
				toast.setText("Connection error (" + statusCode + ")");
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
