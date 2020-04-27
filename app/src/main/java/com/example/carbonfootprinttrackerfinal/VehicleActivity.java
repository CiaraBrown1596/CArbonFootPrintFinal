package com.example.carbonfootprinttrackerfinal;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VehicleActivity extends AppCompatActivity {

    TextView vehicleEmissions;
    EditText type, reg, litres;
    Button Enter, calculate;
    CheckBox fuelType;
    private DatabaseReference reff;
    private FirebaseAuth myFirebaseAuth;
    private FirebaseDatabase myFirebaseDatabase;
    Vehicle vehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        myFirebaseAuth = FirebaseAuth.getInstance();
        reff = FirebaseDatabase.getInstance().getReference("Vehicles");

        reg = findViewById(R.id.registration);
        litres = findViewById(R.id.mileage);
        type = findViewById(R.id.vehType);
        fuelType = findViewById(R.id.checkBox);
        Enter = findViewById(R.id.button5);
        calculate = findViewById(R.id.button6);

        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveVehicleInformation();
            }
        });


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateEmissions();
            }
        });




    }
    public void saveVehicleInformation() {

        int Litres = Integer.parseInt((litres.getText().toString().trim()));
        String RegNumber = reg.getText().toString().trim();
        String Type = type.getText().toString().trim();
        String VehicleEmissions = vehicleEmissions.getText().toString().trim();


        String id = reff.push().getKey();

        Vehicle vehicle = new Vehicle(id,RegNumber,Litres,Type,VehicleEmissions);

        reff.child(id).setValue(vehicle);

        Toast.makeText(this, "Vehicles Information saved", Toast.LENGTH_LONG).show();

    }
    public void calculateEmissions(){
        String s1 = litres.getText().toString();
        double result;

        if(fuelType.isChecked()){
            result = Integer.parseInt(s1) * 2.31 ;
        }
        else {
            result = Integer.parseInt(s1) * 2.68;
        }
        TextView vehicleEmissions = findViewById(R.id.vehicleEmissions);
        vehicleEmissions.setText(String.valueOf(result));
    }




}


