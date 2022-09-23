package com.example.prolendas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AtletaActivity extends AppCompatActivity {

    private Button botaoXbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atleta);
        //getSupportActionBar().hide();

        botaoXbox = findViewById(R.id.botaoXbox);

        botaoXbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), XboxActivity.class);
                startActivity(intent);

            }
        });


        Toast.makeText(getApplicationContext(), "Atleta", Toast.LENGTH_LONG).show();
    }
}
