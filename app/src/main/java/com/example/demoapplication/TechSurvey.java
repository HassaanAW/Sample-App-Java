package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class TechSurvey extends AppCompatActivity {

    private Spinner dropdown;
    private String get_background, get_experience, get_language, get_education, get_name, get_country, get_gender, get_city, get_date, get_MAC;
    private RadioButton background_yes, background_no, experience_yes, experience_no, language_yes, language_no, language_somewhat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_survey);

        dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"School", "College", "Bachelors", "Masters", "PhD"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        background_yes = (RadioButton) findViewById(R.id.radio1);
        background_no= (RadioButton) findViewById(R.id.radio2);
        experience_yes = (RadioButton) findViewById(R.id.radio3);
        experience_no = (RadioButton) findViewById(R.id.radio4);
        language_yes = (RadioButton) findViewById(R.id.radio5);
        language_no = (RadioButton) findViewById(R.id.radio6);
        language_somewhat = (RadioButton) findViewById(R.id.radio7);

        get_name = getIntent().getStringExtra("Name");
        get_country = getIntent().getStringExtra("Country");
        get_city = getIntent().getStringExtra("City");
        get_gender = getIntent().getStringExtra("Gender");
        get_date = getIntent().getStringExtra("DOB");
        get_MAC = getIntent().getStringExtra("MAC");
    }

    public void onClick(View v) {

        get_education = dropdown.getSelectedItem().toString();
        // Getting background
        if(background_yes.isChecked()){
            get_background= background_yes.getText().toString();}
        else if (background_no.isChecked()){
            get_background = background_no.getText().toString();}

        // Getting experience
        if(experience_yes.isChecked()){
            get_experience= experience_yes.getText().toString();}
        else if (experience_no.isChecked()){
            get_experience = experience_no.getText().toString();}

        // Getting language
        if(language_yes.isChecked()){
            get_language= language_yes.getText().toString();}
        else if (language_no.isChecked()){
            get_language = language_no.getText().toString();}
        else{
            get_language = language_somewhat.getText().toString();
        }


        HashMap<String, Object> map = new HashMap<>();
        map.put("Name", get_name);
        map.put("Country", get_country);
        map.put("Gender", get_gender);
        map.put("City", get_city);
        map.put("DOB", get_date);
        map.put("Education", get_education);
        map.put("Background", get_background);
        map.put("Experience", get_experience);
        map.put("Language", get_language);

        if (TextUtils.isEmpty(get_language) || TextUtils.isEmpty(get_experience) || TextUtils.isEmpty(get_background) || TextUtils.isEmpty((get_education)) ){
            Toast.makeText(TechSurvey.this, "Please fill missing fields", Toast.LENGTH_SHORT).show();
        }
        else{
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("Participants").document(get_MAC).set(map);
            Toast.makeText(TechSurvey.this, "Submitted", Toast.LENGTH_SHORT).show();
        }
    }
    public void Previous(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Name", get_name);
        intent.putExtra("Country", get_country);
        intent.putExtra("Gender", get_gender);
        intent.putExtra("City", get_city);
        intent.putExtra("DOB", get_date);
        startActivity(intent);
    }
}