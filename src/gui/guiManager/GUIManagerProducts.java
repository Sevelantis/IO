package gui.guiManager;

import gui.GUIMain;
import gui.guiManager.guiPopups.GIUDelete.GUIDeleteProduct;
import gui.guiManager.guiPopups.GUIAdd.GUIAddProduct;
import gui.guiManager.guiPopups.GUIDisplayProducts;
import gui.guiManager.guiPopups.GUIUpdateProduct;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GUIManagerProducts extends GUIObject
{
    //constants
    private final String TITLE = "Zarządzaj Produktami";

    //attributes
    private JButton buttonDelete = new JButton("Usuń produkt");
    private JButton buttonUpdate = new JButton("Zaktualizuj produkt");

    //methods
    public GUIManagerProducts(Window parent)
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
        buttonDelete.addActionListener(this);
        buttonUpdate.addActionListener(this);
    }

    @Override
    public void initComponents()
    {
        panel.setLayout(new GridLayout(4,1,20,20));
        buttonAdd.setText("Dodaj Produkt");
        buttonDisplayAll.setText("Wyświetl wszystkie");

        panel.add(buttonAdd);
        panel.add(buttonDisplayAll);
        panel.add(buttonDelete);
        panel.add(buttonUpdate);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == buttonAdd)
        {
            new GUIAddProduct(this);
        }
        else if(source == buttonDisplayAll)
        {
            new GUIDisplayProducts(this);
        }
        else if(source == buttonDelete)
        {
            new GUIDeleteProduct(this);
        }
        else if(source == buttonUpdate)
        {
            new GUIUpdateProduct(this);
            System.out.println("buttonUpdate");
        }
    }

    @Override
    public void initDialog(int width, int height, String title)
    {
        super.initDialog(width, height, title);
    }
}
