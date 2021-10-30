package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.reducing;

public class Basket {
    private final List<Item> items;

    public Basket() {
        this.items = new ArrayList<>();
    }

    public void add(final Item item) {
        this.items.add(item);
    }

    List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal total() {
        return new TotalCalculator().calculate();
    }

    private class TotalCalculator {
        private final List<Item> items;

        TotalCalculator() {
            this.items = items();
        }

        private BigDecimal subtotal() {
            return items.stream()
                    .map(Item::price)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        /**
         * TODO: This could be a good place to apply the results of the discount calculations. It is
         * not likely to be the best place to do those calculations. Think about how Basket could
         * interact with something which provides that functionality.
         */
        private BigDecimal discounts() {
            BigDecimal discountAmount = BigDecimal.ZERO;

            Map<String, Integer> basketStats = getBasketStats();
            Map<String, BigDecimal> priceTable = new HashMap<>();

            for (Item item : items) {
                if (!priceTable.containsKey(item.id())) {
                    priceTable.put(item.id(), item.price());
                }
            }
            for (Map.Entry<String, BigDecimal> entry : priceTable.entrySet()) {
                if (basketStats.containsKey(entry.getKey())) {
                    int howManyTimesToApplyDiscount =
                            basketStats.get(entry.getKey()) / 2;
                    BigDecimal amountOfDiscountForThisItem =
                            entry.getValue().multiply(new BigDecimal(howManyTimesToApplyDiscount));
                    discountAmount = discountAmount.add(amountOfDiscountForThisItem);
                }
            }

            return discountAmount;
        }

        private BigDecimal calculate() {
            return subtotal().subtract(discounts());
        }

        private Map<String, Integer> getBasketStats() {
            return items.stream()
                    .map(Item::id)
                    .collect(Collectors.groupingBy(element -> element, reducing(0, element -> 1, Integer::sum)));
        }
    }
}
