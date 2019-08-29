package com.brq.layout.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.brq.layout.Model.DiaSemana;
import com.brq.layout.R;

import java.util.ArrayList;
import java.util.List;

import static com.brq.layout.R.layout.item_lista_dia;

public class ListaDiasAdapter extends BaseAdapter {
    private final List<DiaSemana> dias = new ArrayList<>();
    private Context context;
    public ListaDiasAdapter(Context cont){
        this.context = cont;
    }

    @Override
    public int getCount() {

        return dias.size();
    }

    @Override
    public DiaSemana getItem(int position) {
        return dias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dias.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View viewCriada = LayoutInflater.from(context).inflate(item_lista_dia, viewGroup,false);
        TextView nome = viewCriada.findViewById(R.id.item_dia_nome);
        TextView nomeMat = viewCriada.findViewById(R.id.item_dia_nomeMat);
        TextView sala = viewCriada.findViewById(R.id.item_dia_sala);
        nome.setText(dias.get(position).getTxtNomeDia());
        nomeMat.setText(dias.get(position).getTxtNomeMat());
        sala.setText(dias.get(position).getTxtSala());
        return viewCriada;
    }

    private void clear() {
        dias.clear();
    }

    private void addAll(List<DiaSemana> allDay) {
        this.dias.addAll(allDay);
    }
    public void update(List<DiaSemana> list){
        clear();
        addAll(list);
        notifyDataSetChanged();
    }



}
