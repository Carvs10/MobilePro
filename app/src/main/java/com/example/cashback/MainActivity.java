package com.example.cashback;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private SeekBar seekBarCB;
    private EditText editValue;
    private TextView textTotal;
    private TextView textPercentage;
    private TextView cashBack;
    private Switch swDiscount;
    private EditText editDiiscount;

    private double percentage = 0;
    public double discount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editValue = findViewById(R.id.editValue);
        cashBack = findViewById(R.id.cashBack);
        textPercentage = findViewById(R.id.porcentage);
        textTotal = findViewById(R.id.total);
        seekBarCB = findViewById(R.id.seekBarCB);
        swDiscount = findViewById(R.id.swDiscount);
        editDiiscount = findViewById(R.id.editDiiscount);

        editDiiscount.setEnabled(false);

        seekBarCB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                percentage = progress;
                textPercentage.setText(Math.round(percentage) +"%");
                calculate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        swDiscount.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked == false){
                    editDiiscount.setEnabled(false);
                }else{
                    editDiiscount.setEnabled(true);

                }
            }
        });

    }

    public void calculate(){

        String valueReturned = editValue.getText().toString();
        if (valueReturned == null || valueReturned.equals("")) {
            Toast.makeText(
                    getApplicationContext(),
                    "Digite algum valor!",
                    Toast.LENGTH_LONG
            ).show();
        }else{

            double typedValue = Double.parseDouble(valueReturned);

            double cashback = typedValue * (percentage/100);

            if(editDiiscount.isEnabled()){

                discount = Double.parseDouble(editDiiscount.getText().toString());
                double total = (typedValue - discount) - cashback;
                cashBack.setText("R$" + Math.round(cashback));
                textTotal.setText("R$" + total);


            }
            else{
                double total = typedValue - cashback;
                cashBack.setText("R$" + Math.round(cashback));
                textTotal.setText("R$" + total);

            }
        }

    }



}