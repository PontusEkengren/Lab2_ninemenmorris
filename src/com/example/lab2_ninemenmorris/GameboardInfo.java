package com.example.lab2_ninemenmorris;

import java.util.Iterator;

import android.util.Log;

public class GameboardInfo {

	private int[] piecesPos=new int[25];
	private int pieceColor;
	private int playerTurn;
	private int gameStatus;
	private int[] piecesPosNMM=new int[25];
	private int messageInfo=-1;
	private NineMenMorrisRules nmm =null;
	private int bluemarker=0;
	private int redmarker=0;
	
	
	public GameboardInfo(){}
	
	public NineMenMorrisRules initNmm(){
		nmm = new NineMenMorrisRules();
		nmm.setGameplan(piecesPosNMM);
		nmm.setTurn(playerTurn);
		nmm.setBluemarker(bluemarker);
		nmm.setRedmarker(redmarker);
		return nmm;
	}

	public int[] getPiecesPos() {
		
		int[] tempPos= new int[25];
		for (int i = 1; i < 25; i++) {
			//Log.d("Lab2", "Converter values: converterFromNMMtoPos(i): "+ converterFromNMMtoPos(i)+" piecesPosNMM[converterFromNMMtoPos(i)] "+piecesPosNMM[converterFromNMMtoPos(i)]);
			
			tempPos[converterFromNMMtoPos(i)]=piecesPosNMM[i];
		}
		
		return tempPos;
	}

	public void setPiecesPos(int[] piecesPos) {
		
		
		piecesPosNMM=piecesPos;
		for (int i=1;i<piecesPos.length;i++) {
			//System.out.println("CHECKING gbInfo piecePos"+piecesPos[i]);
			this.piecesPos[i] = piecesPos[i]; //converterFromNMMtoPos(i, piecesPos);
			
		}
	}

	public int getPieceColor() {
		return pieceColor;
	}

	public void setPieceColor(int pieceColor) {
		this.pieceColor = pieceColor;
	}

	public int getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(int playerTurn) {
		this.playerTurn = playerTurn;
	}

	public int getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(int gameStatus) {
		this.gameStatus = gameStatus;
	}
	
	public void savePieces(int bluemarker, int redmarker){
		this.bluemarker=bluemarker;
		this.redmarker=redmarker;
	}
	
	public int getBluemarker() {
		return bluemarker;
	}

	public int getRedmarker() {
		return redmarker;
	}

	public String getMessageInfo() {
		switch(messageInfo){
		case -1:
			return "-1";
		case 0:
			return "Invalid remove, remove your opponents tile";
		case 1:
			return "Removed oppnents tile successfully";
		case 2:
			return "MORRIS! Remove your opponents tile!";
		case 3:
			return "Invalid move! Select your from tile";
		case 4:
			return "Congratulations! BLUE have won!";
		case 5:
			return "Congratulations! RED have won!";
			default:
				return "YOU SUCK!";
		}
	}

	public void setMessageInfo(int messageInfo) {
		this.messageInfo = messageInfo;
	}

	public int converterFromNMMtoPos(int NmmPos){
    	switch(NmmPos){
    	case 1:
    		return 7;
    	case 2:
    		return 4;
    	case 3:
    		//return nmm.getGameplan()[3];
    		return 1;
    	case 4:
    		return 8;
    	case 5:
    		return 5;
    	case 6:
    		return 2;
    	case 7:
    		return 9;
    	case 8:
    		return 6;
    	case 9:
    		return 3;
    	case 10:
    		return 13;
    	case 11:
    		return 14;
    	case 12:
    		return 15;
    	case 13:
    		return 18;
    	case 14:
    		return 21;
    	case 15:
    		return 24;
    	case 16:
    		return 17;
    	case 17:
    		return 20;
    	case 18:
    		return 23;
    	case 19:
    		return 16;
    	case 20:
    		return 19;
    	case 21:
    		return 22;
    	case 22:
    		return 12;
    	case 23:
    		return 11;
    	case 24:
    		return 10;
    		default:
    			return -1;
    	}
    }
}
