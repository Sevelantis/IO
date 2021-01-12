package gui.guiManager.guiPopups;

import entities.Product;
import gui.GUIMain;
import gui.guiManager.GUIObject;
import managers.MainManager;
import managers.ProductManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GUIUpdateProduct extends GUIObject
{
    //constants
    private final String TITLE = "Edycja Produktu";

    //variables
    private final int cntrRowsProducts = 2;

    private final String[] rowsProduct = {
            "Nazwa:",
            "Cena: "
    };

    private JTextField textFieldProductId = new JTextField(10);

    private final JLabel labelProductId = new JLabel("ID produktu:");

    //client header
    private final JLabel labelsProduct[] = new JLabel[cntrRowsProducts];

    //client data
    private JTextField textFieldsProduct[] = new JTextField[cntrRowsProducts];

    //JButton
    private JButton buttonShow = new JButton("Wybierz");

    public GUIUpdateProduct(Window parent)
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
        buttonShow.addActionListener(this);
    }

    @Override
    public void initComponents()
    {
        int AxisY = 0;
        //prepare JObjects
        buttonSubmit.setText("Update");
        textFieldProductId.setMinimumSize(new Dimension(200, 20));

        //layout
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets =  new Insets(5,5,5,5);

        gbc.gridx = 0;
        gbc.gridy = AxisY;
        panel.add(labelProductId, gbc);

        gbc.gridx = 1;
        gbc.gridy = AxisY++;
        panel.add(textFieldProductId, gbc);

        gbc.gridx = 0;
        gbc.gridy = AxisY++;
        gbc.gridwidth = 2;
        panel.add(buttonShow, gbc);

        gbc.gridx = 0;
        gbc.gridy = AxisY++;
        panel.add(new JLabel("Produkt :"), gbc);
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
            textFieldsProduct[i].setMinimumSize(new Dimension(200,20));
            gbc.gridx = 1;
            gbc.gridy = AxisY++;
            panel.add(textFieldsProduct[i], gbc);
        }

        gbc.gridx = 0;
        gbc.gridy = AxisY;
        gbc.gridwidth = 2;
        panel.add(buttonSubmit, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == buttonShow)
        {
            if (textFieldProductId.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Podaj ID produktu");
                return;
            }
            try
            {   // catch NumberFormatException
                int id_product = Integer.parseInt(textFieldProductId.getText());
                // check if reservation exists
                Product product = ProductManager.getInstance().get(id_product);
                if(product == null)
                {
                    JOptionPane.showMessageDialog(this,
                            "Podany produkt nie istnieje", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                textFieldsProduct[0].setText(product.getName());
                textFieldsProduct[1].setText(Double.toString(product.getPrice()));
                textFieldProductId.setEditable(false);
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this,
                        "Wypelnij poprawne dane!", "Wypelnij poprawne dane!", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(source == buttonSubmit)
        {
            String name;
            double price;
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
            MainManager.getInstance().modifyProduct(Integer.parseInt(textFieldProductId.getText()), name, (float)price);
            textFieldProductId.setEditable(true);
            JOptionPane.showMessageDialog(this, "Zaktualizowano produkt");
        }
    }
}
