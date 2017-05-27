package com.fjbatresv.callrest.entities;

import com.fjbatresv.callrest.db.RingControlDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

/**
 * Created by javie on 27/05/2017.
 */
@Table(database = RingControlDB.class)
public class List extends BaseModel implements Serializable {
    @PrimaryKey
    @Column
    private String cui;
    @Column
    private String nombre;
    @Column
    private String descripcion;
    @Column
    private String tipo;
    @Column
    private boolean publico;
    @Column
    private long time;

    public List() {
    }


    public List(String cui, String nombre, String descripcion, String tipo, boolean publico, long time) {
        this.cui = cui;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.publico = publico;
        this.time = time;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isPublico() {
        return publico;
    }

    public void setPublico(boolean publico) {
        this.publico = publico;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
