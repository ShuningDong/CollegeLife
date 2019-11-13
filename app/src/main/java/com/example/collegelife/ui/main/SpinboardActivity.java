package com.example.collegelife.ui.main;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
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
                        int currNum = currentNumber(360 - (degree % 360));
                        String showNum = "" + currNum;
                        textView.setText(showNum);

                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int spinResult = currNum;
                        Intent sendRes = new Intent();
                        sendRes.putExtra("spin", spinResult);
                        setResult(0, sendRes);
                        SpinboardActivity.super.onBackPressed();
                        finish();

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
    private int currentNumber(int degrees) {
        int spin = 0;
        if (degrees >= (FACTOR * 0) && degrees < (FACTOR * 2)) {
            spin = 2;
        }
        if (degrees >= (FACTOR * 2) && degrees < (FACTOR * 4)) {
            spin = 3;
        }
        if (degrees >= (FACTOR * 4) && degrees < (FACTOR * 6)) {
            spin = 4;
        }
        if (degrees >= (FACTOR * 6) && degrees < (FACTOR * 8)) {
            spin = 1;
        }
        return spin;
    }
}
