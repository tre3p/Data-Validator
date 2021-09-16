package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    private final Validator validator = new Validator();

    @Test
    void stringSchemaInstanceTest() {
        StringSchema stringSchema = validator.string();
        assertTrue(stringSchema instanceof StringSchema);
    }

    @Test
    void numberSchemaInstanceTest() {
        NumberSchema numberSchema = validator.number();
        assertTrue(numberSchema instanceof NumberSchema);
    }

    @Test
    void mapSchemaInstanceTest() {
        MapSchema mapSchema = validator.map();
        assertTrue(mapSchema instanceof MapSchema);
    }
}
