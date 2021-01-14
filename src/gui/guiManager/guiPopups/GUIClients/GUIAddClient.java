package gui.guiManager.guiPopups.GUIClients;

import gui.GUIMain;
import gui.guiManager.GUIObject;
import managers.MainManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GUIAddClient extends GUIObject
{
    //constants
    private final String TITLE = "Dodawanie Produktu";

    //variables
    private final int cntrRowsClients = 4;

    private final String[] rowsProduct = {
            "Imię:",
            "Nazwisko: ",
            "Nr telefonu(9): ",
            "E-mail"
    };

    //client header
    private final JLabel labelsProduct[] = new JLabel[cntrRowsClients];

    //client data
    private JTextField textFieldsClient[] = new JTextField[cntrRowsClients];

    //JButton
    private JButton buttonAdd = new JButton("Dodaj");

    public GUIAddClient(Window parent)
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
        panel.add(new JLabel("Nowy Klient :"), gbc);
        gbc.gridwidth = 1;

        for (int i = 0; i < cntrRowsClients; i++)
        {
            //left
            labelsProduct[i] = new JLabel(rowsProduct[i]);
            //set grid
            gbc.gridx = 0;
            gbc.gridy = AxisY;
            //add to panel
            panel.add(labelsProduct[i], gbc);

            //right
            textFieldsClient[i] = new JTextField(10);
            textFieldsClient[i].setMinimumSize(new Dimension(120,20));
            gbc.gridx = 1;
            gbc.gridy = AxisY++;
            panel.add(textFieldsClient[i], gbc);
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
            String firstName;
            String lastname;
            String phoneNr;
            String email;

            for(int i = 0 ; i < cntrRowsClients ; i++)
                if(textFieldsClient[i].getText().equals(""))
                {
                    if (i == 3) continue; // mail can be null
                    JOptionPane.showMessageDialog(this, "Uzupełnij puste pola!");
                    return;
                }
            firstName = textFieldsClient[0].getText();
            lastname = textFieldsClient[1].getText();
            phoneNr = textFieldsClient[2].getText();
            email = textFieldsClient[3].getText();

            MainManager.getInstance().addClient(firstName, lastname, phoneNr, email);
            JOptionPane.showMessageDialog(this, "Dodano klienta:\n"
                    + firstName + " " + lastname + "\ntel: " + phoneNr);
            dispose();
        }
    }
}
