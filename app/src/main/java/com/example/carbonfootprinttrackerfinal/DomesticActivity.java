package com.example.carbonfootprinttrackerfinal;



import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class DomesticActivity extends AppCompatActivity {

    EditText totalDomestic, light, heat, water, waste;
    Button submit;

    private String saveCurrentTime;
    private String saveCurrentDate;

    private String Light;
    private String Heat;
    private String Water;
    private String Waste;
    private String TotalDomestic;

    private DatabaseReference DomesticReff;
    private FirebaseAuth myFirebaseAuth;
    private FirebaseDatabase myFirebaseDatabase;
    private ProgressDialog loadingBar;
    private String DomesticRandomKey;
    Domestic domestic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domestic);

        myFirebaseAuth = FirebaseAuth.getInstance();
        DomesticReff = FirebaseDatabase.getInstance().getReference("Domestic");

        totalDomestic = (EditText)findViewById(R.id.Domestic);
        light = (EditText)findViewById(R.id.Light);
        heat = (EditText)findViewById(R.id.Heat);
        water =(EditText)findViewById(R.id.Water);
        waste = (EditText)findViewById(R.id.Waste);
        submit = (Button) findViewById(R.id.button2);

        loadingBar = new ProgressDialog(this);

        domestic = new Domestic();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateDomesticData();
            }
        });
    }


    private void ValidateDomesticData()
    {
        Heat = heat.getText().toString().trim();
        Light = light.getText().toString().trim();
        Water = water.getText().toString().trim();
        Waste = waste.getText().toString().trim();
        TotalDomestic = totalDomestic.getText().toString().trim();


        if (TextUtils.isEmpty(Heat))
        {
            Toast.makeText(this, "Please write heat ...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Light))
        {
            Toast.makeText(this, "Please enter light...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Water))
        {
            Toast.makeText(this, "Please enter water...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Waste))
        {
            Toast.makeText(this, "Please enter Waste...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StoreDomesticInformation();
        }
    }



    private void StoreDomesticInformation()
    {
        loadingBar.setTitle("Add New Domestic data");
        loadingBar.setMessage("Dear User, please wait while we are adding the domestic data.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();


        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        DomesticRandomKey = saveCurrentDate + saveCurrentTime;

        SaveDomesticInfoToDatabase();
    }



    private void SaveDomesticInfoToDatabase()
    {
        HashMap<String, Object> domMap = new HashMap<>();
        domMap.put("Water",Water);
        domMap.put("Waste", Waste);
        domMap.put("date", saveCurrentDate);
        domMap.put("time", saveCurrentTime);
        domMap.put("Heat", Heat);
        domMap.put("Light", Light);
        domMap.put("Total Domestic", TotalDomestic);

        DomesticReff.child(DomesticRandomKey).updateChildren(domMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(DomesticActivity.this, HomeActivity.class);
                            startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(DomesticActivity.this, "Domestic Data is added successfully..", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(DomesticActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

