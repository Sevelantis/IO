package tests;

import entities.Product;
import managers.ProductManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductManagerTest
{
    @Test
    void getInstance()
    {
        assertEquals(ProductManager.getInstance(), ProductManager.getInstance(), "1");
        ProductManager productManager = ProductManager.getInstance();
        assertEquals(ProductManager.getInstance(), productManager, "1");
    }

    @Test
    void add_getProduct()
    {
        Product product = new Product("name", 0.99);
        ProductManager.getInstance().add(product);
        int id = product.getId();
        assertEquals(ProductManager.getInstance().get(id), product, "1");
    }
}