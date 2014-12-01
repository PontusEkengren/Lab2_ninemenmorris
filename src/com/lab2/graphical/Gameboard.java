package com.lab2.graphical;

import com.example.lab2_ninemenmorris.R;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/*
 	Width = 454
	To first box: 78           78/454=0,1718=17,18%
	To second box: 148    148/454=0,3259=32,6%

	Height = 451
	To first box: 71			71/451=0,1574=15,74%
	To second box: 142		142/451=0,3148=31,48%
 */

public class Gameboard extends ActionBarActivity{

	private ImageView gameBoard;
	private Rect rect;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameboard);
		gameBoard = (ImageView) findViewById(R.id.imgGameboard);
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.relaLayout);
		
		layout.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent ev) {
				switch(ev.getAction()){
				case MotionEvent.ACTION_UP:
					
					rect=new Rect(gameBoard.getLeft(),gameBoard.getTop(), gameBoard.getRight(), gameBoard.getBottom());
					
					if(rect.contains((int)ev.getX(), (int)ev.getY())){
						System.out.println("X CORDS: "+ev.getX()+"Y CORDS: "+ev.getY());
					}
					
					break;
				
				}
				return true;
			}
		});
		
	}
	
}
