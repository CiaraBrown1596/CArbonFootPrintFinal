package com.example.carbonfootprinttrackerfinal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserActivity extends AppCompatActivity{
    private EditText userID, firstname, surname, numEmployees, numFlights, emissions, vehicles, domestic;
    private Button btnSubmit;

    private DatabaseReference reff;
    private FirebaseAuth myFirebaseAuth;
    private FirebaseDatabase myFirebaseDatabase;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        myFirebaseAuth = FirebaseAuth.getInstance();
        reff = FirebaseDatabase.getInstance().getReference("Users");

        userID = (EditText) findViewById(R.id.ID);
        firstname = (EditText) findViewById(R.id.firstname);
        surname = (EditText) findViewById(R.id.Surname);
        numEmployees = (EditText) findViewById(R.id.employees);
        numFlights = (EditText) findViewById(R.id.numFlights);
        emissions = (EditText) findViewById(R.id.TotalEmissions);
        vehicles = (EditText) findViewById(R.id.TotalVehicles);
        domestic = (EditText) findViewById(R.id.TotalDomestic);

        btnSubmit = (Button) findViewById(R.id.btnSave);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation();
            }
        });


    }


    private void saveUserInformation(){
        int userId = Integer.parseInt(userID.getText().toString().trim());
        String name = firstname.getText().toString().trim();
        String Surname = surname.getText().toString().trim();
        int Employees = Integer.parseInt(numEmployees.getText().toString().trim());
        int Flights = Integer.parseInt(numFlights.getText().toString().trim());
        Double Emissions = Double.parseDouble(emissions.getText().toString().trim());
        int Vehicles = Integer.parseInt(vehicles.getText().toString().trim());
        Double Domestic = Double.parseDouble(domestic.getText().toString().trim());

        String id = reff.push().getKey();

        User user = new User(id,userId,name,Surname,Employees,Flights,Emissions,Vehicles,Domestic);

        reff.child(id).setValue(user);

        Toast.makeText(this, "User Information saved", Toast.LENGTH_LONG).show();


    }
}







