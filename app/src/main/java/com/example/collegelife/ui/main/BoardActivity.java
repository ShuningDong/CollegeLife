package com.example.collegelife.ui.main;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.example.collegelife.R;

import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity {

    private static final String TAG = "Board_Activity";
    private ArrayList<Character> characters = new ArrayList<>();
    int turn = 0;
    private Game game;
    private TextView showName;

    //gpa and debt information
    TextView gpaview;
    TextView debtview;


    double gpa = 4.0;
    int debt = 0;
    double spadegpa = 4.0;
    int spadedebt = 0;
    double heartgpa = 4.0;
    int heartdebt = 0;
    double clubgpa = 4.0;
    int clubdebt = 0;
    double diamondgpa = 4.0;
    int diamonddebt = 0;

    String str = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        TextView gpaview = (TextView)this.findViewById(R.id.gpa);
        TextView debtview = (TextView)this.findViewById(R.id.debt);

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

        String gpa1 = Double.toString(gpa);
        String debt1 = Integer.toString(debt);

        gpaview.setText(gpa1);
        debtview.setText(debt1);

        game = new Game(characters);

        Character currPlayer = game.getCurrentPlayer();
        showName = findViewById(R.id.display_name);
        showName.setText(currPlayer.getName());
        //TextView showGPA = findViewById(R.id.GPA);
       // showGPA.setText(player1.getGPA());

    }


    private void setBoard(ArrayList<Character> playerList){

        TableRow startBlock = findViewById(R.id.icons_s);
        //transfer gpa and debt to string in order to fit in textView.
        String gpa1 = Double.toString(gpa);
        String debt1 = Integer.toString(debt);

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

        //setContentView(R.layout.activity_board);
        if(game.validMove(Integer.parseInt(space), move_space)) {

            // get next block based off spin
            TableRow nextBlock = getNextBlock(space, move_space);
            int newSpace = Integer.parseInt(space) + move_space;


            //removes symbol from current space
            block.removeView(player.getCurrentBlock());


            //link to pop up window
            Intent intent = new Intent(getApplicationContext(), PopupCardActivity.class);


            TextView gpaview = (TextView)this.findViewById(R.id.gpa);
            TextView debtview = (TextView)this.findViewById(R.id.debt);



            String spadegpa_s;
            String spadedebt_s;
            String heartgpa_s;
            String heartdebt_s;
            String clubgpa_s;
            String clubdebt_s;
            String diamondgpa_s;
            String diamonddebt_s;

            //move symbol to next block
            switch (player.getIcon()) {
                case ("spade"):
                    ImageView spade = new ImageView(this);
                    spade.setImageResource(R.drawable.spade);
                    player.setCurrentBlock(spade);
                    nextBlock.addView(spade, 100, 100);
                    switch (newSpace) {

                        case (1):

                            intent.putExtra("str","Welcome !!You need to register for class");
                            spadedebt-=10000;
                            break;
                        case (2):

                            intent.putExtra("str","You attend a community service. Trying to give back to the society");

                            break;
                        case (3):

                            intent.putExtra("str", "Finally Break time !!\n" +
                                    "You went to Florida for fun\n");
                            spadedebt-=200;
                            break;
                        case (4):

                            intent.putExtra("str","You were kick off from your parent home\n" +
                                    "You need to rent an apartment");
                            spadedebt-=500;
                            break;
                        case (5):

                        intent.putExtra("str","You feel lonely\n" +
                                "You decide to buy a dog\n");
                            spadedebt-=200;
                        break;
                        case (6):

                            intent.putExtra("str","You fail your mid-term exam\n" +
                                    "You decide to drop the class a retake\n");
                            break;

                        case (7):

                        intent.putExtra("str","You enter the Mobile App Development Club\n" +
                                "You start to build your own mobile app");
                        break;
                        case (8):

                            intent.putExtra("str","Break time Again!! \n" +
                                    "You went to Chicago for fun ");
                            spadedebt-=200;
                            break;
                        case (9):

                            intent.putExtra("str","You overslept\n" +
                                    "You miss the most important class");
                            spadegpa-=0.1;
                            break;
                        case (10):

                            intent.putExtra("str","You feel hungry \n" +
                                    "It is grocery shopping time");
                            spadedebt-=50;
                            break;
                        case (11):

                            intent.putExtra("str","Opp! Your electricity went off!\n" +
                                    "Did you pay your utilities bill?\n");

                            spadedebt -=100;
                            break;
                        case (12):

                            intent.putExtra("str","Finals finally over ~~\n" +
                                    "Here is your grade\n");
                            break;
                        case (13):

                            intent.putExtra("str","Happy second semester\n" +
                                    "you need to register for class again ");
                            spadedebt-=10000;
                            break;
                        case (14):

                            intent.putExtra("str","You attend a community service \n" +
                                    "Trying to give back to the society");
                            break;
                        case (15):

                            intent.putExtra("str","You can’t go anywhere\n" +
                                    "You need to buy a car");
                            spadedebt-=9000;
                            break;
                        case (16):

                            intent.putExtra("str","Don’t forget your rent!\n" +
                                    "You no longer live with your parent");
                            spadedebt-=500;
                            break;
                        case (17):

                            intent.putExtra("str","You lovely dog get sick\n" +
                                    "You need to take her to the pet");
                            spadedebt-=300;
                            break;
                        case (18):

                            intent.putExtra("str","You try to pay off your loan\n" +
                                    "You try to apply for scholarship\n");
                            spadedebt+=5000;
                            break;
                        case (19):

                            intent.putExtra("str","You enter the Game Animation Club\n" +
                                    "You start to build your own game");
                            break;
                        case (20):

                            intent.putExtra("str","Break time Again!! \n" +
                                    "You went to Chicago for fun");
                            spadedebt-=200;
                            break;

                        case (21):

                            intent.putExtra("str","You overslept\n" +
                                    "You miss the most important class");
                            break;
                        case (22):

                            intent.putExtra("str","You feel hungry \n" +
                                    "It is grocery shopping time");
                            spadedebt-=50;
                            break;
                        case (23):

                            intent.putExtra("str","Oops! You drop your phone!\n" +
                                    "Touch screen no longer working \n");
                            spadedebt-=50;
                            break;
                        case (24):

                            intent.putExtra("str","Finals finally over ~~\n" +
                                    "Here is your grade\n");
                            break;


                    }




                    spadegpa_s = Double.toString(spadegpa);
                    spadedebt_s = Integer.toString(spadedebt);



                    startActivity(intent);

                    gpaview.setText(spadegpa_s);
                    debtview.setText(spadedebt_s);


                    break;

                case ("heart"):
                    ImageView club = new ImageView(this);
                    club.setImageResource(R.drawable.heart);
                    player.setCurrentBlock(club);
                    nextBlock.addView(club, 100, 100);

                    switch (newSpace) {

                        case (1):

                            intent.putExtra("str","Welcome !!You need to register for class");
                            heartdebt-=10000;
                            break;
                        case (2):

                            intent.putExtra("str","You attend a community service. Trying to give back to the society");

                            break;
                        case (3):

                            intent.putExtra("str", "Finally Break time !!\n" +
                                    "You went to Florida for fun\n");
                            heartdebt-=200;
                            break;
                        case (4):

                            intent.putExtra("str","You were kick off from your parent home\n" +
                                    "You need to rent an apartment");
                            heartdebt-=500;
                            break;
                        case (5):

                            intent.putExtra("str","You feel lonely\n" +
                                    "You decide to buy a dog\n");
                            heartdebt-=200;
                            break;
                        case (6):

                            intent.putExtra("str","You fail your mid-term exam\n" +
                                    "You decide to drop the class a retake\n");
                            break;

                        case (7):

                            intent.putExtra("str","You enter the Mobile App Development Club\n" +
                                    "You start to build your own mobile app");
                            break;
                        case (8):

                            intent.putExtra("str","Break time Again!! \n" +
                                    "You went to Chicago for fun ");
                            heartdebt-=200;
                            break;
                        case (9):

                            intent.putExtra("str","You overslept\n" +
                                    "You miss the most important class");
                            heartgpa-=0.1;
                            break;
                        case (10):

                            intent.putExtra("str","You feel hungry \n" +
                                    "It is grocery shopping time");
                            heartdebt-=50;
                            break;
                        case (11):

                            intent.putExtra("str","Opp! Your electricity went off!\n" +
                                    "Did you pay your utilities bill?\n");

                            heartdebt -=100;
                            break;
                        case (12):

                            intent.putExtra("str","Finals finally over ~~\n" +
                                    "Here is your grade\n");
                            break;
                        case (13):

                            intent.putExtra("str","Happy second semester\n" +
                                    "you need to register for class again ");
                            heartdebt-=10000;
                            break;
                        case (14):

                            intent.putExtra("str","You attend a community service \n" +
                                    "Trying to give back to the society");
                            break;
                        case (15):

                            intent.putExtra("str","You can’t go anywhere\n" +
                                    "You need to buy a car");
                            heartdebt-=9000;
                            break;
                        case (16):

                            intent.putExtra("str","Don’t forget your rent!\n" +
                                    "You no longer live with your parent");
                            heartdebt-=500;
                            break;
                        case (17):

                            intent.putExtra("str","You lovely dog get sick\n" +
                                    "You need to take her to the pet");
                            heartdebt-=300;
                            break;
                        case (18):

                            intent.putExtra("str","You try to pay off your loan\n" +
                                    "You try to apply for scholarship\n");
                            heartdebt+=5000;
                            break;
                        case (19):

                            intent.putExtra("str","You enter the Game Animation Club\n" +
                                    "You start to build your own game");
                            break;
                        case (20):

                            intent.putExtra("str","Break time Again!! \n" +
                                    "You went to Chicago for fun");
                            heartdebt-=200;
                            break;

                        case (21):

                            intent.putExtra("str","You overslept\n" +
                                    "You miss the most important class");
                            break;
                        case (22):

                            intent.putExtra("str","You feel hungry \n" +
                                    "It is grocery shopping time");
                            heartdebt-=50;
                            break;
                        case (23):

                            intent.putExtra("str","Oops! You drop your phone!\n" +
                                    "Touch screen no longer working \n");
                            heartdebt-=50;
                            break;
                        case (24):

                            intent.putExtra("str","Finals finally over ~~\n" +
                                    "Here is your grade\n");
                            break;


                    }


                    startActivity(intent);

                    heartgpa_s = Double.toString(heartgpa);
                    heartdebt_s = Integer.toString(heartdebt);


                    gpaview.setText(heartgpa_s);
                    debtview.setText(heartdebt_s);


                    break;

                case ("club"):
                    ImageView heart = new ImageView(this);
                    heart.setImageResource(R.drawable.club);
                    player.setCurrentBlock(heart);
                    nextBlock.addView(heart, 100, 100);

                    switch (newSpace) {

                        case (1):

                            intent.putExtra("str","Welcome !!You need to register for class");
                            clubdebt-=10000;
                            break;
                        case (2):

                            intent.putExtra("str","You attend a community service. Trying to give back to the society");

                            break;
                        case (3):

                            intent.putExtra("str", "Finally Break time !!\n" +
                                    "You went to Florida for fun\n");
                            clubdebt-=200;
                            break;
                        case (4):

                            intent.putExtra("str","You were kick off from your parent home\n" +
                                    "You need to rent an apartment");
                            clubdebt-=500;
                            break;
                        case (5):

                            intent.putExtra("str","You feel lonely\n" +
                                    "You decide to buy a dog\n");
                            clubdebt-=200;
                            break;
                        case (6):

                            intent.putExtra("str","You fail your mid-term exam\n" +
                                    "You decide to drop the class a retake\n");
                            break;

                        case (7):

                            intent.putExtra("str","You enter the Mobile App Development Club\n" +
                                    "You start to build your own mobile app");
                            break;
                        case (8):

                            intent.putExtra("str","Break time Again!! \n" +
                                    "You went to Chicago for fun ");
                            clubdebt-=200;
                            break;
                        case (9):

                            intent.putExtra("str","You overslept\n" +
                                    "You miss the most important class");
                            clubgpa-=0.1;
                            break;
                        case (10):

                            intent.putExtra("str","You feel hungry \n" +
                                    "It is grocery shopping time");
                            clubdebt-=50;
                            break;
                        case (11):

                            intent.putExtra("str","Opp! Your electricity went off!\n" +
                                    "Did you pay your utilities bill?\n");

                            clubdebt -=100;
                            break;
                        case (12):

                            intent.putExtra("str","Finals finally over ~~\n" +
                                    "Here is your grade\n");
                            break;
                        case (13):

                            intent.putExtra("str","Happy second semester\n" +
                                    "you need to register for class again ");
                            clubdebt-=10000;
                            break;
                        case (14):

                            intent.putExtra("str","You attend a community service \n" +
                                    "Trying to give back to the society");
                            break;
                        case (15):

                            intent.putExtra("str","You can’t go anywhere\n" +
                                    "You need to buy a car");
                            clubdebt-=9000;
                            break;
                        case (16):

                            intent.putExtra("str","Don’t forget your rent!\n" +
                                    "You no longer live with your parent");
                            clubdebt-=500;
                            break;
                        case (17):

                            intent.putExtra("str","You lovely dog get sick\n" +
                                    "You need to take her to the pet");
                            clubdebt-=300;
                            break;
                        case (18):

                            intent.putExtra("str","You try to pay off your loan\n" +
                                    "You try to apply for scholarship\n");
                            clubdebt+=5000;
                            break;
                        case (19):

                            intent.putExtra("str","You enter the Game Animation Club\n" +
                                    "You start to build your own game");
                            break;
                        case (20):

                            intent.putExtra("str","Break time Again!! \n" +
                                    "You went to Chicago for fun");
                            clubdebt-=200;
                            break;

                        case (21):

                            intent.putExtra("str","You overslept\n" +
                                    "You miss the most important class");
                            break;
                        case (22):

                            intent.putExtra("str","You feel hungry \n" +
                                    "It is grocery shopping time");
                            clubdebt-=50;
                            break;
                        case (23):

                            intent.putExtra("str","Oops! You drop your phone!\n" +
                                    "Touch screen no longer working \n");
                            clubdebt-=50;
                            break;
                        case (24):

                            intent.putExtra("str","Finals finally over ~~\n" +
                                    "Here is your grade\n");
                            break;


                    }


                    clubgpa_s = Double.toString(clubgpa);
                    clubdebt_s = Integer.toString(clubdebt);


                    gpaview.setText(clubgpa_s);
                    debtview.setText(clubdebt_s);
                    startActivity(intent);

                    break;

                case ("diamond"):
                    ImageView diamond = new ImageView(this);
                    diamond.setImageResource(R.drawable.diamond);
                    player.setCurrentBlock(diamond);
                    nextBlock.addView(diamond, 100, 100);

                    switch (newSpace) {

                        case (1):

                            intent.putExtra("str","Welcome !!You need to register for class");
                            diamonddebt-=10000;
                            break;
                        case (2):

                            intent.putExtra("str","You attend a community service. Trying to give back to the society");

                            break;
                        case (3):

                            intent.putExtra("str", "Finally Break time !!\n" +
                                    "You went to Florida for fun\n");
                            diamonddebt-=200;
                            break;
                        case (4):

                            intent.putExtra("str","You were kick off from your parent home\n" +
                                    "You need to rent an apartment");
                            diamonddebt-=500;
                            break;
                        case (5):

                            intent.putExtra("str","You feel lonely\n" +
                                    "You decide to buy a dog\n");
                            diamonddebt-=200;
                            break;
                        case (6):

                            intent.putExtra("str","You fail your mid-term exam\n" +
                                    "You decide to drop the class a retake\n");
                            break;

                        case (7):

                            intent.putExtra("str","You enter the Mobile App Development Club\n" +
                                    "You start to build your own mobile app");
                            break;
                        case (8):

                            intent.putExtra("str","Break time Again!! \n" +
                                    "You went to Chicago for fun ");
                            diamonddebt-=200;
                            break;
                        case (9):

                            intent.putExtra("str","You overslept\n" +
                                    "You miss the most important class");
                            diamondgpa-=0.1;
                            break;
                        case (10):

                            intent.putExtra("str","You feel hungry \n" +
                                    "It is grocery shopping time");
                            diamonddebt-=50;
                            break;
                        case (11):

                            intent.putExtra("str","Opp! Your electricity went off!\n" +
                                    "Did you pay your utilities bill?\n");

                            diamonddebt -=100;
                            break;
                        case (12):

                            intent.putExtra("str","Finals finally over ~~\n" +
                                    "Here is your grade\n");
                            break;
                        case (13):

                            intent.putExtra("str","Happy second semester\n" +
                                    "you need to register for class again ");
                            diamonddebt-=10000;
                            break;
                        case (14):

                            intent.putExtra("str","You attend a community service \n" +
                                    "Trying to give back to the society");
                            break;
                        case (15):

                            intent.putExtra("str","You can’t go anywhere\n" +
                                    "You need to buy a car");
                            diamonddebt-=9000;
                            break;
                        case (16):

                            intent.putExtra("str","Don’t forget your rent!\n" +
                                    "You no longer live with your parent");
                            diamonddebt-=500;
                            break;
                        case (17):

                            intent.putExtra("str","You lovely dog get sick\n" +
                                    "You need to take her to the pet");
                            diamonddebt-=300;
                            break;
                        case (18):

                            intent.putExtra("str","You try to pay off your loan\n" +
                                    "You try to apply for scholarship\n");
                            diamonddebt+=5000;
                            break;
                        case (19):

                            intent.putExtra("str","You enter the Game Animation Club\n" +
                                    "You start to build your own game");
                            break;
                        case (20):

                            intent.putExtra("str","Break time Again!! \n" +
                                    "You went to Chicago for fun");
                            diamonddebt-=200;
                            break;

                        case (21):

                            intent.putExtra("str","You overslept\n" +
                                    "You miss the most important class");
                            break;
                        case (22):

                            intent.putExtra("str","You feel hungry \n" +
                                    "It is grocery shopping time");
                            diamonddebt-=50;
                            break;
                        case (23):

                            intent.putExtra("str","Oops! You drop your phone!\n" +
                                    "Touch screen no longer working \n");
                            diamonddebt-=50;
                            break;
                        case (24):

                            intent.putExtra("str","Finals finally over ~~\n" +
                                    "Here is your grade\n");
                            break;


                    }





                    diamondgpa_s = Double.toString(diamondgpa);
                    diamonddebt_s = Integer.toString(diamonddebt);
                    startActivity(intent);

                    gpaview.setText(diamondgpa_s);
                    debtview.setText(diamonddebt_s);
                    break;
            }


            //set gpa and debt shown on broad.
            //String gpa1 = Double.toString(gpa);
            //String debt1 = Integer.toString(debt);

            //gpaview.setText(gpa1);
            //debtview.setText(debt1);
        }else {
            int validSpace = (move_space + Integer.parseInt(space)) - 25;
            Toast.makeText(getApplicationContext(), "Invalid move. Need a " + validSpace + " or lower", Toast.LENGTH_SHORT).show();
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
                //updateInfo(game.getCurrentPlayer(), turn);
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
    protected void onSaveInstanceState(@NonNull Bundle outstate){
        super.onSaveInstanceState(outstate);
    }

    @Override
    // Should be called after onStart and onResume
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }
}

