package com.example.collegelife.ui.main;


import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;

class Game {

    //private ArrayList<Character> characters;
    private SparseArray<Character> characters;
    private Character currentPlayer;
    private int size;

    private static final String TAG = "Game_logic";



    // constructor
    Game( SparseArray<Character> players) {
        characters = players;
        currentPlayer = characters.get(0);
        size = characters.size();
    }


    /////////// Methods ///////////////////////
    void nextPlayer(){
        int curr = characters.indexOfValue(this.currentPlayer);
        if (curr == (this.size - 1)){
            curr = 0;
        } else {
            curr += 1;
        }
        currentPlayer = characters.valueAt(curr);
   }

   Character getCurrentPlayer() {
        return this.currentPlayer;
   }

   boolean validMove(int currentSpace, int nextSpace){

        return ((currentSpace + nextSpace ) < 26);
    }

    ArrayList<Character> getWinners(){
        ArrayList<Character> temp = new ArrayList<>();
        int c_size = characters.size();
        for (int j = 0; j < c_size; j++){
                temp.add(characters.valueAt(j));
            }
        return temp;
    }

}
