

public class ModelElementRegistry extends com.gs.dmn.runtime.discovery.ModelElementRegistry {
    public ModelElementRegistry() {
        register("BooleanInput", "BooleanInput");
        register("DateAndTimeInput", "DateAndTimeInput");
        register("DateInput", "DateInput");
        register("Decision", "Decision");
        register("EnumerationInput", "EnumerationInput");
        register("NumberInput", "NumberInput");
        register("TextInput", "TextInput");
        register("TimeInput", "TimeInput");
    }
}