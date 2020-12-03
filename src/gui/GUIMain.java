package gui;

import gui.guiManager.GUIManagerReservation;
import managers.MainManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIMain extends JFrame implements ActionListener
{
    //constants
    public static final int WIDTH_WINDOW = 300;
    public static final int HEIGHT_WINDOW = 200;


    //attributes
    private MainManager manager = new MainManager();
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
