package se.marric.othello;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class GameActivity extends Activity {

	Button[][] cols;
	int currentPlayerColor = Color.YELLOW;
	int otherPlayer = Color.RED;
	Boolean yourTurn = true;
	Boolean isValidButtonChoosed = false;
	Boolean isMoreValidMoves = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		
		
		int startValue = R.id.button1;
		cols = new OthelloButton[8][8];
		
		for (int i = 0 ; i <8 ; i++) {
			for (int j = 0; j < 8; j++) {
				cols[i][j] = (OthelloButton) findViewById(startValue++);
				
				((OthelloButton)cols[i][j]).setOnClickListener(new View.OnClickListener() {
		            public void onClick(View v) {
		            	makeDraw(v);
		            }
		        });
			}
		}
	
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
		button36.setButtonColor(Color.YELLOW);
	
	}

	protected boolean checkIfMoreValidMoves(View v) {
		isMoreValidMoves = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				//if (cols[i][j] == (OthelloButton)findViewById(v.getId())) {
					makeTheDraw(i, j,(OthelloButton)cols[i][j], false);
				//}
			}
		}
		return isMoreValidMoves;
	}
	protected void makeDraw(View v) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (cols[i][j] == (OthelloButton)findViewById(v.getId())) {
					makeTheDraw(i, j,(OthelloButton)findViewById(v.getId()));
					if (isValidButtonChoosed) {
						isValidButtonChoosed = false;
						int tempa = currentPlayerColor;
						int tempb = otherPlayer;
						currentPlayerColor = tempb;
						otherPlayer = tempa;

						computerDraw(v);

					}

				}
			}
		}
		
	}
	
	private void makeTheDraw(int y, int x, OthelloButton button) {
		makeTheDraw(y,x,button,true);
	}

	private void makeTheDraw(int y, int x, OthelloButton button, Boolean isRealDraw) {
		int arrayIndex=0;
		int xValue=0;
		int yValue=0;
		
		if (button.getButtonColor() != Color.WHITE) {
			return;
		}
		
		//south
		if (y!=7){   
			yValue=y;
			OthelloButton[] tempArray = new OthelloButton[7-yValue];
			arrayIndex=0;
			while (++yValue <8) {
				tempArray[arrayIndex++] = ((OthelloButton)cols[yValue][x]);
			}
			flipMarkers(button, tempArray, isRealDraw);
		}
		
		//north
		if (y!=0){
			yValue=y;
			OthelloButton[] tempArray2 = new OthelloButton[yValue];
			arrayIndex=0;
			while (--yValue >=0) {
				tempArray2[arrayIndex++] = ((OthelloButton)cols[yValue][x]);
			}
			flipMarkers(button, tempArray2, isRealDraw);
		}
		
		//east
		if (x != 7) {
			xValue=x;
			OthelloButton[] tempArray3 = new OthelloButton[7-xValue];
			arrayIndex=0;
			while (++xValue <8) {
				tempArray3[arrayIndex++] = ((OthelloButton)cols[y][xValue]);
			}
			flipMarkers(button, tempArray3, isRealDraw);
		}
		
		//west
		if (x != 0){
			xValue=x;
			OthelloButton[] tempArray4 = new OthelloButton[xValue];
			arrayIndex=0;
			while (--xValue >=0) {
				tempArray4[arrayIndex++] = ((OthelloButton)cols[y][xValue]);
			}
			flipMarkers(button, tempArray4, isRealDraw);
		}
		
		//north west
		if (y != 0 && x != 0) {
			yValue = y;
			xValue = x;
			System.out.println("math min " + Math.min(yValue, xValue));
			OthelloButton[] tempArray5 = new OthelloButton[Math.min(yValue, xValue)];
			arrayIndex=0;
			while (--yValue != -1 && --xValue != -1) {
				tempArray5[arrayIndex++] = ((OthelloButton)cols[yValue][xValue]);
			}
			flipMarkers(button, tempArray5, isRealDraw);
		}
		
		//nort east
		if (y != 0 && x != 7) {
			yValue = y;
			xValue = x;
			OthelloButton[] tempArray6 = new OthelloButton[Math.min(yValue, 7-xValue)];
			arrayIndex=0;
			while (--yValue != -1 && ++xValue != 8) {
				tempArray6[arrayIndex++] = ((OthelloButton)cols[yValue][xValue]);
			}
			flipMarkers(button, tempArray6, isRealDraw);
		}
		
		//south west
		if (y != 7 && x != 0) {
			yValue = y;
			xValue = x;
			OthelloButton[] tempArray8 = new OthelloButton[Math.min(7-yValue, xValue)];
			arrayIndex=0;
			while (++yValue != 8 && --xValue != -1) {
				tempArray8[arrayIndex++] = ((OthelloButton)cols[yValue][xValue]);
			}
			flipMarkers(button, tempArray8, isRealDraw);
		}
		
		//south east
		if (y != 7 && x != 7) {
			yValue = y;
			xValue = x;
			OthelloButton[] tempArray7 = new OthelloButton[Math.min(7-yValue, 7-xValue)];
			arrayIndex=0;
			while (++yValue != 8 && ++xValue != 8) {
				tempArray7[arrayIndex++] = ((OthelloButton)cols[yValue][xValue]);
			}
			flipMarkers(button, tempArray7, isRealDraw);
		}
		
		
	}

	private void computerDraw(View v) {
		
		out:
			for (int u=0;u<8;u++) {
				for(int uu=0; uu<8; uu++) {
					makeTheDraw(u, uu,((OthelloButton)cols[u][uu]));
					if (isValidButtonChoosed) {
						//isValidButtonChoosed = false;
						int tempa = currentPlayerColor;
						int tempb = otherPlayer;
						currentPlayerColor = tempb;
						otherPlayer = tempa;
						break out;
					}
				}
			
		}
	if (!isValidButtonChoosed) {
		System.out.println("Computer loosed");
	}
if (!checkIfMoreValidMoves(v)){
	System.out.println("you loosed");
}
	isValidButtonChoosed = false;
		// TODO Auto-generated method stub
		yourTurn = true;
	}

	private Boolean flipMarkers(OthelloButton button, OthelloButton[] tempArray, Boolean realDraw) {
		int arrayIndex = 0;
		while (arrayIndex < tempArray.length && ((OthelloButton)tempArray[arrayIndex]).getButtonColor()==(otherPlayer)   ) {
			arrayIndex++;
			}
		if (arrayIndex!=0 && arrayIndex < tempArray.length && ((OthelloButton)tempArray[arrayIndex]).getButtonColor()==(currentPlayerColor) ){
			if (!realDraw) {
				isMoreValidMoves = true;
				return true;
			}
			for (int indexOfMarkToFlip=0; indexOfMarkToFlip<arrayIndex;indexOfMarkToFlip++) {
				flipMark((OthelloButton)tempArray[indexOfMarkToFlip]);
			}
			flipMark(button);
		
			isValidButtonChoosed = true;
			return true;
		}
		return false;
	}
	
	private void flipMark(OthelloButton button) {
		button.getBackground().setColorFilter(currentPlayerColor,PorterDuff.Mode.MULTIPLY);
		button.setButtonColor(currentPlayerColor);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

}
