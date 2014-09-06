package net.acegik.simpleioccontainer;

import java.util.List;

/**
 *
 * @author drupalex
 */
public class StoreService {

    private StoreRepository repository;

    public void setRepository(StoreRepository storeRepository) {
        this.repository = storeRepository;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int countProducts() {
        int total = 0;
        List<Product> products = repository.getProducts();
        for(Product product:products) {
            total += product.getAmount();
        }
        return total;
    }

    public double totalAssets() {
        double assets = 0;
        List<Product> products = repository.getProducts();
        for(Product product:products) {
            assets += product.getPrice() * product.getAmount();
        }
        return assets;
    }
}
