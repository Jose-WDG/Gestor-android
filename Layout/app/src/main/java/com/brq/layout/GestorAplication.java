package com.brq.layout;

import android.app.Application;

import com.brq.layout.Dao.DiaDao;
import com.brq.layout.Model.DiaSemana;

class GestorAplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //if list is empety, creat new´s item´s
        DiaDao dao = new DiaDao();
        dao.salvar(new DiaSemana("Segunda", "ADS", "A201"));
        dao.salvar(new DiaSemana("Terça", "PI-II", "A203"));
    }
}
