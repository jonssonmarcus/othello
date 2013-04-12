package se.marric.othello;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GameActivity extends Activity {

	static Button[][] board;
	static int currentPlayerColor = Color.YELLOW;
	static int otherPlayerColor = Color.RED;
	static Boolean yourTurn = true;
	static Boolean isValidButtonChoosed = false;
	static Boolean isMoreValidMoves = true;
	private OthelloEngine oe;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		Bundle b = getIntent().getExtras();
		
		board = new OthelloButton[8][8];		
		addButtonListeners();
	
		oe = new OthelloEngine();
		
		if (b.getString("board") != null){
			setBoard(b.getString("board"));
		}
		
		if (b.getString("yourColor") != null) {
			if( b.getString("yourColor").equalsIgnoreCase("yellow") ){
				currentPlayerColor = Color.YELLOW;
				otherPlayerColor = Color.RED;
			} else {
				currentPlayerColor = Color.RED;
				otherPlayerColor = Color.YELLOW;
			}
		}
		/*
		final OthelloButton button28 = (OthelloButton) findViewById(R.id.Button28);
		button28.getBackground().setColorFilter(Color.RED,PorterDuff.Mode.MULTIPLY);
		button28.setButtonColor(Color.RED);
		
		final OthelloButton button37 = (OthelloButton) findViewById(R.id.Button37);
		button37.getBackground().setColorFilter(Color.RED,PorterDuff.Mode.MULTIPLY);
		button37.setButtonColor(Color.RED);
		
		final OthelloButton button29 = (OthelloButton) findViewById(R.id.Button29);
		button29.getBackground().setColorFilter(Color.YELLOW,PorterDuff.Mode.MULTIPLY);
		button29.setButtonColor(Color.YELLOW);
		
		final OthelloButton button36 = (OthelloButton) findViewById(R.id.Button36);
		button36.getBackground().setColorFilter(Color.YELLOW,PorterDuff.Mode.MULTIPLY);
		button36.setButtonColor(Color.YELLOW);*/
	
	}

	private void addButtonListeners() {
		int startValue = R.id.button1;
		for (int i = 0 ; i <8 ; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = (OthelloButton) findViewById(startValue++);
				
				((OthelloButton)board[i][j]).setOnClickListener(new View.OnClickListener() {
		            public void onClick(View v) {
		            	makeDraw(v);
		            }
		        });
			}
		}
	}

	protected boolean checkIfMoreValidMoves(View v) {
		isMoreValidMoves = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
					oe.makeTheDraw(i, j,(OthelloButton)board[i][j], false);
			}
		}
		return isMoreValidMoves;
	}
	
	protected void makeDraw(View v) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] == (OthelloButton)findViewById(v.getId())) {
					oe.makeTheDraw(i, j,(OthelloButton)findViewById(v.getId()));
					if (isValidButtonChoosed) {
						isValidButtonChoosed = false;
						int tempa = currentPlayerColor;
						int tempb = otherPlayerColor;
						currentPlayerColor = tempb;
						otherPlayerColor = tempa;

						computerDraw(v);

					}
				}
			}
		}
	}
	
	private void computerDraw(View v) {
		
		out:
			for (int u=0;u<8;u++) {
				for(int uu=0; uu<8; uu++) {
					oe.makeTheDraw(u, uu,((OthelloButton)board[u][uu]));
					if (isValidButtonChoosed) {
						//isValidButtonChoosed = false;
						int tempa = currentPlayerColor;
						int tempb = otherPlayerColor;
						currentPlayerColor = tempb;
						otherPlayerColor = tempa;
						break out;
					}
				}
			
		}
		if (!isValidButtonChoosed) {
			System.out.println("Computer loosed");
			checkWhoWon();
		}
		if (!checkIfMoreValidMoves(v)){
			System.out.println("you loosed");
			checkWhoWon();
		}
		isValidButtonChoosed = false;
		// TODO Auto-generated method stub
		yourTurn = true;
	}

	private void checkWhoWon() {
		int red = 0;
		int yellow = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (((OthelloButton)board[i][j]).getButtonColor() == Color.YELLOW){
					yellow++;
				}else if(((OthelloButton)board[i][j]).getButtonColor() == Color.RED){
						red++;
				}	
			}
		}
		Context context = getApplicationContext();
		
		CharSequence text = "";
		if(red != yellow){
			text = (red<yellow) ? "You won!!" : "Computer won!!"; 
		} else {
			text = "Tie!!";
		}
		text = text + "  Red=" + red + " Yellow=" + yellow;

		Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
		toast.show();
	}
	
	public String getBoardAsString(){
		StringBuilder boardString = new StringBuilder();
		for(int i=0; i<8;i++){
			for(int j=0;j<8;j++){
				if ( ((OthelloButton)board[i][j]).getButtonColor() == Color.RED ) {
				boardString.append('r');
				} else if( ((OthelloButton)board[i][j]).getButtonColor() == Color.YELLOW ){
					boardString.append('y');
				} else {
					boardString.append('e');
				}
			}
		}
		return boardString.toString();
	}
		
	public void setBoard(String boardString){
		char[] theBoard = boardString.toCharArray();
			int k=0;
			out:
			for(int i=0;i<8;i++){
				for (int j=0;j<8;j++){
					if (theBoard[k] == 'r'){
						setMarkColor(((OthelloButton)board[i][j]), Color.RED); 
					}else if (theBoard[k] == 'y'){
						setMarkColor(((OthelloButton)board[i][j]), Color.YELLOW);
					} else if (theBoard[k] == 'w'){
						setMarkColor(((OthelloButton)board[i][j]), Color.WHITE);
					}
					k++;
					if (k>(theBoard.length - 1)) {
						break out;
					}
				}
			}
	}
		
	private void setMarkColor(OthelloButton button, int color) {
		button.getBackground().setColorFilter(color,PorterDuff.Mode.MULTIPLY);
		button.setButtonColor(color);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

}
