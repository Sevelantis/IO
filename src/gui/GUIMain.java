package gui;

import entities.Client;
import entities.Reservation;
import gui.guiManager.GUIManagerReservation;
import managers.ClientManager;
import managers.ItemManager;
import managers.MainManager;
import managers.ReservationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

public class GUIMain extends JFrame implements ActionListener
{
    //constants
    public static final int WIDTH_WINDOW = 300;
    public static final int HEIGHT_WINDOW = 200;


    //attributes
    private MainManager manager;
    private JDialog guiManager = null;

    //JPanel
    private JPanel panelButtons = new JPanel();

    //JButton
    private JButton buttonManageReservations = new JButton("Zarządzaj Rezerwacjami");
    private JButton buttonManageProducts = new JButton("Zarządzaj Produktami");
    private JButton buttonManageClients = new JButton("Zarządzaj Klientami");

    //methods
    public GUIMain()
    {
        initFrame();
        initButtons();
        addActionListeners();
        manager = new MainManager();

        //fake populate
        for(int i = 0 ; i < 100 ; i++ )
            ProductManager.getInstance().add(new Product(Integer.toHexString(i),(float)(i+i/10.0)));

        Client client = new Client("Adam", "Małysz", "592876090", "mammalego@.gumeil.dom");
        ClientManager.getInstance().add(client);
        MainManager mm = new MainManager();
        MainManager.getInstance().addProduct("Zabawkowe Narty", (float) 15.99, 5);
        MainManager.getInstance().addProduct("Pistolet maszynowy RKM", (float) 2999.99, 6);
        MainManager.getInstance().addProduct("Hamburger 100% plastik", (float) 4.99, 9);

        Reservation res = new Reservation(client.getId(), new Date(2019, 12, 10), new Date(2020, 12, 10),
                ItemManager.getInstance().itemList);

        try {
            ReservationManager.getInstance().createAgreementFile(res);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void addActionListeners()
    {
        buttonManageClients.addActionListener(this);
        buttonManageProducts.addActionListener(this);
        buttonManageReservations.addActionListener(this);
    }

    private void initButtons()
    {
        panelButtons.setLayout(new GridLayout(3,1,20,20));
        panelButtons.add(buttonManageReservations);
        panelButtons.add(buttonManageProducts);
        panelButtons.add(buttonManageClients);
    }

    private void initFrame()
    {
        setContentPane(panelButtons);
        setTitle("Wypożyczalnia Kostiumów - aplikacja do zarządzania bazą");
        setSize(WIDTH_WINDOW, HEIGHT_WINDOW);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    //listeners

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == buttonManageReservations)
        {
            guiManager = new GUIManagerReservation(this);
        }
        else if(source == buttonManageProducts)
        {
            System.out.println("Manage Products");
//            new GUIProducts();
        }
        else if(source == buttonManageClients)
        {
            System.out.println("Manage Clients");
//            new GUIClients();
        }
    }
}
