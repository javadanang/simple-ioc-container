package net.acegik.simpleioccontainer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author drupalex
 */
public class StoreRepository {

    public StoreRepository() {
        products.add(new Product("Asus Zenfone 5", "ASZF-105", 50, 200.0));
        products.add(new Product("Samsung Galaxy V", "SSGL-PV", 10, 110.0));
        products.add(new Product("Sony Xperia Z Ultra C6802", "SXZU-C6802", 100, 450.0));
        products.add(new Product("Sony Xperia Z Ultra C6802", "IPAD-MW16", 30, 350.0));
        products.add(new Product("HTC Desire 210 Dual SIM", "HTC-210", 10, 115.0));
    }

    private List<Product> products = new ArrayList<Product>();

    public List<Product> getProducts() {
        return products;
    }
}
