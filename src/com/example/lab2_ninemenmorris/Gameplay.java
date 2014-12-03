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
    private boolean fromSelected=false;
    private int savedFrom;
    
    
    public Gameplay(int[] xCords, int[] yCords){
    	this.xCords = xCords;
    	this.yCords = yCords;
    	rectHitBoxes = new Rect[25];
    	gbInfo=new GameboardInfo();
    	//if saved game gbInfo.init to nmm;
    	nmm=new NineMenMorrisRules();
    	
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
    			move(converterFromPosToNMM(i)); 
    			return i;
    		}
    	}
    	return -1;
    	
    }
    
    private void move(int posInNMM){
    	//savedFrom=-1;
    	int messageInfo = -1;
    	
    	int color = nmm.getTurn();
    	//If remove = true, remove piece
    	if(timeForRemove){
    		
    		if(nmm.remove(posInNMM, color)){
    			
    			timeForRemove=false;
    			messageInfo=1; //Removed successfully
    			
    			System.out.println("COLOR TURN NOW: "+nmm.getTurn());
    			
    			if(nmm.win(nmm.getTurn())){
    				if(nmm.getTurn()==1){
    					messageInfo=5;
    				}else{
    					messageInfo=4;
    				}
    				
    			}
    		}else{
    			messageInfo=0; //Invalid move, cannot remove own piece or empty space
    			
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
	    				messageInfo=2; //MORRIS, remove your opponents piece
	    				//Morris
	    				//Set in gbinfo morris, same players turn
	    			}
	    			
	    			
	    			initMoveCounter++;
	    			if((nmm.bluemarker+nmm.redmarker)<1){ //Note: Maybe remove and put in gameboardinfo
	        			initNotDone=false;
	        		}
	    			
	    		}else{
	    			//Illegal move, message the player
	    		}
	    		
	    		
	    	}else{
	    		//If all 18 pieces is on board
	    		System.out.println("18 pieces on board");
	    		if(fromSelected){
	    			System.out.println("18 pieces on board: Preforming move");
	    			int from = savedFrom;
		    		if(nmm.legalMove(posInNMM, from, color)){
		    			//Set in gbinfo next player turns
		    			//Remove
		    			if(nmm.remove(posInNMM)){
		    				togglePlayer();
		    				timeForRemove=true;
		    				messageInfo=2; //MORRIS, remove your opponents piece
		    				//Morris
		    				//Set in gbinfo morris, same players turn
		    			}
		    			gbInfo.setPiecesPos(nmm.getGameplan());
		    		}else{
		    			messageInfo=3;
		    		}
		    		fromSelected=false;
	    		}else{
	    			System.out.println("18 pieces on board: Saving from location");
	    			savedFrom=posInNMM;
	    			fromSelected=true;
	    		}
	    		
	    	}
    	}
    	gbInfo.setPlayerTurn(nmm.getTurn());
    	gbInfo.setMessageInfo(messageInfo);
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
