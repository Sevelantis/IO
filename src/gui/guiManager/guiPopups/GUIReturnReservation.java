package gui.guiManager.guiPopups;

import gui.GUIMain;
import gui.guiManager.GUIObject;
import managers.ReservationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GUIReturnReservation extends GUIObject
{
    //constants
    private final String TITLE = "Zwrot Rezerwacji";

    //variables
    private final int cntrRowsReservation = 4;
    private final int cntrRowsClient = 5;

    private final String[] rowsReservation = {
            "id_rezerwacji: ",
            "id_client:",
            "dateStart:",
            "dateEnd:"
    };

    private final String[] rowsClient = {
            "id_client: ",
            "firstName:",
            "lastName:",
            "phoneNr:",
            "email:"
    };

    //JLabel
    private final JLabel labelReservationId = new JLabel("ID rezerwacji:");

    //reservation header
    private final JLabel labelsReservation[] = new JLabel[cntrRowsReservation];
    //client header
    private final JLabel labelsClient[] = new JLabel[cntrRowsClient];

    //JTextField
    private JTextField textFieldReservationId = new JTextField(10);

    //reservation data
    private JTextField textFieldsReservation[] = new JTextField[cntrRowsReservation];
    //client data
    private JTextField textFieldsClient[] = new JTextField[cntrRowsClient];

    //JButton
    private JButton buttonShow = new JButton("Pokaż");

    public GUIReturnReservation(Window parent)
    {
        super(parent);
        addActionListeners();
        initComponents();
        initDialog(GUIMain.WIDTH_WINDOW+100, GUIMain.HEIGHT_WINDOW + 300, TITLE);
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
        //prepare JObjects
        buttonSubmit.setText("Zwrot Rezerwacji");
        textFieldReservationId.setMinimumSize(new Dimension(120, 20));

         //layout
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets =  new Insets(5,5,5,5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelReservationId, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(textFieldReservationId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(buttonShow, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Klient :"), gbc);
        gbc.gridwidth = 1;

        // client object columns 3-7
        for (int i =0 ; i < cntrRowsClient; i++)
        {
            //left
            labelsClient[i] = new JLabel(rowsClient[i]);
            //set grid
            gbc.gridx = 0;
            gbc.gridy = 3 + i;
            //add to panel
            panel.add(labelsClient[i], gbc);

            //right
            textFieldsClient[i] = new JTextField(10);
            textFieldsClient[i].setMinimumSize(new Dimension(120,20));
            textFieldsClient[i].setEditable(false);
            //set grid
            gbc.gridx = 1;
            gbc.gridy = 3 + i;
            //add to panel
            panel.add(textFieldsClient[i], gbc);
        }

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        panel.add(new JLabel("Rezerwacja :"), gbc);
        gbc.gridwidth = 1;

        //reservation object columns 9-12
        for (int i =0 ; i < cntrRowsReservation; i++)
        {
            //left
            labelsReservation[i] = new JLabel(rowsReservation[i]);
            //set grid
            gbc.gridx = 0;
            gbc.gridy = 9 + i;
            //add to panel
            panel.add(labelsReservation[i], gbc);

            //right
            textFieldsReservation[i] = new JTextField(10);
            textFieldsReservation[i].setMinimumSize(new Dimension(120,20));
            textFieldsReservation[i].setEditable(false);
            //set grid
            gbc.gridx = 1;
            gbc.gridy = 9 + i;
            //add to panel
            panel.add(textFieldsReservation[i], gbc);
        }

        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.gridwidth = 2;
        panel.add(buttonSubmit, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == buttonSubmit)
        {
            System.out.println("button submit");
            JOptionPane.showMessageDialog(this,
                    "Pomyślnie dokonano zwrotu rezerwacji.", "INFO", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(source == buttonShow)
        {
            if (textFieldReservationId.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Podaj ID rezerwacji!");
                return;
            }
            try
            {   // catch NumberFormatException
                int id_reservation = Integer.parseInt(textFieldReservationId.getText());
                // check if reservation exists
                if(ReservationManager.getInstance().get(id_reservation) == null)
                {
                    JOptionPane.showMessageDialog(this,
                            "Podana rezerwacja nie istnieje!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //show reservation
                //todo

            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this,
                        "Wypelnij poprawne dane!", "Wypelnij poprawne dane!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
