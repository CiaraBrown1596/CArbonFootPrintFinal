package com.example.carbonfootprinttrackerfinal;



import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class EmissionsActivity extends AppCompatActivity {

    private ImageView logoEM;
    private String TotalEmissions;
    private String TotalFlight;
    private String TotalVehicle;
    private String TotalDomestic;

    private ProgressDialog loadingBar;
    private String emissionsRandomKey;


    private String calculatedEmisisons;
    private TextView totalEmissions;
    private String saveCurrentTime;
    private String saveCurrentDate;
    EditText flights, vehicles, domestic;
    Button Submit, Show;
    private DatabaseReference EmissionsReff;

    private FirebaseAuth myFirebaseAuth;
    private FirebaseDatabase myFirebaseDatabase;
    Emissions emissions;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emissions);

        myFirebaseAuth = FirebaseAuth.getInstance();
        EmissionsReff = FirebaseDatabase.getInstance().getReference("Emissions");


        totalEmissions = findViewById(R.id.totalemissions);
        flights =  findViewById(R.id.totalFlights);
        vehicles =  findViewById(R.id.totalVehicles);
        domestic =  findViewById(R.id.totalDomestic);
        Submit = (Button) findViewById(R.id.button3);
        Show = (Button)findViewById(R.id.btnShow);

        loadingBar = new ProgressDialog(this);


        logoEM = findViewById(R.id.app_logoEmis);
        logoEM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmissionsActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

        Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TotalEmissions();
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateEmissionsData();
            }
        });
    }
    private void  TotalEmissions()
    {
        String totalDom = domestic.getText().toString();
        String totalVeh = vehicles.getText().toString();
        String totalFly = flights.getText().toString();

        calculatedEmisisons = totalDom + totalVeh + totalFly;
        TextView emissionsCalculated = findViewById(R.id.totalemissions);
        emissionsCalculated.setText(String.valueOf(calculatedEmisisons));
    }



    private void ValidateEmissionsData()
    {
        TotalDomestic = domestic.getText().toString().trim();
        TotalFlight = flights.getText().toString().trim();
        TotalVehicle = vehicles.getText().toString().trim();
        TotalEmissions = String.valueOf(Double.parseDouble(String.valueOf(calculatedEmisisons)));


        if (TextUtils.isEmpty(TotalDomestic))
        {
            Toast.makeText(this, "Please enter domestic ...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(TotalFlight))
        {
            Toast.makeText(this, "Please enter Flight...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(TotalVehicle))
        {
            Toast.makeText(this, "Please enter vehicles...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StoreEmissionsInformation();
        }
    }

    private void StoreEmissionsInformation()
    {
        loadingBar.setTitle("Add New Emissions");
        loadingBar.setMessage("Dear User, please wait while we are adding the new Emissions info.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        emissionsRandomKey = saveCurrentDate + saveCurrentTime;

        SaveEmissionsInfoToDatabase();
    }



    private void SaveEmissionsInfoToDatabase()
    {
        HashMap<String, Object> emMap = new HashMap<>();
        emMap.put("Total Emissions",TotalEmissions);
        emMap.put("Total Flight Emissions", TotalFlight);
        emMap.put("date", saveCurrentDate);
        emMap.put("time", saveCurrentTime);
        emMap.put("Total Vehicle Emissions", TotalVehicle);
        emMap.put("Total Domestic Emissions", TotalDomestic);

        EmissionsReff.child(emissionsRandomKey).updateChildren(emMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(EmissionsActivity.this, HomeActivity.class);
                            startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(EmissionsActivity.this, "Fuel data is added successfully..", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(EmissionsActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

