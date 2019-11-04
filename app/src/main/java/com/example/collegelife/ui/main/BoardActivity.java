package com.example.collegelife.ui.main;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.collegelife.R;

import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity {

    private static final String TAG = "Board_Activity";
    private ArrayList<Character> characters = new ArrayList<>();
    int turn = 0;
    private Game game;
    private TextView showName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        Button spin = findViewById(R.id.SpinButton);
        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(view.getContext(), SpinboardActivity.class);
                //intent1.putExtra("Player_name", player1.getName());
                //view.getContext().startActivity(intent1);
                startActivityForResult(intent1, 0);
            }
        });

        createCharacters();

        setBoard(characters);

        game = new Game(characters);

        Character currPlayer = game.getCurrentPlayer();
        showName = findViewById(R.id.display_name);
        showName.setText(currPlayer.getName());
        //TextView showGPA = findViewById(R.id.GPA);
       // showGPA.setText(player1.getGPA());

    }

    private void setBoard(ArrayList<Character> playerList){

        TableRow startBlock = findViewById(R.id.icons_s);
        for (Character player: playerList) {
            switch (player.getIcon()) {
                case ("spade"):
                    ImageView spade = new ImageView(this);
                    spade.setImageResource(R.drawable.spade);
                    startBlock.addView(spade, 100, 100);
                    player.setCurrentBlock(spade);
                    break;

                case ("heart"):
                    ImageView heart = new ImageView(this);
                    heart.setImageResource(R.drawable.heart);
                    startBlock.addView(heart, 100, 100);
                    player.setCurrentBlock(heart);
                    break;

                case ("club"):
                    ImageView club = new ImageView(this);
                    club.setImageResource(R.drawable.club);
                    startBlock.addView(club, 100, 100);
                    player.setCurrentBlock(club);
                    break;

                case ("diamond"):
                    ImageView diamond = new ImageView(this);
                    diamond.setImageResource(R.drawable.diamond);
                    startBlock.addView(diamond, 100, 100);
                    player.setCurrentBlock(diamond);
                    break;
            }
        }
    }

    private void updateBoard(Character player, int move_space){

        // gets current block
        TableRow block = (TableRow) player.getCurrentBlock().getParent();
        String space = (String) block.getTag();

        // get next block based off spin
        TableRow nextBlock = getNextBlock(space, move_space);

        //removes symbol from current space
        block.removeView(player.getCurrentBlock());

        //move symbol to next block
        switch (player.getIcon()) {
            case ("spade"):
                ImageView spade = new ImageView(this);
                spade.setImageResource(R.drawable.spade);
                player.setCurrentBlock(spade);
                nextBlock.addView(spade, 100, 100);
                break;

            case ("heart"):
                ImageView club = new ImageView(this);
                club.setImageResource(R.drawable.heart);
                player.setCurrentBlock(club);
                nextBlock.addView(club, 100, 100);
                break;

            case ("club"):
                ImageView heart = new ImageView(this);
                heart.setImageResource(R.drawable.club);
                player.setCurrentBlock(heart);
                nextBlock.addView(heart, 100, 100);
                break;

            case ("diamond"):
                ImageView diamond = new ImageView(this);
                diamond.setImageResource(R.drawable.diamond);
                player.setCurrentBlock(diamond);
                nextBlock.addView(diamond, 100, 100);
                break;
        }
        game.nextPlayer();
        Character currPlayer = game.getCurrentPlayer();
        showName = findViewById(R.id.display_name);
        showName.setText(currPlayer.getName());
    }

    private TableRow getNextBlock(String current_space, int move_space) {

        int newSpace = Integer.parseInt(current_space) + move_space;
        Log.d(TAG, "new space " + newSpace);
        TableRow next;
        switch (newSpace){

            case(1):
                next = findViewById(R.id.icons_1);
                break;
            case(2):
                next = findViewById(R.id.icons_2);
                break;
            case(3):
                next = findViewById(R.id.icons_3);
                break;
            case(4):
                next = findViewById(R.id.icons_4);
                break;
            case(5):
                next = findViewById(R.id.icons_5);
                break;
            case(6):
                next = findViewById(R.id.icons_6);
                break;
            case(7):
                next = findViewById(R.id.icons_7);
                break;
            case(8):
                next = findViewById(R.id.icons_8);
                break;
            case(9):
                next = findViewById(R.id.icons_9);
                break;
            case(10):
                next = findViewById(R.id.icons_10);
                break;
            case(11):
                next = findViewById(R.id.icons_11);
                break;
            case(12):
                next = findViewById(R.id.icons_12);
                break;
            case(13):
                next = findViewById(R.id.icons_13);
                break;
            default:
                next = findViewById(R.id.icons_s);
        }
        return next;

    }

    private void createCharacters(){
        // get info from calling fragment
        Intent intent = getIntent();
        // multiplayer
        String[] players = intent.getStringArrayExtra("players");
        String[] icons = intent.getStringArrayExtra("tokens");
        int count = 0;
        for (String player_name: players) {
            if (player_name != null) {
                Log.d(TAG, "Name: " + player_name);
                Character player = new Character(player_name);
                Log.d(TAG, "Name: " + icons[count]);
                if (icons[count] != null) {
                    player.setIcon(icons[count]);
                }
                characters.add(player);
                count++;
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0){
            if(resultCode == 0) {
                turn = data.getIntExtra("spin", 0);
                updateBoard(game.getCurrentPlayer(), turn);
                Log.d(TAG, "Spin: " + turn);
            }
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart is called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume is called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause is called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop is called");
    }

    @Override
    // Shoulde be called before onPause
    protected void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
    }

    @Override
    // Should be called after onStart and onResume
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }
}

