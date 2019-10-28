package com.example.user.musafirride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText rideIDEditbox;
    private Button seePassengers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rideIDEditbox=(EditText)findViewById(R.id.ridIDinput);
        seePassengers=(Button)findViewById(R.id.passengers);

       seePassengers.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(MainActivity.this, Passengers.class);
               intent.putExtra("rideID",rideIDEditbox.getText().toString());
               startActivity(intent);
           }
       });
    }
}
