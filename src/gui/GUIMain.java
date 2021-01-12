package gui;

import filereader.FileHelper;
import gui.guiManager.GUIManagerProducts;
import gui.guiManager.GUIManagerReservation;
import managers.MainManager;
import managers.ProductManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class GUIMain extends JFrame implements ActionListener
{
    //constants
    public static final int WIDTH_WINDOW = 300;
    public static final int HEIGHT_WINDOW = 250;


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
        simulateData();

//        new GUIReturnReservation(this);
    }

    private void addActionListeners()
    {
        buttonManageClients.addActionListener(this);
        buttonManageProducts.addActionListener(this);
        buttonManageReservations.addActionListener(this);
    }

    private void simulateData()
    {
        // declarations lists
        List<String> clients = new Vector<>();
        List<String> products = new Vector<>();

        // -- read clients form file --
        FileHelper.readFile("data/clients.txt", clients);
        for(String str : clients)
        {
            String[] data = str.split(",");
            MainManager.getInstance().addClient(
                    data[0],data[1],data[2],data[3]);
        }
        // -- read clients form file --

        // -- read products form file --
        FileHelper.readFile("data/products", products);
        for(String str : products)
        {
            String[] data = str.split(",");
            MainManager.getInstance().addProduct(
                    data[0], Float.parseFloat(data[1]), 5);
        }
        // -- read products form file --

        // example reservations.
        MainManager.getInstance().addReservation(4, new Date(120, 12, 10), new Date(120, 12, 17),
                ProductManager.getInstance().get(1).getItems());
        MainManager.getInstance().addReservation(5, new Date(120, 11, 22), new Date(120, 11, 29),
                ProductManager.getInstance().get(2).getItems());
        MainManager.getInstance().addReservation(22, new Date(120, 9, 9), new Date(120, 9, 16),
                ProductManager.getInstance().get(3).getItems());
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
            guiManager = new GUIManagerProducts(this);
        }
        else if(source == buttonManageClients)
        {
            System.out.println("Manage Clients");
//            new GUIClients();
        }
    }
}
