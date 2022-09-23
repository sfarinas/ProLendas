package com.example.prolendas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prolendas.helper.ConfiguracaoFirebase;
import com.example.prolendas.model.Atleta;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import com.google.android.gms.ads.InterstitialAd;


import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class ConfiguracoesUsuariosActivity extends AppCompatActivity {

    private Button botaoXbox;

    private EditText editNome, editSobre, editOveraa, editCelu, editGamerTag,
            posicao, qtdAssistencias, qtdDef, qtdDes, qtdGol;

    // CLUBE
    private TextView editNomeClube, valorMercado, qtdGolClube, qtdGolTemporada, cartaoTemporada, valorCompra;


    private ImageView imagePerfilAtleta;

    private static final int SELECAO_GALERIA = 200;

    private StorageReference storageReference;

    //RECUPERAR DADOS ATLETA
    private DatabaseReference firebaseRef;

    private String idUsuarioLogado;
    private String urlImagemSelecionada = "";

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes_usuarios);

        Toast.makeText(ConfiguracoesUsuariosActivity.this,
                "Sucesso ao fazer Upload da Imagem",
                Toast.LENGTH_SHORT).show();


        // ADMOB
        MobileAds.initialize(this,
                "ca-app-pub-9825390915193711~3767242704");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9825390915193711/6372406508");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());






        //    Configuracoes iniciais
        inicializarComponentes();

        //    FIREBASE STORAGE
        storageReference = ConfiguracaoFirebase.getFireStorage();

        //  RECUPERAR DADOS ATLETA
        firebaseRef = ConfiguracaoFirebase.getFirebase();

        idUsuarioLogado = UsuarioFirebase.getIdUsuario();


        //    Configurações Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Configuracoes Atleta");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //     MUDAR FOTO
        imagePerfilAtleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mudarFoto = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                );
                if (mudarFoto.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(mudarFoto, SELECAO_GALERIA);
                }
            }
        });


        recuperarDadosAtleta();


        //  Recuperar dados ATLETA


        Toast.makeText(getApplicationContext(), "Configuracoes usuario", Toast.LENGTH_LONG).show();

    }

    private void recuperarDadosAtleta() {

        DatabaseReference atletaRef = ConfiguracaoFirebase.getFirebase()
                .child("atletas")
                .child(idUsuarioLogado);
        atletaRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue() != null) {
                    Atleta atleta = dataSnapshot.getValue(Atleta.class);

                    editNome.setText(atleta.getEditNome());
                    editCelu.setText(atleta.getEditCelu());
                    editGamerTag.setText(atleta.getEditGamerTag());
                    editOveraa.setText(atleta.getEditOveraa());
                    editSobre.setText(atleta.getEditSobre());
                    posicao.setText(atleta.getPosicao());
                    qtdAssistencias.setText(atleta.getQtdAssistencias());
                    qtdDef.setText(atleta.getQtdDef());
                    qtdDes.setText(atleta.getQtdDes());
                    qtdGol.setText(atleta.getQtdGol());
                    // CLUBE

                    // SOMENTE PARA CLUBES PAGOS
                    // COMENTAR A EDITNOMECLUBE   PARA PODER VENDER SEPARADO PARA OS CLUBES
                    //editNomeClube.setText(atleta.getEditnomeClube());
                    valorMercado.setText(atleta.getValorMercado().toString());
                    qtdGolClube.setText(atleta.getQtdGolClube());
                    qtdGolTemporada.setText(atleta.getQtdGolTemporada());
                    cartaoTemporada.setText(atleta.getCartaoTemporada());
                    valorCompra.setText(atleta.getValorCompra().toString());



                    urlImagemSelecionada = atleta.getUrlImagem();
                    if (urlImagemSelecionada != "") {

                        Picasso.get()
                                .load(urlImagemSelecionada)
                                .into(imagePerfilAtleta);

                        //  FIM DE RECUPERAR DADOS ATLETA

                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    //    ANTES DE SALVAR VALIDAR DADOS  *************************************
    public void validarDadosAtleta(View view) {

        //  VALIDA SE OS CAMPOS FORAM PREENCHIDOS
        String nome = editNome.getText().toString();
        String sobrenome = editSobre.getText().toString();
        String overall = editOveraa.getText().toString();
        String celular = editCelu.getText().toString();
        String gamertag = editGamerTag.getText().toString();
        String posica = posicao.getText().toString();
        String assistencia = qtdAssistencias.getText().toString();
        String defesa = qtdDef.getText().toString();
        String desarme = qtdDes.getText().toString();
        String gol = qtdGol.getText().toString();


        // CLUBE
        String clube = editNomeClube.getText().toString();
        String valormercado = valorMercado.getText().toString();
        String qtdgolClube = qtdGolClube.getText().toString();
        String qtdgolTemporada = qtdGolTemporada.getText().toString();
        String cartaotemporada = cartaoTemporada.getText().toString();
        String valorcompra = valorCompra.getText().toString();


        if (!nome.isEmpty()) {
            if (!sobrenome.isEmpty()) {
                if (!overall.isEmpty()) {
                    if (!celular.isEmpty()) {
                        if (!gamertag.isEmpty()) {
                            if (!posica.isEmpty()) {
                                if (!assistencia.isEmpty()) {
                                    if (!defesa.isEmpty()) {
                                        if (!desarme.isEmpty()) {
                                            if (!gol.isEmpty()) {
                                                if (!urlImagemSelecionada.isEmpty()) {
                                                if ( !clube.isEmpty() ){
                                                if ( !valormercado.isEmpty() ){
                                                if ( !qtdgolClube.isEmpty() ){
                                                if ( !qtdgolTemporada.isEmpty() ){
                                                if ( !cartaotemporada.isEmpty() ){


                                                Atleta atleta = new Atleta();
                                                atleta.setIdUsuario(idUsuarioLogado);
                                                atleta.setEditNome(nome);
                                                atleta.setEditSobre(sobrenome);
                                                atleta.setEditCelu(celular);
                                                atleta.setEditGamerTag(gamertag);
                                                atleta.setEditOveraa(overall);
                                                atleta.setPosicao(posica);
                                                atleta.setQtdAssistencias(assistencia);
                                                atleta.setQtdDef(defesa);
                                                atleta.setQtdDes(desarme);
                                                atleta.setQtdGol(gol);
                                                atleta.setUrlImagem(urlImagemSelecionada);
                                                //CLUBE
                                                atleta.setEditnomeClube( clube );
                                                atleta.setValorMercado( Double.parseDouble (valormercado) );
                                                atleta.setQtdGolClube( qtdgolClube );
                                                atleta.setQtdGolTemporada( qtdgolTemporada );
                                                atleta.setCartaoTemporada( cartaotemporada );
                                                atleta.setValorCompra( Double.parseDouble (valorcompra) );

                                                    Toast.makeText(getApplicationContext(),"Abre Anuncio", Toast.LENGTH_SHORT).show();

                                                    if (mInterstitialAd.isLoaded()) {
                                                        mInterstitialAd.show();
                                                    } else {
                                                        Log.d("TAG", "The interstitial wasn't loaded yet.");
                                                    }

                                                atleta.salvar();
                                                finish();


                                                }else {exibirMensagem("Valor cartao Temporada");}

                                                }else {exibirMensagem("Valor qtdGolTemporada");}

                                                }else {exibirMensagem("Valor qtdGolClube");}

                                                }else {exibirMensagem("Valor setValorMercado");}

                                                }else {exibirMensagem("Valor setIdClube");}

                                                }else {exibirMensagem("Faça upload da foto");}

                                            } else {
                                                exibirMensagem("Atualize seus gols");
                                            }
                                        } else {
                                            exibirMensagem("Atualize seus desarmes");
                                        }
                                    } else {
                                        exibirMensagem("Atualize suas defesas como GK");
                                    }
                                } else {
                                    exibirMensagem("Atualize suas assistencias");
                                }
                            } else {
                                exibirMensagem("Digite sua posicao");
                            }
                        } else {
                            exibirMensagem("Digite sua Gamertag");
                        }
                    } else {
                        exibirMensagem("Digite seu celular");
                    }
                } else {
                    exibirMensagem("Digite seu Overall");
                }
            } else {
                exibirMensagem("Digite seu ultimo nome");
            }
        } else {
            exibirMensagem("Digite seu primeiro nome");
        }

    }


    private void exibirMensagem(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bitmap imagem = null;

            try {
                switch (requestCode) {
                    case SELECAO_GALERIA:
                        Uri localImagem = data.getData();
                        imagem = MediaStore.Images
                                .Media
                                .getBitmap(
                                        getContentResolver(),
                                        localImagem
                                );
                        break;
                }

                if (imagem != null) {
                    imagePerfilAtleta.setImageBitmap(imagem);

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    imagem.compress(Bitmap.CompressFormat.JPEG, 70, baos);
                    byte[] dadosImagem = baos.toByteArray();

                    final StorageReference imagemRef = storageReference
                            .child("imagens")
                            .child("atletas")
                            .child(idUsuarioLogado + "jpeg");

                    UploadTask uploadTask = imagemRef.putBytes(dadosImagem);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ConfiguracoesUsuariosActivity.this,
                                    "Erro ao fazer upload da Imagem", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            //   FAZENDO DONWLOAD DA FOTO
                            urlImagemSelecionada = taskSnapshot.getDownloadUrl().toString();

                            imagemRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    Uri url = task.getResult();
                                }
                            });


                        }
                    });
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void inicializarComponentes() {
        editNome = findViewById(R.id.editNomeClube);
        editSobre = findViewById(R.id.editPresidente);
        editOveraa = findViewById(R.id.editObs);
        editCelu = findViewById(R.id.editCelu);
        editGamerTag = findViewById(R.id.editGamerTag);
        posicao = findViewById(R.id.posicao);
        qtdAssistencias = findViewById(R.id.qtdAssistencias);
        qtdDef = findViewById(R.id.qtdDef);
        qtdDes = findViewById(R.id.qtdDes);
        qtdGol = findViewById(R.id.qtdGol);
        imagePerfilAtleta = findViewById(R.id.imagePerfilAtleta);
        // CLUBE
        editNomeClube = findViewById(R.id.idClube);
        valorMercado = findViewById(R.id.editeValorMercado);
        qtdGolClube = findViewById(R.id.editeGolsClube);
        qtdGolTemporada = findViewById(R.id.editeGolsTempo);
        cartaoTemporada = findViewById(R.id.editeCartoesTempo);
        valorCompra = findViewById(R.id.editeValorCompra);





        botaoXbox = findViewById(R.id.botaoXbox);
    }



}





