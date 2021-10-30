package kata.supermarket;

import java.math.BigDecimal;

public class WeighedProduct {

    private final String id;

    private final BigDecimal pricePerKilo;

    public WeighedProduct(final BigDecimal pricePerKilo, final String id) {
        this.pricePerKilo = pricePerKilo;
        this.id = id;
    }

    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }

    String id() {
        return id;
    }


}
