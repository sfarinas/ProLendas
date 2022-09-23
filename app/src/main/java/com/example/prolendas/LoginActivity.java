package com.example.prolendas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.prolendas.helper.ConfiguracaoFirebase;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private Button botaoClubesAcesso;
    private Button botaoAcesso;
    private EditText campoEmail, campoSenha;
    private Switch tipoAcesso;
    //private Button botaoLoginGoogle;

    //private GoogleSignInClient googleSignInClient;
    //private Button conti5;

    private FirebaseAuth autenticacao;

    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

// ADMOB
        MobileAds.initialize(this, "ca-app-pub-9825390915193711~3767242704");

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-9825390915193711/6372406508");
        interstitialAd.loadAd(new AdRequest.Builder().build());

        //servicosGoogle();

        // OCULTAR BARRA SUPERIOR
        //getSupportActionBar().hide();

        inicializarComponentes();
        autenticacao = ConfiguracaoFirebase.getFireAutenticacao();

        // ***************** PARA DESLOGAR NOS TESTES ******
        autenticacao.signOut();

        //Verficar usuario logado
        verificarUsuarioLogado();

        botaoAcesso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = campoEmail.getText().toString();
                String senha = campoSenha.getText().toString();

                if ( !email.isEmpty() ){
                    if ( !senha.isEmpty() ){

                        //Verificar estado do switch
                        if ( tipoAcesso.isChecked() ){ //Cadatro usuario

                            autenticacao.createUserWithEmailAndPassword(
                                    email, senha
                            ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()){

                                        Toast.makeText(LoginActivity.this,
                                                "Cadastro Realizado com sucesso! ",
                                                Toast.LENGTH_SHORT).show();

                                        abrirTelaPrincipal();


                                    }else {

                                        String erroExcecao = "";

                                        try{
                                            throw task.getException();
                                        } catch ( FirebaseAuthWeakPasswordException e ){
                                            erroExcecao = "Digite uma senha mais forte!";
                                        } catch ( FirebaseAuthInvalidCredentialsException e ){
                                            erroExcecao = "Por favor, digite um e-mail valido";
                                        } catch ( FirebaseAuthUserCollisionException e ){
                                            erroExcecao = "Esta conta ja foi cadastrada";
                                        } catch ( Exception e ){
                                            erroExcecao = "Ao cadastrar usuario: " + e.getMessage();
                                            e.printStackTrace();
                                        }

                                        Toast.makeText(LoginActivity.this,
                                                "Erro: " + erroExcecao,
                                                Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });

                        }else {//Login

                            autenticacao.signInWithEmailAndPassword(
                                    email, senha
                            ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){

                                        Toast.makeText(LoginActivity.this,
                                                "Logado com secesso: ",
                                                Toast.LENGTH_SHORT).show();
                                        abrirTelaPrincipal();

                                        //ADMOB
                                        Toast.makeText(getApplicationContext(),"Abre Anuncio", Toast.LENGTH_SHORT).show();

                                        if (interstitialAd.isLoaded()) {
                                            interstitialAd.show();
                                        } else {
                                            Log.d("TAG", "The interstitial wasn't loaded yet.");
                                        }

                                    }else {

                                        Toast.makeText(LoginActivity.this,
                                                "Erro ao fazer login : " + task.getException(),
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                        }

                    }else {
                        Toast.makeText(LoginActivity.this,
                                "Preencha a senha",
                                Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginActivity.this,
                            "Preencha o E-mail",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });


        /*

        botaoClubesAcesso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ClubeActivity.class);
                startActivity(intent);
            }
        });

        */

        //conti5 = findViewById(R.id.conti5);

       // conti5.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View v) {
             //   Intent intent = new Intent(getApplicationContext(), PosLoginActivity.class);
           //     startActivity(intent);
          //  }
        //});



        Toast.makeText(getApplicationContext(), "LoginActivity", Toast.LENGTH_LONG).show();

    }



    private void verificarUsuarioLogado(){
        FirebaseUser usuarioAtual = autenticacao.getCurrentUser();

        if( usuarioAtual != null ){
            abrirTelaPrincipal();
        }

        }


    private void abrirTelaPrincipal(){
        startActivity(new Intent(getApplicationContext(), PosLoginActivity.class));
    }

    private void inicializarComponentes(){
        campoEmail = findViewById(R.id.editCadastroEmail);
        campoSenha = findViewById(R.id.editCadastroSenha);
        botaoAcesso = findViewById(R.id.buttonAcesso);
        botaoClubesAcesso = findViewById(R.id.botaoClubesAcesso);
        tipoAcesso = findViewById(R.id.switchAcesso);
        //botaoLoginGoogle = findViewById(R.id.botaoLoginGoogle);
    }
}
