const variables = { Var1: 0 };
const operandStack = [];
const operatorStack = [];

// Agregar operadores y números a la expresión
function addToExpression(char) {
    document.getElementById("expression").value += char;
}

// Evaluar la expresión ingresada
document.getElementById("evaluate").addEventListener("click", () => {
    const expression = document.getElementById("expression").value;
    try {
        const result = evaluateExpression(expression);
        document.getElementById("operand-stack").textContent = operandStack.join(", ");
        document.getElementById("operator-stack").textContent = operatorStack.join(", ");
        document.getElementById("errors").value = "Resultado: " + result;
    } catch (error) {
        document.getElementById("errors").value = "Error: " + error.message;
    }
});

// Crear una nueva variable
document.getElementById("create-variable").addEventListener("click", () => {
    const varName = document.getElementById("new-variable").value;
    if (varName && !variables[varName]) {
        variables[varName] = 0;
        const option = document.createElement("option");
        option.value = varName;
        option.textContent = varName;
        document.getElementById("variables").appendChild(option);
    }
});

// Función para evaluar la expresión
function evaluateExpression(expression) {
    operandStack.length = 0; // Resetear pilas
    operatorStack.length = 0;

    // Procesar la expresión aquí, usando operandStack y operatorStack
    // (Implementa el algoritmo de evaluación según sea necesario)
    return "Funcionalidad no implementada completamente";
}