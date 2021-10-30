package kata.supermarket;

import kata.supermarket.discountschemes.DiscountSchemes;

import java.math.BigDecimal;

public class Product {

    private final BigDecimal pricePerUnit;
    private final String id;
    private final DiscountSchemes discountScheme;

    public Product(final BigDecimal pricePerUnit, final String id, DiscountSchemes discountScheme) {
        this.pricePerUnit = pricePerUnit;
        this.id = id;
        this.discountScheme = discountScheme;
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

    public DiscountSchemes getDiscountScheme() {
        return discountScheme;
    }
}
