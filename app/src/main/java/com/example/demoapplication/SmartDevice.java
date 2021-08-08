package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SmartDevice extends AppCompatActivity {

    private String get_age, get_region, get_ISP, get_gender, get_city, get_MAC;
    private String get_degree, get_industry, get_security, get_course, get_sec_course, get_language;
    private CheckBox Smartphone, Router, SmartTV, SecuritySystem, Health, Sensor, DoorLock, SmartLight, HomeAssist, Others;
    private RadioButton Functionality, Convenience, PriSec, Brand, Price;
    private String get_priority;
    private List<String> SmartDevices = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_device);

        Smartphone = (CheckBox) findViewById(R.id.checkBox4);
        Router = (CheckBox) findViewById(R.id.checkBox6);
        SmartTV = (CheckBox) findViewById(R.id.checkBox5);
        SecuritySystem = (CheckBox) findViewById(R.id.checkBox7);
        Health = (CheckBox) findViewById(R.id.checkBox8);
        Sensor = (CheckBox) findViewById(R.id.checkBox9);
        DoorLock = (CheckBox) findViewById(R.id.checkBox10);
        SmartLight = (CheckBox) findViewById(R.id.checkBox11);
        HomeAssist = (CheckBox) findViewById(R.id.checkBox12);
        Others = (CheckBox) findViewById(R.id.checkBox13);

        Functionality = (RadioButton) findViewById(R.id.radioButton3);
        Convenience = (RadioButton) findViewById(R.id.radioButton4);
        PriSec = (RadioButton) findViewById(R.id.radioButton5);
        Brand = (RadioButton) findViewById(R.id.radioButton6);
        Price = (RadioButton) findViewById(R.id.radioButton7);

        get_city = getIntent().getStringExtra("City");
        get_gender = getIntent().getStringExtra("Gender");
        get_age = getIntent().getStringExtra("Age");
        get_region = getIntent().getStringExtra("Region");
        get_ISP = getIntent().getStringExtra("ISP");
        get_MAC = getIntent().getStringExtra("MAC");

        get_degree = getIntent().getStringExtra("Degree");
        get_industry = getIntent().getStringExtra("Industry");
        get_security = getIntent().getStringExtra("Field");
        get_course = getIntent().getStringExtra("Courses");
        get_sec_course = getIntent().getStringExtra("SecurityCourse");
        get_language = getIntent().getStringExtra("Language");

    }

    public void onClick(View v){

        if(Smartphone.isChecked()){
          SmartDevices.add(Smartphone.getText().toString());
        }
        if(Router.isChecked()){
            SmartDevices.add(Router.getText().toString());
        }
        if(SmartTV.isChecked()){
            SmartDevices.add(SmartTV.getText().toString());
        }
        if(SecuritySystem.isChecked()){
            SmartDevices.add(SecuritySystem.getText().toString());
        }
        if(Health.isChecked()){
            SmartDevices.add(Health.getText().toString());
        }
        if(Sensor.isChecked()){
            SmartDevices.add(Sensor.getText().toString());
        }
        if(DoorLock.isChecked()){
            SmartDevices.add(DoorLock.getText().toString());
        }
        if(SmartLight.isChecked()){
            SmartDevices.add(SmartLight.getText().toString());
        }
        if(HomeAssist.isChecked()){
            SmartDevices.add(HomeAssist.getText().toString());
        }
        if(Others.isChecked()){
            SmartDevices.add(Others.getText().toString());
        }

        // Getting Priority
        if(Functionality.isChecked()){
            get_priority = Functionality.getText().toString();
        }
        else if(Convenience.isChecked()){
            get_priority = Convenience.getText().toString();
        }
        else if(PriSec.isChecked()){
            get_priority = PriSec.getText().toString();
        }
        else if(Brand.isChecked()){
            get_priority = Brand.getText().toString();
        }
        else if(Price.isChecked()){
            get_priority = Price.getText().toString();
        }

        if (TextUtils.isEmpty(get_priority) || (SmartDevices.size() == 0)){
            Toast.makeText(SmartDevice.this, "Please fill missing fields", Toast.LENGTH_SHORT).show();
        }
        else{

        HashMap<String, Object> map = new HashMap<>();
        map.put("Gender", get_gender);
        map.put("Age", get_age);
        map.put("City", get_city);
        map.put("Region", get_region);
        map.put("ISP", get_ISP);
        map.put("Degree", get_degree);
        map.put("Tech Industry", get_industry);
        map.put("Security Field", get_security);
        map.put("Taken Courses", get_course);
        map.put("Security Courses", get_sec_course);
        map.put("Language", get_language);
        map.put("Smart Devices", SmartDevices);
        map.put("Priority while Buying", get_priority);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Participants").document(get_MAC).set(map);
        Toast.makeText(SmartDevice.this, "Survey Three complete!", Toast.LENGTH_SHORT).show();

//            Intent intent = new Intent(this, SmartDevice.class);
//            intent.putExtra("MAC", get_MAC);
//            intent.putExtra("Age", get_age);
//            intent.putExtra("Gender", get_gender);
//            intent.putExtra("City", get_city);
//            intent.putExtra("Region", get_region);
//            intent.putExtra("ISP", get_ISP);
//
//            intent.putExtra("Degree", get_degree);
//            intent.putExtra("Industry", get_industry);
//            intent.putExtra("Field", get_security);
//            intent.putExtra("Courses", get_course);
//            intent.putExtra("SecurityCourse", get_sec_course);
//            intent.putExtra("Language", get_language);
//
//            intent.putStringArrayListExtra("SmartDevices", (ArrayList<String>) SmartDevices);
//            intent.putExtra("FirstPriority", get_priority);
//            startActivity(intent);
        }

    }

    public void Previous(View v){
        Intent intent = new Intent(this, TechSurvey.class);
        startActivity(intent);
    }
}