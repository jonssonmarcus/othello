package se.marric.othello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		
		final OthelloButton button = (OthelloButton) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	startGame();
                // Perform action on click
            }
        });
        
		final OthelloButton loadButton = (OthelloButton) findViewById(R.id.load_game);
		loadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	getGame();
                // Perform action on click
            }
        });

        }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

	private void startGame() {
		 Intent i = new Intent(this, GameActivity.class);
	     Bundle b = new Bundle();
	     b.putString("board", "rrwrerwyrryyyyyyyyrrrerrryyyyyyrr"); //get the board from a WS
	     b.putString("yourColor", "yellow"); // check in database or with a WS
	     i.putExtras(b);
	        
	     startActivity(i);
	     finish();
	}
	
	private void getGame() {
		Toast toast = Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT);
		toast.show();
		startGame();
	}
	
	
}
