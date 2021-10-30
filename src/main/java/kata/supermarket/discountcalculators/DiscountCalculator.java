package kata.supermarket.discountcalculators;

import kata.supermarket.Item;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountCalculator {
    BigDecimal calculateDiscount(List<Item> basket);
}
