package com.example.carbonfootprinttrackerfinal;



import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class FuelActivity extends AppCompatActivity {

        EditText reg, date, amount, type,price;
        Button Submit;

        private String FuelType;
        private String FuelDate;
        private String FuelAmout;
        private String FuelPrice;
        private String VehicleUsed;

        private String saveCurrentTime;
        private String saveCurrentDate;
        private String VehicleReg;

        private Spinner vehicleSpinner;
        private DatabaseReference FuelReff, databaseReff;
        private FirebaseAuth myFirebaseAuth;
        private FirebaseDatabase myFirebaseDatabase;
        ValueEventListener listener;
        ArrayAdapter<String> adapter;
        ArrayList<String> spinnerDataList;
        private ProgressDialog loadingBar;
        private String userRandomKey;
        Fuel fuel;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_fuel);

                myFirebaseAuth = FirebaseAuth.getInstance();
                FuelReff = FirebaseDatabase.getInstance().getReference("Fuel");

                databaseReff = FirebaseDatabase.getInstance().getReference("Vehicles");

                vehicleSpinner = findViewById(R.id.veh_Spinner);

                amount = (EditText) findViewById(R.id.fuelAmount);
                type = (EditText) findViewById(R.id.fuelType);
                date = (EditText) findViewById(R.id.fuelDate);
                price = findViewById(R.id.fuelPrice);

                Submit = (Button) findViewById(R.id.button4);
                loadingBar = new ProgressDialog(this);


                Submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                ValidateFuelData();
                        }
                });

                spinnerDataList = new ArrayList<>();
                adapter = new ArrayAdapter<String>(FuelActivity.this,
                        android.R.layout.simple_spinner_dropdown_item,spinnerDataList);

                vehicleSpinner.setAdapter(adapter);
                retrieveData();

        }

        private void ValidateFuelData()
        {
                FuelType = type.getText().toString().trim();
                FuelAmout = amount.getText().toString().trim();
                FuelDate = date.getText().toString().trim();
                FuelPrice = price.getText().toString().trim();

                if (TextUtils.isEmpty(FuelType))
                {
                        Toast.makeText(this, "Please write the Fuel type ...", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(FuelAmout))
                {
                        Toast.makeText(this, "Please enter the fuel amount...", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(FuelDate))
                {
                        Toast.makeText(this, "Please enter date the fuel was purchased...", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(FuelPrice))
                {
                        Toast.makeText(this, "Please enter the price of the fuel...", Toast.LENGTH_SHORT).show();
                }
                else
                {
                        StoreFuelInformation();
                }
        }

        private void StoreFuelInformation()
        {
                loadingBar.setTitle("Add New Fuel");
                loadingBar.setMessage("Dear User, please wait while we are adding the new fuel info.");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();

                Calendar calendar = Calendar.getInstance();

                SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
                saveCurrentDate = currentDate.format(calendar.getTime());

                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                saveCurrentTime = currentTime.format(calendar.getTime());

                userRandomKey = saveCurrentDate + saveCurrentTime;

                SaveFuelInfoToDatabase();
        }



        private void SaveFuelInfoToDatabase()
        {
                HashMap<String, Object> fuelMap = new HashMap<>();
                fuelMap.put("Fuel Type",FuelType);
                fuelMap.put("Fuel Amount", FuelAmout);
                fuelMap.put("date", saveCurrentDate);
                fuelMap.put("time", saveCurrentTime);
                fuelMap.put("Date Purchased", FuelDate);
                fuelMap.put("Fuel Price", FuelPrice);

                FuelReff.child(userRandomKey).updateChildren(fuelMap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                        if (task.isSuccessful())
                                        {
                                                Intent intent = new Intent(FuelActivity.this, HomeActivity.class);
                                                startActivity(intent);

                                                loadingBar.dismiss();
                                                Toast.makeText(FuelActivity.this, "Fuel data is added successfully..", Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                                loadingBar.dismiss();
                                                String message = task.getException().toString();
                                                Toast.makeText(FuelActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                                        }
                                }
                        });
        }

        public void retrieveData()
        {
                listener = databaseReff.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                        {
                                for(DataSnapshot item:dataSnapshot.getChildren())
                                {
                                        VehicleReg = item.child("Vehicle Reg").getValue(String.class);
                                        spinnerDataList.add(VehicleReg);
                                }
                                adapter.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                });

        }

}

