
package ed1_proyectotdasavanzados;

import java.util.ArrayList;
import java.util.Comparator;

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
    
    // Encuentra el grafo que visite todos los vertices con el recorrido mas corto posible
    public TDAGrafo prim() {
        // Ordenar las aristas de menor a mayor costo
        aristas.sort(Comparator.comparing(Arista::getCosto));
        TDAGrafo grafo = new TDAGrafo();
        int i = 0;
        
        // Mientras queden vertices por visitar
        while (vertices.size() != grafo.vertices.size()) {
            boolean contieneV1 = false, contieneV2 = false;
            Vertice vertice1 = aristas.get(i).getVertice1(),
                    vertice2 = aristas.get(i).getVertice2();
            if (grafo.getVertices().isEmpty()) {
                // Agrega la primera arista (la mas corta) y sus dos vertices
                grafo.getVertices().add(vertice1);
                grafo.getVertices().add(vertice2);
                grafo.getAristas().add(aristas.get(i));
                i = 0;
            }
            else {
                contieneV1 = grafo.getVertices().contains(vertice1);
                contieneV2 = grafo.getVertices().contains(vertice2);
                // Se agrega el nuevo vertice visitado
                if (contieneV1 && !contieneV2) {
                    grafo.getVertices().add(vertice2);
                    grafo.getAristas().add(aristas.get(i));
                    i = 0;
                }
                else if (!contieneV1 && contieneV2) {
                    grafo.getVertices().add(vertice1);
                    grafo.getAristas().add(aristas.get(i));
                    i = 0;
                }
                else {
                    // Si ambos vertices de la actual arista ya fueron visitados,
                    // evaluara la siguiente arista mas corta.
                    // Esto para evitar ciclos
                    i++;
                }
            }
        }
        return grafo;
    }
    
    public String[][] matrizAdyacencia(boolean dirigido) {
        int size = vertices.size()+1;
        String adyacencia[][] = new String[size][size];
        // Crear los indices con las etiquetas
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                adyacencia[i][j] = "-";
                if (i == 0 && j != 0)
                    adyacencia[i][j] = vertices.get(j-1).getEtiqueta();
                if (j == 0 && i != 0)
                    adyacencia[i][j] = vertices.get(i-1).getEtiqueta();
            }
        }
        // Rellenar la matriz con los costos de las aristas
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
            // Si el grafo es dirigido, entonces a -> b != a <- b
             if (!dirigido)
                 adyacencia[v2][v1] = Integer.toString(aristas.get(i).getCosto());
        }
        return adyacencia;
    }
    
    public String[][] floyd() {
        String adyacencia[][] = matrizAdyacencia(true), infinito = "-";
        // Se debe repetir el algoritmo para evaluar con los nuevos
        // valores que se vayan sustituyendo en la matriz
        for (int n = 0; n < adyacencia.length/2; n++) {
            for (int i = 1; i < adyacencia.length; i++) {
                for (int j = 1; j < adyacencia.length; j++) {
                    // Evaluar cada columna con fila
                    for (int k = 1; k < adyacencia.length; k++) {
                        if (!adyacencia[i][j].equals(infinito)
                                && !adyacencia[j][k].equals(infinito)
                                && i != k && i != j && j != k) {
                            // Si los valores a comparar no son infinito
                            try {
                                int suma = Integer.parseInt(adyacencia[i][j])
                                       +Integer.parseInt(adyacencia[j][k]);
                                if (adyacencia[i][k].equals(infinito)) {
                                    // Si el valor actual es infinito,
                                    // la suma siempre sera menor
                                    adyacencia[i][k] = Integer.toString(suma);
                                }
                                else if (suma < Integer.parseInt(adyacencia[i][k])) {
                                    // Si el valor actual es menor a la suma
                                    adyacencia[i][k] = Integer.toString(suma);
                                }
                            } catch (Exception e) {}
                        }
                    }
                }
            }
        }
        return adyacencia;
    }
    
}
