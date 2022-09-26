
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
    
    // Buscar el mas chiki
    // Si esta visitado busca el siguiente mas chiki
    // Marcar visitados los dos vertices
    // Buscar los visitados en la matriz de adyacencia
    public void matrizAdyacencia(boolean dirigido) {
        int size = vertices.size()+1;
        String adyacencia[][] = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                adyacencia[i][j] = " ";
                if (i == 0 && j != 0)
                    adyacencia[i][j] = vertices.get(j-1).getEtiqueta();
                if (j == 0 && i != 0)
                    adyacencia[i][j] = vertices.get(i-1).getEtiqueta();
                
            }
        }
        for (int i = 0; i < aristas.size(); i++) {
            int v1 = 0, v2 = 0;
            for (int j = 0; j < adyacencia.length; j++) {
                if (aristas.get(i).getVertice1().getEtiqueta().equals(adyacencia[0][j])) {
                    v1 = j;
                }
                if (aristas.get(i).getVertice2().getEtiqueta().equals(adyacencia[0][j])) {
                    v2 = j;
                }
            }
            adyacencia[v1][v2] = Integer.toString(aristas.get(i).getCosto());
            if (!dirigido)
                adyacencia[v2][v1] = Integer.toString(aristas.get(i).getCosto());
        }
    }
    
    
    public Object[][] visitados() {
        int size = vertices.size();
        Object visitado[][] = new Object[size][2];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 2; j++) {
                visitado[i][j] = j == 0 ? vertices.get(i) : false;
            }
        }
        return visitado;
    }
}
