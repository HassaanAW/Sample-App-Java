package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.net.NetworkInterface;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button next;
    private EditText name, country, city, DOB;
    private String get_gender, get_date, get_name, get_country, get_city, MacAdd;
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

        MacAdd = getMacAddr();
        Log.d("Mac", MacAdd);

        try {
            get_name = getIntent().getStringExtra("Name");
            get_country = getIntent().getStringExtra("Country");
            get_city = getIntent().getStringExtra("City");
            //get_gender = getIntent().getStringExtra("Gender");
            get_date = getIntent().getStringExtra("DOB");

            name.setText(get_name);
            DOB.setText(get_date);
            country.setText("Pakistan");
            city.setText(get_city);
        }
        catch (Exception e) {
            System.out.println("Oops!");
        }
    }

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
        else if (option_others.isChecked()){
            get_gender= option_others.getText().toString();}

        if (TextUtils.isEmpty(get_name) || TextUtils.isEmpty(get_country) || TextUtils.isEmpty(get_city) || TextUtils.isEmpty(get_date) || TextUtils.isEmpty(get_gender) ){
            Toast.makeText(MainActivity.this, "Please fill missing fields", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(MainActivity.this, "Survey One complete!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, TechSurvey.class);
            intent.putExtra("MAC", MacAdd);
            intent.putExtra("Name", get_name);
            intent.putExtra("Country", get_country);
            intent.putExtra("Gender", get_gender);
            intent.putExtra("City", get_city);
            intent.putExtra("DOB", get_date);
            startActivity(intent);
        }
    }

    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:",b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }
}