package com.evaluador.evaluador_expressions;

import java.util.Stack;

public class PilaOperadores {
    private Stack<Character> operadores;

    public PilaOperadores()  {
        operadores = new Stack<>();
    }

    // Agregar un operador a la pila
    public void push(char operador) {
        operadores.push(operador);
        System.out.println("Operador '" + operador + "' agregado a la pila de operadores.");
    }

    // Obtener y eliminar el operador que esté arriba de la pila
    public char pop() {
        char operador = operadores.pop();
        System.out.println("Operador '" + operador + "' eliminado de la pila de operadores.");
        return operador;
    }

    // Verificar si la pila está vacía
    public boolean isEmpty() {
        return operadores.isEmpty();
    }

    // Mostrar todos los operadores actuales en la pila
    public void mostrarOperadores() {
        System.out.println("Estado actual de la pila de Operadores: " + operadores);
    }
}
