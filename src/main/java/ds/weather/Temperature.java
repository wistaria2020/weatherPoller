package ds.weather;

public class Temperature {
    // temperatures in both code and configuration are
    // in Celsius; but webservice provides them in Fahrenheit
    Double value;
    boolean known = false;

    public Temperature() {
    }

    public Temperature(Double celsius) {
        value = celsius;
        known = true;
    }

    public Double getValue() {
        return value;
    }

    public void setFahrenheit(Double fahrenheit) {
        setValue((fahrenheit -32)  / 1.8);
    }

    public String getFormatted(int digitsAfterDecimal) {
        return known ?
                String.format("%."+digitsAfterDecimal+"f", value) :
                "Unknown";
    }

    public void setValue(Double value) {
        this.value = value;
        known = (value != null);
    }

    public boolean isKnown() {
        return known;
    }
}
