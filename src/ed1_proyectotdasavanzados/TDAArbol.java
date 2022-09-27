
package ed1_proyectotdasavanzados;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;


public class TDAArbol implements Serializable {
    private Nodo raiz = new Nodo();

    // Constructores
    public TDAArbol() {}
    public TDAArbol(Nodo raiz) { this.raiz = raiz; }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }
    
    public int getFreqRaiz() {
        return raiz.getFreq();
    }
    
    // Codifica el texto leido de un archivo de texto y
    // guarda el codigo de Huffman y el arbol generado
    public String codificar(String texto) {
        codificarHojas(raiz, "");
        String codigo = "";
        for (int i = 0; i < texto.length(); i++) {
            // Reemplaza los caracteres del texto por los codigos correspondientes
            codigo += buscarCodigo(raiz, texto.charAt(i), "");
        }
        
        // Guardar codigo de Huffman en un archivo txt
        FileWriter fw = null;
        BufferedWriter bw = null;
        File archivoTexto = new File("./Codigo_Huffman.txt");
        try {
            fw = new FileWriter(archivoTexto, false);
            bw = new BufferedWriter(fw);
            bw.write(codigo);
            bw.flush();
        } catch (Exception ex) {}
        finally {
            try {
                bw.close();
                fw.close();
            } catch (Exception ex) {}
        }
        
        // Guardar el arbol en un archivo bin
        FileOutputStream fs = null;
        ObjectOutputStream bs = null;
        File archivoBinario = new File("./Arbol_Huffman.bin");
        try {
            fs = new FileOutputStream(archivoBinario);
            bs = new ObjectOutputStream(fs);
            bs.writeObject(this);
            bs.flush();
        } catch (Exception ex) {}
        finally {
            try {
                bs.close();
                fs.close();
            } catch (Exception ex) {}
        }
        // Retorna el texto codificado para imprimirlo
        return codigo;
    }
    
    // Genera un codigo para cada hoja del arbol
    public void codificarHojas(Nodo nodo, String codigo) {
        if (!nodo.esHoja()) {
            if (nodo.hijo_Izq() != null)
                codificarHojas(nodo.hijo_Izq(), codigo+"0");
            if (nodo.hijo_Der() != null)
                codificarHojas(nodo.hijo_Der(), codigo+"1");
        }
        else
            nodo.setCodigo(codigo);
    }
    
    // Retorna el codigo del caracter buscado
    public String buscarCodigo(Nodo nodo, char letra, String codigo) {
        if (!nodo.esHoja()) {
            if (nodo.hijo_Izq() != null)
                codigo = buscarCodigo(nodo.hijo_Izq(), letra, codigo);
            if (nodo.hijo_Der() != null)
                codigo = buscarCodigo(nodo.hijo_Der(), letra, codigo);
        }
        else
            if (nodo.getSimbolo() == letra)
                return nodo.getCodigo();
        return codigo;
    }
    
    // Decodifica texto a partir de
    // un arbol dado (archivo binario)
    // y un texto codificado (archivo txt)
    public String decodificar(String codigo, String texto) {
        while (codigo.length() > 0) {
            Nodo nodo = raiz;
            while (!nodo.esHoja()) {
                if (codigo.charAt(0) == '0')
                    nodo = nodo.hijo_Izq();
                else
                    nodo = nodo.hijo_Der();
                // Consumir 0 o 1
                codigo = codigo.substring(1);
            }
            texto += nodo.getSimbolo();
        }
        return texto;
    }

    @Override
    public boolean equals(Object obj) {
        if (!Objects.equals(this.raiz, ((TDAArbol)obj).raiz)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "\nFrecuencia de la raiz: " + raiz.getFreq();
    }
    
    
}
