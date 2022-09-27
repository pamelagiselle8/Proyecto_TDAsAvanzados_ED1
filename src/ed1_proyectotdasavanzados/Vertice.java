
package ed1_proyectotdasavanzados;

import java.util.Objects;


public class Vertice {
    // Etiqueta del vertice
    private String etiqueta;

    // Constructores
    public Vertice() {}
    public Vertice(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    // Getters y setters
    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
    
    // Metodos de administracion
    @Override
    public String toString() {
        return etiqueta;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Vertice)obj).getEtiqueta().equals(etiqueta);
    }
    
}