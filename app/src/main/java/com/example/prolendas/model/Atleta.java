package com.example.prolendas.model;

import com.example.prolendas.helper.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;

public class Atleta implements Serializable {

    private String idUsuario;
    private String urlImagem;
    private String editNome;
    private String editSobre;
    private String editOveraa;
    private String editCelu;
    private String editGamerTag;
    private String posicao;
    private String qtdAssistencias;
    private String qtdDef;
    private String qtdDes;
    private String qtdGol;

    //  VINCULO CLUBE
    private String qtdGolClube;
    private String qtdGolTemporada;
    private Double valorMercado;
    private Double valorCompra;
    private String cartaoTemporada;
    private String idClube;

    private String editnomeClube;
    private String editnomePresidente;
    private String editObs;
    private Double editSaldo;

    public Atleta() {
    }

    public void salvar(){
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference atletaRef = firebaseRef.child("atletas")
                .child( getIdUsuario() );
        atletaRef.setValue(this);

    }

    //  SOMENTE CLUBE


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

    public String getEditNome() {
        return editNome;
    }

    public void setEditNome(String editNome) {
        this.editNome = editNome;
    }

    public String getEditSobre() {
        return editSobre;
    }

    public void setEditSobre(String editSobre) {
        this.editSobre = editSobre;
    }

    public String getEditOveraa() {
        return editOveraa;
    }

    public void setEditOveraa(String editOveraa) {
        this.editOveraa = editOveraa;
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

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String getQtdAssistencias() {
        return qtdAssistencias;
    }

    public void setQtdAssistencias(String qtdAssistencias) {
        this.qtdAssistencias = qtdAssistencias;
    }

    public String getQtdDef() {
        return qtdDef;
    }

    public void setQtdDef(String qtdDef) {
        this.qtdDef = qtdDef;
    }

    public String getQtdDes() {
        return qtdDes;
    }

    public void setQtdDes(String qtdDes) {
        this.qtdDes = qtdDes;
    }

    public String getQtdGol() {
        return qtdGol;
    }

    public void setQtdGol(String qtdGol) {
        this.qtdGol = qtdGol;
    }

    public String getQtdGolClube() {
        return qtdGolClube;
    }

    public void setQtdGolClube(String qtdGolClube) {
        this.qtdGolClube = qtdGolClube;
    }

    public String getQtdGolTemporada() {
        return qtdGolTemporada;
    }

    public void setQtdGolTemporada(String qtdGolTemporada) {
        this.qtdGolTemporada = qtdGolTemporada;
    }

    public Double getValorMercado() {
        return valorMercado;
    }

    public void setValorMercado(Double valorMercado) {
        this.valorMercado = valorMercado;
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public String getCartaoTemporada() {
        return cartaoTemporada;
    }

    public void setCartaoTemporada(String cartaoTemporada) {
        this.cartaoTemporada = cartaoTemporada;
    }

    public String getIdClube() {
        return idClube;
    }

    public void setIdClube(String idClube) {
        this.idClube = idClube;
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
}
