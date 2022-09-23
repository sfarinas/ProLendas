package com.example.prolendas;

import com.example.prolendas.helper.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

class UsuarioFirebase {

    //public static String getIdUsuario;

    public static String getIdUsuario(){

        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFireAutenticacao();
        return autenticacao.getCurrentUser().getUid();

    }


    public static FirebaseUser getUsuarioAtual(){

        FirebaseAuth usuario = ConfiguracaoFirebase.getFireAutenticacao();
        return usuario.getCurrentUser();

    }
}
