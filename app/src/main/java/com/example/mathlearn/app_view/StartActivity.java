package com.example.mathlearn.app_view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.example.mathlearn.R;
import com.example.mathlearn.controller.GameModeData;

public class StartActivity extends AppCompatActivity {
    private Spinner numLimitChoiceSpinner;
    private CheckBox modeCheckBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        modeCheckBox=findViewById(R.id.mode_checkBox);


        createSpinner();


    }

    private void createSpinner() {

        numLimitChoiceSpinner=findViewById(R.id.num_limit_spinner);
        ArrayAdapter<CharSequence> arrayAdapter=ArrayAdapter.createFromResource(this,R.array.number_value_limit,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numLimitChoiceSpinner.setAdapter(arrayAdapter);

    }

    public void go_click(View view) {
        boolean fullMode =modeCheckBox.isChecked();
        int numLimit=Integer.parseInt(numLimitChoiceSpinner.getSelectedItem().toString());

        Intent intent= new Intent();
        intent.setClass(this,GameActivity.class);
        intent.putExtra(String.valueOf(R.string.intentName),new GameModeData(numLimit,fullMode));

        startActivity(intent);


    }
}
