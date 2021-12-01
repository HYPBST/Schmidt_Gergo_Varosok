package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    private EditText etNev;
    private EditText etOrszag;
    private EditText etLakossag;
    private Button btnVissza;
    private Button btnFelvesz;
    private DBHelper adatbazis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        init();
        btnVissza.setOnClickListener(view -> {
            Intent vissza=new Intent(InsertActivity.this,MainActivity.class);
            startActivity(vissza);
            finish();
        });
        btnFelvesz.setOnClickListener(view -> {
            String nev=etNev.getText().toString().trim();
            String orszag=etOrszag.getText().toString().trim();
            String lakossagString=etLakossag.getText().toString().trim();
            if (nev.isEmpty()||orszag.isEmpty()||lakossagString.isEmpty()){
                Toast.makeText(getApplicationContext(), "Minden mező kitöltése kötelező",
                        Toast.LENGTH_SHORT).show();
            }else{
                int lakossag=Integer.parseInt(lakossagString);
                if (lakossag>0){
                    adatbazis.rogzites(nev,orszag,lakossag);
                    Toast.makeText(getApplicationContext(), "Sikeres rögzítés",
                            Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "A lakosság nem lehet nulla",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void init(){
        etNev=findViewById(R.id.et_nev);
        etOrszag=findViewById(R.id.et_orszag);
        etLakossag=findViewById(R.id.et_lakossag);
        btnFelvesz=findViewById(R.id.btn_felvesz);
        btnVissza=findViewById(R.id.btn_vissza);
        adatbazis = new DBHelper(this);
    }
}