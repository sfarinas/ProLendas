package com.example.prolendas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();

    private Button conti1;
    private Button pula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //referencia.child( "clubes" ).setValue("SIMONE");

        // temporizador

        //new Handler().postDelayed(new Runnable() {
          //  @Override
            //public void run() {

                //abrirAutenticacao();

            //}
        //},90000);

        conti1 = findViewById(R.id.conti1);

        pula = findViewById(R.id.pula);

        conti1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), SegundaActivity.class);
                startActivity(intent);
            }
        });

        pula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        Toast.makeText(getApplicationContext(), "onCreat MAIN", Toast.LENGTH_LONG).show();
    }

    // apos o temporizador chama a autenticacao
    //private void abrirAutenticacao(){
      //  Intent i = new Intent(MainActivity.this, LoginActivity.class);
        //startActivity(i);
        //finish();
    //}


}
