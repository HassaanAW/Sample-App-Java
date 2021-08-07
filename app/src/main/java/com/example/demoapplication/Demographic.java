package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.net.NetworkInterface;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Demographic extends AppCompatActivity {

    private Button next;
    private EditText region;
    private String get_gender, get_age, get_region, get_city, get_ISP, MacAdd;
    private RadioButton option_male, option_female, option_other;
    private RadioButton below_eighteen, eighteen, twentyfive, forty, above_sixty;
    private Spinner dropdown, dropdown2, dropdown3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demographic);

        next = findViewById(R.id.addButton);

        below_eighteen = (RadioButton) findViewById(R.id.belowEighteen);
        eighteen = (RadioButton) findViewById(R.id.eighteen);
        twentyfive = (RadioButton) findViewById(R.id.twentyfive);
        forty = (RadioButton) findViewById(R.id.forty);
        above_sixty = (RadioButton) findViewById(R.id.sixty);

        option_male = (RadioButton) findViewById(R.id.option_male);
        option_female = (RadioButton) findViewById(R.id.option_female);
        option_other = (RadioButton) findViewById(R.id.option_other);

        region = findViewById(R.id.editTextRegion);

        dropdown = findViewById(R.id.spinner);
        String[] items = new String[]{"Faisalabad","Gujranwala", "Islamabad", "Karachi", "Lahore", "Multan", "Peshawar", "Quetta", "Rawalpindi", "Sialkot", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        dropdown2 = findViewById(R.id.spinner2);
        String[] items2 = new String[]{"Allama Iqbal Town","Askari Phase 9", "Askari Phase 10", "Bahria Town","Cantt", "DHA Phase 1", "DHA Phase 2","DHA Phase 3"
                , "DHA Phase 4", "DHA Phase 5","DHA Phase 6", "EME Society", "Gulberg", "Izmir Society", "Johar Town", "Lake City", "Model Town", "Revenue Society",
                "Wapda Town", "Other"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);

        dropdown3 = findViewById(R.id.spinner3);
        String[] items3 = new String[]{ "Optix", "PTCL", "StormFiber","Wateen", "Wi-Tribe", "Worldcall", "Zong", "Other" };
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items3);
        dropdown3.setAdapter(adapter3);


        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                get_city = dropdown.getSelectedItem().toString();
                if(get_city == "Lahore"){
                    View TextRegion = findViewById(R.id.editTextRegion);
                    TextRegion.setVisibility(View.GONE);
                    dropdown2.setVisibility(View.VISIBLE);
                }
                else{
                    View TextRegion = findViewById(R.id.editTextRegion);
                    TextRegion.setVisibility(View.VISIBLE);
                    dropdown2.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Log.d("There", "Nothing to show");
            }
        });

        MacAdd = getMacAddr();

    }

    public void onClick(View v) {

        // Getting Age
        if(below_eighteen.isChecked()){
            get_age= below_eighteen.getText().toString();}
        if(eighteen.isChecked()){
            get_age= eighteen.getText().toString();}
        if(twentyfive.isChecked()){
            get_age= twentyfive.getText().toString();}
        if(forty.isChecked()){
            get_age= forty.getText().toString();}
        if(above_sixty.isChecked()){
            get_age= above_sixty.getText().toString();}

        // Getting Gender
        if(option_male.isChecked()){
            get_gender= option_male.getText().toString();}
        else if (option_female.isChecked()){
            get_gender = option_female.getText().toString();}
        else if (option_other.isChecked()){
            get_gender= option_other.getText().toString();}

        get_city = dropdown.getSelectedItem().toString();
        if(get_city == "Lahore"){
            get_region = dropdown2.getSelectedItem().toString();
        }
        else{
            get_region = region.getText().toString();
        }
        get_ISP = dropdown3.getSelectedItem().toString();


        if (TextUtils.isEmpty(get_region) || TextUtils.isEmpty(get_ISP) || TextUtils.isEmpty(get_city) || TextUtils.isEmpty(get_age) || TextUtils.isEmpty(get_gender) ){
            Toast.makeText(Demographic.this, "Please fill missing fields", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(Demographic.this, "Survey One complete!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, TechSurvey.class);
            intent.putExtra("MAC", MacAdd);
            intent.putExtra("Age", get_age);
            intent.putExtra("Gender", get_gender);
            intent.putExtra("City", get_city);
            intent.putExtra("Region", get_region);
            intent.putExtra("ISP", get_ISP);
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