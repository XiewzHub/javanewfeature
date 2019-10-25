package com.example.demo1.testinterface;

import com.example.demo1.domain.Product;

/**
 * @author Xiewz
 */
public class ColorPredicate implements MyPredicate<Product>,TestInterf<Product>{

    @Override
    public boolean test(Product product) {
        return Product.RED.equals(product.getColor());
    }
}
