package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {
    private final int positiveDigitForRange = 5;
    private final Validator validator = new Validator();
    private final StringSchema stringSchema = validator.string();

    @Test
    void unrequiredTest() {
        assertTrue(stringSchema.isValid(null));
    }

    @Test
    void requiredTest() {
        stringSchema.required();
        assertTrue(stringSchema.isValid("hexlet"));
    }

    @Test
    void requiredNullTest() {
        stringSchema.required();
        assertFalse(stringSchema.isValid(null));
    }

    @Test
    void containsTrueTest() {
        stringSchema.required();
        assertTrue(stringSchema.contains("what").isValid("what does the fox say"));
    }

    @Test
    void containsFalseTest() {
        stringSchema.required();
        assertFalse(stringSchema.contains("whatthe").isValid("what does the fox say"));
    }

    @Test
    void minLengthTrueTest() {
        stringSchema.required();
        assertTrue(stringSchema.minLength(positiveDigitForRange).isValid("onetwothree"));
    }

    @Test
    void minLengthFalseTest() {
        stringSchema.required();
        assertFalse(stringSchema.minLength(positiveDigitForRange).isValid("one"));
    }

    @Test
    void emptyStringFalseTest() {
        stringSchema.required();
        assertFalse(stringSchema.isValid(""));
    }
}
