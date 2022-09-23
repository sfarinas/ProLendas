package com.example.prolendas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseUser;

public class VendasActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendas);

        //Configurações Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ATLETAS A VENDA");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_empresa, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.menuSair:
                deslogarUsuario();
                break;

            case R.id.menuConfiguracoes:
                abrirConfiguracoes();
                break;

            case R.id.menuVendas:
                abrirNovoProduto();
                break;
        }



        return super.onOptionsItemSelected(item);
    }

    private void abrirNovoProduto() {

        startActivity(new Intent(VendasActivity.this, NovaVendaClubeActivity.class));

    }

    private void abrirConfiguracoes() {

        startActivity(new Intent(VendasActivity.this, ConfiguracoesClubeActivity.class));

    }

    private void deslogarUsuario() {

        try {
            Intent intent = new Intent(getApplicationContext(), PosClubesActivity.class);
            startActivity(intent);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
