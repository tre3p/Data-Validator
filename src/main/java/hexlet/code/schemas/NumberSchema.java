package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {

    @Override
    public final boolean isValid(Object o) {
        return super.isValid(o);
    }

    public final NumberSchema positive() {
        Predicate<Object> positivePredicate = p -> p instanceof Integer && (((Integer) p) > 0);
        super.addPredicate(positivePredicate);
        return this;
    }

    public final void range(int firstDigit, int secondDigit) {
        Predicate<Object> rangePredicate = p -> p instanceof Integer
                && (((Integer) p) >= firstDigit)
                && (((Integer) p) <= secondDigit);
        super.addPredicate(rangePredicate);
    }

    public final NumberSchema required() {
        Predicate<Object> requiredNullPredicate = p -> !Objects.equals(p, null);
        super.addPredicate(requiredNullPredicate);
        super.setRequired();
        return this;
    }
}
