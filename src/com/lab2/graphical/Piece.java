package com.lab2.graphical;

import android.graphics.Paint;

public class Piece{

	private int x, y;
	private Paint paint;
	
	public Piece(int x, int y, Paint paint){
		this.x=x;
		this.y=y;
		this.paint=paint;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint paint) {
		this.paint = paint;
	}

	
	
	
}
