package kata.supermarket;

import kata.supermarket.discountschemes.DiscountSchemes;

import java.math.BigDecimal;

public interface Item {
    BigDecimal price();
    String id();
    DiscountSchemes getDiscountScheme();
}
