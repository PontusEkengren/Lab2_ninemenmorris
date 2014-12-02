package com.lab2.graphical;

import com.example.lab2_ninemenmorris.NineMenMorrisRules;
import com.example.lab2_ninemenmorris.R;
import com.example.lab2_ninemenmorris.Unitinfo;

import android.app.ActionBar.LayoutParams;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Gameboard extends ActionBarActivity{

	private ImageDraw drawPiece;
    private Rect wholeGameboard;
    private int widthSecRect=0;
    private int heightSecRect=0;
    private ImageView gameBoard;
    private int[] xCords=new int[25];
    private int[] yCords=new int[25];
    private boolean initDone=false;
    private Unitinfo unitInfo;
    private int checkIfValidPos=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameboard);
        
        gameBoard = (ImageView) findViewById(R.id.imgGameboard);
        
        
        final RelativeLayout layout = (RelativeLayout) findViewById(R.id.relaLayout);
        
        
        drawPiece = new ImageDraw(getApplicationContext());
        layout.addView(drawPiece);
        
        //Rect rect = new Rect(gameBoard.getLeft(),gameBoard.getTop(), gameBoard.getRight(), gameBoard.getBottom());
        
        layout.setOnTouchListener(new OnTouchListener() {
           
            public boolean onTouch(View v, MotionEvent ev) {
            	if(initDone==false){
            		initializeGameBoard();
            		unitInfo = new Unitinfo(xCords, yCords);
            		initDone=true;
            	}
                switch(ev.getAction()){
                case MotionEvent.ACTION_UP:
                	
                	checkIfValidPos=unitInfo.checkIfInBound(ev.getX(), ev.getY());
                	if(checkIfValidPos!=-1){
                		//drawPiece.drawCircle(xCords[checkIfValidPos], yCords[checkIfValidPos]);	
                		
                		
                		if(play(converter(checkIfValidPos),0)==1){
                			drawPiece.drawCircle(xCords[checkIfValidPos], yCords[checkIfValidPos]);
                		}
                		
                		checkIfValidPos=-1;
                	}else{
                		Toast.makeText(getApplicationContext(), "Not accurate enough...", Toast.LENGTH_SHORT).show();
                		
                	}
                   
                    break;
                case MotionEvent.ACTION_DOWN:
                	
                	break;
                }
                
                    return true;
            }
			
        });
        
    }
    
    public int play(int to, int from){
    	NineMenMorrisRules nmm = new NineMenMorrisRules();
    	
    	int color= nmm.getTurn();
    	if(nmm.legalMove(to, from, color))
    		if(color==1){
    			return 4;
    		}else{
    			return 5;
    		}
    	else 
    		return -1;
    	
    	
    }
    
    public int converter(int pos){
    	switch (pos){
    	case 0:
    		return 3;
    	case 1:
    		return 6;
    	case 2:
    		return 9;
    	case 3:
    		return 2;
    	case 4:
    		return 5;
    	case 5:
    		return 8;
    	case 6:
    		return 1;
    	case 7:
    		return 4;
    	case 8:
    		return 7;
    	case 9:
    		return 24;
    	case 10:
    		return 23;
    	case 11:
    		return 22;
    	case 12:
    		return 10;
    	case 13:
    		return 11;
    	case 14:
    		return 12;
    	case 15:
    		return 19;
    	case 16:
    		return 16;
    	case 17:
    		return 13;
    	case 18:
    		return 20;
    	case 19:
    		return 17;
    	case 20:
    		return 14;
    	case 21:
    		return 21;
    	case 22:
    		return 18;
    	case 23:
    		return 15;
    		default:
    			return -1;
    	}
    	
    }
    
    public void initializeGameBoard(){
	      //Rad0
        xCords[0]=0+gameBoard.getLeft();//gameBoard.getLeft()-gameBoard.getLeft();
        yCords[0]=0+gameBoard.getTop();//gameBoard.getTop()-gameBoard.getTop();
        xCords[1]=(gameBoard.getWidth()/2)+gameBoard.getLeft();
        yCords[1]=0+gameBoard.getTop();
        xCords[2]=gameBoard.getWidth()+gameBoard.getLeft();
        yCords[2]=0+gameBoard.getTop();
        //Rad1
        xCords[3]=((gameBoard.getWidth()/6)*1)+gameBoard.getLeft();
        yCords[3]=((gameBoard.getHeight()/6)*1)+gameBoard.getTop();
        xCords[4]=(gameBoard.getWidth()/2)+gameBoard.getLeft();
        yCords[4]=(gameBoard.getHeight()/2/3)+gameBoard.getTop();
        xCords[5]=((gameBoard.getWidth()/2/3)*5)+gameBoard.getLeft();
        yCords[5]=(gameBoard.getHeight()/2/3)+gameBoard.getTop();
        //Rad2
        xCords[6]=((gameBoard.getWidth()/2/3)*2)+gameBoard.getLeft();
        yCords[6]=((gameBoard.getHeight()/2/3)*2)+gameBoard.getTop();
        xCords[7]=((gameBoard.getWidth()/2/3)*3)+gameBoard.getLeft();
        yCords[7]=((gameBoard.getHeight()/2/3)*2)+gameBoard.getTop();
        xCords[8]=((gameBoard.getWidth()/2/3)*4)+gameBoard.getLeft();
        yCords[8]=((gameBoard.getHeight()/2/3)*2)+gameBoard.getTop();
        //Rad3
        xCords[9]=0+gameBoard.getLeft();
        yCords[9]=((gameBoard.getHeight()/6)*3)+gameBoard.getTop();
        xCords[10]=((gameBoard.getWidth()/6)*1)+gameBoard.getLeft();
        yCords[10]=((gameBoard.getHeight()/6)*3)+gameBoard.getTop();
        xCords[11]=((gameBoard.getWidth()/6)*2)+gameBoard.getLeft();
        yCords[11]=((gameBoard.getHeight()/6)*3)+gameBoard.getTop();
        xCords[12]=((gameBoard.getWidth()/6)*4)+gameBoard.getLeft();
        yCords[12]=((gameBoard.getHeight()/6)*3)+gameBoard.getTop();
        xCords[13]=((gameBoard.getWidth()/6)*5)+gameBoard.getLeft();
        yCords[13]=((gameBoard.getHeight()/6)*3)+gameBoard.getTop();
        xCords[14]=((gameBoard.getWidth()/6)*6)+gameBoard.getLeft();
        yCords[14]=((gameBoard.getHeight()/6)*3)+gameBoard.getTop();
        //Rad4
        xCords[15]=((gameBoard.getWidth()/6)*2)+gameBoard.getLeft();
        yCords[15]=((gameBoard.getHeight()/6)*4)+gameBoard.getTop();
        xCords[16]=((gameBoard.getWidth()/6)*3)+gameBoard.getLeft();
        yCords[16]=((gameBoard.getHeight()/6)*4)+gameBoard.getTop();
        xCords[17]=((gameBoard.getWidth()/6)*4)+gameBoard.getLeft();
        yCords[17]=((gameBoard.getHeight()/6)*4)+gameBoard.getTop();
        //Rad5
        xCords[18]=((gameBoard.getWidth()/6)*1)+gameBoard.getLeft();
        yCords[18]=((gameBoard.getHeight()/6)*5)+gameBoard.getTop();
        xCords[19]=((gameBoard.getWidth()/6)*3)+gameBoard.getLeft();
        yCords[19]=((gameBoard.getHeight()/6)*5)+gameBoard.getTop();
        xCords[20]=((gameBoard.getWidth()/6)*5)+gameBoard.getLeft();
        yCords[20]=((gameBoard.getHeight()/6)*5)+gameBoard.getTop();
        //Rad6
        xCords[21]=((gameBoard.getWidth()/6)*0)+gameBoard.getLeft();
        yCords[21]=((gameBoard.getHeight()/6)*6)+gameBoard.getTop();
        xCords[22]=((gameBoard.getWidth()/6)*3)+gameBoard.getLeft();
        yCords[22]=((gameBoard.getHeight()/6)*6)+gameBoard.getTop();
        xCords[23]=((gameBoard.getWidth()/6)*6)+gameBoard.getLeft();
        yCords[23]=((gameBoard.getHeight()/6)*6)+gameBoard.getTop();
         
  }
	
}
