package com.example.prolendas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PosLoginActivity extends AppCompatActivity {

    private Button botaoLogo;
    private Button atletaLivre;
    private Button atletaClubeLivre;
    private Button atleta;
    private Button clubes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pos_login);

        //getSupportActionBar().hide();


        botaoLogo = findViewById(R.id.botaoLogo);
        atletaLivre = findViewById(R.id.atletaLivre);
        atletaClubeLivre = findViewById(R.id.atletaClubeLivre);
        atleta = findViewById(R.id.botaoatleta);
        clubes = findViewById(R.id.clubes);

        botaoLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        atleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ConfiguracoesUsuariosActivity.class);
                startActivity(intent);
            }
        });

        clubes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ClubeActivity.class);
                startActivity(intent);
            }
        });

        atletaLivre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AtletaPesquisaActivity.class );
                startActivity(intent);
            }
        });



        Toast.makeText(getApplicationContext(), "PosLoginActivity", Toast.LENGTH_LONG).show();

    }


}
