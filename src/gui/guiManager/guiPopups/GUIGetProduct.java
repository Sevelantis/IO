package gui.guiManager.guiPopups;

import entities.Product;
import gui.GUIMain;
import gui.guiManager.GUIObject;
import managers.MainManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GUIGetProduct extends GUIObject
{
    //constants
    private final String TITLE = "Dodawanie Egzemplarza";

    //JLabel
    private JLabel labelProductId = new JLabel("ID egzemplarza: ");

    //JButton
    private JButton buttonOk = new JButton();//for OK purposes

    //JTextField
    private JTextField textFieldProductId = new JTextField(10);

    public Product product;

    public GUIGetProduct(Window parent)
    {
        super(parent);
        addActionListeners();
        initComponents();
        initDialog(GUIMain.WIDTH_WINDOW-33, GUIMain.HEIGHT_WINDOW-80, TITLE);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == buttonOk)//READY
        {
            //check input val
            if (textFieldProductId.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Wypełnij puste pole!");
                return;
            }
            //set product
            try {
                product = MainManager.getInstance()
                        .searchProduct(Integer.parseInt(textFieldProductId.getText()));
                if(product == null)
                {
                    JOptionPane.showMessageDialog(this, "Nie znaleziono produktu!");
                    return;
                }
                dispose();
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this,
                        "Wypelnij poprawne dane!", "Wypelnij poprawne dane!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void addActionListeners()
    {
        buttonOk.addActionListener(this);
    }

    @Override
    public void initComponents()
    {
        //set Text / size
        buttonOk.setText("OK");
        textFieldProductId.setMinimumSize(new Dimension(90, 20));

        //layout
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());
        gbc.insets =  new Insets(5,5,5,5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelProductId, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(textFieldProductId, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(buttonOk, gbc);
    }

    public static Product getProduct(Window parent)
    {
        return new GUIGetProduct(parent).product;
    }
}
