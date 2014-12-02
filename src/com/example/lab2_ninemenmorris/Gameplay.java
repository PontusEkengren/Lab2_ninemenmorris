package com.example.lab2_ninemenmorris;

import android.graphics.Rect;

public class Gameplay {

	private int[] xCords=new int[25];
    private int[] yCords=new int[25];
    private Rect[] rectHitBoxes=null;
    private NineMenMorrisRules nmm= null;
    private Boolean initNotDone=true;
    private int initMoveCounter=0;
    private Boolean timeForRemove=false;
    
    
    public Gameplay(int[] xCords, int[] yCords){
    	this.xCords = xCords;
    	this.yCords = yCords;
    	rectHitBoxes = new Rect[25];
    	nmm=new NineMenMorrisRules();
    	createHitBoxes();
    	
    }
    
    private void createHitBoxes(){
    	for(int i=0;i<24;i++){
    		System.out.println("create Hit boxes: "+i+" xCordsi: "+xCords[i]+" yCordsi: "+yCords[i]);
    		
    		rectHitBoxes[i]=new Rect(xCords[i]-35,yCords[i]-35,xCords[i]+35,yCords[i]+35);
    	}
    	
    }
    
    public int checkIfInBound(float x, float y){
    	for(int i=0;i<24;i++){
    		if(rectHitBoxes[i].contains((int)x, (int)y)){
    			return i;
    		}
    	}
    	return -1;
    	
    }
    
    private void move(int posInNMM){
    	
    	int color = nmm.getTurn();
    	//If remove = true, remove piece
    	if(timeForRemove){
    		togglePlayer();
    		nmm.remove(posInNMM, color);
    		//Win
    		timeForRemove=false;
    	}else{
    		// If pieces on board below count 18
	    	if(initNotDone){
	    		
	    		if(nmm.legalMove(posInNMM, 0, color)){
	    			//Remove
	    			if(nmm.remove(posInNMM)){
	    				togglePlayer();
	    				
	    			}
	    			
	    			
	    			initMoveCounter++;
	    			if(initMoveCounter>17){
	        			initNotDone=false;
	        		}
	    			
	    		}else{
	    			//Illegal move, message the player
	    		}
	    		
	    		
	    	}else{
	    		//If all 18 pieces is on board
	    		
	    	}
    	}
    	
    }
    
    private void togglePlayer(){
    	if(nmm.getTurn()==1) 
			nmm.setTurn(2);//change maby
        else 
        	nmm.setTurn(1);
    }
    
    
    
    public int converterFromPosToNMM(int pos){
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
	
}
