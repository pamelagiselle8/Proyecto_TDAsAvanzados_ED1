
package ed1_proyectotdasavanzados;


public class Arista {
    // Peso o costo de la arista
    private int peso;
    // Vertices que conecta la arista
    private Vertice vertice1, vertice2;

    // Constructores
    public Arista() {}
    public Arista(int peso, Vertice vertice1, Vertice vertice2) {
        this.peso = peso;
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
    }
    
    // Getters y setters
    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Vertice getVertice1() {
        return vertice1;
    }

    public void setVertice1(Vertice vertice1) {
        this.vertice1 = vertice1;
    }

    public Vertice getVertice2() {
        return vertice2;
    }

    public void setVertice2(Vertice vertice2) {
        this.vertice2 = vertice2;
    }

    // Metodos de administracion
    @Override
    public String toString() {
        return "Arista {" + "peso " + peso + ", vertice1 "
                          + vertice1.toString() + ", vertice2 "
                          + vertice2.toString() + '}';
    }
    
}
