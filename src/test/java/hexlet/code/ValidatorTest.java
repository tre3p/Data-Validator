package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ValidatorTest {
    private final Validator validator = new Validator();
    private final StringSchema stringSchema = validator.string();
    private final NumberSchema numberSchema = validator.number();
    private final MapSchema mapSchema = validator.map();
    private final int positiveDigitForRange = 5;
    private final int positiveDigit = 10;
    private final int negativeDigit = -10;
    private final MapSchema mapShapeSchema = mapSchemaInit();

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
        stringSchema.contains(""); // discard 'contains' value to make tests work
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
    void nonRequiredMapTest() {
        assertTrue(mapSchema.isValid(null));
    }

    @Test
    void requiredMapNullTest() {
        mapSchema.required();
        assertFalse(mapSchema.isValid(null));
    }


    @Test
    void requiredMapTest() {
        mapSchema.required();
        assertTrue(mapSchema.isValid(new HashMap<>()));
    }

    @Test
    void requiredSizeMapTest() {
        Map<String, String> test = new HashMap<>(Map.of("data1", "data2",
                "data3", "data4"));
        mapSchema.required();
        mapSchema.sizeof(2);
        assertTrue(mapSchema.isValid(test));
    }

    @Test
    void requiredSizeEmptyMapTest() {
        Map<String, String> test = new HashMap<>(Map.of("1", "2"));
        mapSchema.required();
        mapSchema.sizeof(positiveDigitForRange);
        assertFalse(mapSchema.isValid(test));
    }

    @Test
    void mapShapeTrueTest() {
        final int ageForLint = 100;
        Map<String, Object> human = new HashMap<>();
        human.put("name", "Kolya");
        human.put("age", ageForLint);
        assertTrue(mapShapeSchema.isValid(human));
    }

    @Test
    void mapNullTest() {
        Map<String, Object> human = new HashMap<>();
        human.put("name", "Maya");
        human.put("age", null);
        assertTrue(mapShapeSchema.isValid(human));
    }

    @Test
    void mapShapeFalseTest() {
        Map<String, Object> human = new HashMap<>();
        human.put("name", "");
        human.put("age", null);
        assertFalse(mapShapeSchema.isValid(human));
    }

    @Test
    void mapShapeNegativeDigitFalseTest() {
        final int ageForLint = -5;
        Map<String, Object> human = new HashMap<>();
        human.put("name", "Valya");
        human.put("age", ageForLint);
        assertFalse(mapShapeSchema.isValid(human));
    }

    @Test
    void mapShapeContainsTest() {

    }

    private MapSchema mapSchemaInit() {
        MapSchema shapeMapSchema = validator.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().positive());
        shapeMapSchema.shape(schemas);
        return shapeMapSchema;
    }
}
