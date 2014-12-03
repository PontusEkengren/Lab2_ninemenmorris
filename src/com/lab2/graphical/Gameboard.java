package com.lab2.graphical;

import java.util.ArrayList;

import com.example.lab2_ninemenmorris.GameboardInfo;
import com.example.lab2_ninemenmorris.Gameplay;
import com.example.lab2_ninemenmorris.MainActivity;
import com.example.lab2_ninemenmorris.NineMenMorrisRules;
import com.example.lab2_ninemenmorris.R;

import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

public class Gameboard extends ActionBarActivity{

	private ImageDraw drawPiece;
    private ImageView gameBoard;
    private TextView tvPlayersTurn;
    private int[] xCords=new int[25];
    private int[] yCords=new int[25];
    private boolean initDone=false;
    private Gameplay gamePlay;
    private int checkIfValidPos=-1;
    private GameboardInfo gbInfo=null;
    private ArrayList<Piece> pieces = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameboard);
        
        gameBoard = (ImageView) findViewById(R.id.imgGameboard);
        tvPlayersTurn = (TextView) findViewById(R.id.txtViewPTurn);
        
        final RelativeLayout layout = (RelativeLayout) findViewById(R.id.relaLayout);
        
        tvPlayersTurn.setText("RED PLAYER TURN");
        
        drawPiece = new ImageDraw(getApplicationContext());
        layout.addView(drawPiece);
        
        //Rect rect = new Rect(gameBoard.getLeft(),gameBoard.getTop(), gameBoard.getRight(), gameBoard.getBottom());
        
        layout.setOnTouchListener(new OnTouchListener() {
           
            public boolean onTouch(View v, MotionEvent ev) {
            	if(initDone==false){
            		initializeGameBoard();
            		gamePlay = new Gameplay(xCords, yCords);
            		initDone=true;
            	}
                switch(ev.getAction()){
                case MotionEvent.ACTION_UP:
                	
                	System.out.println("Check pos: X: "+ev.getX()+" Y: "+ev.getY());
                	
                	checkIfValidPos=gamePlay.checkIfInBound(ev.getX(), ev.getY());
                	
                	if(checkIfValidPos!=-1){
                		
                		gbInfo = gamePlay.getGbInfo();
                		
                		
                		//gbInfo.getPiecesPos();
                		pieces=null;
                		pieces = new ArrayList<Piece>();
                		for(int i=1;i<gbInfo.getPiecesPos().length;i++){
                			//System.out.println(i+": "+gbInfo.getPiecesPos()[i]);
                			if(gbInfo.getPiecesPos()[i]==4){
                				
                				Paint paint = new Paint();
                        		paint.setColor(Color.BLUE);
                				pieces.add(new Piece(xCords[i], yCords[i], paint));
                			
                			}else if(gbInfo.getPiecesPos()[i]==5){
                				Paint paint = new Paint();
                        		paint.setColor(Color.RED);
                				pieces.add(new Piece(xCords[i], yCords[i], paint));
                			}
                		}
                		
                		
                		
                		drawPiece.drawCircle(pieces);
                		
                		if(gbInfo.getMessageInfo().contains("Congratulations")){
                			Toast.makeText(getApplicationContext(), gbInfo.getMessageInfo(), Toast.LENGTH_SHORT).show();
                			new AlertDialog.Builder(Gameboard.this)
        		            .setTitle("Congratulations")
        		            .setMessage(gbInfo.getMessageInfo()+" Do you want to restart or cancel?")
        		            .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
        		                public void onClick(DialogInterface dialog, int which) { 
        		                	Intent i = new Intent(getApplicationContext(),Gameboard.class);
        		                	finish();
        		                	startActivity(i);
        		                }
        		             })
        		            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        		                public void onClick(DialogInterface dialog, int which) { 
        		                	finish();
        		                	finishActivity(1);
        		                }
        		             }).show();
                		}else if(!gbInfo.getMessageInfo().equalsIgnoreCase("-1")){
                			Toast.makeText(getApplicationContext(), gbInfo.getMessageInfo(), Toast.LENGTH_SHORT).show();
                		}
                		if(gbInfo.getPlayerTurn() == 1){
                			tvPlayersTurn.setText("BLUE PLAYER TURN");
                		}else{
                			tvPlayersTurn.setText("RED PLAYER TURN");
                		}
                		
                		
                		//pieces.removeAll(pieces);
                		//redraw from gamebord info
                		
            			//drawPiece.drawCircle(xCords[checkIfValidPos], yCords[checkIfValidPos]);
                		
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
    
    
    public void initializeGameBoard(){
	      //Rad0
        xCords[1]=0+gameBoard.getLeft();//gameBoard.getLeft()-gameBoard.getLeft();
        yCords[1]=0+gameBoard.getTop();//gameBoard.getTop()-gameBoard.getTop();
        xCords[2]=(gameBoard.getWidth()/2)+gameBoard.getLeft();
        yCords[2]=0+gameBoard.getTop();
        xCords[3]=gameBoard.getWidth()+gameBoard.getLeft();
        yCords[3]=0+gameBoard.getTop();
        //Rad1
        xCords[4]=((gameBoard.getWidth()/6)*1)+gameBoard.getLeft();
        yCords[4]=((gameBoard.getHeight()/6)*1)+gameBoard.getTop();
        xCords[5]=(gameBoard.getWidth()/2)+gameBoard.getLeft();
        yCords[5]=(gameBoard.getHeight()/2/3)+gameBoard.getTop();
        xCords[6]=((gameBoard.getWidth()/2/3)*5)+gameBoard.getLeft();
        yCords[6]=(gameBoard.getHeight()/2/3)+gameBoard.getTop();
        //Rad2
        xCords[7]=((gameBoard.getWidth()/2/3)*2)+gameBoard.getLeft();
        yCords[7]=((gameBoard.getHeight()/2/3)*2)+gameBoard.getTop();
        xCords[8]=((gameBoard.getWidth()/2/3)*3)+gameBoard.getLeft();
        yCords[8]=((gameBoard.getHeight()/2/3)*2)+gameBoard.getTop();
        xCords[9]=((gameBoard.getWidth()/2/3)*4)+gameBoard.getLeft();
        yCords[9]=((gameBoard.getHeight()/2/3)*2)+gameBoard.getTop();
        //Rad3
        xCords[10]=0+gameBoard.getLeft();
        yCords[10]=((gameBoard.getHeight()/6)*3)+gameBoard.getTop();
        xCords[11]=((gameBoard.getWidth()/6)*1)+gameBoard.getLeft();
        yCords[11]=((gameBoard.getHeight()/6)*3)+gameBoard.getTop();
        xCords[12]=((gameBoard.getWidth()/6)*2)+gameBoard.getLeft();
        yCords[12]=((gameBoard.getHeight()/6)*3)+gameBoard.getTop();
        xCords[13]=((gameBoard.getWidth()/6)*4)+gameBoard.getLeft();
        yCords[13]=((gameBoard.getHeight()/6)*3)+gameBoard.getTop();
        xCords[14]=((gameBoard.getWidth()/6)*5)+gameBoard.getLeft();
        yCords[14]=((gameBoard.getHeight()/6)*3)+gameBoard.getTop();
        xCords[15]=((gameBoard.getWidth()/6)*6)+gameBoard.getLeft();
        yCords[15]=((gameBoard.getHeight()/6)*3)+gameBoard.getTop();
        //Rad4
        xCords[16]=((gameBoard.getWidth()/6)*2)+gameBoard.getLeft();
        yCords[16]=((gameBoard.getHeight()/6)*4)+gameBoard.getTop();
        xCords[17]=((gameBoard.getWidth()/6)*3)+gameBoard.getLeft();
        yCords[17]=((gameBoard.getHeight()/6)*4)+gameBoard.getTop();
        xCords[18]=((gameBoard.getWidth()/6)*4)+gameBoard.getLeft();
        yCords[18]=((gameBoard.getHeight()/6)*4)+gameBoard.getTop();
        //Rad5
        xCords[19]=((gameBoard.getWidth()/6)*1)+gameBoard.getLeft();
        yCords[19]=((gameBoard.getHeight()/6)*5)+gameBoard.getTop();
        xCords[20]=((gameBoard.getWidth()/6)*3)+gameBoard.getLeft();
        yCords[20]=((gameBoard.getHeight()/6)*5)+gameBoard.getTop();
        xCords[21]=((gameBoard.getWidth()/6)*5)+gameBoard.getLeft();
        yCords[21]=((gameBoard.getHeight()/6)*5)+gameBoard.getTop();
        //Rad6
        xCords[22]=((gameBoard.getWidth()/6)*0)+gameBoard.getLeft();
        yCords[22]=((gameBoard.getHeight()/6)*6)+gameBoard.getTop();
        xCords[23]=((gameBoard.getWidth()/6)*3)+gameBoard.getLeft();
        yCords[23]=((gameBoard.getHeight()/6)*6)+gameBoard.getTop();
        xCords[24]=((gameBoard.getWidth()/6)*6)+gameBoard.getLeft();
        yCords[24]=((gameBoard.getHeight()/6)*6)+gameBoard.getTop();
         
  }
	
}
