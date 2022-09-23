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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prolendas.helper.ConfiguracaoFirebase;
import com.example.prolendas.model.Empresa;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class ConfiguracoesClubeActivity extends AppCompatActivity {

    private Button botaoXbox;

    private EditText editNomeClube, editPresidente, editObs, editCelu, editGamerTag;
            private TextView editSaldo;

    private ImageView imagePerfilEmpresa;

    private static final int SELECAO_GALERIA = 200;

    private StorageReference storageReference;

    //RECUPERAR DADOS CLUBE
    private DatabaseReference firebaseRef;

    private String idUsuarioLogado;
    private String urlImagemSelecionada = "";
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes_clube);

        //    Configuracoes iniciais
        inicializarComponentes();

        //    FIREBASE STORAGE
        storageReference = ConfiguracaoFirebase.getFireStorage();

        //  RECUPERAR DADO CLUBE
        firebaseRef = ConfiguracaoFirebase.getFirebase();

        idUsuarioLogado = UsuarioFirebase.getIdUsuario();



        //    Configurações Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Configuracoes Clube");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //     MUDAR FOTO
        imagePerfilEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mudarFoto = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                );
                if ( mudarFoto.resolveActivity(getPackageManager()) != null ){
                    startActivityForResult(mudarFoto, SELECAO_GALERIA);
                }
            }
        });


        recuperarDadosEmpresa();



        //  Recuperar dados EMPRESA



        Toast.makeText(getApplicationContext(), "ConfiguracoesClubeActivity", Toast.LENGTH_LONG).show();

    }

    private void recuperarDadosEmpresa() {

        DatabaseReference empresaRef = ConfiguracaoFirebase.getFirebase()
                .child("empresas")
                .child( idUsuarioLogado );
        empresaRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if ( dataSnapshot.getValue() != null ){
                    Empresa empresa = dataSnapshot.getValue(Empresa.class);

                    editNomeClube.setText(empresa.getEditnomeClube());
                    editCelu.setText(empresa.getEditCelu());
                    editGamerTag.setText(empresa.getEditGamerTag());
                    editPresidente.setText(empresa.getEditnomePresidente());
                    editObs.setText(empresa.getEditObs());
                    editSaldo.setText(empresa.getEditSaldo().toString());


                    urlImagemSelecionada = empresa.getUrlImagem();
                    if ( urlImagemSelecionada != ""){

                        Picasso.get()
                                .load(urlImagemSelecionada)
                                .into(imagePerfilEmpresa);

                        //  FIM DE RECUPERAR DADOS CLUBE

                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    //    ANTES DE SALVAR VALIDAR DADOS  *************************************
    public void validarDadosEmpresa(View view){

        //  VALIDA SE OS CAMPOS FORAM PREENCHIDOS
        String nomeEmpresa = editNomeClube.getText().toString();
        String presidente = editPresidente.getText().toString();
        String observacoes = editObs.getText().toString();
        String celular = editCelu.getText().toString();
        String gamertag = editGamerTag.getText().toString();
        String saldo = editSaldo.getText().toString();


        if ( !nomeEmpresa.isEmpty() ){
            if ( !presidente.isEmpty() ){
                if ( !observacoes.isEmpty() ){
                    if ( !celular.isEmpty() ){
                        if ( !gamertag.isEmpty() ){
                            if ( !saldo.isEmpty() ){
                                if (!urlImagemSelecionada.isEmpty()) {

                                Empresa empresa = new Empresa();
                                empresa.setIdUsuario( idUsuarioLogado  );
                                empresa.setEditnomeClube( nomeEmpresa );
                                empresa.setEditnomePresidente( presidente );
                                empresa.setEditCelu( celular );
                                empresa.setEditGamerTag( gamertag );
                                empresa.setEditObs( observacoes );
                                empresa.setEditSaldo( Double.parseDouble(saldo) );

                                empresa.setUrlImagem( urlImagemSelecionada );
                                empresa.salvar();
                                finish();

                                }else {exibirMensagem("Faça upload da foto");}
                                }else { exibirMensagem("veja seu saldo"); }
                            }else { exibirMensagem("Digite sua Gamertag"); }
                        }else { exibirMensagem("Digite seu celular"); }
                    }else { exibirMensagem("Digite suas observacoes"); }
                }else { exibirMensagem("Digite nome Presidente"); }
            }else { exibirMensagem("Digite o nome da empresa"); }

    }

    private void exibirMensagem (String texto){
        Toast.makeText(this, texto, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( resultCode == RESULT_OK ){
            Bitmap imagem = null;

            try {
                switch (requestCode){
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

                if ( imagem != null ){
                    imagePerfilEmpresa.setImageBitmap( imagem );

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    imagem.compress(Bitmap.CompressFormat.JPEG, 70, baos);
                    byte[] dadosImagem = baos.toByteArray();

                    final StorageReference imagemRef = storageReference
                            .child("imagens")
                            .child("empresas")
                            .child( idUsuarioLogado + "jpeg" );

                    UploadTask uploadTask = imagemRef.putBytes( dadosImagem );
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ConfiguracoesClubeActivity.this,
                                    "Erro ao fazer upload da Imagem", Toast.LENGTH_SHORT).show();
                        }

                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            //   FAZENDO DONWLOAD DA FOTO
                            urlImagemSelecionada = taskSnapshot.getDownloadUrl().toString();


                            Toast.makeText(ConfiguracoesClubeActivity.this,
                                    "Sucesso ao fazer Upload da Imagem",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void inicializarComponentes() {
        editNomeClube = findViewById(R.id.editNomeClube);
        editPresidente = findViewById(R.id.editPresidente);
        editObs = findViewById(R.id.editObs);
        editCelu = findViewById(R.id.editCelu);
        editGamerTag = findViewById(R.id.editGamerTag);
        editSaldo = findViewById(R.id.editSaldo);

        imagePerfilEmpresa = findViewById(R.id.imagePerfilAtleta);

        botaoXbox = findViewById(R.id.botaoXbox);
    }


}
