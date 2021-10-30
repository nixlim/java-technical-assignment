package kata.supermarket.discountschemes;

import kata.supermarket.Item;
import kata.supermarket.discountcalculators.DiscountCalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DiscountStrategy {

    DiscountCalculator buyOneGetOneFreeDiscountCalculator;

    public DiscountStrategy(DiscountCalculator discountCalculator) {
        this.buyOneGetOneFreeDiscountCalculator = discountCalculator;
    }

    public BigDecimal getDiscountAmount(List<Item> basket) {

        BigDecimal totalDiscountForBasket = BigDecimal.ZERO.setScale(2);

        if (basket.size() == 0) {
            return totalDiscountForBasket;
        }

        List<Item> buyOneGetOneFreeDiscountItems = new ArrayList<>();

        for (Item item : basket) {
            switch (item.getDiscountScheme()) {
                case BOGOF:
                    buyOneGetOneFreeDiscountItems.add(item);
                    break;
                case NONE:
                    break;
            }
        }

        BigDecimal buyOneGetOneFreeDiscount = buyOneGetOneFreeDiscountCalculator.calculateDiscount(buyOneGetOneFreeDiscountItems);

        totalDiscountForBasket = totalDiscountForBasket.add(buyOneGetOneFreeDiscount);

        return totalDiscountForBasket;
    }


}
