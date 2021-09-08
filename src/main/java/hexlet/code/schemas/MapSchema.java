package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema {
    private int sizeOfMap;
    private  List<BaseSchema> listOfSchemas = new ArrayList<>();
    private List<String> listOfKeys = new ArrayList<>();
    private boolean required = false;

    @Override
    public final boolean isValid(Object o) {
        if (listOfSchemas.isEmpty()) {
            if (isRequired()) {
                if (o == null) {
                    return super.isValid(o);
                }
                return ((HashMap<Object, Object>) o).size() >= sizeOfMap;
            }
        } else {
            return shapeCheck(((HashMap<String, String>) o));
        }
        return true;
    }

    /**
     * @param map - casted 'o' to HashMap
     * @return boolean if shape is 'true'
     */
    public boolean shapeCheck(HashMap<String, String> map) {
        for (int i = 0; i < listOfSchemas.size(); i++) {
            BaseSchema schema = listOfSchemas.get(i);
            if (!schema.isValid(map.get(listOfKeys.get(i)))) {
                return false;
            }
        }
        return true;
    }

    public final void sizeof(int size) {
        sizeOfMap = size;
    }

    public final void shape(Map<String, BaseSchema> map) {
        for (Map.Entry<String, BaseSchema> temp : map.entrySet()) {
            listOfKeys.add(temp.getKey());
            listOfSchemas.add(temp.getValue());
        }
    }

    public final MapSchema required() {
        required = true;
        return this;
    }

    /**
     * @return condition of boolean 'required'
     */
    public boolean isRequired() {
        return this.required;
    }
}
