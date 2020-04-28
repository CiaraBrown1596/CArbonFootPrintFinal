package com.example.carbonfootprinttrackerfinal;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.util.Calendar;
import java.util.HashMap;

public class VehicleActivity extends AppCompatActivity {

    TextView vehicleEmissions;
    EditText type, reg, litres;
    Button Enter, calculate;
    CheckBox fuelType;
    private String saveCurrentTime;
    private String saveCurrentDate;

    private String VehiclesReg;
    private String VehiclesLiters;
    private String VehiclesDescription;
    private String VehiclesEmissions;
    private String vehicleRandomKey;

    private ImageView logoVeh;

    private DatabaseReference VehicleReff;
    private FirebaseAuth myFirebaseAuth;
    private FirebaseDatabase myFirebaseDatabase;
    private ProgressDialog loadingBar;
    Vehicle vehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        myFirebaseAuth = FirebaseAuth.getInstance();
        VehicleReff = FirebaseDatabase.getInstance().getReference("Vehicles");

        reg = findViewById(R.id.registration);
        litres = findViewById(R.id.mileage);
        type = findViewById(R.id.vehType);
        fuelType = findViewById(R.id.checkBox);
        Enter = findViewById(R.id.button5);
        calculate = findViewById(R.id.button6);

        logoVeh = findViewById(R.id.app_logoVeh);
        logoVeh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VehicleActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

        loadingBar = new ProgressDialog(this);

        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateVehicleData();
            }
        });


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateEmissions();
            }
        });
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

    private void ValidateVehicleData()
    {
        VehiclesReg = reg.getText().toString().trim();
        VehiclesLiters = litres.getText().toString().trim();
        VehiclesDescription = type.getText().toString().trim();

        if (TextUtils.isEmpty(VehiclesReg))
        {
            Toast.makeText(this, "Please write Vehicle Registration ...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(VehiclesLiters))
        {
            Toast.makeText(this, "Please enter litres used...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(VehiclesDescription))
        {
            Toast.makeText(this, "Please enter Vehicles description...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StoreVehicleInformation();
        }
    }



    private void StoreVehicleInformation()
    {
        loadingBar.setTitle("Add New Vehicle");
        loadingBar.setMessage("Dear User, please wait while we are adding the new Vehicle.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();


        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        vehicleRandomKey = saveCurrentDate + saveCurrentTime;

        SaveFlightInfoToDatabase();
    }



    private void SaveFlightInfoToDatabase()
    {
        HashMap<String, Object> vehicleMap = new HashMap<>();
        vehicleMap.put("Vehicle Reg", VehiclesReg);
        vehicleMap.put("date", saveCurrentDate);
        vehicleMap.put("time", saveCurrentTime);
        vehicleMap.put("Liters Used", VehiclesLiters);
        vehicleMap.put("Description", VehiclesDescription);
        vehicleMap.put("Vehicle Emissions", VehiclesEmissions);

        VehicleReff.child(vehicleRandomKey).updateChildren(vehicleMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(VehicleActivity.this, HomeActivity.class);
                            startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(VehicleActivity.this, "Vehicle is added successfully..", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(VehicleActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }




}


