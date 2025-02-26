package kata.supermarket.discountcalculators;

import kata.supermarket.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.reducing;

public class BuyOneGetOneFreeDiscountCalculator implements DiscountCalculator{

    public BigDecimal calculateDiscount(List<Item> basket) {
        BigDecimal discountAmount = BigDecimal.ZERO.setScale(2);

        if (basket.size() == 0) {
            return discountAmount;
        }

        Map<String, Integer> statisticsForThisBasket = getBasketStats(basket);
        Map<String, BigDecimal> priceTableForThisBasket = new HashMap<>();

        populatePriceBasket(basket, priceTableForThisBasket);

        return getDiscountAmount(discountAmount, statisticsForThisBasket, priceTableForThisBasket);
    }

    private BigDecimal getDiscountAmount(BigDecimal discountAmount, Map<String, Integer> basketStats, Map<String, BigDecimal> priceTableForThisBasket) {
        for (Map.Entry<String, BigDecimal> entry : priceTableForThisBasket.entrySet()) {
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

    private void populatePriceBasket(List<Item> basket, Map<String, BigDecimal> priceTableForThisBasket) {
        for (Item item : basket) {
            if (!priceTableForThisBasket.containsKey(item.id())) {
                priceTableForThisBasket.put(item.id(), item.price());
            }
        }
    }

    private Map<String, Integer> getBasketStats(List<Item> basket) {
        return basket.stream()
                .map(Item::id)
                .collect(Collectors.groupingBy(element -> element, reducing(0, element -> 1, Integer::sum)));
    }
}
