package com.example.collegelife.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegelife.R;

import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity {

    private static final String TAG = "Board_Activity";
    private SparseArray<Character> characters = new SparseArray<>();
    int turn = 0;
    private Game game;
    private TextView showName;
    private TextView gpaview;
    private TextView debtview;

    Boolean ownHouse_s = false;
    Boolean ownHouse_h = false;
    Boolean ownHouse_c = false;
    Boolean ownHouse_d = false;
    Boolean ownPet_s = false;
    Boolean ownPet_h = false;
    Boolean ownPet_c = false;
    Boolean ownPet_d = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        gpaview = this.findViewById(R.id.gpa);
        debtview = this.findViewById(R.id.debt);

        Button spin = findViewById(R.id.SpinButton);
        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(view.getContext(), SpinboardActivity.class);
                startActivityForResult(intent1, 0);
            }
        });

        createCharacters();

        setBoard(characters);

        game = new Game(characters);

        Character currPlayer = game.getCurrentPlayer();
        showName = findViewById(R.id.display_name);
        showName.setText(currPlayer.getName());


        gpaview.setText(currPlayer.getGPA());
        debtview.setText("$ " + currPlayer.getDebt());

    }


    private void setBoard(SparseArray<Character> playerList){

        TableRow startBlock = findViewById(R.id.icons_s);
        //transfer gpa and debt to string in order to fit in textView.

        int size = characters.size();
        for (int i = 0; i < size; i++) {
            Character player = playerList.valueAt(i);

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

        //setContentView(R.layout.activity_board);
        if(game.validMove(Integer.parseInt(space), move_space)) {

            // get next block based off spin
            TableRow nextBlock = getNextBlock(space, move_space);
            int newSpace = Integer.parseInt(space) + move_space;

            //add to players gpa
            player.setGPA(move_space);

            //removes symbol from current space
            block.removeView(player.getCurrentBlock());

            switch (player.getIcon()) {
                case ("spade"):
                    ImageView spade = new ImageView(this);
                    spade.setImageResource(R.drawable.spade);
                    player.setCurrentBlock(spade);
                    nextBlock.addView(spade, 100, 100);
                    getEventCard(newSpace, player);
                    break;

                case ("heart"):
                    ImageView club = new ImageView(this);
                    club.setImageResource(R.drawable.heart);
                    player.setCurrentBlock(club);
                    nextBlock.addView(club, 100, 100);
                    getEventCard(newSpace, player);
                    break;

                case ("club"):
                    ImageView heart = new ImageView(this);
                    heart.setImageResource(R.drawable.club);
                    player.setCurrentBlock(heart);
                    nextBlock.addView(heart, 100, 100);
                    getEventCard(newSpace, player);
                    break;

                case ("diamond"):
                    ImageView diamond = new ImageView(this);
                    diamond.setImageResource(R.drawable.diamond);
                    player.setCurrentBlock(diamond);
                    nextBlock.addView(diamond, 100, 100);
                    getEventCard(newSpace, player);
                    break;
            }

        }else {
            int validSpace = (25 - Integer.parseInt(space));
            Toast.makeText(getApplicationContext(), "Invalid move. Need a " + validSpace + " or lower", Toast.LENGTH_SHORT).show();
        }
        game.nextPlayer();
        Character currPlayer = game.getCurrentPlayer();
        showName = findViewById(R.id.display_name);
        showName.setText(currPlayer.getName());
        gpaview.setText(currPlayer.getGPA());
        debtview.setText("$ " + currPlayer.getDebt());
    }

    private void getEventCard(int newSpace, Character player) {


        //link to pop up window
        Intent intent = new Intent(getApplicationContext(), PopupCardActivity.class);

        // link to winners view
        Intent gameOver = new Intent(getApplicationContext(), ScoreActivity.class);

        gpaview = this.findViewById(R.id.gpa);
        debtview = this.findViewById(R.id.debt);

        //move symbol to next block
        ArrayList<Character> winners;

        switch (newSpace) {
            case (1):
                intent.putExtra("str",getString( R.string.Welcome_1)+"\n"+getString(R.string.Welcome_2));
                player.addDebt(10000);
                break;
            case (2):
            case (14):

                intent.putExtra("str",getString(R.string.Community_1)+"\n"+getString(R.string.Community_2));

                break;
            case (3):

                intent.putExtra("str", getString(R.string.Fall_1)+"\n"+getString(R.string.Fall_2));
                player.addDebt(200);
                break;
            case (4):

                intent.putExtra("str",getString(R.string.Apartment1)+"\n"+getString(R.string.Apartment2));
                player.addDebt(500);
                ownHouse_d = true;
                findViewById(R.id.imageHome).setVisibility(View.VISIBLE);
                break;
            case (5):

                intent.putExtra("str",getString(R.string.BuyDog_1)+"\n"+getString(R.string.BuyDog_2));
                player.addDebt(200);
                ownPet_d = true;
                findViewById(R.id.imageDog).setVisibility(View.VISIBLE);
                break;
            case (6):

                intent.putExtra("str",getString(R.string.FailMidterm_1)+"\n"+getString(R.string.FailMidterm_2));
                player.subtractDebt(150);
                break;

            case (7):

                intent.putExtra("str",getString(R.string.MobileClub1)+"\n"+getString(R.string.MobileClub2));
                break;
            case (8):

                intent.putExtra("str",getString(R.string.Spring_1)+"\n"+getString(R.string.Spring_2));
                player.addDebt(500);
                break;
            case (9):

            case (21):

                intent.putExtra("str",getString(R.string.MissClass_1)+"\n"+getString(R.string.MissClass_2));
                break;
            case (10):
            case (22):

                intent.putExtra("str",getString(R.string.Hungry_1)+"\n"+getString(R.string.Hungry_2));
                player.addDebt(50);
                break;
            case (11):

                intent.putExtra("str",getString(R.string.electricityOff_1)+"\n"+getString(R.string.electricityOff_2));
                intent.putExtra("str",getString(R.string.PreRentHome));
                if (ownHouse_d) {
                    player.addDebt(100);
                }
                break;
            case (12):
            case (24):

                intent.putExtra("str",getString(R.string.FinalGrade_1)+"\n"+getString(R.string.FinalGrade_2));
                break;
            case (13):
                intent.putExtra("str",getString(R.string.SecondSem_1)+"\n"+getString(R.string.SecondSem_2));
                player.addDebt(10000);
                break;
            case (15):
                intent.putExtra("str",getString(R.string.BuyCar_1)+"\n"+getString(R.string.BuyCar_2));
                player.addDebt(9000);
                break;
            case (16):
                intent.putExtra("str",getString(R.string.RentRemind_1)+"\n"+getString(R.string.RentRemind_2));
                intent.putExtra("str",getString(R.string.PreRentHome));
                if (ownHouse_d) {
                    player.addDebt(500);
                }
                break;
            case (17):
                intent.putExtra("str",getString(R.string.PetSick_1)+"\n"+getString(R.string.PetSick_2));
                intent.putExtra("str",getString(R.string.PrePet));
                if (ownPet_d) {
                    player.addDebt(300);
                }
                break;
            case (18):
                intent.putExtra("str",getString(R.string.ApplyScholarship_1)+"\n"+getString(R.string.ApplyScholarship_2));
                player.subtractDebt(5000);
                break;
            case (19):
                intent.putExtra("str",getString(R.string.GameClub_1)+"\n"+getString(R.string.GameClub_2));
                player.subtractDebt(50);
                break;
            case (20):
                intent.putExtra("str",getString(R.string.Spring_1)+"\n"+getString(R.string.Spring_2));
                player.addDebt(200);
                break;
            case (23):
                intent.putExtra("str",getString(R.string.DropPhone_1)+"\n"+getString(R.string.DropPhone_2));
                player.addDebt(300);
                break;
            case (25):
                winners = game.getWinners();
                int w_size = winners.size();
                String[] names = new String[w_size];
                String[] fScores = new String[w_size];
                String[] fGpa = new String[w_size];
                for (int i = 0; i < w_size; i++) {
                    Character p = winners.get(i);
                    names[i] = p.getName();
                    fScores[i] = p.getDebt();
                    fGpa[i] = p.getGPA();
                }

                gameOver.putExtra("pname", names);
                gameOver.putExtra("debt", fScores);
                gameOver.putExtra("fgpa", fGpa);

                break;

        }
        if (newSpace == 25){
            startActivity(gameOver);
            finish();
        }else {
            startActivity(intent);
            gpaview.setText(player.getGPA());
            debtview.setText("$ " + player.getDebt());
        }
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
            case(14):
                next = findViewById(R.id.icons_14);
                break;
            case(15):
                next = findViewById(R.id.icons_15);
                break;
            case(16):
                next = findViewById(R.id.icons_16);
                break;
            case(17):
                next = findViewById(R.id.icons_17);
                break;
            case(18):
                next = findViewById(R.id.icons_18);
                break;
            case(19):
                next = findViewById(R.id.icons_19);
                break;
            case(20):
                next = findViewById(R.id.icons_20);
                break;
            case(21):
                next = findViewById(R.id.icons_21);
                break;
            case(22):
                next = findViewById(R.id.icons_22);
                break;
            case(23):
                next = findViewById(R.id.icons_23);
                break;
            case(24):
                next = findViewById(R.id.icons_24);
                break;
            case(25):
                next = findViewById(R.id.icons_25);
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
        assert players != null;
        for (String player_name: players) {
            if (player_name != null) {
                Log.d(TAG, "Name: " + player_name);
                Character player = new Character(player_name);
                assert icons != null;
                Log.d(TAG, "Name: " + icons[count]);
                if (icons[count] != null) {
                    player.setIcon(icons[count]);
                }
                characters.append(count, player);
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
    private void updateUI() {
        switch (game.getCurrentPlayer().getIcon()) {
            case ("spade"):
                if (ownPet_s)
                    findViewById(R.id.imageDog).setVisibility(View.VISIBLE);
                else
                    findViewById(R.id.imageDog).setVisibility(View.GONE);

                if (ownHouse_s) {
                    findViewById(R.id.imageHome).setVisibility(View.VISIBLE);
                }
                else
                    findViewById(R.id.imageHome).setVisibility(View.GONE);
                break;

            case ("heart"):
                if (ownPet_h)
                    findViewById(R.id.imageDog).setVisibility(View.VISIBLE);
                else
                    findViewById(R.id.imageDog).setVisibility(View.GONE);

                if (ownHouse_h) {
                    findViewById(R.id.imageHome).setVisibility(View.VISIBLE);
                }
                else {
                    findViewById(R.id.imageHome).setVisibility(View.GONE);
                }
                break;

            case ("club"):
                if (ownPet_c)
                    findViewById(R.id.imageDog).setVisibility(View.VISIBLE);
                else
                    findViewById(R.id.imageDog).setVisibility(View.GONE);

                if (ownHouse_c) {
                    findViewById(R.id.imageHome).setVisibility(View.VISIBLE);
                }
                else {
                    findViewById(R.id.imageHome).setVisibility(View.GONE);
                }
                break;

            case ("diamond"):
                if (ownPet_d)
                    findViewById(R.id.imageDog).setVisibility(View.VISIBLE);
                else
                    findViewById(R.id.imageDog).setVisibility(View.GONE);
                if (ownHouse_d) {
                    findViewById(R.id.imageHome).setVisibility(View.VISIBLE);
                }
                else {
                    findViewById(R.id.imageHome).setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart is called");
        updateUI();
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

/*    @Override
    // Should be be called before onPause
    protected void onSaveInstanceState(@NonNull Bundle outstate){
        super.onSaveInstanceState(outstate);
    }

    @Override
    // Should be called after onStart and onResume
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }*/
}

