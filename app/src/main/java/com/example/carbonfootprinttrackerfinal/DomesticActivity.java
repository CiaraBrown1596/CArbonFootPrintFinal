package com.example.carbonfootprinttrackerfinal;



import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DomesticActivity extends AppCompatActivity {

    EditText  light, heat;
    Button submit, btnCalculateLight;
    TextView totalLightReading,totalHeatReading;

    private String saveCurrentTime;
    private String saveCurrentDate;
    private String textResultLight;
    private String textResultHeat;

    private ImageView domLogo;
    private double resultLight;
    private double resultHeat;

    private String LightEmissions;
    private String Heat;
    private String TotalDomestic;


    private EditText CurrentReading,PreviousReading;

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

        totalLightReading = (TextView) findViewById(R.id.TotalLight);
        totalHeatReading = (TextView)findViewById(R.id.totalHeat);

        CurrentReading = (EditText)findViewById(R.id.Light1);
        PreviousReading = findViewById(R.id.Light2);
        heat = (EditText)findViewById(R.id.Heat);
        submit = (Button) findViewById(R.id.button2);
        btnCalculateLight = (Button) findViewById(R.id.btncalculateLight);

        domLogo = findViewById(R.id.app_logoDom);

        domLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DomesticActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

        loadingBar = new ProgressDialog(this);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateDomesticData();
            }
        });

        btnCalculateLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                domesticCalculator();
            }
        });
    }


    private void ValidateDomesticData()
    {
        LightEmissions = String.valueOf(Double.parseDouble(String.valueOf(textResultLight)));
        Heat = String.valueOf(Double.parseDouble(String.valueOf(textResultHeat)));


        if (TextUtils.isEmpty(Heat))
        {
            Toast.makeText(this, "Please write heat ...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(LightEmissions))
        {
            Toast.makeText(this, "Please enter light...", Toast.LENGTH_SHORT).show();
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

    public void domesticCalculator()
    {
        double lightCurrent = Double.parseDouble(CurrentReading.getText().toString());
        double lightPrevious = Double.parseDouble(PreviousReading.getText().toString());
        double heatReading = Double.parseDouble(heat.getText().toString());
        double resultLight, resultHeat, thisYearReading;

        thisYearReading = lightCurrent - lightPrevious;

        resultLight = thisYearReading * 0.453592;
        textResultLight = String.valueOf(resultLight);
        totalLightReading.setText(textResultLight);


        resultHeat = heatReading * 0.05443108;
        textResultHeat = String.valueOf(resultHeat);
        totalHeatReading.setText(textResultHeat);


        TotalDomestic = String.valueOf(resultHeat + resultLight);

    }



    private void SaveDomesticInfoToDatabase()
    {
        HashMap<String, Object> domMap = new HashMap<>();
        domMap.put("date", saveCurrentDate);
        domMap.put("time", saveCurrentTime);
        domMap.put("Heat", textResultHeat);
        domMap.put("Light", textResultLight);
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

