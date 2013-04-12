package se.marric.othello;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.widget.Button;



public class OthelloEngine {
	
	
	public void makeTheDraw(int y, int x, OthelloButton button) {
		makeTheDraw(y,x,button,true);
	}

	public void makeTheDraw(int y, int x, OthelloButton button, Boolean isRealDraw) {
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
				tempArray[arrayIndex++] = ((OthelloButton)GameActivity.board[yValue][x]);
			}
			flipMarkers(button, tempArray, isRealDraw);
		}
		
		//north
		if (y!=0){
			yValue=y;
			OthelloButton[] tempArray2 = new OthelloButton[yValue];
			arrayIndex=0;
			while (--yValue >=0) {
				tempArray2[arrayIndex++] = ((OthelloButton)GameActivity.board[yValue][x]);
			}
			flipMarkers(button, tempArray2, isRealDraw);
		}
		
		//east
		if (x != 7) {
			xValue=x;
			OthelloButton[] tempArray3 = new OthelloButton[7-xValue];
			arrayIndex=0;
			while (++xValue <8) {
				tempArray3[arrayIndex++] = ((OthelloButton)GameActivity.board[y][xValue]);
			}
			flipMarkers(button, tempArray3, isRealDraw);
		}
		
		//west
		if (x != 0){
			xValue=x;
			OthelloButton[] tempArray4 = new OthelloButton[xValue];
			arrayIndex=0;
			while (--xValue >=0) {
				tempArray4[arrayIndex++] = ((OthelloButton)GameActivity.board[y][xValue]);
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
				tempArray5[arrayIndex++] = ((OthelloButton)GameActivity.board[yValue][xValue]);
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
				tempArray6[arrayIndex++] = ((OthelloButton)GameActivity.board[yValue][xValue]);
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
				tempArray8[arrayIndex++] = ((OthelloButton)GameActivity.board[yValue][xValue]);
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
				tempArray7[arrayIndex++] = ((OthelloButton)GameActivity.board[yValue][xValue]);
			}
			flipMarkers(button, tempArray7, isRealDraw);
		}
		
		
	}
	
	public Boolean flipMarkers(OthelloButton button, OthelloButton[] tempArray, Boolean realDraw) {
		int arrayIndex = 0;
		while (arrayIndex < tempArray.length && ((OthelloButton)tempArray[arrayIndex]).getButtonColor()==(GameActivity.otherPlayerColor)   ) {
			arrayIndex++;
			}
		if (arrayIndex!=0 && arrayIndex < tempArray.length && ((OthelloButton)tempArray[arrayIndex]).getButtonColor()==(GameActivity.currentPlayerColor) ){
			if (!realDraw) {
				GameActivity.isMoreValidMoves = true;
				return true;
			}
			for (int indexOfMarkToFlip=0; indexOfMarkToFlip<arrayIndex;indexOfMarkToFlip++) {
				flipMark((OthelloButton)tempArray[indexOfMarkToFlip]);
			}
			flipMark(button);
		
			GameActivity.isValidButtonChoosed = true;
			return true;
		}
		return false;
	}
	
	public void flipMark(OthelloButton button) {
		button.getBackground().setColorFilter(GameActivity.currentPlayerColor,PorterDuff.Mode.MULTIPLY);
		button.setButtonColor(GameActivity.currentPlayerColor);
	}




}
