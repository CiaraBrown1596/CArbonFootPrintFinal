package com.example.carbonfootprinttrackerfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class UserActivity extends AppCompatActivity{
    private EditText userID, firstname, surname, numEmployees, numFlights;
    private Button btnSubmit;
    private String saveCurrentTime;
    private String saveCurrentDate;

    private ImageView logoUser;

    private String UserID;
    private String UserFirstName;
    private String UserSurname;
    private String TotalEmployees;
    private String TotalFLights;

    private DatabaseReference UserReff;
    private FirebaseAuth myFirebaseAuth;
    private FirebaseDatabase myFirebaseDatabase;
    private ProgressDialog loadingBar;
    private String userRandomKey;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        myFirebaseAuth = FirebaseAuth.getInstance();
        UserReff = FirebaseDatabase.getInstance().getReference("Users");

        userID = (EditText) findViewById(R.id.ID);
        firstname = (EditText) findViewById(R.id.firstname);
        surname = (EditText) findViewById(R.id.Surname);
        numEmployees = (EditText) findViewById(R.id.employees);
        numFlights = (EditText) findViewById(R.id.numFlights);

        logoUser = findViewById(R.id.app_logoUser);
        logoUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

        loadingBar = new ProgressDialog(this);

        btnSubmit = (Button) findViewById(R.id.btnSave);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ValidateUserData();
            }
        });
    }


    private void ValidateUserData()
    {
        UserID = userID.getText().toString().trim();
        UserFirstName = firstname.getText().toString().trim();
        UserSurname = surname.getText().toString().trim();
        TotalEmployees =numEmployees.getText().toString().trim();
        TotalFLights = numFlights.getText().toString().trim();


        if (TextUtils.isEmpty(UserID))
        {
            Toast.makeText(this, "Please write Flight ID ...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(UserFirstName))
        {
            Toast.makeText(this, "Please enter First Name...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(UserSurname))
        {
            Toast.makeText(this, "Please enter Surname...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(TotalEmployees))
        {
            Toast.makeText(this, "Please enter total Employees...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(TotalFLights))
        {
            Toast.makeText(this, "Please enter total flights...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StoreUserInformation();
        }
    }



    private void StoreUserInformation()
    {
        loadingBar.setTitle("Add New User");
        loadingBar.setMessage("Dear User, please wait while we are adding the new User.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();


        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        userRandomKey = saveCurrentDate + saveCurrentTime;

        SaveUserInfoToDatabase();
    }



    private void SaveUserInfoToDatabase()
    {
        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("UserID",UserID);
        userMap.put("First Name", UserFirstName);
        userMap.put("date", saveCurrentDate);
        userMap.put("time", saveCurrentTime);
        userMap.put("Second Name", UserSurname);
        userMap.put("Employees", TotalEmployees);
        userMap.put("Flights", TotalFLights);

        UserReff.child(userRandomKey).updateChildren(userMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(UserActivity.this, HomeActivity.class);
                            startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(UserActivity.this, "User is added successfully..", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(UserActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}







