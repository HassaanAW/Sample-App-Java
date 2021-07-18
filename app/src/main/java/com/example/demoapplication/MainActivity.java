package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button next;
    private EditText name, country, city, DOB;
    private String get_gender, get_date;
    private RadioButton option_male, option_female, option_others;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next = findViewById(R.id.addButton);
        name = findViewById(R.id.editTextTextPersonName);
        DOB = findViewById(R.id.editTextDate);
        country = findViewById(R.id.editTextTextPersonName4);
        city = findViewById(R.id.editTextTextPersonName5);

        option_male = (RadioButton) findViewById(R.id.male);
        option_female = (RadioButton) findViewById(R.id.female);
        option_others = (RadioButton) findViewById(R.id.others);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String get_name = name.getText().toString();
                String get_city = city.getText().toString();
                String get_country = country.getText().toString();
                // Log.d("MyApp",get_name + get_city + get_country);

                // Getting Date
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Make sure user insert date into edittext in this format.
                Date dateObject;
                try {
                    String dob_var = (DOB.getText().toString());
                    dateObject = formatter.parse(dob_var);
                    get_date = new SimpleDateFormat("dd/MM/yyyy").format(dateObject);
                    // Log.d("MyDate", get_date);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                    Log.i("ErrorInDate", e.toString());
                }

                // Getting Gender
                if(option_male.isChecked()){
                    get_gender= option_male.getText().toString();}
                else if (option_female.isChecked()){
                    get_gender = option_female.getText().toString();}
                else{
                    get_gender= option_others.getText().toString();}

                HashMap<String, Object> map = new HashMap<>();
                map.put("Name", get_name);
                map.put("Country", get_country);
                map.put("Gender", get_gender);
                map.put("City", get_city);
                map.put("DOB", get_date);

                if (TextUtils.isEmpty(get_name) || TextUtils.isEmpty(get_country) || TextUtils.isEmpty(get_city) || TextUtils.isEmpty(get_date) || TextUtils.isEmpty(get_gender) ){
                    Toast.makeText(MainActivity.this, "Please fill missing fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("Participants").document("123").set(map);
                    Toast.makeText(MainActivity.this, "Submitted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}