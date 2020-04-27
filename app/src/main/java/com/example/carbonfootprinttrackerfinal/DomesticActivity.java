package com.example.carbonfootprinttrackerfinal;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DomesticActivity extends AppCompatActivity {

    EditText totalDomestic, light, heat, water, waste;
    Button submit;
    private DatabaseReference reff;
    private FirebaseAuth myFirebaseAuth;
    private FirebaseDatabase myFirebaseDatabase;
    Domestic domestic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domestic);

        myFirebaseAuth = FirebaseAuth.getInstance();
        reff = FirebaseDatabase.getInstance().getReference("Domestic");

        totalDomestic = (EditText)findViewById(R.id.Domestic);
        light = (EditText)findViewById(R.id.Light);
        heat = (EditText)findViewById(R.id.Heat);
        water =(EditText)findViewById(R.id.Water);
        waste = (EditText)findViewById(R.id.Waste);
        submit = (Button) findViewById(R.id.button2);
        domestic = new Domestic();
        reff = FirebaseDatabase.getInstance().getReference().child("Domestic");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDomesticInformation();
            }
        });
    }

    private void saveDomesticInformation(){
        int TotalDomestic = Integer.parseInt((totalDomestic.getText().toString().trim()));
        int Light = Integer.parseInt(light.getText().toString().trim());
        int Heat = Integer.parseInt(heat.getText().toString().trim());
        int Water = Integer.parseInt(water.getText().toString().trim());
        int Waste = Integer.parseInt(waste.getText().toString().trim());

        String id = reff.push().getKey();

        Domestic domestic = new Domestic(id, TotalDomestic,Light,Heat,Water,Waste);

        reff.child(id).setValue(domestic);

        Toast.makeText(this, "Domestic Information saved", Toast.LENGTH_LONG).show();

    }
}

