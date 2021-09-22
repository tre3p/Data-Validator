package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {
    private final List<BaseSchema> listOfSchemas = new ArrayList<>();
    private final List<String> listOfKeys = new ArrayList<>();

    @Override
    public final boolean isValid(Object o) {
        if (listOfSchemas.isEmpty()) {
            return super.isValid(o);
        }
        return shapeCheck(((HashMap<String, String>) o));
    }

    /**
     * @param map - casted 'o' to HashMap
     * @return boolean if shape is 'true'
     */
    private boolean shapeCheck(HashMap<String, String> map) {
        for (int i = 0; i < listOfSchemas.size(); i++) {
            BaseSchema schema = listOfSchemas.get(i);
            if (!schema.isValid(map.get(listOfKeys.get(i)))) {
                return false;
            }
        }
        return true;
    }

    public final void shape(Map<String, BaseSchema> map) {
        for (Map.Entry<String, BaseSchema> temp : map.entrySet()) {
            listOfKeys.add(temp.getKey());
            listOfSchemas.add(temp.getValue());
        }
    }

    public final void sizeof(int size) {
        Predicate<Object> sizeOfPredicate = p -> p instanceof Map && ((Map<?, ?>) p).size() >= size;
        super.addPredicate(sizeOfPredicate);
    }

    public final MapSchema required() {
        Predicate<Object> requiredPredicate = p -> p instanceof Map;
        super.addPredicate(requiredPredicate);
        super.setRequired();
        return this;
    }
}
