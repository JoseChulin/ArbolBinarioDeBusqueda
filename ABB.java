/**
 * Descripción:Clase que implementa un arbol binario de busqueda. 
 * Permite la manipulación de nodos en un arbol, incluyendo mostrar, eliminaciones,
 * búsquedas y operaciones de inorden, postorden y preorden. 
 * Esta clase proporciona dichos métodos
 * Clase recuperada y modificada de un ejercicio academico
 * Autores: Martínez Chulin José Alexander
 * Fecha: 08/12/2023
 */
public class ABB {
    private Nodo raiz;

    // Constructor: Inicializa la raíz como nula al crear una instancia de ABB
    ABB() {
        this.raiz = null;
    }

    // Verifica sí el árbol está vacío
    public boolean esVacio() {
        return (this.raiz == null);
    }

    // Devuelve la raíz del árbol
    public Nodo regresaRaiz() {  //getRaiz -- getter 
        return this.raiz;
    }

    // Método para insertar un valor en el árbol, sin la necesidad de colocar el valor del nodo
    // de referencia desde el main
    public void insertarValor(int valor) {
        // Llama al método insertarNodo con la raíz del árbol y la guardamos el nodo en la raíz
        this.raiz = insertarNodo(valor, regresaRaiz());
    }

    // Método insertarNodo para insertar un nodo en el árbol
    public Nodo insertarNodo(int valor, Nodo nodoRef) {
        // Sí el nodo es nulo, se crea un nuevo nodo con el valor proporcionado
        if (nodoRef == null) {
            Nodo nuevoNodo = new Nodo();  // Se crea el nodo
            nuevoNodo.dato = valor;       // Se le asigna el valor a su atributo dato
            nuevoNodo.izquierdo = null;   // Sus hojas las hacemos nulas 
            nuevoNodo.derecho = null;
            return nuevoNodo;             // Retornamos el nodo
        }
        // Sí el valor es menor o igual, recursivamente inserta en el subárbol izquierdo
        else if (valor <= nodoRef.dato) {
            nodoRef.izquierdo = insertarNodo(valor, nodoRef.izquierdo); // Colocamos el valor del nodo en la hoja izquierda
        }
        // Sí el valor es mayor, recursivamente inserta en el subárbol derecho
        else if (valor > nodoRef.dato) {
            nodoRef.derecho = insertarNodo(valor, nodoRef.derecho); // Colocamos el valor del nodo en la hoja derecha
        }
        // Devuelve el nodo actualizado
        return nodoRef;
    }

    // Método para imprimir el árbol de forma horizontal
    public String imprimirArbolHorizontal() { 
        // Se inicializa un StringBuilder para almacenar la representación del árbol
        StringBuilder resultado = new StringBuilder();
        // Se llama al método para construir la representación del árbol
        imprimirHorizontal(raiz, 0, resultado);
        // Convierte el StringBuilder a String y lo devuelve
        return resultado.toString();
    }

    // Método para construir la representación horizontal del árbol
    public void imprimirHorizontal(Nodo nodo, int nivel, StringBuilder resultado) {
        // Verifica sí el nodo actual no es nulo
        if (nodo != null) {
            // Recursivamente imprime el subárbol derecho con un nivel aumentado
            imprimirHorizontal(nodo.derecho, nivel + 1, resultado);

            // Agrega espacios según el nivel para la indentación
            for (int i = 0; i < nivel; i++) {
                resultado.append("    ");
            }

            // Agrega el valor del nodo seguido de un salto de línea
            resultado.append(nodo.dato).append("\n");

            // Recursivamente imprime el subárbol izquierdo con un nivel aumentado
            imprimirHorizontal(nodo.izquierdo, nivel + 1, resultado);
        }
    }

    // Método para buscar un nodo y obtener su referencia, el valor y el valor y la referencia a su padre
    public String CadenaDelNodoBuscado(int valor) {
        // Inicializa una instancia de ResultadoBusqueda
        ResultadoBusqueda resultado = buscarNodo(regresaRaiz(), valor, null);

        // Verifica sí se encontró el nodo
        if (resultado != null && resultado.nodoEncontrado != null) {
            // Nodo encontrado
            Nodo padre = resultado.padre != null ? resultado.padre : resultado.nodoEncontrado;
            return "El nodo buscado: " + resultado.nodoEncontrado.dato +
            ", Su referencia: " + resultado.nodoEncontrado +
            ", Su padre: " + padre.dato + " y la referencia del padre (" + padre + ")";
        } else {
            // Nodo no encontrado
            return "No existe el nodo con valor " + valor;
        }
    }

    // Creamos una clase interna estática como auxiliar para almacenar el resultado de la búsqueda
    public static class ResultadoBusqueda { // operación recate _(._.)_
        Nodo nodoEncontrado; 
        Nodo padre;
    }

    // Método que busca un nodo y su padre
    public ResultadoBusqueda buscarNodo(Nodo nodo, int valor, Nodo padre) {
        // Inicializa una instancia de ResultadoBusqueda
        ResultadoBusqueda resultado = new ResultadoBusqueda();

        // Verifica si el nodo actual es nulo
        if (nodo == null) {
            // Nodo no encontrado
            resultado.nodoEncontrado = null;
            resultado.padre = padre;
        } else if (valor == nodo.dato) {
            // Nodo encontrado
            resultado.nodoEncontrado = nodo;
            resultado.padre = padre;
        } else if (valor < nodo.dato) {
            // Buscar en el subárbol izquierdo
            resultado = buscarNodo(nodo.izquierdo, valor, nodo);
        } else {
            // Buscar en el subárbol derecho
            resultado = buscarNodo(nodo.derecho, valor, nodo);
        }

        // Devuelve el resultado de la búsqueda
        return resultado;
    }

    // Método para eliminar un nodo y confirmar su eliminación
    public String eliminarNodo(int valor) {
        ResultadoEliminacion resultado = eliminar(raiz, valor, null);
        if (resultado != null && resultado.nodoEliminado != null) {
            // Nodo encontrado y eliminado
            return "El nodo " + valor + " fue eliminado";
        } else {
            // Nodo no encontrado
            return "El nodo " + valor + " no puede ser eliminado porque no existe";
        }
    }

    // Clase interna estática auxiliar para almacenar el resultado de la eliminación
    public static class ResultadoEliminacion {
        Nodo nodoEliminado;
    }

    // Para eliminar un nodo
    public ResultadoEliminacion eliminar(Nodo nodo, int valor, Nodo padre) {
        ResultadoEliminacion resultado = new ResultadoEliminacion();
        if (nodo == null) {
            // Nodo no encontrado
            resultado.nodoEliminado = null;
        } else if (valor == nodo.dato) {
            // Nodo encontrado, realiza la eliminación
            resultado.nodoEliminado = nodo;
            realizarEliminacion(nodo, padre);
        } else if (valor < nodo.dato) {
            // Buscar en el subárbol izquierdo
            resultado = eliminar(nodo.izquierdo, valor, nodo);
        } else {
            // Buscar en el subárbol derecho
            resultado = eliminar(nodo.derecho, valor, nodo);
        }

        return resultado;
    }

    //Método para realizar la eliminación del nodo y reacomodar a sus hijos
    public void realizarEliminacion(Nodo nodo, Nodo padre) {
        // Caso 1: Nodo sin hijos
        if (nodo.izquierdo == null && nodo.derecho == null) {
            if (padre != null) {
                // Actualiza la referencia del padre
                if (padre.izquierdo == nodo) {
                    padre.izquierdo = null;
                } else {
                    padre.derecho = null;
                }
            } else {
                // Nodo raíz
                this.raiz = null;
            }
        }
        // Caso 2: Nodo con un hijo, ya sea en la derecha o en la izquierda
        else if (nodo.izquierdo == null || nodo.derecho == null) {
            Nodo hijo = (nodo.izquierdo != null) ? nodo.izquierdo : nodo.derecho;
            if (padre != null) {
                // Actualiza la referencia del padre
                if (padre.izquierdo == nodo) {
                    padre.izquierdo = hijo;
                } else {
                    padre.derecho = hijo;
                }
            } else {
                // Nodo raíz
                this.raiz = hijo;
            }
        }
        // Caso 3: Nodo con dos hijos
        else {
            // Encuentra el sucesor inmediato (nodo más a la izquierda en el subárbol derecho)
            Nodo sucesor = encontrarSucesor(nodo.derecho);

            // Copia el valor del sucesor al nodo actual
            nodo.dato = sucesor.dato;

            // Elimina el sucesor (que ahora se encuentra en el nodo actual)
            eliminar(nodo.derecho, sucesor.dato, nodo);
        }
    }

    // Método para encontrar el sucesor inmediato de un nodo
    public Nodo encontrarSucesor(Nodo nodo) {
        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }
        return nodo;
    }

    // Método para hacer la cadena del recorrido en preorden del árbol
    public String preorden() {
        // Inicializamos un StringBuilder para almacenar el resultado del recorrido
        StringBuilder resultado = new StringBuilder();
        
        // Se llama al método para realizar el recorrido en preorden, comenzando desde la raíz del árbol
        realizarPreorden(regresaRaiz(), resultado);
        
        // Se retorna una cadena
        return "Preorden: " + resultado.toString();

    }

    // Método para realizar el recorrido en preorden
    public void realizarPreorden(Nodo nodo, StringBuilder resultado) {
        if (nodo != null) {
            // Visitar el nodo actual
            resultado.append(nodo.dato).append(", ");

            // Recorrer el subárbol izquierdo
            realizarPreorden(nodo.izquierdo, resultado);

            // Recorrer el subárbol derecho
            realizarPreorden(nodo.derecho, resultado);
        }
    }

    // Método para realizar la cadena del recorrido en inorden del árbol
    public String inorden() {
        // Inicializamos un StringBuilder para almacenar el resultado del recorrido
        StringBuilder resultado = new StringBuilder();
        
        // Se llama al método para realizar el recorrido en inorden, comenzando desde la raíz del árbol
        realizarInorden(regresaRaiz(), resultado);
        
        // Se retorna una cadena
        return "Inorden: " + resultado.toString();
    }

    // Método para realizar el recorrido en inorden
    public void realizarInorden(Nodo nodo, StringBuilder resultado) {
        if (nodo != null) {
            // Recorrer el subárbol izquierdo
            realizarInorden(nodo.izquierdo, resultado);

            // Visitar el nodo actual
            resultado.append(nodo.dato).append(", ");

            // Recorrer el subárbol derecho
            realizarInorden(nodo.derecho, resultado);
        }
    }

    // Método para realizar la cadena del recorrido en postorden del árbol
    public String postorden() {
        // Inicializamos un StringBuilder para almacenar el resultado del recorrido
        StringBuilder resultado = new StringBuilder();
        
        // Se llama al método para realizar el recorrido en postorden, comenzando desde la raíz del árbol
        realizarPostorden(regresaRaiz(), resultado);
        
        // Se retorna una cadena
        return "Postorden: " + resultado.toString();
    }

    // Método para realizar el recorrido en postorden
    public void realizarPostorden(Nodo nodo, StringBuilder resultado) {
        if (nodo != null) {
            // Recorrer el subárbol izquierdo
            realizarPostorden(nodo.izquierdo, resultado);

            // Recorrer el subárbol derecho
            realizarPostorden(nodo.derecho, resultado);

            // Visitar el nodo actual
            resultado.append(nodo.dato).append(", ");
        }
    }
}
