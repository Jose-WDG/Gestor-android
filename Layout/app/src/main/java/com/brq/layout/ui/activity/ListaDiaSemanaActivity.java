package com.brq.layout.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.RecentlyNonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.brq.layout.Dao.DiaDao;
import com.brq.layout.Model.DiaSemana;
import com.brq.layout.R;
import com.brq.layout.ui.adapter.ListaDiasAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.brq.layout.ui.activity.ConstantesActivities.CHAVE_DIA;


public class ListaDiaSemanaActivity extends AppCompatActivity {

    private final DiaDao dao = new DiaDao();
    private ListaDiasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lista_semana);
        configuraFab();
        configList();


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lida_dia_menu, menu);
    }


    @Override
    protected void onResume() {
        super.onResume();
        attListDados();
    }

    @Override
    public boolean onContextItemSelected(@RecentlyNonNull final MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.act_lis_dia_menu_remover) {
            DialogDelete(item);
        }
        return super.onContextItemSelected(item);

    }

    private void DialogDelete(final MenuItem item) {
        new AlertDialog.Builder(this)
                .setTitle("Remover Registro")
                .setMessage("Tem certeza que deseja remover o registro?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AdapterView.AdapterContextMenuInfo menuInfo =
                                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        DiaSemana diaEscolhido = adapter.getItem(menuInfo.position);
                        remove(diaEscolhido);
                    }
                })
                .setNegativeButton("Não",null)
                .show();
    }

    private void remove(DiaSemana diaEscolhido) {
        dao.deleTDay(diaEscolhido);
        attListDados();
    }

    private void configuraFab() {

        FloatingActionButton btnFloat = findViewById(R.id.activity_main_btnFloat);
        btnFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(
                        ListaDiaSemanaActivity.this,
                        FormularioDiaSemanaActivity.class));
            }
        });
    }

    private void attListDados() {
        adapter.update(dao.getAllDay());

    }
    private void remover(){

    }


    private void configList() {
        ListView listaDiaSema = findViewById(R.id.activityMainListDiasSemana);
        configuraAdapter(listaDiaSema);
        configuraListenerDeCliqueProItem(listaDiaSema);
        registerForContextMenu(listaDiaSema);

    }


    private void configuraListenerDeCliqueProItem(ListView listaDiaSema) {
        listaDiaSema.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {

                DiaSemana nomeItemClicado = (DiaSemana) adapterView.getItemAtPosition(position);
                //String nomeItemClicado = adapterView.getItemAtPosition(position).toString();
                abrirResumoForm(nomeItemClicado);
            }
        });
    }

    private void abrirResumoForm(DiaSemana diaSemana) {
        Intent resumo = new Intent(
                ListaDiaSemanaActivity.this,
                ResumoActivity.class);
        resumo.putExtra(CHAVE_DIA, diaSemana);
        startActivity(resumo);
    }

    private void configuraAdapter(ListView listaDiaSema) {
        adapter = new ListaDiasAdapter(this);
        listaDiaSema.setAdapter(adapter);
    }


}
