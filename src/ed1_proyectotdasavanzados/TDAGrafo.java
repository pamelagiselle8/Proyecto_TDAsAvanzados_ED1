
package ed1_proyectotdasavanzados;

import java.util.ArrayList;

public class TDAGrafo {
    // Lista de vertices del grafo
    private ArrayList<Vertice> vertices = new ArrayList();
    // Lista de aristas que conectan los vertices
    private ArrayList<Arista> aristas = new ArrayList();

    // Constructores
    public TDAGrafo() {}
    public TDAGrafo(ArrayList<Vertice> vertices, ArrayList<Arista> aristas) {
        this.vertices = vertices;
        this.aristas = aristas;
    }

    // Getters y setters
    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertice> vertices) {
        this.vertices = vertices;
    }

    public ArrayList<Arista> getAristas() {
        return aristas;
    }

    public void setAristas(ArrayList<Arista> aristas) {
        this.aristas = aristas;
    }

    // Metodos de administracion
    @Override
    public String toString() {
        return "\nVertices\n" + vertices.toString() +
               "\nAristas\n" + aristas.toString();
    }
}
