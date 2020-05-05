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

public class ViewVehicles extends AppCompatActivity {


    ListView lv;
    FirebaseListAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vehicles);

        lv = findViewById(R.id.listView);

        Query query = FirebaseDatabase.getInstance().getReference().child("Vehicles");
        FirebaseListOptions<Vehicle> options  = new FirebaseListOptions.Builder<Vehicle>()
                .setLayout(R.layout.vehicle)
                .setQuery(query,Vehicle.class)
                .build();

        myAdapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {
                TextView VehicleReg = v.findViewById(R.id.vehicleReg);
                TextView Description = v.findViewById(R.id.Description);
                TextView LitersUsed  = v.findViewById(Integer.parseInt(String.valueOf(R.id.literUsed)));
                TextView VehicleEmissions = v.findViewById(R.id.Emissions);
                TextView Time  = v.findViewById(R.id.time);
                TextView Date = v.findViewById(R.id.vehDate);

                Vehicle vehicle = (Vehicle) model;
                VehicleReg.setText("Vehicle Registration : " + vehicle.getVehicleReg());
                Description.setText("Vehicle Description : "+vehicle.getDescription());
                LitersUsed.setText("Liters Used : "+vehicle.getLitersUsed());
                VehicleEmissions.setText("Vehicle Emissions : "+vehicle.getVehicleEmissions());
                Time.setText("Time of Entry : " + vehicle.getTime());
                Date.setText("Date of Entry :" + vehicle.getDate());
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
