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

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public abstract class JsonMailPost extends AsyncTask<String, String, Void>
		implements CallbackReceiver {
	private ProgressDialog mProgressDialog;
	Handler handler;
	Runnable callback;
	Activity activity;
	private String jsonId;

	public JsonMailPost(Activity activity, String jsonId) {
		this.activity = activity;
		this.jsonId = jsonId;
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
	protected Void doInBackground(String... url) {
		String source = retrieveStream(url[0], jsonId);
		if (source == null) {
			if (mProgressDialog != null || mProgressDialog.isShowing()) {
				mProgressDialog.dismiss();
			}
		}
		
		Toast.makeText(activity.getApplicationContext(), "The mail has now been sent!", Toast.LENGTH_LONG).show();
		return null;
	}

	protected void onPostExecute(Account account) {
		if (mProgressDialog != null || mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
		}

	private String retrieveStream(String url, String jsonId) {

		HttpClient client = new DefaultHttpClient();

		HttpPost httpPost = new HttpPost(url);

		try {
			StringEntity entity = new StringEntity(jsonId, "UTF8");
			httpPost.setEntity(entity);
			httpPost.setHeader("Content-type", "application/json");
			HttpResponse response = client.execute(httpPost);
			final int statusCode = response.getStatusLine().getStatusCode();
			System.out.println(statusCode);

			if (statusCode != HttpStatus.SC_OK) {
				Log.w(getClass().getSimpleName(), "Error " + statusCode
						+ " for URL " + url);
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
