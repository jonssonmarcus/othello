package se.marric.othello;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;

public class GameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		final Button button28 = (Button) findViewById(R.id.Button28);
		button28.getBackground().setColorFilter(Color.RED,PorterDuff.Mode.MULTIPLY);
		
		final Button button37 = (Button) findViewById(R.id.Button37);
		button37.getBackground().setColorFilter(Color.RED,PorterDuff.Mode.MULTIPLY);
		
		final Button button29 = (Button) findViewById(R.id.Button29);
		button29.getBackground().setColorFilter(Color.YELLOW,PorterDuff.Mode.MULTIPLY);
		
		final Button button36 = (Button) findViewById(R.id.Button36);
		button36.getBackground().setColorFilter(Color.YELLOW,PorterDuff.Mode.MULTIPLY);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

}
