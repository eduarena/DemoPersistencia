package com.example.fiap.mob8.demopersistencia.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fiap.mob8.demopersistencia.R;
import com.example.fiap.mob8.demopersistencia.model.Pessoa;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rm49684 on 26/08/2015.
 */
public class PessoaAdapter extends BaseAdapter {

    List<Pessoa> pessoas;
    LayoutInflater inflater;
    Context context;
    Holder holder;

    public PessoaAdapter(Context context, List<Pessoa> pessoas){
        this.context = context;
        this.pessoas = pessoas;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return pessoas != null ? pessoas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return pessoas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            convertView = inflater.inflate(R.layout.row_pessoas, parent, false);

            holder = new Holder();
            holder.tvId = (TextView)convertView.findViewById(R.id.tvId);
            holder.tvNome = (TextView)convertView.findViewById(R.id.tvNome);
            holder.tvIdade = (TextView)convertView.findViewById(R.id.tvIdade);

            //Define os itens na view;
            convertView.setTag(holder);
        }else{
            //Pega os dados da lista e define os valores nos itens.
            holder = (Holder) convertView.getTag();
        }

        holder.tvId.setText(String.valueOf("Id: " + pessoas.get(position).getId()));
        holder.tvNome.setText(String.valueOf("Nome: " + pessoas.get(position).getNome()));
        holder.tvIdade.setText(String.valueOf("Idade: " + pessoas.get(position).getIdade()));

        return convertView;
    }


    public class Holder {
        TextView tvId;
        TextView tvNome;
        TextView tvIdade;
    }
}
