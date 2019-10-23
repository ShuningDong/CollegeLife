package com.example.collegelife.ui.main;

import java.util.ArrayList;

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
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


/**
 * A simple {@link Fragment} subclass.
 */
public class HighscoreFragment extends Fragment implements View.OnClickListener {

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

        Button del = v.findViewById(R.id.del_button);
        del.setOnClickListener(this);
        Button update = v.findViewById(R.id.Update_button);
        update.setOnClickListener(this);

        //startActivity(new Intent(activity.getApplicationContext(), CharacterActivity.class));

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Task<QuerySnapshot> docRef = db.collection("ranking list")
                .orderBy("score", Query.Direction.DESCENDING)
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
                                    String score = document.get("score").toString();
                                    //TextView first = v.findViewById(R.id.place1);
                                    if (count == 0) {
                                        first.setText("Name: " + name + "  |  Score: " + score);
                                    }else if(count == 1) {
                                        second.setText("Name: " + name + "  |  Score: " + score);
                                    }else if(count == 2) {
                                        third.setText("Name: " + name + "  |  Score: " + score);
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


    public void onClick(View v) {

        Activity activity =  getActivity();
        if (activity != null) {
            switch (v.getId()) {

                case R.id.del_button:
                    //startActivity(new Intent(activity.getApplicationContext(), CharacterActivity.class));
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("ranking list").document("KR5zNqF190hlV6DXPf2L")
                            .delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "DocumentSnapshot successfully deleted!");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                 Log.w(TAG, "Error deleting document", e);
                                }
                            });
                    break;
                case R.id.Update_button:
                    //startActivity(new Intent(activity.getApplicationContext(), CharacterActivity.class));
                    FirebaseFirestore db_u = FirebaseFirestore.getInstance();
                    db_u.collection("ranking list")
                            .document("KR5zNqF190hlV6DXPf2L")
                            .update("score", 187)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "DocumentSnapshot successfully updated!");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error editing document", e);
                                }
                            });
                    break;
            }
        }
    }

}