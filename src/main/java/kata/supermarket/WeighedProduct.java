package kata.supermarket;

import kata.supermarket.discountschemes.DiscountSchemes;

import java.math.BigDecimal;

public class WeighedProduct {

    private final String id;

    private final BigDecimal pricePerKilo;
    private DiscountSchemes applicableDiscountScheme;

    public WeighedProduct(final BigDecimal pricePerKilo, final String id, DiscountSchemes discountScheme) {
        this.pricePerKilo = pricePerKilo;
        this.id = id;
        this.applicableDiscountScheme = discountScheme;
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

    DiscountSchemes getDiscountScheme() {
        return applicableDiscountScheme;
    }


}
