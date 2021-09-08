package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    private boolean positive = false;
    private int[] range = new int[2];
    private boolean isRange = false;
    private boolean required = false;

    @Override
    public final boolean isValid(Object o) {
        if (isRequired()) {
            if (o == null) {
                return super.isValid(o);
            }
            if (isRange && o instanceof Integer) {
                return (int) o >= range[0] && (int) o <= range[1];
            }
        }
        if (positive && o instanceof Integer) {
            return (int) o > 0;
        }
        return o instanceof Integer || o == null;
    }

    public final NumberSchema positive() {
        positive = true;
        return this;
    }

    public final void range(int firstDigit, int secondDigit) {
        range[0] = firstDigit;
        range[1] = secondDigit;
        isRange = true;
    }

    public final NumberSchema required() {
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
