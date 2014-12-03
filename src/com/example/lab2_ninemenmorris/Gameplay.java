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
    GameboardInfo gbInfo = null;
    
    
    public Gameplay(int[] xCords, int[] yCords){
    	this.xCords = xCords;
    	this.yCords = yCords;
    	rectHitBoxes = new Rect[25];
    	nmm=new NineMenMorrisRules();
    	gbInfo=new GameboardInfo();
    	createHitBoxes();
    	
    }
    
    private void createHitBoxes(){
    	for(int i=1;i<25;i++){
    		System.out.println("create Hit boxes: "+i+" xCordsi: "+xCords[i]+" yCordsi: "+yCords[i]);
    		
    		rectHitBoxes[i]=new Rect(xCords[i]-35,yCords[i]-35,xCords[i]+35,yCords[i]+35);
    	}
    	
    }
    
    public int checkIfInBound(float x, float y){
    	for(int i=1;i<25;i++){
    		if(rectHitBoxes[i].contains((int)x, (int)y)){
    			move(converterFromPosToNMM(i), 0); 
    			return i;
    		}
    	}
    	return -1;
    	
    }
    
    private void move(int posInNMM, int from){
    	
    	int color = nmm.getTurn();
    	//If remove = true, remove piece
    	if(timeForRemove){
    		
    		if(nmm.remove(posInNMM, color)){
    			//Win
    			togglePlayer();
    			timeForRemove=false;
    		}else{
    			//You can not remove your own piece
    		}
    		
    	}else{
    		// If pieces on board below count 18 
	    	if(initNotDone){
	    		
	    		if(nmm.legalMove(posInNMM, 0, color)){
	    			//Set in gbinfo next player turns
	    			//Remove
	    			if(nmm.remove(posInNMM)){
	    				togglePlayer();
	    				timeForRemove=true;
	    				//Morris
	    				//Set in gbinfo morris, same players turn
	    			}
	    			
	    			
	    			initMoveCounter++;
	    			if(initMoveCounter>17){ //Note: Maybe remove and put in gameboardinfo
	        			initNotDone=false;
	        		}
	    			
	    		}else{
	    			//Illegal move, message the player
	    		}
	    		
	    		
	    	}else{
	    		//If all 18 pieces is on board
	    		
	    	}
    	}
    	
    	gbInfo.setPiecesPos(nmm.getGameplan());
    	
    }
    
    private void togglePlayer(){
    	if(nmm.getTurn()==1) 
			nmm.setTurn(2);//change maby
        else 
        	nmm.setTurn(1);
    }
    
    
    public int converterFromPosToNMM(int pos){
    	switch (pos){
    	
    	case 1:
    		return 3;
    	case 2:
			return 6;
		case 3:
			return 9;
		case 4:
			return 2;
		case 5:
			return 5;
		case 6:
			return 8;
		case 7:
			return 1;
		case 8:
			return 4;
		case 9:
			return 7;
		case 10:
			return 24;
		case 11:
    		return 23;
		case 12:
			return 22;
		case 13:
    		return 10;
		case 14:
			return 11;
		case 15:
    		return 12;
		case 16:
    		return 19;
		case 17:
    		return 16;
		case 18:
			return 13;
		case 19:
    		return 20;
		case 20:
    		return 17;
		case 21:
    		return 14;
		case 22:
    		return 21;
		case 23:
    		return 18;
		case 24:
    		return 15;
    		default:
    			return -1;
    	}
    	
    }

	public GameboardInfo getGbInfo() {
		return gbInfo;
	}

	public void setGbInfo(GameboardInfo gbInfo) {
		this.gbInfo = gbInfo;
	}
	
}
