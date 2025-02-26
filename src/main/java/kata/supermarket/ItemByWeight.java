package kata.supermarket;

import kata.supermarket.discountschemes.DiscountSchemes;

import java.math.BigDecimal;

public class ItemByWeight implements Item {

    private final WeighedProduct product;
    private final BigDecimal weightInKilos;

    ItemByWeight(final WeighedProduct product, final BigDecimal weightInKilos) {
        this.product = product;
        this.weightInKilos = weightInKilos;
    }

    public BigDecimal price() {
        return product.pricePerKilo().multiply(weightInKilos).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public String id() {
        return product.id();
    }

    @Override
    public DiscountSchemes getDiscountScheme() {
        return product.getDiscountScheme();
    }
}
