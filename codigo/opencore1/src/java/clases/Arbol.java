/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;

import java.util.ArrayList;

/**
 *
 * @author jgonzalezc
 */
public class Arbol {
    private ArrayList<Nodo> listaNodos;
    
    public ArrayList<Nodo> getListaNodos(){
        return listaNodos;
    }

    public Arbol(Nodo raiz) {
        listaNodos = new ArrayList<Nodo>();
        listaNodos.add(raiz);
    }

    public int posicionNodo(int id) {
        int index = -1;
        for (int i = 0; i < listaNodos.size(); i++) {
            if (id == listaNodos.get(i).getId())
                index = i;
        }
        return index;
    }

    /**
     * Sirve para agregar un nodo al arlbol
     * Excepcion, cuando el nodo hace referencia 
     * a un padre que no se encuentra en el arbol
     * @param nodo
     */
    public void agregarNodo(Nodo nodo) {
        int posPadre = posicionNodo(nodo.getPadre());
        listaNodos.add(posPadre + 1, nodo);
        if (listaNodos.get(posPadre).isNodoHoja()) {
            listaNodos.get(posPadre).setNodoHoja(false);
            listaNodos.get(posPadre).setUltimoHijo(nodo.getId());
        }

    }

    public String cerrarRama(Nodo nodo, String cadena, boolean json) {
        int posPadre = posicionNodo(nodo.getPadre());
        if (posPadre > 0) {
            Nodo nodoPadre = new Nodo(listaNodos.get(posPadre));
            if (nodoPadre.getUltimoHijo() == nodo.getId()) {
                if (json)
                    cadena = cerrarRama(nodoPadre, cadena + "]}", json);
                else
                    cadena = 
                            cerrarRama(nodoPadre, cadena + "</ul></li>", json);
            }

        }
        return cadena;
    }
    
    public String imprimirLista (String aplicacion)
    {
        return imprimirLista();
    }
    /**
     * Sirve para imprimir el arbol como lista de html
     * @return cadena
     */
    private String imprimirLista() {
        String html = "<ul>";
        for (int i = 0; i < listaNodos.size(); i++) {
            Nodo nodoTemp = new Nodo(listaNodos.get(i));
            if (nodoTemp.isNodoHoja()) {
                int ultimoHijo = 
                    listaNodos.get(posicionNodo(nodoTemp.getPadre())).getUltimoHijo();
                if (ultimoHijo == nodoTemp.getId())
                    html += 
                            "<li id='" + nodoTemp.getId() + "'>" + nodoTemp.getTitulo() + 
                            "</li>" + cerrarRama(nodoTemp, "", false);
                else
                    html += 
                            "<li id='" + nodoTemp.getId() + "'>" + nodoTemp.getTitulo() + 
                            "</li>";
            } else
                html += 
                        "<li id='" + nodoTemp.getId() + "'>" + nodoTemp.getTitulo() + 
                        "<ul>";
        }
        html += "</ul>";
        return html;
    }

    /**
     * Sirve para imprimir el arbol en formato json
     * @return cadena
     */
    public String getJson() {
        String json = "[";
        int i;
        for (i = 1; i < listaNodos.size(); i++) {
            Nodo nodoTemp = new Nodo(listaNodos.get(i));
            if (nodoTemp.isNodoHoja()) {
                int ultimoHijo = 
                    listaNodos.get(posicionNodo(nodoTemp.getPadre())).getUltimoHijo();
                if (ultimoHijo == nodoTemp.getId())
                    json += 
                            "{\"id\":" + nodoTemp.getId() + ",\"text\":\"" + nodoTemp.getTitulo() + 
                            "\"}" + cerrarRama(nodoTemp, "", true) + ",";
                else
                    json += 
                            "{\"id\":" + nodoTemp.getId() + ",\"text\":\"" + nodoTemp.getTitulo() + 
                            "\"},";
            } else
                json += 
                        "{\"id\":" + nodoTemp.getId() + ",\"text\":\"" + nodoTemp.getTitulo() + 
                        "\",\"items\":[";
        }
        if (i > 0)
            json = json.substring(0, json.length() - 1);
        json += "]";
        return json;
    }
}
