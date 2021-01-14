package tests;


import entities.Client;
import entities.Item;
import entities.Product;
import entities.Reservation;
import enums.Status;
import managers.MainManager;
import managers.ProductManager;
import managers.ReservationManager;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class Test1_AddReservation
{
    private Client client = new Client("Michael","SMITH","86850343", "chunyanj@yahoo.com");
    private Product product = new Product("Miecz swietlny czerwony", 99.99);
    private ProductManager pm = ProductManager.getInstance();

    private Reservation reservationIn;

    @Test
    public void test() throws ParseException
    {
        //  Dodaje produkt wraz z egzemplarzami
        MainManager.getInstance().addProduct(product.getName(), product.getPrice(), 5);

        // input parameters - helpers
        int id_product = 0;
        // input parameters
        int id_client_in = 0;
        Date dateStart_in = new SimpleDateFormat("yyyy-MM-dd").parse("2020-11-10");
        Date dateEnd_in = new SimpleDateFormat("yyyy-MM-dd").parse("2020-11-17");
        List<Item> itemList_in = pm.get(id_product).getItems();
        Status status_in = Status.aktywny;
        double price_in=.0;
        for(Item item : pm.get(id_product).getItems())
            price_in += pm.get(item.getId_product()).getPrice();

        // output parameters
        int id_client_out;
        Date dateStart_out;
        Date dateEnd_out;
        List<Item> itemList_out;
        Status status_out;
        double price_out;

        // dodanie rezerwacji o wskazanych parametrach
        MainManager.getInstance().addReservation(id_client_in, dateStart_in, dateEnd_in, itemList_in);

        // od tego momentu należy sprawdzic:
            // - rezerwacja - parametry IN czy sa rowne paramtrom OUT
            // - egzemplarze - czy sa zarezerwowane
            // - klient - czy ma tą rezerwacje

        // Nie było dodawanych zadnych rezerwacji, więc id dodawanej = 0
        int id_reservation = 0;
        Reservation rOut = ReservationManager.getInstance().get(id_reservation);

        id_client_out = rOut.getId_client();
        dateStart_out = rOut.getDateStart();
        dateStart_out = rOut.getDateEnd();
        itemList_out = rOut.getItems();
        status_out = rOut.getStatus();
        price_out = rOut.getPrice();

        assertFalse("1", false);
        assertFalse("2", true);
        assertFalse("3", false);
        assertFalse("4", true);
    }
}
