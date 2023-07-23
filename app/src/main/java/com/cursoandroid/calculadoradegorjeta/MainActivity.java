package com.cursoandroid.calculadoradegorjeta;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText textInputValor;
    private TextView textProgresso;
    private SeekBar seekBarProgresso;
    private TextView textViewGorjeta;
    private TextView textViewTotal;

    private String valor;
    private int percentGorjeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInputValor = findViewById(R.id.textInputValorText);
        textProgresso = findViewById(R.id.textProgresso);
        seekBarProgresso = findViewById(R.id.seekBarProgresso);
        textViewGorjeta = findViewById(R.id.textViewGorjeta);
        textViewTotal = findViewById(R.id.textViewTotal);

        seekBarProgresso.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                percentGorjeta = i*10;
                textProgresso.setText(percentGorjeta + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                getGorjeta();
            }
        });

        textInputValor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                getGorjeta();
            }
        });

    }

    public void getGorjeta(){
        valor = textInputValor.getText().toString();
        if(valor == null || valor.equals("")){
            Toast.makeText(MainActivity.this,
                    "Insira um valor válido", Toast.LENGTH_LONG).show();
        } else {
            double valorDigitado = Double.parseDouble(valor);
            textViewGorjeta.setText("R$ " + String.valueOf(valorDigitado*percentGorjeta/100));
            textViewTotal.setText("R$ " + String.valueOf(valorDigitado + (valorDigitado*percentGorjeta/100)));
        }

    }

}
