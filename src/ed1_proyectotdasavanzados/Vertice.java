
package ed1_proyectotdasavanzados;


public class Vertice {
    // Valor almacenado
    private int valor;
    // Etiqueta del vertice
    private char etiqueta;

    // Constructores
    public Vertice() {}
    public Vertice(int valor, char etiqueta) {
        this.valor = valor;
        this.etiqueta = etiqueta;
    }

    // Getters y setters
    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public char getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(char etiqueta) {
        this.etiqueta = etiqueta;
    }
    
    // Metodos de administracion

    @Override
    public String toString() {
        return '{' + "valor " + valor + ", etiqueta " + etiqueta + '}';
    }
    
}
