package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema {
    public final StringSchema contains(String str) {
        Predicate<Object> containsPredicate = p -> p instanceof String && ((String) p).contains(str);
        super.addPredicate(containsPredicate);
        return this;
    }

    public final StringSchema minLength(int min) {
        Predicate<Object> minLengthPredicate = p -> p instanceof String && (((String) p).length() >= min);
        super.addPredicate(minLengthPredicate);
        return this;
    }

    public final StringSchema required() {
        super.addPredicate(p -> p instanceof String && !((String) p).isEmpty());
        super.setRequired();
        return this;
    }
}
