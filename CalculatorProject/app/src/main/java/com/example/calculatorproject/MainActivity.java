package com.example.calculatorproject;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv,solutionTv;
    MaterialButton buttonC;
    MaterialButton buttonBack;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEquals,buttonModulo;
    MaterialButton btn_0,btn_1, btn_2, btn_3, btn_4, btn_5,btn_6, btn_7, btn_8, btn_9;
    MaterialButton buttonAC,buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);
        assignId(buttonBack,R.id.btn_back);
        assignId(buttonC,R.id.button_c);
        assignId(buttonDivide,R.id.button_divide);
        assignId(buttonMultiply,R.id.button_multiply);
        assignId(buttonModulo,R.id.btn_modulo);
        assignId(buttonPlus,R.id.button_plus);
        assignId(buttonMinus,R.id.button_minus);
        assignId(buttonEquals,R.id.button_equals);
        assignId(btn_0,R.id.btn_0);
        assignId(btn_1,R.id.btn_1);
        assignId(btn_2,R.id.btn_2);
        assignId(btn_3,R.id.btn_3);
        assignId(btn_4,R.id.btn_4);
        assignId(btn_5,R.id.btn_5);
        assignId(btn_6,R.id.btn_6);
        assignId(btn_7,R.id.btn_7);
        assignId(btn_8,R.id.btn_8);
        assignId(btn_9,R.id.btn_9);
        assignId(buttonAC,R.id.button_ac);
        assignId(buttonDot,R.id.button_dot);
    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        solutionTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }

    }

    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

}