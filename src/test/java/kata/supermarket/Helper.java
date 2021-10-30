package kata.supermarket;

import kata.supermarket.discountschemes.DiscountSchemes;

import java.math.BigDecimal;

public class Helper {

    public static Item aBagOfWidgets() {
        return new Product(new BigDecimal("1.00"), "3", DiscountSchemes.BOGOF).oneOf();
    }

    public static Item aPintOfMilk() {
        return new Product(new BigDecimal("0.49"), "1", DiscountSchemes.NONE).oneOf();
    }

    public static Item aPackOfDigestives() {
        return new Product(new BigDecimal("1.55"), "2", DiscountSchemes.NONE).oneOf();
    }
}
