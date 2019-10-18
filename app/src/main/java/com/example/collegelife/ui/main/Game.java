package com.example.collegelife.ui.main;

class Game {

    private enum STATE {Inactive, Active, Won};
    private STATE gamestate;

    //private Symbol currentSymbol;

    private enum Player {Player1};

    // constructor
    Game() {
        //gameGrid = new GameGrid();
        gamestate = STATE.Active;
        //currentSymbol = Symbol.Symbol_Create();
    }



}
