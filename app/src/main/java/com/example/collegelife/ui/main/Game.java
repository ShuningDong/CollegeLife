package com.example.collegelife.ui.main;


import java.util.ArrayList;

class Game {

    private enum STATE {Inactive, Active, Won};
    private STATE gamestate;
    private ArrayList<Character> characters;
    private Character currentPlayer;



    // constructor
    Game( ArrayList<Character> players) {
        gamestate = STATE.Active;
        characters = players;
        currentPlayer = characters.get(0);
    }


    /////////// Methods ///////////////////////
   public void nextPlayer(){
        int curr = characters.indexOf(this.currentPlayer);
        if (curr == (characters.size() - 1)){
            curr = 0;
        } else {
            curr += 1;
        }
        currentPlayer = characters.get(curr);
   }

   public Character getCurrentPlayer() {
        return this.currentPlayer;
   }

   public void playerTurn(double debt, int gpa) {
        if (debt < 0) {
            this.currentPlayer.subtractDebt(debt);
        } else {
            this.currentPlayer.addDebt(debt);
        }
        this.currentPlayer.setGPA(gpa);
   }

   public boolean validMove(int currentSpace, int nextSpace){

        return ((currentSpace + nextSpace ) < 26);
    }

    public ArrayList<Character> getWinners(){
        ArrayList<Character> temp = new ArrayList<>();
        for (Character c: characters){
            if (temp.size() == 0){
                temp.add(c);
            } else {

                temp.add(c);
                /*for (int i= 0; i < temp.size(); i++) {
                    if (Integer.parseInt(c.getDebt()) < Integer.parseInt(temp.get(i).getDebt())) {
                        temp.add(i, c);
                        break;
                    } else if(Integer.parseInt(c.getDebt()) == Integer.parseInt(temp.get(i).getDebt())) {
                        if(Double.parseDouble(c.getGPA()) > Double.parseDouble(temp.get(i).getGPA())){
                            temp.add(i,c);

                        }else {
                            temp.add(c);
                        }
                        break;
                    }
                }*/
            }
        }
        return temp;
    }

}
