package com.example.carbonfootprinttrackerfinal;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FuelActivity extends AppCompatActivity {

        EditText reg, date, amount, type;
        Button Submit;
        private DatabaseReference reff;
        private FirebaseAuth myFirebaseAuth;
        private FirebaseDatabase myFirebaseDatabase;
        Fuel fuel;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_fuel);

                myFirebaseAuth = FirebaseAuth.getInstance();
                reff = FirebaseDatabase.getInstance().getReference("Fuel");

                reg = (EditText) findViewById(R.id.vehicleReg);
                amount = (EditText) findViewById(R.id.fuelAmount);
                type = (EditText) findViewById(R.id.fuelType);
                date = (EditText) findViewById(R.id.fuelDate);
                Submit = (Button) findViewById(R.id.button4);


                Submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                saveFuelInformation();
                        }
                });
        }

        public void saveFuelInformation() {
                Double Amount = Double.parseDouble(amount.getText().toString().trim());
                String Reg = reg.getText().toString().trim();
                String Type = type.getText().toString().trim();
                String Date = date.getText().toString().trim();

                String id = reff.push().getKey();

                Fuel fuel = new Fuel(id,Reg,Amount,Type,Date);

                reff.child(id).setValue(fuel);

                Toast.makeText(this, "Fuel Information saved", Toast.LENGTH_LONG).show();


        }

}

