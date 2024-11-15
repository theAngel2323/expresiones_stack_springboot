package com.evaluador.evaluador_expressions;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

@Component
public class Evaluador {
    private Stack<Double> operandos = new Stack<>();
    private Stack<Character> operadores = new Stack<>();
    private Map<String, Double> variables = new HashMap<>();

    public Stack<Double> getOperandos() {
        return operandos;
    }

    public Stack<Character> getOperadores() {
        return operadores;
    }

    public void crearVariable(String nombre, Double valor) {
        if (nombre.length() <= 12 && nombre.matches("[a-zA-Z][a-zA-Z0-9]*")) {
            variables.put(nombre, valor);
        } else {
            throw new IllegalArgumentException("Nombre de variable inválido");
        }
    }

    public Double evaluarExpresion(String expresion) {
        for (int i = 0; i < expresion.length(); i++) {
            char simbolo = expresion.charAt(i);

            if (Character.isWhitespace(simbolo)) continue;

            if (Character.isLetter(simbolo)) {
                StringBuilder nombreVariable = new StringBuilder();
                while (i < expresion.length() && Character.isLetterOrDigit(expresion.charAt(i))) {
                    nombreVariable.append(expresion.charAt(i));
                    i++;
                }
                i--;
                String nombre = nombreVariable.toString();
                if (variables.containsKey(nombre)) {
                    operandos.push(variables.get(nombre));
                } else {
                    throw new IllegalArgumentException("Variable no definida: " + nombre);
                }
            } else if (Character.isDigit(simbolo) || simbolo == '.') {
                StringBuilder numero = new StringBuilder();
                while (i < expresion.length() && (Character.isDigit(expresion.charAt(i)) || expresion.charAt(i) == '.')) {
                    numero.append(expresion.charAt(i));
                    i++;
                }
                i--;
                operandos.push(Double.parseDouble(numero.toString()));
            } else if (simbolo == '@') {
                mostrarEstadoPilas(); // Llama al método para mostrar el estado de las pilas
            } else if (simbolo == '(') {
                operadores.push(simbolo);
            } else if (simbolo == ')') {
                while (!operadores.isEmpty() && operadores.peek() != '(') {
                    evaluarTopDePilas();
                }
                operadores.pop();
            } else if (esOperador(simbolo)) {
                while (!operadores.isEmpty() && precedencia(operadores.peek()) >= precedencia(simbolo)) {
                    evaluarTopDePilas();
                }
                operadores.push(simbolo);
            }
        }

        while (!operadores.isEmpty()) {
            evaluarTopDePilas();
        }

        return operandos.pop();
    }

    private void evaluarTopDePilas() {
        if (operadores.isEmpty() || operandos.size() < 2) return;

        char operador = operadores.pop();
        double b = operandos.pop();
        double a = operandos.pop();

        double resultado;
        switch (operador) {
            case '+': resultado = a + b; break;
            case '-': resultado = a - b; break;
            case '*': resultado = a * b; break;
            case '/':
                if (b == 0) throw new ArithmeticException("División por cero");
                resultado = a / b;
                break;
            case '^': resultado = Math.pow(a, b); break;
            case '%': resultado = a % b; break;
            default: throw new IllegalArgumentException("Operador no soportado: " + operador);
        }
        operandos.push(resultado);
    }

    private boolean esOperador(char simbolo) {
        return simbolo == '+' || simbolo == '-' || simbolo == '*' || simbolo == '/' || simbolo == '^' || simbolo == '%';
    }

    private int precedencia(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }

    // Método para mostrar el estado de las pilas de operandos y operadores
    private void mostrarEstadoPilas() {
        System.out.println("Estado actual de las pilas:");
        System.out.println("Operadores: " + operadores);
        System.out.println("Operandos: " + operandos);
    }

    public String mostrarPilas() {
        return "Operadores: " + operadores.toString() + "\nOperandos: " + operandos.toString();
    }

    public Map<String, Double> obtenerVariables() {
        return variables;
    }
}
