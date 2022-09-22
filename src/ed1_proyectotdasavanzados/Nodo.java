
package ed1_proyectotdasavanzados;

import java.io.Serializable;
import java.util.*;


public class Nodo implements Serializable {
    // Lista de adyacencia
    private ArrayList<Nodo> listaAdy = new ArrayList();
    // Caracter almacenado (solo en hojas)
    private char simbolo = ' ';
    // Caminito del nodo en el arbol
    private String codigo = "";
    // Id del nodo y frecuencia
    private int id = 0, freq = 0;
    
    // Constructores
    
    public Nodo() {
        listaAdy.add(0, null); // Hijo izquierdo
        listaAdy.add(1, null); // Hijo derecha
    }
    
    public Nodo(int id, int freq, char simbolo) {
        listaAdy.add(0, null); // Hijo izquierdo
        listaAdy.add(1, null); // Hijo derecha
        this.id = id;
        this.freq = freq;
        this.simbolo = simbolo;
    }
    
    // Getters y Setters
    
    public ArrayList getListaAdy() {
        return listaAdy;
    }

    public void setListaAdy(ArrayList listaAdy) {
        this.listaAdy = listaAdy;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    // Metodos de administracion
    
    public Nodo hijo_Izq() {
        return listaAdy.get(0);
    }
    
    public Nodo hijo_Der() {
        return listaAdy.get(1);
    }
    
    public boolean esHoja() {
        return listaAdy.get(0) == null && listaAdy.get(1) == null;
    }
    
    public void aumentarFreq() { freq++; }

    @Override
    public String toString() {
        return "\nSimbolo: " + simbolo + ", id: " + id + ", freq: " + freq + ", codigo: " + codigo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.simbolo != ((Nodo)obj).getSimbolo()) {
            return false;
        }
        return true;
    }
    
    public void actualizarCodigo(String cad) {
        codigo += cad;
    }
    
}
