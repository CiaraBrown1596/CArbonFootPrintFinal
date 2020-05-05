package com.example.carbonfootprinttrackerfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ViewFlights extends AppCompatActivity {


    ListView lv;
    FirebaseListAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flights);

        lv = findViewById(R.id.listViewFlight);

        Query query = FirebaseDatabase.getInstance().getReference().child("Flights");
        FirebaseListOptions<Flight> options  = new FirebaseListOptions.Builder<Flight>()
                .setLayout(R.layout.flight)
                .setQuery(query,Flight.class)
                .build();

        myAdapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {
                TextView FlightID = v.findViewById(R.id.ViewFligthID);
                TextView Aircraft = v.findViewById(R.id.ViewFlightAircraft);
                TextView Distance  = v.findViewById(R.id.ViewFlightDistance);
                TextView FlightEmisions = v.findViewById(R.id.ViewFLightEmissions);
                TextView FlightSeat = v.findViewById(R.id.ViewFlightSeat);
                TextView Time  = v.findViewById(R.id.ViewFlightTime);
                TextView Date = v.findViewById(R.id.ViewFlightTime);

                Flight flight = (Flight) model;
                FlightID.setText("Flight ID : " + flight.getFlightID());
                Aircraft.setText("Flight Aircraft : "+ flight.getAircraft());
                Distance.setText("Flight Distance : "+ flight.getDistance());
                FlightEmisions.setText("Flight Emissions : "+flight.getEmissionPerPassenger());
                FlightSeat.setText("Seat: " + flight.getSeat());
                Time.setText("Time of Entry : " + flight.getTime());
                Date.setText("Date of Entry :" + flight.getDate());
            }
        };
        lv.setAdapter(myAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        myAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myAdapter.stopListening();
    }
}
