package se.marric.othello;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

public class OthelloButton extends  android.widget.Button{

	private int buttonColor = Color.WHITE;
	private int oppositeColor;
	private Boolean markedForChange;
	
	
	
	
	public OthelloButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public OthelloButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public OthelloButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	public void setOppositeColor(int color) {
		oppositeColor = color;
	}
	
	public int getOppositeColor() {
		return oppositeColor;
	}
	
	
	public void setButtonColor(int color) {
		buttonColor = color;
	}
	
	public int getButtonColor() {
		return buttonColor;
	}

	public void markForChange(Boolean mark) {
		markedForChange = mark;
	}
	
	public Boolean getMarkedForChange(){
		return markedForChange;
	}
}
