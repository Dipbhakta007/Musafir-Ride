package com.example.user.musafirride;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Passengers extends AppCompatActivity {
    private EditText user;
    private Button search,refresh;
    private TextView mob,amount;
    private String usermob,rideID;
    private boolean dekhi=true;
    private  DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passengers);

        user=(EditText)findViewById(R.id.user);
        search=(Button)findViewById(R.id.searchpassenger);
        refresh=(Button)findViewById(R.id.refreshButton);
        mob=(TextView)findViewById(R.id.mob);
        amount=(TextView)findViewById(R.id.amount);

        Intent intent=getIntent();
        rideID=intent.getStringExtra("rideID");



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usermob="+88"+user.getText().toString();






                    FirebaseDatabase.getInstance().getReference().child("Riders").child(rideID).child(usermob).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {



                          jatri j=dataSnapshot.getValue(jatri.class);
                            if(j==null) mob.setText("Have no tickets");
                            else {
                                mob.setText(j.getPhoneno());
                                amount.setText("Number of tickets: " + String.valueOf(j.getNumberofTickets()));
                            }


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });



            }
        });


        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference=FirebaseDatabase.getInstance().getReference().child("Riders").child(rideID);
                databaseReference.removeValue();
            }
        });



    }
}
