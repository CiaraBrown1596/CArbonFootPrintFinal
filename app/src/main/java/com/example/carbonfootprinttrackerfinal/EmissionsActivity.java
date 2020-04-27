package com.example.carbonfootprinttrackerfinal;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EmissionsActivity extends AppCompatActivity {

    EditText TotalEmissions, fuel, flights, vehicles, domestic;
    Button Submit;
    private DatabaseReference reff;
    private FirebaseAuth myFirebaseAuth;
    private FirebaseDatabase myFirebaseDatabase;
    Emissions emissions;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emissions);

        myFirebaseAuth = FirebaseAuth.getInstance();
        reff = FirebaseDatabase.getInstance().getReference("Emissions");

        TotalEmissions = (EditText) findViewById(R.id.totalemissions);
        fuel = (EditText) findViewById(R.id.totalFuel);
        flights = (EditText) findViewById(R.id.totalFlights);
        vehicles = (EditText) findViewById(R.id.totalVehicles);
        domestic = (EditText) findViewById(R.id.totalDomestic);
        Submit = (Button) findViewById(R.id.button3);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveEmissionsInformation();
            }
        });
    }

    private void saveEmissionsInformation(){
        int emission = Integer.parseInt((TotalEmissions.getText().toString().trim()));
        int Fuel = Integer.parseInt(fuel.getText().toString().trim());
        int Flights = Integer.parseInt(flights.getText().toString().trim());
        int Vehicles = Integer.parseInt(vehicles.getText().toString().trim());
        int Domestic = Integer.parseInt(domestic.getText().toString().trim());

        String id = reff.push().getKey();

        Emissions emissions = new Emissions(id,emission,Fuel,Flights,Vehicles,Domestic);

        reff.child(id).setValue(emissions);

        Toast.makeText(this, "Emissions Information saved", Toast.LENGTH_LONG).show();
    }
}

