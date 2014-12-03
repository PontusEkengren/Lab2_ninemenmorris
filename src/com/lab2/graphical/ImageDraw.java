package com.lab2.graphical;

import java.util.ArrayList;

import com.example.lab2_ninemenmorris.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class ImageDraw extends View{

	private Drawable redCircle,blueCircle;
	public float x=0;
	public float y=0;
    private int[] xCords=new int[25];
    private int[] yCords=new int[25];
    ArrayList<Piece> pieces = new ArrayList<Piece>();
    private Context context;
    
	
	public ImageDraw(Context context){
		super(context);
		this.context=context;
		Resources resources = context.getResources();
		redCircle = (Drawable) resources.getDrawable(R.drawable.red);
		blueCircle = (Drawable) resources.getDrawable(R.drawable.green);

		
		
	}
	
	@Override
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);

		/*int iw = redCircle.getIntrinsicWidth();
		int ih = redCircle.getIntrinsicHeight();
		Rect bounds = new Rect(x+0, y+0, (int)(x-iw), (int)(y-ih));*/
		
		for(Piece piece: pieces){
			canvas.drawCircle(piece.getX(), piece.getY(), 30, piece.getPaint());
		}

	}
	
	
	public void drawCircle(ArrayList<Piece> pieces){
		this.pieces=pieces;
		this.invalidate();
	}

}
