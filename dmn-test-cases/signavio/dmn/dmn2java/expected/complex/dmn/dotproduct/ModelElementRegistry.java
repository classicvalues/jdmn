
public class ModelElementRegistry extends com.gs.dmn.runtime.discovery.ModelElementRegistry {
    public ModelElementRegistry() {
        // Register elements from model 'DotProduct'
        register("A", "A");
        register("B", "B");
        register("CalculateDotProduct", "CalculateDotProduct");
        register("Componentwise", "Componentwise");
        register("Componentwise", "Componentwise4_iterator");
        register("DotProduct", "DotProduct");
        register("Product", "Product");
    }
}
