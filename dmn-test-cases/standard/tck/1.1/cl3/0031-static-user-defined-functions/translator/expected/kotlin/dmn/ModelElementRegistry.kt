

class ModelElementRegistry : com.gs.dmn.runtime.discovery.ModelElementRegistry {
    constructor() {
        register("Circumference", "Circumference")
        register("'fn invocation complex parameters'", "FnInvocationComplexParameters")
        register("'fn invocation named parameters'", "FnInvocationNamedParameters")
        register("'fn invocation positional parameters'", "FnInvocationPositionalParameters")
        register("'fn library'", "FnLibrary")
        register("inputA", "InputA")
        register("inputB", "InputB")
    }
}