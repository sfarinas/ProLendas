package com.example.prolendas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prolendas.R;
import com.example.prolendas.model.Atleta;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by Salomao
 */

public class AdapterClube extends RecyclerView.Adapter<AdapterClube.MyViewHolder>{

    private List<Atleta> atletas;
    private Context context;

    public AdapterClube(List<Atleta> atletas, Context context) {
        this.atletas = atletas;
        this.context = context;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_clube, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        Atleta atleta = atletas.get(i);
        holder.posicao.setText(" POSICAO :    " + atleta.getPosicao());
        holder.overall.setText(" OVERALL :    " + atleta.getEditOveraa());
        holder.nome.setText(" NOME " + atleta.getEditNome());
        holder.sobrenome.setText(" SOBRENOME " + atleta.getEditSobre());
        holder.gamertag.setText(" GAMERTAG " + atleta.getEditGamerTag());
        holder.celular.setText(" CELULAR " + atleta.getEditCelu());
        holder.defesas.setText(" QTD DEFESAS " + atleta.getQtdDef());
        holder.desarmes.setText(" QTD DESARMES " + atleta.getQtdDes());
        holder.assistencias.setText(" QTD ASSISTENCIAS " + atleta.getQtdAssistencias());
        holder.gols.setText(" QTD GOLS " + atleta.getQtdGol());
        holder.golsClube.setText(" QTD GOLS CLUBE " + atleta.getQtdGolClube());
        holder.golsTemporada.setText(" QTD GOLS TEMPORADA " + atleta.getQtdGolTemporada());
        holder.valorMercado.setText(" VALOR MERCADO " + atleta.getValorMercado());
        holder.valorCompra.setText(" VALOR COMPRA " + atleta.getValorCompra());
        holder.cartaoTemporada.setText(" CATÕES TEMPORADA " + atleta.getCartaoTemporada());
        holder.clube.setText(" CLUBE " + atleta.getEditnomeClube());

        //Carregar imagem
        String urlImagem = atleta.getUrlImagem();
        Picasso.get().load( urlImagem ).into( holder.imagePerfilAtleta );

    }

    @Override
    public int getItemCount() {
        return atletas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imagePerfilAtleta;
        TextView posicao;
        TextView overall;
        TextView nome;
        TextView sobrenome;
        TextView celular;
        TextView gamertag;
        TextView defesas;
        TextView desarmes;
        TextView assistencias;
        TextView gols;
        TextView golsTemporada;
        TextView golsClube;
        TextView valorMercado;
        TextView valorCompra;
        TextView cartaoTemporada;
        TextView clube;

        public MyViewHolder(View itemView) {
            super(itemView);

            imagePerfilAtleta = itemView.findViewById(R.id.imagePerfilAtleta);
            posicao = itemView.findViewById(R.id.textPosicao);
            overall = itemView.findViewById(R.id.textOverall);
            nome = itemView.findViewById(R.id.textNome);
            sobrenome = itemView.findViewById(R.id.textSobre);
            celular = itemView.findViewById(R.id.textCelu);
            gamertag = itemView.findViewById(R.id.textGamertag);
            defesas = itemView.findViewById(R.id.textDefesa);
            desarmes = itemView.findViewById(R.id.textDesarme);
            assistencias = itemView.findViewById(R.id.textAssistencia);
            gols = itemView.findViewById(R.id.textGol);
            golsTemporada = itemView.findViewById(R.id.textGolTempo);
            golsClube = itemView.findViewById(R.id.textGolClube);
            valorMercado = itemView.findViewById(R.id.textValorMercado);
            valorCompra = itemView.findViewById(R.id.textValorCompra);
            cartaoTemporada = itemView.findViewById(R.id.textCartaoTempo);
            clube = itemView.findViewById(R.id.textClube);


        }
    }
}
