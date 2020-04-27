package com.example.carbonfootprinttrackerfinal;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth myFirebaseAuth;
    private TextView UserEmail;
    private Button buttonLogout;
    private Button begin, Dom, Emm, emp, veh, fly, fuel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myFirebaseAuth = FirebaseAuth.getInstance();

        if (myFirebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = myFirebaseAuth.getCurrentUser();

        UserEmail = findViewById(R.id.textViewUserEmail);

        UserEmail.setText("Welcome " + user.getEmail());
        Spinner mySpinner = findViewById(R.id.spinner);

        List<String> options = new ArrayList<>();
        options.add(0, "Select an option to start..");
        options.add("User");
        options.add("Employee");
        options.add("Flight");
        options.add("Fuel");
        options.add("Domestic");
        options.add("Vehicles");
        options.add("QR Code Scanner");
        options.add("Emissions");
        options.add("Log Out");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, options);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mySpinner.setAdapter(dataAdapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Choose a Day")) {
                    //do nothing
                } else {
                    //on select a spinner
                    String item = parent.getItemAtPosition(position).toString();

                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                    if (parent.getItemAtPosition(position).equals("User")) {
                        Intent intent = new Intent(HomeActivity.this, UserActivity.class);
                        startActivity(intent);
                    }
                    if (parent.getItemAtPosition(position).equals("Flight")) {
                        Intent intent = new Intent(HomeActivity.this, FlightActivity.class);
                        startActivity(intent);
                    }
                    if (parent.getItemAtPosition(position).equals("Fuel")) {
                        Intent intent = new Intent(HomeActivity.this, FuelActivity.class);
                        startActivity(intent);
                    }
                    if (parent.getItemAtPosition(position).equals("Domestic")) {
                        Intent intent = new Intent(HomeActivity.this, DomesticActivity.class);
                        startActivity(intent);
                    }
                    if (parent.getItemAtPosition(position).equals("Vehicles")) {
                        Intent intent = new Intent(HomeActivity.this, VehicleActivity.class);
                        startActivity(intent);
                    }
                    if (parent.getItemAtPosition(position).equals("QR Code Scanner")) {
                        Intent intent = new Intent(HomeActivity.this, QrCodeActivity.class);
                        startActivity(intent);
                    }
                    if (parent.getItemAtPosition(position).equals("Log Out")) {
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    if (parent.getItemAtPosition(position).equals("Emissions")) {
                        Intent intent = new Intent(HomeActivity.this, EmissionsActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}


