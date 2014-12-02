package com.example.lab2_ninemenmorris;

import android.graphics.Rect;

public class Unitinfo {
	private int[] xCords=new int[25];
    private int[] yCords=new int[25];
    private Rect[] rectHitBoxes=null;
    
    
    public Unitinfo(int[] xCords, int[] yCords){
    	this.xCords = xCords;
    	this.yCords = yCords;
    	rectHitBoxes = new Rect[25];
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
}
