package com.example.collegelife.ui.main;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;

import com.example.collegelife.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import android.view.View;
import android.widget.Toast;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

public class SpinboardActivity extends AppCompatActivity {

    private static final String TAG = "SpinBoardActivity";

    private FirebaseFirestore mFirestore;
    //Button button;
    TextView textView;
    ImageView spinboard2;

    //sensor
    private SensorManager sm;
    private float acelVal; //current acceleration value and gravity.
    private float acelLast;  //last acceleration value and gravity.
    private float shake;  //acceleration value differ from gravity.

    Random r;

    int degree = 0, degree_old = 0;

    private static final float FACTOR = 45.0f;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinboard);

        //button = (Button) findViewById(R.id.spinbn);
        textView = (TextView) findViewById(R.id.result);
        spinboard2 = (ImageView) findViewById(R.id.spinboard);

        //sensor
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sm.registerListener(sensorListener, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);

        acelVal = SensorManager.GRAVITY_EARTH;
        acelLast = SensorManager.GRAVITY_EARTH;
        shake = 0.00f;



        r = new Random();
    /*    button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                degree_old = degree % 360;
                degree = r.nextInt(3600) + 720;
                RotateAnimation rotate = new RotateAnimation(degree_old, degree,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(3600);
                rotate.setFillAfter(true);
                rotate.setInterpolator(new DecelerateInterpolator());
                rotate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        textView.setText("");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        textView.setText(currentNumber(360 - (degree % 360)));
                        Intent i = new Intent(getApplicationContext(), PopupCardActivity.class);
                        startActivity(i);


                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }

                });
                spinboard.startAnimation(rotate);
            }
        });*/
    }

    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            acelLast = acelVal;
            acelVal = (float) Math.sqrt((double) (x*x+y*y+z*z));
            float delta = acelVal-acelLast;
            shake = shake * 0.9f + delta;

            if(shake > 12){
                degree_old = degree % 360;
                degree = r.nextInt(3600) + 720;
                RotateAnimation rotate = new RotateAnimation(degree_old, degree,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(3600);
                rotate.setFillAfter(true);
                rotate.setInterpolator(new DecelerateInterpolator());
                rotate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        textView.setText("");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        textView.setText(currentNumber(360 - (degree % 360)));
                        Intent i = new Intent(getApplicationContext(), PopupCardActivity.class);
                        startActivity(i);


                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }

                });
                spinboard2.startAnimation(rotate);
                Toast toast = Toast.makeText(getApplicationContext(),"I am shaking!", Toast.LENGTH_LONG);
                toast.show();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    private String currentNumber(int degrees) {
        String text = "";
        if (degrees >= (FACTOR * 0) && degrees < (FACTOR * 2)) {
            text = "2";
        }
        if (degrees >= (FACTOR * 2) && degrees < (FACTOR * 4)) {
            text = "3";
        }
        if (degrees >= (FACTOR * 4) && degrees < (FACTOR * 6)) {
            text = "4";
        }
        if (degrees >= (FACTOR * 6) && degrees < (FACTOR * 8)) {
            text = "1";
        }
     /*   if (degrees >= (FACTOR * 1) && degrees < (FACTOR * 3)) {
            text = "32";
        }
        if (degrees >= (FACTOR * 3) && degrees < (FACTOR * 5)) {
            text = "15";
        }
        if (degrees >= (FACTOR * 5) && degrees < (FACTOR * 7)) {
            text = "19";
        }
        if (degrees >= (FACTOR * 7) && degrees < (FACTOR * 9)) {
            text = "4";
        }
        if (degrees >= (FACTOR * 9) && degrees < (FACTOR * 11)) {
            text = "21";
        }
        if (degrees >= (FACTOR * 11) && degrees < (FACTOR * 13)) {
            text = "2";
        }
        if (degrees >= (FACTOR * 13) && degrees < (FACTOR * 15)) {
            text = "25";
        }
        if (degrees >= (FACTOR * 15) && degrees < (FACTOR * 17)) {
            text = "17";
        }
        if (degrees >= (FACTOR * 17) && degrees < (FACTOR * 19)) {
            text = "34";
        }
        if (degrees >= (FACTOR * 19) && degrees < (FACTOR * 21)) {
            text = "6";
        }
        if (degrees >= (FACTOR * 21) && degrees < (FACTOR * 23)) {
            text = "27";
        }
        if (degrees >= (FACTOR * 23) && degrees < (FACTOR * 25)) {
            text = "13";
        }
        if (degrees >= (FACTOR * 25) && degrees < (FACTOR * 27)) {
            text = "36";
        }
        if (degrees >= (FACTOR * 27) && degrees < (FACTOR * 29)) {
            text = "11";
        }
        if (degrees >= (FACTOR * 29) && degrees < (FACTOR * 31)) {
            text = "30";
        }
        if (degrees >= (FACTOR * 31) && degrees < (FACTOR * 33)) {
            text = "8";
        }
        if (degrees >= (FACTOR * 33) && degrees < (FACTOR * 35)) {
            text = "23";
        }
        if (degrees >= (FACTOR * 35) && degrees < (FACTOR * 37)) {
            text = "10";
        }
        if (degrees >= (FACTOR * 37) && degrees < (FACTOR * 39)) {
            text = "5";
        }
        if (degrees >= (FACTOR * 39) && degrees < (FACTOR * 41)) {
            text = "24";
        }
        if (degrees >= (FACTOR * 41) && degrees < (FACTOR * 43)) {
            text = "16";
        }
        if (degrees >= (FACTOR * 43) && degrees < (FACTOR * 45)) {
            text = "33";
        }
        if (degrees >= (FACTOR * 45) && degrees < (FACTOR * 47)) {
            text = "1";
        }
        if (degrees >= (FACTOR * 47) && degrees < (FACTOR * 49)) {
            text = "20";
        }
        if (degrees >= (FACTOR * 49) && degrees < (FACTOR * 51)) {
            text = "14";
        }
        if (degrees >= (FACTOR * 51) && degrees < (FACTOR * 53)) {
            text = "31";
        }
        if (degrees >= (FACTOR * 53) && degrees < (FACTOR * 55)) {
            text = "9";
        }
        if (degrees >= (FACTOR * 55) && degrees < (FACTOR * 57)) {
            text = "22";
        }
        if (degrees >= (FACTOR * 57) && degrees < (FACTOR * 59)) {
            text = "18";
        }
        if (degrees >= (FACTOR * 59) && degrees < (FACTOR * 61)) {
            text = "29";
        }
        if (degrees >= (FACTOR * 61) && degrees < (FACTOR * 63)) {
            text = "7";
        }
        if (degrees >= (FACTOR * 63) && degrees < (FACTOR * 65)) {
            text = "28";
        }
        if (degrees >= (FACTOR * 65) && degrees < (FACTOR * 67)) {
            text = "12";
        }
        if (degrees >= (FACTOR * 67) && degrees < (FACTOR * 69)) {
            text = "35";
        }
        if (degrees >= (FACTOR * 69) && degrees < (FACTOR * 71)) {
            text = "3";
        }
        if (degrees >= (FACTOR * 71) && degrees < (FACTOR * 73)) {
            text = "26";
        }
        if ((degrees >= (FACTOR * 73) && degrees < 360) || (degrees >= 0 && degrees < (FACTOR * 1))) {
            text = "0";
        }*/



        mFirestore = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        String player_name = intent.getStringExtra("Player_name");

        Map<String, Object> scores = new HashMap<>();
        scores.put("name", player_name);
        scores.put("score", Integer.parseInt(text));
        mFirestore.collection("ranking list").add(scores).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(SpinboardActivity.this, "player added to firebase", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String error = e.getMessage();
                Toast.makeText(SpinboardActivity.this, "Error: "+error, Toast.LENGTH_SHORT).show();
            }
        });
        return text;
    }
}
