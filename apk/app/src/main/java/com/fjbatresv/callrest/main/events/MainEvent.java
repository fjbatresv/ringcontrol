package com.fjbatresv.callrest.main.events;

/**
 * Created by javie on 24/05/2017.
 */

public class MainEvent {
    private int tipo;
    private String mensaje;
    private String error;
    private Object object;

    public static final int REVIEW_INTRO = 0;
    public static final int LOAD_USER = 1;

    public MainEvent() {
    }

    public MainEvent(int tipo, String mensaje, String error, Object object) {
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.error = error;
        this.object = object;
    }

    public MainEvent(int tipo, Object object) {
        this.tipo = tipo;
        this.object = object;
    }

    public MainEvent(int tipo, String mensaje, Object object) {
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.object = object;
    }

    public MainEvent(int tipo, String error) {
        this.tipo = tipo;
        this.error = error;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}

