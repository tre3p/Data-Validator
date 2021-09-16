package hexlet.code.schemas;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {
    private List<Predicate<Object>> predicateList = new ArrayList<>();
    private boolean required = false;
    /**
     * @param o
     * @return boolean depends on predicates condition
     */
    public boolean isValid(Object o) {
        if (!required && o == null) {
            return true;
        }
        for (Predicate<Object> p : predicateList) {
            if (!p.test(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * adding predicate to predicate list.
     * @param predicate
     */

    public void addPredicate(Predicate<Object> predicate) {
        predicateList.add(predicate);
    }

    /**
     * setting boolean 'required' as true.
     */

    public void setRequired() {
        required = true;
    }
}
