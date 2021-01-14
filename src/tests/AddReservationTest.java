package tests;


import entities.Item;
import entities.Reservation;
import enums.Status;
import gui.GUIMain;
import managers.*;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class AddReservationTest
{

    @Test
    public void test() throws ParseException
    {
        GUIMain.simulateData();
        // input parameters - helpers
        int id_product = 0;
        // input parameters
        int id_client_in = 0;
        Date dateStart_in = new SimpleDateFormat("yyyy-MM-dd").parse("2020-11-10");
        Date dateEnd_in = new SimpleDateFormat("yyyy-MM-dd").parse("2020-11-17");
        List<Item> itemList_in = ProductManager.getInstance().get(id_product).getItems();
        Status status_in = Status.aktywny;
        double price_in=0.0;
        for(Item item : ProductManager.getInstance().get(id_product).getItems())
            price_in += ProductManager.getInstance().get(item.getId_product()).getPrice();


        // dodanie rezerwacji o wskazanych parametrach
        MainManager.getInstance().addReservation(id_client_in, dateStart_in, dateEnd_in, itemList_in);


        // Nie było dodawanych zadnych rezerwacji, więc id dodawanej = 0
        int id_reservation = 0;
        Reservation reservationOut = ReservationManager.getInstance().get(id_reservation);

        int id_client_out = reservationOut.getId_client();
        Date dateStart_out = reservationOut.getDateStart();
        Date dateEnd_out = reservationOut.getDateEnd();
        List<Item> itemList_out = reservationOut.getItems();
        Status status_out = reservationOut.getStatus();
        double price_out = reservationOut.getPrice();

        // - rezerwacja - parametry IN czy sa rowne paramtrom OUT
        assertEquals("1", id_client_in, id_client_out);
        assertEquals("2", dateStart_in, dateStart_out);
        assertEquals("3", dateEnd_in, dateEnd_out);
        assertEquals("4", itemList_in, itemList_out);
        assertEquals("5", status_in, status_out);
        //noinspection deprecation
        assertEquals("6", price_in, price_out, .00001);


        // - klient - czy ma tą rezerwacje
        boolean isContains = ClientManager.getInstance().get(id_client_in).getReservationList().contains(reservationOut);
        assertTrue(isContains);

        // - egzemplarze - czy sa zarezerwowane
        for(Item item : ItemManager.getInstance().itemList)
            if(item.getId_product() == id_product)
                assertNotEquals(item.getId_reservation(), -1);

    }
}