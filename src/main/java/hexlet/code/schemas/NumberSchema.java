package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {
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
        super.addPredicate(p -> p instanceof Integer);
        super.setRequired();
        return this;
    }
}
