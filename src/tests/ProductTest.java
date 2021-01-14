package tests;

import entities.Product;
import managers.MainManager;
import managers.ProductManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest
{
    @Test
    void getNumberOfAvailableItems()
    {
        Product product = new Product("name", 0.99);
        ProductManager.getInstance().add(product);

        assertEquals(product.getItemListSize(), 0, "1.1");
        assertEquals(product.getNumberOfAvailableItems(), 0, "1.2");
        assertEquals(product.getNumberOfReservedItems(), 0, "1.3");

        MainManager.getInstance().addItems(product.getId(), 10);

        assertEquals(product.getItemListSize(), 10, "2.1");
        assertEquals(product.getNumberOfAvailableItems(), 10, "2.2");
        assertEquals(product.getNumberOfReservedItems(), 0, "2.3");

        product.reserveInCart();

        assertEquals(product.getItemListSize(), 10, "3.1");
        assertEquals(product.getNumberOfAvailableItems(), 9, "3.2");
        assertEquals(product.getNumberOfReservedItems(), 1, "3.3");
    }
}
