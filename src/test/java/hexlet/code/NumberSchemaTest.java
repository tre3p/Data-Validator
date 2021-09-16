package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {
    private final int positiveDigitForRange = 5;
    private final int positiveDigit = 10;
    private final int negativeDigit = -10;
    private final Validator validator = new Validator();
    private final NumberSchema numberSchema = validator.number();

    @Test
    void requiredIntegerFalseTest() {
        numberSchema.required();
        assertFalse(numberSchema.isValid(null));
    }

    @Test
    void requiredIntegerTrueTest() {
        numberSchema.required();
        assertTrue(numberSchema.isValid(positiveDigit));
    }

    @Test
    void positiveIntegerTrueTest() {
        numberSchema.required();
        assertTrue(numberSchema.positive().isValid(positiveDigit));
    }

    @Test
    void positiveIntegerFalseTest() {
        numberSchema.required();
        assertFalse(numberSchema.positive().isValid(negativeDigit));
    }

    @Test
    void rangeTrueTest() {
        numberSchema.required();
        numberSchema.range(positiveDigitForRange, positiveDigit);
        assertTrue(numberSchema.isValid(positiveDigitForRange));
    }

    @Test
    void rangeFalseTest() {
        numberSchema.required();
        numberSchema.range(positiveDigitForRange, positiveDigit);
        assertFalse(numberSchema.isValid(positiveDigitForRange - 1));
    }

    @Test
    void rangeClassCastFalseTest() {
        numberSchema.required();
        numberSchema.range(positiveDigitForRange, positiveDigit);
        assertFalse(numberSchema.isValid("positiveDigitForRange"));
    }

    @Test
    void positiveClassCastTest() {
        numberSchema.required().positive();
        assertFalse(numberSchema.isValid("positiveDigitForRange"));
    }

    @Test
    void nullPositiveTest() {
        numberSchema.positive();
        assertTrue(numberSchema.isValid(null));
    }
}
