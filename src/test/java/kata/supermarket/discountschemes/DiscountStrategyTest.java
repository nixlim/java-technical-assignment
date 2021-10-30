package kata.supermarket.discountschemes;

import kata.supermarket.Item;
import kata.supermarket.discountcalculators.DiscountCalculator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static kata.supermarket.Helper.aBagOfWidgets;
import static kata.supermarket.Helper.aPackOfDigestives;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DiscountStrategyTest {

    @Test
    void calculatesDiscountAmountCorrectlyIfAllProductsBelongToNoneScheme(){
        DiscountCalculator discountCalculator = mock(DiscountCalculator.class);
        when(discountCalculator.calculateDiscount(any())).thenReturn(BigDecimal.ZERO.setScale(2));
        DiscountStrategy discountStrategy = new DiscountStrategy(discountCalculator);
        List<Item> basket = new ArrayList<>();
        basket.add(aPackOfDigestives());
        basket.add(aPackOfDigestives());
        basket.add(aPackOfDigestives());

        assertEquals(BigDecimal.ZERO.setScale(2), discountStrategy.getDiscountAmount(basket));
    }

    @Test
    void calculatesDiscountAmountCorrectlyIfAllProductsBelongToBogofScheme(){
        DiscountCalculator discountCalculator = mock(DiscountCalculator.class);
        when(discountCalculator.calculateDiscount(any())).thenReturn(BigDecimal.ONE.setScale(2));
        DiscountStrategy discountStrategy = new DiscountStrategy(discountCalculator);
        List<Item> basket = new ArrayList<>();
        basket.add(aBagOfWidgets());
        basket.add(aBagOfWidgets());

        assertEquals(new BigDecimal("1.00"), discountStrategy.getDiscountAmount(basket));
    }

    @Test
    void calculatesDiscountAmountCorrectlyIfProductsBelongToBogofAndNoneScheme(){
        DiscountCalculator discountCalculator = mock(DiscountCalculator.class);
        when(discountCalculator.calculateDiscount(any())).thenReturn(BigDecimal.ONE.setScale(2));
        DiscountStrategy discountStrategy = new DiscountStrategy(discountCalculator);
        List<Item> basket = new ArrayList<>();
        basket.add(aBagOfWidgets());
        basket.add(aBagOfWidgets());
        basket.add(aPackOfDigestives());
        basket.add(aPackOfDigestives());

        assertEquals(new BigDecimal("1.00"), discountStrategy.getDiscountAmount(basket));
    }

}