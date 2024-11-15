package com.evaluador.evaluador_expressions.controller;

import com.evaluador.evaluador_expressions.Evaluador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EvaluadorController {

    @Autowired
    private Evaluador evaluador;

    // Método GET para la página de inicio
    @GetMapping("/")
    public String index(Model model) {
        // Agregar el evaluador y las variables al modelo
        model.addAttribute("evaluador", evaluador);
        model.addAttribute("variables", evaluador.obtenerVariables());
        return "index";  // Redirige a la página principal
    }

    // Método POST para crear una variable
    @PostMapping("/crearVariable")
    public String crearVariable(@RequestParam("nombre") String nombre,
                                @RequestParam("valor") double valor,
                                Model model) {
        try {
            // Crear una nueva variable y actualizar el modelo con las variables
            evaluador.crearVariable(nombre, valor);
            model.addAttribute("variables", evaluador.obtenerVariables());
        } catch (IllegalArgumentException e) {
            // Manejo de error en caso de nombre de variable inválido
            model.addAttribute("error", "Error al crear variable: " + e.getMessage());
        }
        return "index";  // Volver a la página principal
    }

    // Método POST para evaluar una expresión
    @PostMapping("/evaluar")
    public ModelAndView evaluarExpresion(@RequestParam("expresion") String expresion) {
        ModelAndView mav = new ModelAndView("index");  // Vista 'index' para devolver
        try {
            // Verificar si la expresión es válida antes de evaluar
            if (expresion == null || expresion.trim().isEmpty()) {
                throw new IllegalArgumentException("La expresión no puede estar vacía.");
            }

            // Evaluar la expresión y agregar el resultado
            double resultado = evaluador.evaluarExpresion(expresion);
            mav.addObject("resultado", "Resultado: " + resultado);

            // Obtener las pilas de operadores y operandos para mostrar en la vista
            // Asegurarse de que las pilas no estén vacías antes de pasarlas al modelo
            mav.addObject("pilasOperadores", evaluador.getOperadores());
            mav.addObject("pilasOperandos", evaluador.getOperandos());

        } catch (IllegalArgumentException e) {
            // Si la expresión es inválida, mostrar un error adecuado
            mav.addObject("resultado", "Error: " + e.getMessage());
        } catch (Exception e) {
            // Si ocurre otro tipo de error, capturarlo y mostrar mensaje genérico
            mav.addObject("resultado", "Error inesperado: " + e.getMessage());
        }

        // Pasar las variables al modelo para su visualización
        mav.addObject("variables", evaluador.obtenerVariables());
        mav.addObject("evaluador", evaluador);
        return mav;  // Devolvemos el ModelAndView con todos los datos
    }
}
