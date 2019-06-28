package com.example.mathlearn.app_view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathlearn.R;
import com.example.mathlearn.app_view.sound.PlaySoundKt;
import com.example.mathlearn.controller.AnswerCheckKt;
import com.example.mathlearn.controller.GameModeData;
import com.example.mathlearn.controller.TotalOperation;

import java.util.Random;


public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TotalOperation totalOperation;
    private EditText[] number_editTexts;

    private TextView operator_TextView;
    private Button okButton;
    private static final Random RND = new Random();
    private int rndIndex;
    private int calcNumLimit;
    private int goodAnswerCounter;
    private GameModeData gameModeData;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        gameModeData = (GameModeData) getIntent().getSerializableExtra(String.valueOf(R.string.intentName));

        calcNumLimit = gameModeData.getLimit();


        goodAnswerCounter = 0;
        createTotalOperation();

        initViewElements();
    }

    private void createTotalOperation() {
        totalOperation = new TotalOperation(calcNumLimit, gameModeData.getFullMode());
    }

    private void initViewElements() {

        number_editTexts = new EditText[]{
                findViewById(R.id.a_number_et),//i=0 a szam
                findViewById(R.id.b_number_et),//i=1 b szam
                findViewById(R.id.result_et)//i=2 eredmeny szam
        };

        operator_TextView = findViewById(R.id.operator_textView);

        okButton = findViewById(R.id.ok_button);
        okButton.setOnClickListener(this);


        calculationViewElementsSet();


    }

    private void calculationViewElementsSet() {
        for (EditText editText : number_editTexts) {
            editText.setEnabled(false);
            editText.setLongClickable(false);
        }
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);


        number_editTexts[0].setText(totalOperation.getA().toString());
        number_editTexts[1].setText(totalOperation.getB().toString());
        number_editTexts[2].setText(totalOperation.getOpResult().toString());
        rndIndex = RND.nextInt(number_editTexts.length);
        number_editTexts[rndIndex].setText("");
        number_editTexts[rndIndex].setEnabled(true);

        number_editTexts[rndIndex].setFocusable(true);
        number_editTexts[rndIndex].setFocusableInTouchMode(true);
        number_editTexts[rndIndex].requestFocus();



        operator_TextView.setText(totalOperation.getStrategy().getSign());
    }


    @Override
    public void onClick(View v) {

        if (number_editTexts[rndIndex].getText().length()<1)return;


            if (AnswerCheckKt.chkAnswer(number_editTexts, totalOperation)) {
                goodAnswerCounter++;
                if (goodAnswerCounter % 5 == 0) {
                    calcNumLimit++;
                }
                createTotalOperation();

                calculationViewElementsSet();


            } else {

               PlaySoundKt.playDefaultNotiSound(this);
                AlertDialog badAnswerDialog = ErrorAnswerAl_DialogKt.showBadAnswerDialog(totalOperation, GameActivity.this);
                badAnswerDialog
                        .setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialogInterface) {



                                createTotalOperation();

                                calculationViewElementsSet();
                            }

                        });


            }

    }

    @Override
    protected void onPause() {
        super.onPause();
        imm.toggleSoftInput(InputMethodManager.RESULT_HIDDEN, 0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        imm.toggleSoftInput(InputMethodManager.RESULT_HIDDEN, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imm.toggleSoftInput(InputMethodManager.RESULT_HIDDEN, 0);
    }


}