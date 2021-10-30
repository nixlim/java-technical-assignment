package kata.supermarket.integrationtests;

import kata.supermarket.Basket;
import kata.supermarket.Item;
import kata.supermarket.WeighedProduct;
import kata.supermarket.discountcalculators.BuyOneGetOneFreeDiscountCalculator;
import kata.supermarket.discountcalculators.DiscountCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static kata.supermarket.Helper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BuyOneGetOneFreeBasketIntegrationTest {

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValueWithCorrectBuyOneGetOneFreeDiscountCalculation(String description, String expectedTotal, Iterable<Item> items) {
        DiscountCalculator discountCalculator = new BuyOneGetOneFreeDiscountCalculator();
        final Basket basket = new Basket(discountCalculator);
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValueWithCorrectBuyOneGetOneFreeDiscountCalculation() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight(),
                buyOneGetOneFreeItemPricedPerUnitTwoSameItems(),
                buyOneGetOneFreeItemPricedPerUnitThreeSameItems(),
                buyOneGetOneFreeItemPricedPerUnitFiveSameItems()
        );
    }

    private static Arguments buyOneGetOneFreeItemPricedPerUnitTwoSameItems() {
        return Arguments.of("buy one get one free - two identical items", "1.00",
                Arrays.asList(aBagOfWidgets(), aBagOfWidgets()));
    }

    private static Arguments buyOneGetOneFreeItemPricedPerUnitThreeSameItems() {
        return Arguments.of("buy one get one free - three identical items", "2.00",
                Arrays.asList(aBagOfWidgets(), aBagOfWidgets(), aBagOfWidgets()));
    }

    private static Arguments buyOneGetOneFreeItemPricedPerUnitFiveSameItems() {
        return Arguments.of("buy one get one free - five identical items", "3.00",
                Arrays.asList(aBagOfWidgets(), aBagOfWidgets(), aBagOfWidgets(), aBagOfWidgets(), aBagOfWidgets()));
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25",
                Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(new BigDecimal("4.99"), "4");
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(new BigDecimal("2.99"), "5");
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }
}