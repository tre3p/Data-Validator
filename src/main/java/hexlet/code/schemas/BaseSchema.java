package hexlet.code.schemas;


public class BaseSchema {
    /**
     * @param o
     * @return true/false if 'required' is true and o != 'null'
     */
    public boolean isValid(Object o) {
        return o != null;
    }
}
