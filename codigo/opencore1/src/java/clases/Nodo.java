/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;

/**
 *
 * @author jgonzalezc
 */
public class Nodo {
    private int padre;
    private int id;
    private String titulo;
    private Object objeto;

    private boolean nodoHoja;
    private int ultimoHijo;

    public Nodo(int id, int padre, String titulo,Object objeto) {
        setPadre(padre);
        setId(id);
        setTitulo(titulo);
        setNodoHoja(true);
        setUltimoHijo(-1);
        setObjeto(objeto);

    }

    public Nodo(Nodo nodo) {
        this.setPadre(nodo.getPadre());
        this.setId(nodo.getId());
        this.setTitulo(nodo.getTitulo());
        this.setNodoHoja(nodo.isNodoHoja());
        this.setUltimoHijo(nodo.getUltimoHijo());
        this.setObjeto(nodo.getObjeto());
    }

    public int getPadre() {
        return padre;
    }

    public void setPadre(int padre) {
        this.padre = padre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isNodoHoja() {
        return nodoHoja;
    }

    public void setNodoHoja(boolean nodoHoja) {
        this.nodoHoja = nodoHoja;
    }

    public int getUltimoHijo() {
        return ultimoHijo;
    }

    public void setUltimoHijo(int ultimoHijo) {
        this.ultimoHijo = ultimoHijo;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }
}


