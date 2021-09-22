package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public class StringSchema extends BaseSchema {
    @Override
    public final boolean isValid(Object o) {
        return super.isValid(o);
    }

    /**
     * @param str
     * @return current instance.
     */

    public final StringSchema contains(String str) {
        Predicate<Object> containsPredicate = p -> p instanceof String && ((String) p).contains(str);
        super.addPredicate(containsPredicate);
        return this;
    }

    /**
     * @param min
     * @return current instance.
     */
    public final StringSchema minLength(int min) {
        Predicate<Object> minLengthPredicate = p -> p instanceof String && (((String) p).length() >= min);
        super.addPredicate(minLengthPredicate);
        return this;
    }

    /**
     * @return current instance.
     */

    public final StringSchema required() {
        Predicate<Object> requiredNullPredicate = p -> !Objects.equals(p, null);
        Predicate<Object> requiredEmptyPredicate = p -> p instanceof String && !((String) p).isEmpty();
        super.addPredicate(requiredEmptyPredicate);
        super.addPredicate(requiredNullPredicate);
        super.setRequired();
        return this;
    }
}
