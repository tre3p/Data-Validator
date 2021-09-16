package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {
    private final Validator validator = new Validator();
    private final MapSchema mapShapeSchema = mapSchemaInit();
    private final MapSchema mapSchema = validator.map();

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
        final int positiveDigitForRange = 5;
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

    private MapSchema mapSchemaInit() {
        MapSchema shapeMapSchema = validator.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().positive());
        shapeMapSchema.shape(schemas);
        return shapeMapSchema;
    }
}
