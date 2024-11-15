package com.evaluador.evaluador_expressions;

import java.util.Stack;
import java.util.EmptyStackException;

public class PilaOperandos {
    private Stack<Integer> operandos;

    public PilaOperandos(){
        operandos = new Stack<>();
    }

    // agregamos un operando a la pila
    public void push(int operando) {
        operandos.push(operando);
    }

    //obtener y eliminar el operando arriba de la pila
    public int pop(){
        return operandos.pop();
    }

    // mostrar todos los operandos actuales en la pila
    public void mostrarOperandos() {
        System.out.println("Pila de Operandos :" + operandos);
    }
}
