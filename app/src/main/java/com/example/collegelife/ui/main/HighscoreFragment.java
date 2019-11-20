package com.example.collegelife.ui.main;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.collegelife.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


/**
 * A simple {@link Fragment} subclass.
 */
public class HighscoreFragment extends Fragment  {

    private static final String TAG = "Highscore_Fragment";

    public HighscoreFragment() {
        // Required empty public constructor
    }

    private TableLayout table;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_high_score, container, false);

        TextView first = v.findViewById(R.id.place1);
        TextView second = v.findViewById(R.id.place2);
        TextView third = v.findViewById(R.id.place3);

        DecimalFormat df = new DecimalFormat("#.##");

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // The default cache size threshold is 100 MB. Configure "setCacheSizeBytes"
        // for a different threshold (minimum 1 MB) or set to "CACHE_SIZE_UNLIMITED"
        // to disable clean-up.
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
                .build();
        db.setFirestoreSettings(settings);

        db.collection("ranking list")
                .orderBy("debt", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int count = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                if (document.get("name") != null) {
                                    String name = document.get("name").toString();
                                    String debt = document.get("debt").toString();
                                    String gpa = document.get("gpa").toString();
                                    //TextView first = v.findViewById(R.id.place1);
                                    if (count == 0) {
                                        first.setText( getString(R.string.Name) + name + R.string.Debt_d + debt  + " | "  + "GPA: " + df.format(Double.parseDouble(gpa)));
                                    }else if(count == 1) {
                                        second.setText(getString(R.string.Name) + R.string.Debt_d + debt  + " | "  + "GPA: " + df.format(Double.parseDouble(gpa)));
                                    }else if(count == 2) {
                                        third.setText(getString(R.string.Name) + name + R.string.Debt_d + debt  + " | "  + "GPA: " + df.format(Double.parseDouble(gpa)));
                                    }
                                    count++;
                                }
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


        Log.d(TAG, "onCreate is called");

        return v;
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart is called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume is called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause is called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop is called");
    }

}