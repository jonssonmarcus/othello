package se.marric.othello;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class WebService extends AsyncTask<Void, Void, String>{

	HttpClient client;
	final static String URL = "http://othelloapp.appspot.com/api/a/getstring";
	
	
	
	public String getBoardAsString() {
	
		JSONObject board = null;
		try {
			board = getBoardFromWebService();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(board != null) {
			try {
				return board.getString("board");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";
			}
		} else {
		   return "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";
		}
	   	
	}
	
	private JSONObject getBoardFromWebService() throws ClientProtocolException, IOException, JSONException {
		client = new DefaultHttpClient();
		HttpGet get = new HttpGet(URL);
		HttpResponse r = client.execute(get);
		int status = r.getStatusLine().getStatusCode();
		if(status == 200) {
			HttpEntity e = r.getEntity();
			String data = EntityUtils.toString(e);
			return  new JSONObject(data);
		} else {
			return null;
		}
		
	}

	@Override
	protected String doInBackground(Void... params) {
		return getBoardAsString();
	}
	
	
}
