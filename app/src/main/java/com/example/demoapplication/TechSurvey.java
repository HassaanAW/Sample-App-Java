package com.example.demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class TechSurvey extends AppCompatActivity {

    private String get_age, get_region, get_ISP, get_gender, get_city, get_MAC;
    private String get_degree, get_industry, get_security, get_course, get_sec_course, get_language;
    private RadioButton degree_yes, degree_no, industry_yes, industry_no, security_yes,
            security_no, course_yes, course_no, sec_course_yes, sec_course_no, programming_yes, programming_no;
    private TextView security_ques, course_ques;
    private LinearLayout security_ques_layout, course_ques_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_survey);

        degree_yes = (RadioButton) findViewById(R.id.radio4);
        degree_no= (RadioButton) findViewById(R.id.radio5);
        industry_yes = (RadioButton) findViewById(R.id.radio6);
        industry_no = (RadioButton) findViewById(R.id.radio7);
        security_yes = (RadioButton) findViewById(R.id.radio8);
        security_no = (RadioButton) findViewById(R.id.radio9);
        course_yes = (RadioButton) findViewById(R.id.radio10);
        course_no = (RadioButton) findViewById(R.id.radio11);
        sec_course_yes = (RadioButton) findViewById(R.id.radio13);
        sec_course_no = (RadioButton) findViewById(R.id.radio14);
        programming_yes = (RadioButton) findViewById(R.id.radio15);
        programming_no = (RadioButton) findViewById(R.id.radio16);

        security_ques = (TextView) findViewById(R.id.textView5);
        security_ques_layout = (LinearLayout) findViewById(R.id.linearLayout8);

        course_ques = (TextView) findViewById(R.id.textView7);
        course_ques_layout = (LinearLayout) findViewById(R.id.linearLayout10);

        industry_yes.setOnClickListener(checkOption);
        industry_no.setOnClickListener(checkOption2);

        course_yes.setOnClickListener(checkOption3);
        course_no.setOnClickListener(checkOption4);

        get_city = getIntent().getStringExtra("City");
        get_gender = getIntent().getStringExtra("Gender");
        get_age = getIntent().getStringExtra("Age");
        get_region = getIntent().getStringExtra("Region");
        get_ISP = getIntent().getStringExtra("ISP");
        get_MAC = getIntent().getStringExtra("MAC");
    }

    View.OnClickListener checkOption = new View.OnClickListener(){
        public void onClick(View v) {
            security_ques.setVisibility(View.VISIBLE);
            security_ques_layout.setVisibility(View.VISIBLE);
        }
    };

    View.OnClickListener checkOption2 = new View.OnClickListener(){
        public void onClick(View v) {
            security_ques.setVisibility(View.GONE);
            security_ques_layout.setVisibility(View.GONE);
        }
    };

    View.OnClickListener checkOption3 = new View.OnClickListener(){
        public void onClick(View v) {
            course_ques.setVisibility(View.VISIBLE);
            course_ques_layout.setVisibility(View.VISIBLE);
        }
    };

    View.OnClickListener checkOption4 = new View.OnClickListener(){
        public void onClick(View v) {
            course_ques.setVisibility(View.GONE);
            course_ques_layout.setVisibility(View.GONE);
        }
    };

    public void onClick(View v) {

        // Getting degree
        if(degree_yes.isChecked()){
            get_degree = degree_yes.getText().toString();}
        else if (degree_no.isChecked()){
            get_degree = degree_no.getText().toString();}

        // Getting Industry Experience
        if(industry_yes.isChecked()){
            get_industry = industry_yes.getText().toString();}
        else if (industry_no.isChecked()){
            get_industry = industry_no.getText().toString();
            get_security = "No"; }

        // Getting Security Field
        if(security_yes.isChecked()){
            get_security = security_yes.getText().toString();}
        else{
            get_security = security_no.getText().toString();
        }

        // Getting course
        if(course_yes.isChecked()){
            get_course = course_yes.getText().toString();}
        else if (course_no.isChecked()){
            get_course = course_no.getText().toString();
            get_sec_course = "No"; }

        // Getting Security course
        if(sec_course_yes.isChecked()){
            get_sec_course = sec_course_yes.getText().toString();}
        else if (sec_course_no.isChecked()){
            get_sec_course = sec_course_no.getText().toString();}

        // Getting Language
        if(programming_yes.isChecked()){
            get_language = programming_yes.getText().toString();}
        else if (programming_no.isChecked()){
            get_language = programming_no.getText().toString();}

        if (TextUtils.isEmpty(get_language) || TextUtils.isEmpty(get_sec_course) || TextUtils.isEmpty(get_course) ||
                TextUtils.isEmpty(get_security)  || TextUtils.isEmpty(get_industry)  || TextUtils.isEmpty(get_degree) ){
            Toast.makeText(TechSurvey.this, "Please fill missing fields", Toast.LENGTH_SHORT).show();
        }
        else{
//            FirebaseFirestore db = FirebaseFirestore.getInstance();
//            db.collection("Participants").document(get_MAC).set(map);
//            Toast.makeText(TechSurvey.this, "Submitted", Toast.LENGTH_SHORT).show();
            Toast.makeText(TechSurvey.this, "Survey Two complete!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, SmartDevice.class);
            intent.putExtra("MAC", get_MAC);
            intent.putExtra("Age", get_age);
            intent.putExtra("Gender", get_gender);
            intent.putExtra("City", get_city);
            intent.putExtra("Region", get_region);
            intent.putExtra("ISP", get_ISP);

            intent.putExtra("Degree", get_degree);
            intent.putExtra("Industry", get_industry);
            intent.putExtra("Field", get_security);
            intent.putExtra("Courses", get_course);
            intent.putExtra("SecurityCourse", get_sec_course);
            intent.putExtra("Language", get_language);
            startActivity(intent);
        }
    }
    public void Previous(View v){
        Intent intent = new Intent(this, Demographic.class);
        intent.putExtra("Gender", get_gender);
        intent.putExtra("City", get_city);
        startActivity(intent);
    }
}