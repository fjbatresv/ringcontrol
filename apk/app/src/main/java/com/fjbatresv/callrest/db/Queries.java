package com.fjbatresv.callrest.db;

import com.fjbatresv.callrest.entities.List;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;

/**
 * Created by javie on 27/05/2017.
 */

public class Queries {
    public static ArrayList<List> crossLists(ArrayList<List> listas) {
        ArrayList<List> dbs = new ArrayList<List>();
        ArrayList<List> resp = new ArrayList<List>();
        dbs.addAll(SQLite.select().from(List.class).queryList());
        for (List db : dbs) {
            boolean iguales = false;
            for (List lista : listas) {
                if (lista.getCui().equalsIgnoreCase(db.getCui())) {
                    resp.add((db.getTime() >= lista.getTime()) ? db : lista);
                    iguales = true;
                }
            }
            if (!iguales) {
                resp.add(db);
            }
        }
        for (List lista : listas) {
            boolean iguales = false;
            for (List db : dbs) {
                if (db.getCui().equalsIgnoreCase(lista.getCui())) {
                    iguales = true;
                }
            }
            if (!iguales) {
                resp.add(lista);
            }
        }
        for (List re : resp) {
            re.save();
        }
        return resp;
    }
}
