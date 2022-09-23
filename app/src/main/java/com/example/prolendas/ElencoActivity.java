package com.example.prolendas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.prolendas.adapter.AdapterEmpresa;
import com.example.prolendas.adapter.AdapterProduto;
import com.example.prolendas.helper.ConfiguracaoFirebase;
import com.example.prolendas.listener.RecyclerItemClickListener;
import com.example.prolendas.model.Atleta;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class ElencoActivity extends AppCompatActivity {

    private MaterialSearchView searchView;
    private FirebaseAuth autenticacao;
    private RecyclerView recyclerpesquisa;
    private AdapterProduto adapterProduto;
    private List<Atleta> atletas = new ArrayList<>();
    private DatabaseReference firebaseRef;
    private AdapterEmpresa adapterEmpresa;
    private String idUsuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elenco);

       // inicializarComponentes();

        // FIREBASE
        firebaseRef = ConfiguracaoFirebase.getFirebase();
        autenticacao = ConfiguracaoFirebase.getFireAutenticacao();

        //Configurações Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Elenco Clube");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Configuracao recyclerview
        recyclerpesquisa.setLayoutManager(new LinearLayoutManager(this));
        recyclerpesquisa.setHasFixedSize(true);
        adapterProduto = new AdapterProduto(atletas, this);
        recyclerpesquisa.setAdapter( adapterProduto );

        

        //  Recuperar posicao atleta
        //recuperarAtletas();



        Toast.makeText(getApplicationContext(), "ELENCO EM CONSTRUCAO", Toast.LENGTH_LONG).show();

    }



    private void inicializarComponentes() {

        searchView = findViewById(R.id.materialSearchView);
        recyclerpesquisa = findViewById(R.id.recyclerpesquisa);
        firebaseRef = ConfiguracaoFirebase.getFirebase();
        idUsuarioLogado = UsuarioFirebase.getIdUsuario();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_usuario, menu);

        // Configurar botao de pesquisa
        MenuItem item = menu.findItem(R.id.menuPesquisa);
        searchView.setMenuItem(item);

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

            //case R.id.menuPesquisa:
            //abrirNovoProduto();
            //break;
        }



        return super.onOptionsItemSelected(item);
    }



    private void deslogarUsuario() {

        try {
            Intent intent = new Intent(getApplicationContext(), PosLoginActivity.class);
            startActivity(intent);

        } catch (Exception e){
            e.printStackTrace();
        }
    }


    //private void abrirNovoProduto() {

    //  startActivity(new Intent(AtletaPesquisaActivity.this, NovaVendaClubeActivity.class));

    //}

    private void abrirConfiguracoes() {

        startActivity(new Intent(ElencoActivity.this, ConfiguracoesClubeActivity.class));

    }    // RETIRAR ESSE ULTIMO COMENTARIO

}

