package com.example.lab2_ninemenmorris;


public class PlayModel {
        NineMenMorrisRules nmm;
        int[] gameplanTemp;
        int pos, color;
        int userinput =0;
        boolean win=false;
       
        public void play(int xCord, int yCord){
                nmm = new NineMenMorrisRules();
               
                //initiating game with placing all Morrises
                placeUnits(xCord,yCord);
       
                System.out.println("Starting game...");
                while (win==false){
                       
                        System.out.println("Play: Current turn 1=blue 2=red: "+nmm.getTurn());
                        int to=0;
                        int from;
                        boolean moveUnit =true;
                        while(moveUnit){
                                color= nmm.getTurn(); //maby change
                                //Waiting for input from graphical
                                
                                to=22;//remove
                                from=4;//remove
                                if(nmm.legalMove(to, from, color)==false)
                                        moveUnit=true;
                                else
                                        moveUnit=false;
                        }
                       
                       
                        //next persons turn alternativly win
                        color=nmm.getTurn();
                        if(color==1) color=2;//change maby
                        else color=1;//change maby
                       
                       
                        if(nmm.remove(to)){//if to-positon makes three in a row start remove
                               
                                //Asking user what to remove (tap to remove)
                                //trying to remove that
                                nmm.remove(userinput, color);
                               
                                        win=nmm.win(color);
                        }
                       
                }
                if(color==1) color=2;//change maby
                else color=1;//change maby
                System.out.println("Ending game... Winner: "+ color);
                return;
        }
 
        private void placeUnits(int xCord, int yCord) {
               
                for (int i = 0; i < 18; i++) {
                       
                        System.out.println("Init: Current turn 1=blue 2=red: "+nmm.getTurn());
                       
                        boolean placeUnit =true;
                        while(placeUnit){
                                color= nmm.getTurn(); //maby change
                                pos =1;
                                //graphics wait for to positon from graphics
                                if(nmm.legalMove(pos, 0, color)==false)
                                        placeUnit=true;
                                else
                                        placeUnit=false;
                        }
                       
                        //Toggling color for remove
                        if(color==1) color=2;//change maby
                        else color=1;//change maby
                       
                        if(nmm.remove(i)){//if to-positon makes three in a row start remove
                               
                                //Asking user what to remove
                                //trying to remove that
                                nmm.remove(userinput, color);
                               
                                        win=nmm.win(color);
                        }
                               
                }
                        System.out.println("All units placed...");
        }
}