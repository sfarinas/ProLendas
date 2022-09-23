package com.example.prolendas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prolendas.helper.ConfiguracaoFirebase;
import com.example.prolendas.model.Atleta;
import com.example.prolendas.model.Empresa;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class CardapioActivity extends AppCompatActivity {

    private ImageView imagePerfilAtleta;
    private TextView textPosicao, textOverall, textGamertag, textDefesa, textDesarme, textAssistencia,
            textGol, textCelu, textGolTempo, textGolClube, textValorMercado, textValorCompra,
            textCartaoTempo, textClube, textNome, textSobre;
    private Atleta atletaSelecionada;

    private Button botaoComprar;

    private String idAtleta;

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    private Object Empresa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);

        inicializarComponentes();


        botaoComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "comprar", Toast.LENGTH_LONG).show();


                

            }
        });

        // RECUPERAR ATLETA SELECIONADA
        Bundle bundle = getIntent().getExtras();
        if ( bundle != null ){
            atletaSelecionada = (Atleta) bundle.getSerializable("atletas");

            idAtleta = atletaSelecionada.getIdUsuario();

            textPosicao.setText(" POSICAO: " +  atletaSelecionada.getPosicao() );
            textOverall.setText(" OVERALL: " +  atletaSelecionada.getEditOveraa() );
            textGamertag.setText(" GAMERTAG: " +  atletaSelecionada.getEditGamerTag() );
            textDefesa.setText(" QTD DEFESAS: " +  atletaSelecionada.getQtdDef() );
            textDesarme.setText(" QTD DESARMES: " +  atletaSelecionada.getQtdDes() );
            textAssistencia.setText(" QTD ASSISTENCIAS: " +  atletaSelecionada.getQtdAssistencias() );
            textGol.setText(" QTD GOL'S: " +  atletaSelecionada.getQtdGol() );
            textCelu.setText(" CELULAR: " +  atletaSelecionada.getEditCelu() );
            textGolTempo.setText(" QTD GOL'S TEMPORADA: " +  atletaSelecionada.getQtdGolTemporada() );
            textGolClube.setText(" QTD GOL'S CLUBE: " +  atletaSelecionada.getQtdGolClube() );
            textValorMercado.setText(" VALOR DE MERCADO: " +  atletaSelecionada.getValorMercado() );
            textValorCompra.setText(" VALOR DA COMPRA: " +  atletaSelecionada.getValorCompra() );
            textCartaoTempo.setText(" CARTÕES TEMPORADA MAX. 3: " +  atletaSelecionada.getCartaoTemporada() );
            textClube.setText(" CLUBE: " +  atletaSelecionada.getEditnomeClube() );
            textNome.setText(" NOME: " +  atletaSelecionada.getEditNome() );
            textSobre.setText(" SOBRE-NOME: " +  atletaSelecionada.getEditSobre() );

            String url = atletaSelecionada.getUrlImagem();
            Picasso.get().load(url).into(imagePerfilAtleta);

            //vincularAtletaClube();

        }

        //Configurações Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Info Atleta ");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Toast.makeText(getApplicationContext(), "CardapioActivity", Toast.LENGTH_LONG).show();
    }


    //private void vincularAtletaClube() {

        //Toast.makeText(getApplicationContext(), "comprar", Toast.LENGTH_LONG).show();

        /*
        Atleta atleta = new Atleta();
        atleta.getEditnomeClube();

        String geteditnomeClube = atleta.getEditnomeClube();

        if (geteditnomeClube == "LIVRE"){

            Toast.makeText(getApplicationContext(), "comprar", Toast.LENGTH_LONG).show();



        } */


    //}

    private void inicializarComponentes() {

        imagePerfilAtleta = findViewById(R.id.imagePerfilAtleta);
        textPosicao = findViewById(R.id.textPosicao);
        textOverall = findViewById(R.id.textOverall);
        textGamertag = findViewById(R.id.textGamertag);
        textDefesa = findViewById(R.id.textDefesa);
        textDesarme = findViewById(R.id.textDesarme);
        textAssistencia = findViewById(R.id.textAssistencia);
        textGol = findViewById(R.id.textGol);
        textCelu = findViewById(R.id.textCelu);
        textGolTempo = findViewById(R.id.textGolTempo);
        textGolClube = findViewById(R.id.textGolClube);
        textValorMercado = findViewById(R.id.textValorMercado);
        textValorCompra = findViewById(R.id.textValorCompra);
        textCartaoTempo = findViewById(R.id.textCartaoTempo);
        textClube = findViewById(R.id.textClube);
        textNome = findViewById(R.id.textNome);
        textSobre = findViewById(R.id.textSobre);
        botaoComprar = findViewById(R.id.buttonComprar);

    }




}

