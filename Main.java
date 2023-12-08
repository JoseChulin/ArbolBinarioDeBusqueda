/** 
 * Descripción: Clase de ejemplo que demuestra el uso de un arbol binario de busqueda implementada en ABB.
 * En este ejemplo, se crean instancias de ABB y se realizan varias operaciones, como
 * inserciones, búsquedas, eliminaciones, recoridos en inorden, postorden y preorden y manipulaciones de nodos (hojas y ramas).
 * Clase recuperada y modificada de un ejercicio academico
 * Autores:  Martínez Chulin José Alexander
 * Fecha: 08/12/2023
 */ 

public class Main {
    public static void main(String[] args) {
        // Creamos el constructor
        ABB arbol = new ABB();
        // Insetamos los nodos al arbol
        arbol.insertarValor(5);
        arbol.insertarValor(3);
        arbol.insertarValor(7);
        arbol.insertarValor(11);
        arbol.insertarValor(10);
        arbol.insertarValor(18);
        arbol.insertarValor(84);
        arbol.insertarValor(4);
        arbol.insertarValor(16);
        arbol.insertarValor(2);
        arbol.insertarValor(9);
        arbol.insertarValor(6);
        arbol.insertarValor(64);
        // ocupamos el metodo insertarNodo para agregar un nuevo nodo de la raíz
        arbol.insertarNodo(1, arbol.regresaRaiz());

        // Imprimir el árbol de forma horizontal
        System.out.println(arbol.imprimirArbolHorizontal());

        // Buscar nodo con valor 84 y refegresamos el valor de su dato, su referencia de memoria, el dato de su padre
        // y la eferenia de memoria de su padre
        System.out.println(arbol.CadenaDelNodoBuscado(84));

        // Buscar nodo con valor 100 que no existe
        System.out.println(arbol.CadenaDelNodoBuscado(100));

        // Eliminar un nodo existente
        String resultadoEliminacion1 = arbol.eliminarNodo(3); 
        System.out.println(resultadoEliminacion1);

        // Imprimir el árbol de forma horizontal para comprobar
        System.out.println(arbol.imprimirArbolHorizontal());

        // Eliminar un nodo no existente
        String resultadoEliminacion2 = arbol.eliminarNodo(14); 
        System.out.println(resultadoEliminacion2);

        // Imprime el recorrido en preorden en una cadena
        System.out.println(arbol.preorden());

        // Imprime el recorrido en inorden en una cadena
        System.out.println(arbol.inorden());

        // Imprime el recorrido en postorden en una cadena
        System.out.println(arbol.postorden());
    }
}
