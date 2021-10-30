package kata.supermarket.discountcalculators;

import kata.supermarket.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static kata.supermarket.Helper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BuyOneGetOneFreeDiscountCalculatorTest {

    @DisplayName("calculator correctly determines discount when supplied with ")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void calculatorCorrectlyDeterminesDiscount(String description, String expectedDiscount, List<Item> items){
        final BuyOneGetOneFreeDiscountCalculator calculator = new BuyOneGetOneFreeDiscountCalculator();
        assertEquals(new BigDecimal(expectedDiscount), calculator.calculateDiscount(items));
    }

    static Stream<Arguments> calculatorCorrectlyDeterminesDiscount() {
        return Stream.of(
                noItems(),
                multipleItemsPricedPerUnit(),
                twoIdenticalItems(),
                threeIdenticalItems(),
                fiveIdenticalItems()
        );
    }

    private static Arguments twoIdenticalItems() {
        return Arguments.of("two identical items", "1.00",
                Arrays.asList(aBagOfWidgets(), aBagOfWidgets()));
    }

    private static Arguments threeIdenticalItems() {
        return Arguments.of("three identical items", "1.00",
                Arrays.asList(aBagOfWidgets(), aBagOfWidgets(), aBagOfWidgets()));
    }

    private static Arguments fiveIdenticalItems() {
        return Arguments.of("five identical items", "2.00",
                Arrays.asList(aBagOfWidgets(), aBagOfWidgets(), aBagOfWidgets(), aBagOfWidgets(), aBagOfWidgets()));
    }

    public static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "0.00",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

}