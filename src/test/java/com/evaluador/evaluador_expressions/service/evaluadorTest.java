package com.evaluador.evaluador_expressions.service;


import com.evaluador.evaluador_expressions.Evaluador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class evaluadorTest {

    private Evaluador evaluador;

    @BeforeEach
    void setUp() {
        // Inicializa el objeto Evaluador antes de cada prueba
        evaluador = new Evaluador();
    }

    @Test
    void testEvaluarExpresionConOperacionSencilla() {
        // Prueba con una expresión simple
        String expresion = "3 + 5";
        double resultadoEsperado = 8.0;
        double resultado = evaluador.evaluarExpresion(expresion);
        assertEquals(resultadoEsperado, resultado, "El resultado de la expresión debe ser 8.0");
    }

    @Test
    void testEvaluarExpresionConVariables() {
        // Prueba con variables
        evaluador.crearVariable("x", 10.0);
        String expresion = "x + 5";
        double resultadoEsperado = 15.0;
        double resultado = evaluador.evaluarExpresion(expresion);
        assertEquals(resultadoEsperado, resultado, "El resultado de la expresión con la variable debe ser 15.0");
    }

    @Test
    void testEvaluarExpresionConOperacionConPrecedencia() {
        // Prueba con una expresión que contiene precedencia de operadores
        String expresion = "2 + 3 * 4";
        double resultadoEsperado = 14.0;
        double resultado = evaluador.evaluarExpresion(expresion);
        assertEquals(resultadoEsperado, resultado, "El resultado de la expresión con precedencia debe ser 14.0");
    }

    @Test
    void testEvaluarExpresionConError() {
        // Prueba con una expresión que debe causar un error
        String expresion = "10 / 0";
        assertThrows(ArithmeticException.class, () -> {
            evaluador.evaluarExpresion(expresion);
        }, "Debe lanzar una excepción por división por cero");
    }

    @Test
    void testEvaluarExpresionConParentesis() {
        // Prueba con una expresión que contiene paréntesis
        String expresion = "(3 + 2) * 4";
        double resultadoEsperado = 20.0;
        double resultado = evaluador.evaluarExpresion(expresion);
        assertEquals(resultadoEsperado, resultado, "El resultado de la expresión con paréntesis debe ser 20.0");
    }

}
