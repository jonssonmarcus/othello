package se.marric.othello;

import android.content.Context;

import com.parse.Parse;
import com.parse.ParseObject;

public class WebServiceParse {
	
	Context context;
	private static final String APP_ID = "eZrluX7lqx0DuqaUmYruK2XqhqG12f7f2cZZ4jtk";
	private static final String CLIENT_KEY = "iZj9M6rlh4gQ8EKCjjjMxKfEa3AjuRqAh3mAlPtt";
	
	public WebServiceParse(Context ctx) {
		this.context = ctx;
	}

	public void setBoard(String board) {
		ParseObject boardObject = new ParseObject("board");
		boardObject.put("board", board);
		boardObject.saveInBackground();
	}
	
	public String getBoard() {
		return null;
	}
	
	private void init() {
		Parse.initialize(this.context, APP_ID, CLIENT_KEY);
	}
	
}


//Read more @ https://parse.com/apps/quickstart#android/native/existing