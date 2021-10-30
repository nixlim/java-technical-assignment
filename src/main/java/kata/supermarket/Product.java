package kata.supermarket;

import java.math.BigDecimal;

public class Product {

    private final BigDecimal pricePerUnit;

    private final String id;

    public Product(final BigDecimal pricePerUnit, final String id) {
        this.pricePerUnit = pricePerUnit;
        this.id = id;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }

    public String id() {
        return id;
    }
}
