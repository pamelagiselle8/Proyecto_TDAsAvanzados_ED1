
package ed1_proyectotdasavanzados;


public class Arista {
    // Peso o costo de la arista
    private int costo;
    // Vertices que conecta la arista
    private Vertice vertice1, vertice2;

    // Constructores
    public Arista() {}
    public Arista(Vertice vertice1, Vertice vertice2, int peso) {
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
        this.costo = peso;
    }
    
    // Getters y setters
    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
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
        return vertice1.toString()
                +","+ vertice2.toString()
                +","+ costo +"\n";
    }
    
}
