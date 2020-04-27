package com.example.carbonfootprinttrackerfinal;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import static android.R.layout.simple_list_item_1;


public class FlightActivity extends AppCompatActivity {

    private String FlightID;
    private String FlightAircraft;
    private String FlightSeat;
    private String FlightDistance;
    private String Emissions;
    private String saveCurrentTime;
    private String saveCurrentDate;
    private  Double distance;
    private  Double capacity;
    private  String seatClass;
    private  Double totalEmissions;
    private TextView result;
    EditText flightID;
    Button btnSubmit, btnCalculate;
    private DatabaseReference FlightReff;
    private FirebaseAuth myFirebaseAuth;
    private ProgressDialog loadingBar;
    private String flightRandomKey;
    private FirebaseDatabase myFirebaseDatabase;
    Flight flight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);

        myFirebaseAuth = FirebaseAuth.getInstance();
        FlightReff = FirebaseDatabase.getInstance().getReference("Flights");


        flightID = findViewById(R.id.FlightID);
        Spinner mySpinner1 =  findViewById(R.id.spinner1);

        Spinner mySpinner2 = findViewById(R.id.spinner2);

        Spinner mySpinner3 =  findViewById(R.id.spinner3);

        result = findViewById(R.id.PassengerC02);
        loadingBar = new ProgressDialog(this);


        btnSubmit = findViewById(R.id.btnsubmit);
        btnCalculate = findViewById(R.id.btncalculate);

        List<String> Type = new ArrayList<>();
        Type.add(0, "Choose a Flight Type");
        Type.add("Short Haul");
        Type.add("Long Haul");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this, simple_list_item_1, Type);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mySpinner1.setAdapter(dataAdapter);

        mySpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (adapterView.getItemAtPosition(position).equals("Choose a Flight Type")) {
                    //do nothing
                } else {
                    //on select a spinner
                    String item = adapterView.getItemAtPosition(position).toString();

                    Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                    if (adapterView.getItemAtPosition(position).equals("Short Haul")) {
                        distance = 1500.0;


                    }
                    if (adapterView.getItemAtPosition(position).equals("Long Haul")) {
                        distance = 2500.0;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// new Spinner Aircraft

        List<String> Aircraft = new ArrayList<>();
        Aircraft.add(0, "Choose an Aircraft Type");
        Aircraft.add("Boeing 747");
        Aircraft.add("Boeing 777");
        Aircraft.add("Airbus A330");
        Aircraft.add("Airbus A340");
        Aircraft.add("Airbus A310");
        Aircraft.add("Boeing 737");

        ArrayAdapter<String> dataAdapter2;
        dataAdapter2 = new ArrayAdapter<>(this, simple_list_item_1, Aircraft);

        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mySpinner2.setAdapter(dataAdapter2);

        mySpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Choose an Aircraft Type")) {
                    //do nothing
                } else {
                    //on select a spinner
                    String item = parent.getItemAtPosition(position).toString();

                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                    if (parent.getItemAtPosition(position).equals("Boeing 747")) {
                        capacity = 416.0;
                    }
                    if (parent.getItemAtPosition(position).equals("Boeing 777")) {
                        capacity = 314.0;
                    }
                    if (parent.getItemAtPosition(position).equals("Airbus A330")) {
                        capacity = 335.0;
                    }
                    if (parent.getItemAtPosition(position).equals("Airbus A340")) {
                        capacity = 320.0;
                    }
                    if (parent.getItemAtPosition(position).equals("Airbus A310")) {
                        capacity = 190.0;
                    }
                    if (parent.getItemAtPosition(position).equals("Boeing 737")) {
                        capacity = 140.0;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////////////////// new Spinner Class

        List<String> Class = new ArrayList<>();
        Class.add(0, "Choose a Seat Class");
        Class.add("Economy");
        Class.add("Business");

        ArrayAdapter<String> dataAdapter3;
        dataAdapter3 = new ArrayAdapter<>(this, simple_list_item_1, Class);

        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mySpinner3.setAdapter(dataAdapter3);

        mySpinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Choose a Seat Class")) {
                    //do nothing
                } else {
                    //on select a spinner
                    String item = parent.getItemAtPosition(position).toString();

                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                    if (parent.getItemAtPosition(position).equals("Economy")) {
                        seatClass = "Economy";
                    }
                    if (parent.getItemAtPosition(position).equals("Business")) {
                        seatClass = "Business";
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /// //////////////////////////////////////////////////////////////////////////////////////////////////// end of spinners


        btnSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view){
                ValidateFlightData();
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view){
                CalculateFlightEmissions();
            }
        });

    }


    public void CalculateFlightEmissions() {

        double fuelTotal = distance * 12;   /// 12 is liters used per Km. gives you total fuel used in liters
        double tonnes = fuelTotal/0.8;        /// divided by 0.8 to convert density liters to tonnes.
        totalEmissions = tonnes /(distance * capacity);
        result.setText(totalEmissions.toString());
    }


    private void ValidateFlightData()
    {
        FlightID = flightID.getText().toString().trim();
        FlightAircraft = String.valueOf(Double.parseDouble(capacity.toString().trim()));
        FlightDistance = String.valueOf(Double.parseDouble(distance.toString().trim()));
        FlightSeat = seatClass.trim();
        Emissions = String.valueOf(Double.parseDouble(totalEmissions.toString().trim()));


         if (TextUtils.isEmpty(FlightID))
        {
            Toast.makeText(this, "Please write Flight ID ...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(FlightAircraft))
        {
            Toast.makeText(this, "Please enter Flight Distance...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(FlightDistance))
         {
             Toast.makeText(this, "Please enter Flight Aircraft...", Toast.LENGTH_SHORT).show();
         }
         else if (TextUtils.isEmpty(FlightSeat))
         {
             Toast.makeText(this, "Please enter Flight Seat...", Toast.LENGTH_SHORT).show();
         }

        else
        {
            StoreFlightInformation();
        }
    }



    private void StoreFlightInformation()
    {
        loadingBar.setTitle("Add New Flight");
        loadingBar.setMessage("Dear User, please wait while we are adding the new flight.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();


        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        flightRandomKey = saveCurrentDate + saveCurrentTime;

        SaveFlightInfoToDatabase();
    }



    private void SaveFlightInfoToDatabase()
    {
        HashMap<String, Object> flightMap = new HashMap<>();
        flightMap.put("FlightID", FlightID);
        flightMap.put("date", saveCurrentDate);
        flightMap.put("time", saveCurrentTime);
        flightMap.put("Aircraft", FlightAircraft);
        flightMap.put("Distance", FlightDistance);
        flightMap.put("Seat", FlightSeat);
        flightMap.put("Flight Emissions",totalEmissions);

        FlightReff.child(flightRandomKey).updateChildren(flightMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(FlightActivity.this, HomeActivity.class);
                            startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(FlightActivity.this, "Flight is added successfully..", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(FlightActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}



