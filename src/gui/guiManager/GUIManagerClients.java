package gui.guiManager;

import gui.GUIMain;
import gui.guiManager.guiPopups.GUIClients.GUIAddClient;
import gui.guiManager.guiPopups.GUIClients.GUIDisplayClients;
import gui.guiManager.guiPopups.GUIProducts.GUIAddProduct;
import gui.guiManager.guiPopups.GUIProducts.GUIDisplayProducts;

import java.awt.*;
import java.awt.event.ActionEvent;

public class GUIManagerClients extends GUIObject
{
    //constants
    private final String TITLE = "Zarządzaj Klientami";

    //methods
    public GUIManagerClients(Window parent)
    {
        super(parent);
        addActionListeners();
        initComponents();
        initDialog(GUIMain.WIDTH_WINDOW-15, GUIMain.HEIGHT_WINDOW + 100, TITLE);
    }

    //Overrides
    @Override
    public void addActionListeners()
    {
        buttonAdd.addActionListener(this);
        buttonDisplayAll.addActionListener(this);
    }

    @Override
    public void initComponents()
    {
        panel.setLayout(new GridLayout(2,1,20,20));
        buttonAdd.setText("Dodaj Klienta");
        buttonDisplayAll.setText("Wyświetl wszystkich");

        panel.add(buttonAdd);
        panel.add(buttonDisplayAll);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == buttonAdd)
        {
            new GUIAddClient(this);
        }
        else if(source == buttonDisplayAll)
        {
            new GUIDisplayClients(this);
        }
    }

    @Override
    public void initDialog(int width, int height, String title)
    {
        super.initDialog(width, height, title);
    }

}
