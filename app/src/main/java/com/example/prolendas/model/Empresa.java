package com.example.prolendas.model;

import com.example.prolendas.helper.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

public class Empresa {

    private String idUsuario;
    private String urlImagem;

    private String editnomeClube;
    private String editnomePresidente;
    private String editObs;
    private Double editSaldo;
    private String editCelu;
    private String editGamerTag;

    public Empresa() {
    }

    public void salvar(){

        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference empresaRef = firebaseRef.child("empresas")
                .child( getIdUsuario () );
        empresaRef.setValue(this);

    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getEditnomeClube() {
        return editnomeClube;
    }

    public void setEditnomeClube(String editnomeClube) {
        this.editnomeClube = editnomeClube;
    }

    public String getEditnomePresidente() {
        return editnomePresidente;
    }

    public void setEditnomePresidente(String editnomePresidente) {
        this.editnomePresidente = editnomePresidente;
    }

    public String getEditObs() {
        return editObs;
    }

    public void setEditObs(String editObs) {
        this.editObs = editObs;
    }

    public Double getEditSaldo() {
        return editSaldo;
    }

    public void setEditSaldo(Double editSaldo) {
        this.editSaldo = editSaldo;
    }

    public String getEditCelu() {
        return editCelu;
    }

    public void setEditCelu(String editCelu) {
        this.editCelu = editCelu;
    }

    public String getEditGamerTag() {
        return editGamerTag;
    }

    public void setEditGamerTag(String editGamerTag) {
        this.editGamerTag = editGamerTag;
    }
}
