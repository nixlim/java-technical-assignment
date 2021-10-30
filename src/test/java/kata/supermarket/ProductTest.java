package kata.supermarket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    @Test
    void singleItemHasExpectedUnitPriceFromProduct() {
        final BigDecimal price = new BigDecimal("2.49");
        Assertions.assertEquals(price, new Product(price, "1").oneOf().price());
    }

    @Test
    void anItemHasExpectedIdNumberFromProduct() {
        final String id = "2";
        final BigDecimal price = new BigDecimal("1.55");
        assertEquals(id, new Product(price, id).oneOf().id());
    }

}