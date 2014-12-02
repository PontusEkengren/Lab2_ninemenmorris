package com.lab2.graphical;

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

	private Drawable circle;
	public float x=0;
	public float y=0;
    private int[] xCords=new int[25];
    private int[] yCords=new int[25];
    
	
	public ImageDraw(Context context){
		super(context);
		
		Resources resources = context.getResources();
		circle = (Drawable) resources.getDrawable(R.drawable.red);

	}
	
	@Override
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);

		int iw = circle.getIntrinsicWidth();
		int ih = circle.getIntrinsicHeight();
		//Rect bounds = new Rect(x+0, y+0, (int)(x-iw), (int)(y-ih));
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		
		canvas.drawCircle(x, y, 30, paint);
		
	}
	
	
	public void drawCircle(float x, float y){
		this.x=x;
		this.y=y;
		this.invalidate();
	}
	
	
	
	
}
