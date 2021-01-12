package gui.guiManager.guiPopups.GUIProducts;

import gui.GUIMain;
import gui.guiManager.GUIObject;
import managers.MainManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GUIAddProduct extends GUIObject
{
    //constants
    private final String TITLE = "Dodawanie Produktu";

    //variables
    private final int cntrRowsProducts = 3;

    private final String[] rowsProduct = {
            "Nazwa:",
            "Cena: ",
            "Liczba egzemplarzy: "
    };

    //client header
    private final JLabel labelsProduct[] = new JLabel[cntrRowsProducts];

    //client data
    private JTextField textFieldsProduct[] = new JTextField[cntrRowsProducts];

    //JButton
    private JButton buttonAdd = new JButton("Dodaj");

    public GUIAddProduct(Window parent)
    {
        super(parent);
        addActionListeners();
        initComponents();
        initDialog(GUIMain.WIDTH_WINDOW+100, GUIMain.HEIGHT_WINDOW + 100, TITLE);
    }

    @Override
    public void addActionListeners()
    {
        buttonSubmit.addActionListener(this);
        buttonAdd.addActionListener(this);
    }

    @Override
    public void initComponents()
    {
        int AxisY = 0;
        //layout
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets =  new Insets(5,5,5,5);

        gbc.gridx = 0;
        gbc.gridy = AxisY++;
        gbc.gridwidth = 2;
        panel.add(new JLabel("Nowy Produkt :"), gbc);
        gbc.gridwidth = 1;

        for (int i = 0; i < cntrRowsProducts; i++)
        {
            //left
            labelsProduct[i] = new JLabel(rowsProduct[i]);
            //set grid
            gbc.gridx = 0;
            gbc.gridy = AxisY;
            //add to panel
            panel.add(labelsProduct[i], gbc);

            //right
            textFieldsProduct[i] = new JTextField(10);
            textFieldsProduct[i].setMinimumSize(new Dimension(120,20));
            gbc.gridx = 1;
            gbc.gridy = AxisY++;
            panel.add(textFieldsProduct[i], gbc);
        }

        gbc.gridx = 0;
        gbc.gridy = AxisY;
        gbc.gridwidth = 2;
        panel.add(buttonAdd, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == buttonAdd)
        {
            String name;
            double price;
            int numberOfItems;
            if(textFieldsProduct[0].getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Puste pole nazwy");
                return;
            }
            name = textFieldsProduct[0].getText();
            try
            {
                price = Double.parseDouble(textFieldsProduct[1].getText());
            }catch (NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(this, "Błędna cena");
                return;
            }
            if(price <= 0)
            {
                JOptionPane.showMessageDialog(this, "Błędna cena");
                return;
            }
            try
            {
                numberOfItems = Integer.parseInt(textFieldsProduct[2].getText());
            }catch (NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(this, "Błędna liczba egzemplarzy");
                return;
            }
            if(numberOfItems < 0)
            {
                JOptionPane.showMessageDialog(this, "Błędna liczba egzemplarzy");
                return;
            }
            MainManager.getInstance().addProduct(name, price, numberOfItems);
            JOptionPane.showMessageDialog(this, "Dodano produkt");
        }
    }
}
