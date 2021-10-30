package kata.supermarket;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeighedProductTest {

    @ParameterizedTest
    @MethodSource
    void itemFromWeighedProductHasExpectedUnitPrice(String pricePerKilo, String weightInKilos, String expectedPrice) {
        final WeighedProduct weighedProduct = new WeighedProduct(new BigDecimal(pricePerKilo), "any");
        final Item weighedItem = weighedProduct.weighing(new BigDecimal(weightInKilos));
        assertEquals(new BigDecimal(expectedPrice), weighedItem.price());
    }

    @Test
    void anItemHasExpectedIdNumberFromWeighedProduct() {
        BigDecimal pricePerKilo = new BigDecimal("1");
        BigDecimal weightOfItem = new BigDecimal("0.25");
        String id = "any";

        WeighedProduct product = new WeighedProduct(pricePerKilo, id);
        Item weightedItem = product.weighing(weightOfItem);
        assertEquals(id, weightedItem.id());

    }

    static Stream<Arguments> itemFromWeighedProductHasExpectedUnitPrice() {
        return Stream.of(
                Arguments.of("100.00", "1.00", "100.00"),
                Arguments.of("100.00", "0.33333", "33.33"),
                Arguments.of("100.00", "0.33335", "33.34"),
                Arguments.of("100.00", "0", "0.00")
        );
    }

}