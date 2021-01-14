package tests;

import entities.Item;
import entities.Reservation;
import managers.ReservationManager;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import static org.junit.Assert.assertEquals;

public class ReservationManagerTest {

    @Test
    public void get()
    {
        List<Item> itemList = new Vector<>();
        itemList.add(new Item(1));
        itemList.add(new Item(2));
        itemList.add(new Item(3));
        Reservation reservation = new Reservation(0, new Date(120,10,10), new Date(120,10,17), itemList);

        ReservationManager.getInstance().add(reservation);//

        Reservation getResult = ReservationManager.getInstance().get(3);

        assertEquals("11",reservation, getResult);

//        ReservationManager.getInstance().getReservationList().remove(reservation);
    }

    @Test
    public void getReservationList()
    {
        List<Item> itemList1 = new Vector<>();
        itemList1.add(new Item(1));
        itemList1.add(new Item(2));
        List<Item> itemList2 = new Vector<>();
        itemList2.add(new Item(1));
        itemList2.add(new Item(2));
        List<Item> itemList3 = new Vector<>();
        itemList2.add(new Item(1));
        itemList2.add(new Item(2));

        List<Reservation> reservationList = new Vector<>();

        Reservation reservation1 = new Reservation(0, new Date(120,10,10), new Date(120,10,17), itemList1);
        Reservation reservation2 = new Reservation(0, new Date(120,11,10), new Date(120,11,17), itemList2);
        Reservation reservation3 = new Reservation(0, new Date(120,11,10), new Date(120,11,17), itemList3);
        reservationList.add(reservation1);
        reservationList.add(reservation2);
        reservationList.add(reservation3);

        ReservationManager.getInstance().add(reservation1);//
        ReservationManager.getInstance().add(reservation2);//
        ReservationManager.getInstance().add(reservation3);//

        List<Reservation> getReservationList = ReservationManager.getInstance().getReservationList();

        assertEquals("22",reservationList, getReservationList);

        //
//        ReservationManager.getInstance().getReservationList().remove(reservation1);
//        ReservationManager.getInstance().getReservationList().remove(reservation2);
//        ReservationManager.getInstance().getReservationList().remove(reservation3);
    }
}