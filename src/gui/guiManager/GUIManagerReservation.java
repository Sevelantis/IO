package gui.guiManager;

import gui.GUIMain;
import gui.guiManager.guiPopups.GUIAdd.GUIAddReservation;
import gui.guiManager.guiPopups.GUIReturnReservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GUIManagerReservation extends GUIObject
{
    //constants
    private final String TITLE = "Zarządzaj Rezerwacjami";

    //attributes
    private JButton buttonReturn = new JButton("Zwrot rezerwacji");

    //methods
    public GUIManagerReservation(Window parent)
    {
        super(parent);
        addActionListeners();
        initComponents();
        initDialog(GUIMain.WIDTH_WINDOW-15, GUIMain.HEIGHT_WINDOW, TITLE);
    }

    //Overrides
    @Override
    public void addActionListeners()
    {
        buttonAdd.addActionListener(this);
        buttonDisplayAll.addActionListener(this);
        buttonReturn.addActionListener(this);
    }

    @Override
    public void initComponents()
    {
        panel.setLayout(new GridLayout(3,1,20,20));
        buttonAdd.setText("Dodaj Rezerwacje");
        buttonDisplayAll.setText("Wyszukaj Rezerwacje");

        panel.add(buttonAdd);
        panel.add(buttonDisplayAll);
        panel.add(buttonReturn);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == buttonAdd)
        {
            new GUIAddReservation(this);
        }
        else if(source == buttonDisplayAll)
        {

            System.out.println("buttonSearch");
        }
        else if(source == buttonReturn)
        {
            new GUIReturnReservation(this);
        }
    }

    @Override
    public void initDialog(int width, int height, String title)
    {
        super.initDialog(width, height, title);
    }
}
