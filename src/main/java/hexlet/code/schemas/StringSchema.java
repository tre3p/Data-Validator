package hexlet.code.schemas;

import java.util.Objects;

public class StringSchema extends BaseSchema {
    private String substring = "";
    private int minLength = 0;
    private boolean required = false;

    @Override
    public final boolean isValid(Object o) {
        if (isRequired() && o instanceof String) {
            return requiredStringCheck(o.toString());
        }
        return !isRequired() || !Objects.equals(o, null);
    }

    private boolean requiredStringCheck(String str) {
        if (Objects.equals(str, null)) {
            return super.isValid(str);
        }
        if (str.length() <= 0) {
            return false;
        }
        if (!str.contains(substring)) {
            return false;
        }
        return str.length() >= minLength;
    }

    public final StringSchema contains(String str) {
        substring = str;
        return this;
    }

    public final StringSchema minLength(int min) {
        minLength = min;
        return this;
    }

    public final StringSchema required() {
        required = true;
        return this;
    }

    /**
     * @return condition of boolean 'required'
     */
    public boolean isRequired() {
        return this.required;
    }
}
