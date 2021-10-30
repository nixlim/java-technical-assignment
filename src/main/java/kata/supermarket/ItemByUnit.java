package kata.supermarket;

import kata.supermarket.discountschemes.DiscountSchemes;

import java.math.BigDecimal;

public class ItemByUnit implements Item {

    private final Product product;

    ItemByUnit(final Product product) {
        this.product = product;
    }

    public BigDecimal price() {
        return product.pricePerUnit();
    }

    public String id() {
        return product.id();
    }

    @Override
    public DiscountSchemes getDiscountScheme() {
        return product.getDiscountScheme();
    }
}
