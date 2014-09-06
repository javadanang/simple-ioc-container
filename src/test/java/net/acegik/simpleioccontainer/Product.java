package net.acegik.simpleioccontainer;

import java.io.Serializable;

/**
 *
 * @author drupalex
 */
public class Product implements Serializable {

    private String label;
    private String sku;
    private int amount;
    private double price;

    public Product() {}

    public Product(String label, String sku, int amount, double price) {
        this.label = label;
        this.sku = sku;
        this.amount = amount;
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
