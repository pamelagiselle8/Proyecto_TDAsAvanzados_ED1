
package ed1_proyectotdasavanzados;

import java.io.*;
import java.util.*;

public class Main {
    static Scanner lea = new Scanner(System.in);
    
    public static void main(String[] args) {
        String opcion = menuPrincipal(), subOpcion = "", txt = "";
        ArrayList<Nodo> caracteres = new ArrayList();
        
        
        while (!opcion.equals("3")) {
            switch (opcion) {
                case "1": {
                    // Algoritmos sobre Arboles
                    subOpcion = menuArboles();
                    while (!subOpcion.equals("3")) {
                        switch (subOpcion) {
                            case "1": {
                                // Codificador de Huffman
                                System.out.print("\nIngrese el nombre del archivo de texto a codificar: ");
                                // Contenido de un archivo de texto
                                txt = cargarArchivoTxt(lea.nextLine());
                                if (!txt.isEmpty()) {
                                    for (int i = 0; i < txt.length(); i++) {
                                        Nodo nodo = new Nodo(i, 1, txt.charAt(i));
                                        if (caracteres.contains(nodo))
                                            // Si está, incrementa su frecuencia
                                            caracteres.get(caracteres.indexOf(nodo)).aumentarFreq();
                                        else
                                            // Si no está, lo agrega
                                            caracteres.add(nodo);
                                    }
                                    // Ordenar la lista de menor a mayor frecuencia
                                    caracteres.sort(Comparator.comparing(Nodo::getFreq));
                                    try {
                                        // Crear el arbol binario de caracteres y frecuencias
                                        TDAArbol arbol = new TDAArbol((crearArbol(caracteres, caracteres)));
                                        // Imprimir el texto codificado
                                        System.out.println("\nCodigo de Huffman:\n" + arbol.codificar(txt)
                                                           + "\n\nDatos guardados exitosamente.");
                                        
                                    } catch (Exception e) {}
                                    caracteres.clear();
                                }
                                break;
                            }
                            case "2": {
                                // Decodificador de Huffman
                                System.out.print("\nIngrese el nombre del archivo de texto a decodificar: ");
                                // Leer codigo de Huffman de un archivo de texto
                                txt = cargarArchivoTxt(lea.nextLine());
                                if (!txt.isEmpty()) {
                                    System.out.print("Ingrese el nombre del archivo binario que contiene al arbol: ");
                                    // Leer arbol binario de un archivo binario
                                    TDAArbol arbol = cargarArchivoBin(lea.nextLine());
                                    if (arbol != null)
                                        System.out.println("\nTexto decodificado\n" + arbol.decodificar(txt, ""));
                                }
                                break;
                            }
                            case "3": {
                                // Regresar al Menu Principal
                                break;
                            }
                            default: {
                                System.out.println("\nIngrese una opcion valida.");
                                break;
                            }
                        }
                        subOpcion = menuArboles();
                    }
                    break;
                }
                case "2": {
                    // Algoritmos sobre Grafos
                    subOpcion = menuGrafos();
                    while (!subOpcion.equals("4")) {
                        switch (subOpcion) {
                            case "1": {
                                // Leer grafo de un archivo
                                break;
                            }
                            case "2": {
                                // Prim
                                break;
                            }
                            case "3": {
                                // Floyd
                                break;
                            }
                            case "4": {
                                // Regresar al Menu Principal
                                break;
                            }
                            default: {
                                System.out.println("\nIngrese una opcion valida.");
                                break;
                            }
                        }
                        subOpcion = menuGrafos();
                    }
                    break;
                }
                case "3": {
                    opcion = "3";
                    break;
                }
                default: {
                    System.out.println("\nIngrese una opcion valida.");
                    break;
                }
                
            }
            opcion = menuPrincipal();
        }
        
    }
    
    public static String menuPrincipal() {
        System.out.print("\nMenu Principal"
                + "\n  1. Algoritmos sobre Arboles"
                + "\n  2. Algoritmos sobre Grafos"
                + "\n  3. Salir"
                + "\nIngrese una opcion: ");
        return lea.nextLine();
    }

    public static String menuArboles() {
        System.out.print("\nAlgoritmos sobre Arboles"
                + "\n  1. Codificador de Huffman"
                + "\n  2. Decodificador de Huffman"
                + "\n  3. Regresar al Menu Principal"
                + "\nIngrese una opcion: ");
        return lea.nextLine();
    }

    public static String menuGrafos() {
        System.out.print("\nAlgoritmos sobre Grafos"
                + "\n  1. Leer grafo de un archivo"
                + "\n  2. Prim"
                + "\n  3. Floyd"
                + "\n  4. Regresar al Menu Principal"
                + "\nIngrese una opcion: ");
        return lea.nextLine();
    }
    
    // Crea un arbol a partir de dos nodos
    public static TDAArbol crea2(Nodo nodo1, Nodo nodo2) {
        Nodo raiz = new Nodo();
        raiz.setFreq(nodo1.getFreq() + nodo2.getFreq());
        raiz.getListaAdy().add(0, nodo1); // nodo1 es hijo izquierdo
        raiz.getListaAdy().add(1, nodo2); // nodo2 es hijo derecho
        TDAArbol arbol = new TDAArbol(raiz);
        return arbol;
    }
    
    //
    public static TDAArbol crea(Nodo nodo) {
         TDAArbol arbol = new TDAArbol();
         arbol.setRaiz(nodo);
         return arbol;
    }
    
    // Crea el arbol binario para el codigo Huffman
    public static Nodo crearArbol(ArrayList<Nodo> nodos, ArrayList<Nodo> originales) {
        switch (nodos.size()) {
            case 1:
                // Caso en que solo haya un nodo
                return nodos.get(0);
            default:
                // Caso en que haya mas de un nodo
                ArrayList<Nodo> raices = new ArrayList();
                int i = 0 ;
                while (nodos.size() > i+1) {
                    // Unir dos nodos en un arbol
                    raices.add(crea2(nodos.get(i), nodos.get(i+1)).getRaiz());
                    i+=2;
                }
                if (i == nodos.size()-1)
                    raices.add(nodos.get(nodos.size()-1));
                // Ordenar lista de raices
                raices.sort(Comparator.comparing(Nodo::getFreq));
                return crearArbol(raices, originales);
        }
    }
    
    // Metodos para cargar y leer archivos
    
    // Cargar contenido de un archivo de texto
    public static String cargarArchivoTxt(String path) {
        // Windows -> "C:\\Drive\\Prueba.txt"
        // Mac -> /Users/pame/Prueba.txt
        File archivo = new File(path);
        Scanner sc;
        String contenido = "";
        try {
            sc = new Scanner(archivo);
            while (sc.hasNextLine())
                contenido += sc.nextLine();
                if (sc.hasNextLine())
                    contenido += "\n";
            return contenido;
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo se encontro el archivo.\n");
            return "";
        }
    }
    
    // Cargar el arbol de un archivo binario
    public static TDAArbol cargarArchivoBin(String path) {
        TDAArbol arbol = null;
        try {
            File archivo = new File(path);
            if (archivo.exists()) {
                FileInputStream entrada = new FileInputStream(archivo);
                ObjectInputStream objeto = new ObjectInputStream(entrada);
                try {
                    arbol = (TDAArbol)objeto.readObject();
                } catch (EOFException e) {}
                objeto.close();
                entrada.close();
            }            
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("\nNo se encontro el archivo binario.\n");
        }
        return arbol;
    }
    
}