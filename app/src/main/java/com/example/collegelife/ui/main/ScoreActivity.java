package com.example.collegelife.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.collegelife.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class ScoreActivity extends AppCompatActivity {

    private static final String TAG = "Score_Activity";
    String[] names;
    String[] fScores;
    String[] fGpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        DecimalFormat df = new DecimalFormat("#.##");
        names = getIntent().getStringArrayExtra("pname");
        fScores = getIntent().getStringArrayExtra("debt");
        fGpa = getIntent().getStringArrayExtra("fgpa");
        for (int i=0; i < names.length; i++) {

            String name = names[i];
            String debt = fScores[i];
            assert fGpa != null;
            String gpa = fGpa[i];
            String data = ("Name: " + name + " | GPA: " + df.format(Double.parseDouble(gpa)) + " | Debt: " + debt);

            TableLayout s = this.findViewById(R.id.Score);
            TableRow row = new TableRow(this);
            TextView show = new TextView(this);
            show.setText(data);
            row.addView(show);
            s.addView(row);


            Map<String, Object> scores = new HashMap<>();
            scores.put("name", names[i]);
            scores.put("debt", Integer.parseInt(fScores[i]));
            scores.put("gpa", Double.parseDouble(fGpa[i]));
            firestore.collection("ranking list").document(names[i])
                    .set(scores)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully written!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error writing document", e);
                        }
                    });
        }

    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        startActivity(new Intent(this, MenuActivity.class));
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

}
